package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.short_.ShortSorter;

public class Partition4 implements ShortSorter {
    @Override
    public void sort(short[] array, int start, int endP1) {
        short[] aux = new short[array.length];
        int finalLeft = ShortSorterUtils.partitionStable4(array, start, endP1, (short) 0x8000, aux);
    }
}
