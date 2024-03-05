package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

public class Partition4 implements SorterShort {
    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        short[] aux = new short[array.length];
        int finalLeft = ShortSorterUtils.partitionStable4(array, start, endP1, (short) 0x8000, aux);
    }
}
