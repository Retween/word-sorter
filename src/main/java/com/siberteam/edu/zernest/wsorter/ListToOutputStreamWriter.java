package com.siberteam.edu.zernest.wsorter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class ListToOutputStreamWriter {
    private final OutputStream outputStream;

    public ListToOutputStreamWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeListToFile(List<String> list) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                outputStream))) {

            for (String word : list) {
                bw.write(word + "\n");
            }
        }
    }

    @Override
    public String toString() {
        return "ListToOutputStreamWriter" + "[" +
                "outputStream=" + outputStream +
                ']';
    }
}
