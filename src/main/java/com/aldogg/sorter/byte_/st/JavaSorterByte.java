package com.aldogg.sorter.byte_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.byte_.SorterByte;

import java.util.Arrays;

public class JavaSorterByte implements SorterByte {

    @Override
    public void sort(byte[] array, int start, int endP1, FieldOptions options) {
        Arrays.sort(array, start, endP1);
    }

}
