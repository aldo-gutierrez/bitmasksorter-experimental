package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.shared.OrderAnalysisResult;
import com.aldogg.sorter.shared.int_mask.MaskInfoInt;
import com.aldogg.sorter.short_.SorterShort;

import static com.aldogg.sorter.shared.int_mask.MaskInfoInt.getMaskAsArray;
import static com.aldogg.sorter.short_.st.ShortSorterUtils.getMaskBit;

public abstract class ShortBitMaskSorter implements SorterShort {


    public abstract void sort(short[] array, int start, int endP1, FieldOptions options, int[] bList, Object params);

    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        int n = endP1 - start;
        if (n < 2) {
            return;
        }
        int ordered = com.aldogg.sorter.short_.st.ShortSorterUtils.listIsOrderedSigned(array, start, endP1);
        if (ordered == OrderAnalysisResult.DESCENDING) {
            ShortSorterUtils.reverse(array, start, endP1);
        }
        if (ordered != OrderAnalysisResult.UNORDERED) return;

        MaskInfoInt maskInfo = getMaskBit(array, start, endP1);
        int mask = maskInfo.getMask();
        int[] kList = getMaskAsArray(mask);
        if (kList.length == 0) {
            return;
        }

        sort(array, start, endP1, options, kList, null);
    }

}
