package com.siberteam.edu.zernest.wsorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputStreamToListReader {
    private final InputStream inputStream;
    private final List<String> dictionary;

    public InputStreamToListReader(InputStream inputStream) {
        this.inputStream = inputStream;
        dictionary = new ArrayList<>();
    }

    public List<String> getDictionary() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                dictionary.add(inputLine);
            }
        }

        return dictionary;
    }

    @Override
    public String toString() {
        return "InputStreamToListReader" + "[" +
                "inputStream=" + inputStream +
                ", dictionary=" + dictionary +
                ']';
    }
}
