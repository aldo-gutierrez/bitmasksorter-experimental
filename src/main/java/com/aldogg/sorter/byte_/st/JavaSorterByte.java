package com.aldogg.sorter.byte_.st;

import com.aldogg.sorter.byte_.ByteSorter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaSorterByte implements ByteSorter {

    @Override
    public void sort(byte[] array, int start, int endP1) {
        Arrays.sort(array, start, endP1);
    }

    @Override
    public void sort(List<Byte> list) {
        Collections.sort(list);
    }
}
