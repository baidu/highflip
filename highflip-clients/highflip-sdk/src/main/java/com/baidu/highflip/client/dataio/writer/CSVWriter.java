package com.baidu.highflip.client.dataio.writer;

import com.baidu.highflip.client.dataio.DataWriter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class CSVWriter implements DataWriter<String> {
    String splitter = ",";

    PrintStream output;

    public static CSVWriter from(OutputStream out){
        CSVWriter writer = new CSVWriter();
        writer.output = new PrintStream(out);
        return writer;
    }

    String toLine(List<String> row){
        return String.join(splitter, row);
    }

    @Override
    public void write(Iterator<List<String>> iterator){
        while(iterator.hasNext()){
            output.println(toLine(iterator.next()));
        }
    }
}
