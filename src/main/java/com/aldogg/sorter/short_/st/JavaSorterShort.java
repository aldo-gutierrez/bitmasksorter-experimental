package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

import java.util.Arrays;

public class JavaSorterShort implements SorterShort {

    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        Arrays.sort(array, start, endP1);
    }

}
