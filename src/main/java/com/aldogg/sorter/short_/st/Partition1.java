package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

public class Partition1 implements SorterShort {
    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        int finalLeft = ShortSorterUtils.partitionReverseNotStable(array, start, endP1, 0x8000);
    }
}
