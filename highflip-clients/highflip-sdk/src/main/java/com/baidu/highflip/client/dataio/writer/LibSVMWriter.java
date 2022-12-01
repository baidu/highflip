package com.baidu.highflip.client.dataio.writer;

import com.baidu.highflip.client.dataio.DataWriter;
import com.baidu.highflip.client.model.KeyPair;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class LibSVMWriter implements DataWriter<KeyPair> {

    String label_key = "LABEL";

    String splitter = " ";

    PrintStream output;

    public static LibSVMWriter from(OutputStream out){
        LibSVMWriter writer = new LibSVMWriter();
        writer.output = new PrintStream(out);
        return writer;
    }

    String toLine(List<KeyPair> row){
        StringBuffer line = new StringBuffer();

        for(int i = 0; i < row.size(); i++){
            if (i == 0) {
                if(row.get(0).getKey().equalsIgnoreCase(label_key)){
                    line.append(row.get(0).getValue());
                    line.append(splitter);
                } else {
                    line.append(row.get(0).toString());
                }
            } else {
                line.append(splitter);
                line.append(row.get(i).toString());
            }
        }

        return line.toString();
    }

    public void write(Iterator<List<KeyPair>> iterator){
        while(iterator.hasNext()){
            output.println(toLine(iterator.next()));
        }
    }
}
