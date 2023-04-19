package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.short_.ShortSorter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaSorterShort implements ShortSorter {

    @Override
    public void sort(short[] array, int start, int endP1) {
        Arrays.sort(array, start, endP1);
    }

    @Override
    public void sort(List<Short> list) {
        Collections.sort(list);
    }
}
