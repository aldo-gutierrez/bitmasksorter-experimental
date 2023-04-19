package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.short_.ShortSorter;

public class RadixWordSorterShort implements ShortSorter {

    @Override
    public void sort(short[] array, int start, int endP1) {

        int[] count = new int[65536];
        for (int i = start; i < endP1; ++i) {
            int element = array[i] + 32768;
            count[element]++;
        }
        int i = start;
        for (int j = 0; j < 65536; ++j) {
            int countJ = count[j];
            if (countJ > 0) {
                short jb = (short) (j - 32768);
                for (int k = 0; k < countJ; ++k) {
                    array[i] = jb;
                    i++;
                }
            }
        }
    }
}
