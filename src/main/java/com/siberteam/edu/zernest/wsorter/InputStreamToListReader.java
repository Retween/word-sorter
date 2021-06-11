package com.siberteam.edu.zernest.wsorter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputStreamToListReader {
    private final InputStream inputStream;
    private final List<String> wordsList;

    public InputStreamToListReader(InputStream inputStream) {
        this.inputStream = inputStream;
        wordsList = new ArrayList<>();
    }

    public List<String> getWordsList() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                wordsList.add(inputLine);
            }
        }

        return wordsList;
    }

    @Override
    public String toString() {
        return "InputStreamToListReader" + "[" +
                "inputStream=" + inputStream +
                ", wordsListSize=" + wordsList.size() +
                ']';
    }
}
