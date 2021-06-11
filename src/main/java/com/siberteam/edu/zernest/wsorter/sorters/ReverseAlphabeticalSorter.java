package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;

public class ReverseAlphabeticalSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
}
