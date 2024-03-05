package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

public class Partition5 implements SorterShort {
    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        int finalLeft = ShortSorterUtils.partitionReverseNotStable5(array, start, endP1);
    }
}
