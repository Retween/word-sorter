package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;

public class LengthSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
    }
}
