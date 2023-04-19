package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.short_.ShortSorter;

public class Partition2 implements ShortSorter {
    @Override
    public void sort(short[] array, int start, int endP1) {
        int finalLeft = ShortSorterUtils.partitionReverseNotStable2(array, start, endP1, (short) 0x8000);
    }
}

