package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

public class Partition3 implements SorterShort {
    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        int finalLeft = ShortSorterUtils.partitionReverseNotStable3(array, start, endP1);
    }
}
