package com.aldogg.sorter.int_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.int_.SorterInt;
import com.aldogg.sorter.int_.experimental.IntSorterUtils3;
import com.aldogg.sorter.shared.int_mask.MaskInfoInt;

import static com.aldogg.sorter.int_.SorterUtilsInt.partitionNotStable;
import static com.aldogg.sorter.int_.SorterUtilsInt.partitionReverseNotStable;

public class XXXSorter implements SorterInt {

    @Override
    public void sort(int[] array, int start, int endP1, FieldOptions options) {
        MaskInfoInt maskInfo = MaskInfoInt.calculateMask(array, start, endP1);
        int mask = maskInfo.getMask();
        int[] kList = MaskInfoInt.getMaskAsArray(mask);
        if (kList.length == 0) {
            return;
        }
        if (kList[0] == MaskInfoInt.UPPER_BIT) { //there are negative numbers and positive numbers
            int sortMask = 1 << kList[0];
            int finalLeft = options.isUnsigned() ? partitionNotStable(array, start, endP1, sortMask) : partitionReverseNotStable(array, start, endP1, sortMask);
            if (finalLeft - start > 1) { //sort negative numbers
                maskInfo = MaskInfoInt.calculateMask(array, start, finalLeft);
                mask = maskInfo.getMask();
                kList = MaskInfoInt.getMaskAsArray(mask);
                for (int i = kList.length - 1; i >= 0; i--) {
                    int sortMaskI = 1 << kList[i];
                    partitionStableNoMemory(array, start, finalLeft, sortMaskI);
                }
            }
            if (endP1 - finalLeft > 1) { //sort positive numbers
                maskInfo = MaskInfoInt.calculateMask(array, finalLeft, endP1);
                mask = maskInfo.getMask();
                kList = MaskInfoInt.getMaskAsArray(mask);
                for (int i = kList.length - 1; i >= 0; i--) {
                    int sortMaskI = 1 << kList[i];
                    partitionStableNoMemory(array, finalLeft, endP1, sortMaskI);
                }
            }
        } else {
            for (int i = kList.length - 1; i >= 0; i--) {
                int sortMask = 1 << kList[i];
                partitionStableNoMemory(array, start, endP1, sortMask);
            }
        }
    }

    public static int partitionStableNoMemory(int[] array, int start, int endP1, int mask) {
        // reduce, advance left and right if already ordered to reduce number of elements
        // count, count the number of 0 and 1s
        // technique 1, if the number of one is 75% then create an aux array of 25% size
        // technique 2, split in power of two group for example 256 then sort each, then join them with black white mergue strategy
        int[] aux = IntSorterUtils3.skipSortedPart(array, start, endP1 - 1, mask);
        int left = aux[0];
        int right = aux[1];

        int zeros = 0;
        int ones = 0;
        for(int i = left; i <= right; ++i) {
            int element = array[i];
            if ((element & mask) == 0) {
                zeros++;
            } else {
                ones++;
            }
        }

        //System.arraycopy(aux, 0, array, left, right);
        return left;
    }

}
