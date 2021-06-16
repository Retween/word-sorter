package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VowelsSorter implements Comparator<String> {

    private int countVowels(String string) {
        Pattern vowels = Pattern.compile("[аоуэыяеёюи]");

        int vocalCounter = 0;
        Matcher matcher = vowels.matcher(string);

        while (matcher.find()) {
            vocalCounter++;
        }
        return vocalCounter;
    }

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(countVowels(o1), countVowels(o2));
    }
}
