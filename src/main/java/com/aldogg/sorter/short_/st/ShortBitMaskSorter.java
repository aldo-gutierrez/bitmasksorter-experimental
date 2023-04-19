package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.AnalysisResult;
import com.aldogg.sorter.BitSorterParams;
import com.aldogg.sorter.MaskInfoInt;
import com.aldogg.sorter.short_.ShortSorter;

import static com.aldogg.sorter.MaskInfoInt.getMaskAsArray;
import static com.aldogg.sorter.short_.st.ShortSorterUtils.getMaskBit;

public class ShortBitMaskSorter implements ShortSorter {

    protected BitSorterParams params = BitSorterParams.getSTParams();

    public void setParams(BitSorterParams params) {
        this.params = params;
    }

    @Override
    public void sort(short[] array, int start, int endP1) {
        int n = endP1 - start;
        if (n < 2) {
            return;
        }
        int ordered = com.aldogg.sorter.short_.st.ShortSorterUtils.listIsOrderedSigned(array, start, endP1);
        if (ordered == AnalysisResult.DESCENDING) {
            ShortSorterUtils.reverse(array, start, endP1);
        }
        if (ordered != AnalysisResult.UNORDERED) return;

        MaskInfoInt maskInfo = getMaskBit(array, start, endP1);
        int mask = maskInfo.getMask();
        int[] kList = getMaskAsArray(mask);
        if (kList.length == 0) {
            return;
        }

        sort(array, start, endP1, kList);
    }

}
