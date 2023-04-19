package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.short_.ShortSorter;

public class Partition5 implements ShortSorter {
    @Override
    public void sort(short[] array, int start, int endP1) {
        int finalLeft = ShortSorterUtils.partitionReverseNotStable5(array, start, endP1);
    }
}
