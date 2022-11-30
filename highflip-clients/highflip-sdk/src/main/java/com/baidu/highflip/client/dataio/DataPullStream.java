package com.baidu.highflip.client.dataio;

import com.baidu.highflip.client.model.KeyPair;
import com.baidu.highflip.client.utils.Streams;
import com.google.protobuf.ByteString;
import highflip.v1.Highflip;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataPullStream {

    static Stream<List<String>> fromDense(Highflip.DenseData data){
        return data.getRowsList()
            .stream()
            .map(r -> r.getValueList()
                    .stream()
                    .collect(Collectors.toList()));
    }

    static Stream<List<KeyPair>> fromSparse(Highflip.SparseData data){
        return data.getRowsList()
                .stream()
                .map(r -> r.getPairsList()
                        .stream()
                        .map(p -> KeyPair.of(p.getKey(), p.getValue()))
                        .collect(Collectors.toList()));
    }

    static Stream<List<Object>> fromObject(Highflip.DataPullResponse resp){
        if(resp.hasSparse()){
            return fromSparse(resp.getSparse()).map(v -> (List)v);
        } else if(resp.hasDense()){
            return fromDense(resp.getDense()).map(v -> (List)v);
        } else {
            throw new NoSuchFieldError();
        }
    }

    public static Iterator<List<String>> toDense(Iterator<Highflip.DataPullResponse> iterator){
        return Streams.of(iterator)
                .flatMap(resp -> fromDense(resp.getDense()))
                .iterator();
    }

    public static Iterator<List<KeyPair>> toSparse(Iterator<Highflip.DataPullResponse> iterator){
        return Streams.of(iterator)
                .flatMap(resp -> fromSparse(resp.getSparse()))
                .iterator();
    }

    public static Iterator<List<Object>> toObjects(Iterator<Highflip.DataPullResponse> iterator){
        return Streams.of(iterator)
                .flatMap(resp -> fromObject(resp))
                .iterator();
    }

    public static InputStream toRaw(Iterator<Highflip.DataPullResponse> iterator){
        return new DataPullInputStream(iterator);
    }

    static class DataPullInputStream extends InputStream {

        int index;

        int length;

        ByteString content;

        Iterator<Highflip.DataPullResponse> iterator;

        public DataPullInputStream(Iterator<Highflip.DataPullResponse> iterator){
            this.index = 0;
            this.length = 0;
            this.content = null;
            this.iterator = iterator;
        }

        boolean load(){
            if (!this.iterator.hasNext()) {
                return false;
            }

            this.content = this.iterator.next().getRaw().getContent();
            this.index = 0;
            this.length = this.content.size();
            return true;
        }

        byte next(){
            return this.content.byteAt(this.index++);
        }


        @Override
        public int read() throws IOException {
            if(index >= length){
                if(!load()){
                    return -1;
                }
            }
            return next();
        }
    }
}


