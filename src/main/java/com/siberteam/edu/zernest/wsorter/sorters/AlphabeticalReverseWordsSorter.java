package com.siberteam.edu.zernest.wsorter.sorters;

import java.util.Comparator;

public class AlphabeticalReverseWordsSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        StringBuilder sb1 = new StringBuilder(o1).reverse();
        StringBuilder sb2 = new StringBuilder(o2).reverse();

        return sb1.toString().compareTo(sb2.toString());
    }
}
