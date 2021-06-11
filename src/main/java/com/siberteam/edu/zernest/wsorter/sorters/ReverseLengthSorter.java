package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;

public class ReverseLengthSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o2.length(), o1.length());
    }
}
