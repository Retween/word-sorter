package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class DuplicateLettersSorter implements Comparator<String> {

    private int countDuplicates(String string) {
        int duplicateCount = 0;
        Set<Character> set = new HashSet<>();
        for (Character s : string.toCharArray()) {
            if (set.contains(s)) {
                duplicateCount++;
            } else {
                set.add(s);
            }
        }
        return duplicateCount;
    }


    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(countDuplicates(o1), countDuplicates(o2));
    }
}
