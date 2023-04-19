package com.aldogg.sorter.byte_.st;

import com.aldogg.sorter.byte_.ByteSorter;

public class RadixByteSorterByte implements ByteSorter {

    protected boolean unsigned = false;

    @Override
    public boolean isUnsigned() {
        return unsigned;
    }

    public void setUnsigned(boolean unsigned) {
        this.unsigned = unsigned;
    }

    @Override
    public void sort(byte[] array, int start, int endP1) {
        int[] count = new int[256];
        if (!isUnsigned()) {
            for (int i = start; i < endP1; ++i) {
                count[array[i] + 128]++;
            }
            int i = start;
            for (int j = 0; j < 256; ++j) {
                int countJ = count[j];
                if (countJ > 0) {
                    byte jb = (byte) (j - 128);
                    for (int k = 0; k < countJ; ++k) {
                        array[i] = jb;
                        i++;
                    }
                }
            }
        } else {
            for (int i = start; i < endP1; ++i) {
                count[array[i] & 0xFF]++;
            }
            int i = start;
            for (int j = 0; j < 256; ++j) {
                int countJ = count[j];
                if (countJ > 0) {
                    byte jb = (byte) (j);
                    for (int k = 0; k < countJ; ++k) {
                        array[i] = jb;
                        i++;
                    }
                }
            }
        }
    }
}

