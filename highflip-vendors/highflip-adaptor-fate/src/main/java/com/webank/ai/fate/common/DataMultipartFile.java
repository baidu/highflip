package com.webank.ai.fate.common;

import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DataMultipartFile implements MultipartFile {

    private String originalFilename = "file";

    private final String contentType = "application/octet-stream";

    private final byte[] content;

    public DataMultipartFile(String filename, byte[] bytes) {
        if (filename != null) {
            this.originalFilename = filename;
        }
        this.content = bytes;
    }

    public DataMultipartFile(String filename, String base64str) {
        if (filename != null) {
            this.originalFilename = filename;
        }
        this.content = Base64Utils.decodeFromString(base64str);
    }

    @Override
    @NonNull
    public String getName() {
        return this.originalFilename;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return (this.content.length == 0);
    }

    @Override
    public long getSize() {
        return this.content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return this.content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(this.content, dest);
    }

}