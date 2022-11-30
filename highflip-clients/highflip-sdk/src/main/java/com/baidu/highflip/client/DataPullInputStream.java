package com.baidu.highflip.client;

import com.google.protobuf.ByteString;
import highflip.v1.Highflip;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class DataPullInputStream extends InputStream {

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
