package com.webank.ai.fate.common.deserializer;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FeignSpringFormEncoder extends JacksonEncoder implements Encoder {

    private final List<HttpMessageConverter<?>> converters = new RestTemplate().getMessageConverters();

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public FeignSpringFormEncoder() {}

    /**
     * 实现一个 HttpOutputMessage
     */
    private class HttpOutputMessageImpl implements HttpOutputMessage {
        /**
         * 输出流，请求体
         */
        private final OutputStream body;
        /**
         * 请求头
         */
        private final HttpHeaders headers;

        public HttpOutputMessageImpl(OutputStream body, HttpHeaders headers) {
            this.body = body;
            this.headers = headers;
        }

        @Override
        public OutputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

    /**
     * 内部静态类,保存 MultipartFile 数据
     */
    static class MultipartFileResource extends InputStreamResource {
        /**
         * 文件名
         */
        private final String filename;
        /**
         * 文件大小
         */
        private final long size;

        /**
         * 构造方法
         */
        public MultipartFileResource(InputStream inputStream, String filename, long size) {
            super(inputStream);
            this.filename = filename;
            this.size = size;
        }

        @Override
        public String getFilename() {
            return this.filename;
        }

        @Override
        public InputStream getInputStream() throws IOException, IllegalStateException {
            return super.getInputStream();
        }

        @Override
        public long contentLength() throws IOException {
            return size;
        }
    }

    /**
     * 重写编码器
     */
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (isJsonRequest(template)){
            super.encode(object, bodyType, template);
        } else {
            final HttpHeaders multipartHeaders = new HttpHeaders();
            multipartHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            encodeMultipartFormRequest((Map<Object, ?>) object, multipartHeaders, template);
        }
    }

    private boolean isJsonRequest(RequestTemplate requestTemplate) {
        for (Map.Entry<String, Collection<String>> header : requestTemplate.headers().entrySet()) {
            if (header.getKey().equalsIgnoreCase("Content-Type")) {
                for (String type : header.getValue()) {
                    if (type.contains("json")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 对有文件、表单的进行编码
     */
    private void encodeMultipartFormRequest(Map<Object, ?> formMap, HttpHeaders multipartHeaders, RequestTemplate template){
        if (formMap == null){
            throw new EncodeException("无法对格式为null的请求进行编码。");
        }

        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        //对每个参数进行检查校验
        for (Map.Entry<Object, ?> entry : formMap.entrySet()){
            Object value = entry.getValue();
            //不同的数据类型进行不同的编码逻辑处理
            if (isMultipartFile(value)){
                //单个文件
                map.add(entry.getKey(), encodeMultipartFile((MultipartFile)value));

            } else if (isMultipartFileArray(value)){
                //多个文件
                encodeMultipartFiles(map, (String) entry.getKey(), Arrays.asList((MultipartFile[]) value));

            } else {
                //普通请求数据
                map.add(entry.getKey(), encodeJsonObject(value));
            }
        }

        encodeRequest(map, multipartHeaders, template);
    }

    /**
     * 对请求进行编码
     */
    private void encodeRequest(Object value, HttpHeaders requestHeaders, RequestTemplate template){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HttpOutputMessage dummyRequest = new HttpOutputMessageImpl(outputStream, requestHeaders);

        try {
            Class<?> requestType = value.getClass();
            MediaType requestContentType = requestHeaders.getContentType();
            for (HttpMessageConverter<?> messageConverter : converters){
                if (messageConverter.canWrite(requestType, requestContentType)){
                    ((HttpMessageConverter<Object>) messageConverter).write(value, requestContentType, dummyRequest);
                    break;
                }
            }
        } catch (IOException e) {
            throw new EncodeException("无法对请求进行编码:", e);
        }

        HttpHeaders headers = dummyRequest.getHeaders();
        if (headers != null){
            for (Map.Entry<String, List<String>> entry : headers.entrySet()){
                template.header(entry.getKey(), entry.getValue());
            }
        }

        /*
        请使用模板输出流。。。如果文件太大，这将导致问题，因为整个请求都将在内存中。
         */
        template.body(outputStream.toByteArray(), UTF_8);
    }

    /**
     * 编码为json对象
     */
    private HttpEntity<?> encodeJsonObject(Object obj){
        HttpHeaders jsonPartHeaders = new HttpHeaders();
        jsonPartHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(obj, jsonPartHeaders);
    }

    /**
     * 编码MultipartFile文件，将其转换为HttpEntity，同时设置 Content-type 为 application/octet-stream
     * @param map 当前请求 map.
     * @param name 数组字段的名称
     * @param fileList 要处理的文件
     */
    private void encodeMultipartFiles(LinkedMultiValueMap<Object, Object> map, String name, List<? extends MultipartFile> fileList){
        HttpHeaders filePartHeaders = new HttpHeaders();
        //设置 Content-type
        filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            for (MultipartFile file : fileList){
                Resource multipartFileResource = new MultipartFileResource(file.getInputStream(), file.getOriginalFilename(), file.getSize());
                map.add(name, new HttpEntity<>(multipartFileResource, filePartHeaders));
            }
        } catch (IOException e) {
            throw new EncodeException("无法对请求进行编码:", e);
        }
    }

    /**
     * 编码MultipartFile文件，将其转换为HttpEntity，同时设置 Content-type 为 application/octet-stream
     * @param file 要编码的文件
     */
    private HttpEntity<?> encodeMultipartFile(MultipartFile file){
        HttpHeaders filePartHeaders = new HttpHeaders();
        //设置 Content-type
        filePartHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            Resource multipartFileResource = new MultipartFileResource(file.getInputStream(), file.getOriginalFilename(), file.getSize());
            return new HttpEntity<>(multipartFileResource, filePartHeaders);
        } catch (IOException e) {
            throw new EncodeException("无法对请求进行编码:", e);
        }
    }

    /**
     * 判断是否多个 MultipartFile
     */
    private boolean isMultipartFileArray(Object object){
        return object != null && object.getClass().isArray() && MultipartFile.class.isAssignableFrom(object.getClass().getComponentType());
    }

    /**
     * 判断是否MultipartFile文件
     * @param object 要判断的对象
     */
    private boolean isMultipartFile(Object object){
        return object instanceof MultipartFile;
    }


}