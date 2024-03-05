package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.shared.OrderAnalysisResult;
import com.aldogg.sorter.shared.Section;
import com.aldogg.sorter.shared.int_mask.MaskInfoInt;

import static com.aldogg.sorter.short_.st.ShortSorterUtils.getMaskBit;
import static com.aldogg.sorter.short_.st.ShortSorterUtils.listIsOrderedSigned;


public class RadixByteSorterShort extends ShortBitMaskSorter {

    boolean calculateBitMaskOptimization = true;

    public void setCalculateBitMaskOptimization(boolean calculateBitMaskOptimization) {
        this.calculateBitMaskOptimization = calculateBitMaskOptimization;
    }

    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        int n = endP1 - start;
        if (n < 2) {
            return;
        }
        int ordered = listIsOrderedSigned(array, start, endP1);
        if (ordered == OrderAnalysisResult.DESCENDING) {
            ShortSorterUtils.reverse(array, start, endP1);
        }
        if (ordered != OrderAnalysisResult.UNORDERED) return;

        int[] kList = null;

        if (calculateBitMaskOptimization) {
            MaskInfoInt maskInfo = getMaskBit(array, start, endP1);
            int mask = maskInfo.getMask();
            kList = MaskInfoInt.getMaskAsArray(mask);
            if (kList.length == 0) {
                return;
            }
        }
        sort(array, start, endP1, options, kList, null);
    }

    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options, int[] kList, Object params) {
        int mask = 0x0000FFFF;
        if (calculateBitMaskOptimization) {
            if (kList.length == 0) {
                return;
            }
            MaskInfoInt maskParts;
            if (kList[0] == 15) { //sign bit is set and there are negative numbers and positive numbers
                int sortMask = 1 << kList[0];
                int finalLeft = ShortSorterUtils.partitionReverseNotStable(array, start, endP1, sortMask);
                int n1 = finalLeft - start;
                int n2 = endP1 - finalLeft;
                short[] aux = new short[Math.max(n1, n2)];
                if (n1 > 1) { //sort negative numbers
                    maskParts = getMaskBit(array, start, finalLeft);
                    mask = maskParts.getMask();
                    sortBytes(array, start, finalLeft, aux, mask);
                }
                if (n2 > 1) { //sort positive numbers
                    maskParts = getMaskBit(array, finalLeft, endP1);
                    mask = maskParts.getMask();
                    sortBytes(array, finalLeft, endP1, aux, mask);
                }
                return;
            } else {
                mask = MaskInfoInt.getMaskLastBits(kList, 0);
            }
        }
        int n = endP1 - start;
        short[] aux = new short[n];
        sortBytes(array, start, endP1, aux, mask);
    }

    private void sortBytes(short[] array, int start, int endP1, short[] aux, int mask) {
        boolean s0 = (mask & 0xFF) != 0;
        boolean s8 = (mask & 0xFF00) != 0;
        int n = endP1 - start;
        int ops = 0;
        short[] arrayOrig = array;
        int startOrig = start;
        int startAux = 0;

        if (s0) {
            Section section = new Section(8, 0);
            ShortSorterUtils.partitionStableLastBits(array, start, section, aux, n);

            short[] tempArray = array;
            array = aux;
            aux = tempArray;
            int temp = start;
            start = startAux;
            startAux = temp;
            ops++;

        }
        if (s8) {
            Section section = new Section(8, 8);
            ShortSorterUtils.partitionStableOneGroupBits(array, start, section, aux, startAux, n);
            array = aux;
            start = startAux;
            ops++;
        }
        if (ops % 2 == 1) {
            System.arraycopy(array, start, arrayOrig, startOrig, n);
        }
    }

}
