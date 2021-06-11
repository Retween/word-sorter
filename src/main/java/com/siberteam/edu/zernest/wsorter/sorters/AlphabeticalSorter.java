package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;

public class AlphabeticalSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
