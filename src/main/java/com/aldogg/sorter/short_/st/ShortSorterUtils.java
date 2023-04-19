package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.AnalysisResult;
import com.aldogg.sorter.IntSection;
import com.aldogg.sorter.MaskInfoInt;

public class ShortSorterUtils {

    public static final short ZERO_SHORT = (short) 0;

    public static void swap(final short[] array, final int left, final int right) {
        short auxS = array[left];
        array[left] = array[right];
        array[right] = auxS;
    }

    public static void reverse(short[] array, int start, int endP1) {
        int length = endP1 - start;
        int ld2 = length / 2;
        int end = endP1 - 1;
        for (int i = 0; i < ld2; ++i) {
            swap(array, start + i, end - i);
        }
    }

    public static int listIsOrderedSigned(short[] array, int start, int endP1) {
        short i1 = array[start];
        int i = start + 1;
        while (i < endP1) {
            short i2 = array[i];
            if (i2 != i1) {
                break;
            }
            ++i;
        }
        if (i == endP1) {
            return AnalysisResult.ALL_EQUAL;
        }

        //ascending
        i1 = array[i];
        if (array[i - 1] < i1) {
            ++i;
            for (; i < endP1; ++i) {
                short i2 = array[i];
                if (i1 > i2) {
                    break;
                }
                i1 = i2;
            }
            if (i == endP1) {
                return AnalysisResult.ASCENDING;
            }
        }
        //descending
        else {
            ++i;
            for (; i < endP1; ++i) {
                short i2 = array[i];
                if (i1 < i2) {
                    break;
                }
                i1 = i2;
            }
            if (i == endP1) {
                return AnalysisResult.DESCENDING;
            }
        }
        return AnalysisResult.UNORDERED;
    }


    public static int partitionReverseNotStable(short[] array, int start, int endP1, int mask) {
        int left = start;
        int right = endP1 - 1;

        while (left <= right) {
            short element = array[left];
            if ((element & mask) == 0) {
                while (left <= right) {
                    element = array[right];
                    if (((element & mask) == 0)) {
                        right--;
                    } else {
                        swap(array, left, right);
                        left++;
                        right--;
                        break;
                    }
                }
            } else {
                left++;
            }
        }
        return left;
    }

    public static int partitionReverseNotStable2(short[] array, int start, int endP1, short mask) {
        int left = start;
        int right = endP1 - 1;

        while (left <= right) {
            short element = array[left];
            if ((element & mask) == 0) {
                while (left <= right) {
                    element = array[right];
                    if (((element & mask) == 0)) {
                        right--;
                    } else {
                        swap(array, left, right);
                        left++;
                        right--;
                        break;
                    }
                }
            } else {
                left++;
            }
        }
        return left;
    }

    public static int partitionReverseNotStable3(short[] array, int start, int endP1) {
        int left = start;
        int right = endP1 - 1;

        while (left <= right) {
            short element = array[left];
            if (element >= ZERO_SHORT) {
                while (left <= right) {
                    element = array[right];
                    if (element >= ZERO_SHORT) {
                        right--;
                    } else {
                        swap(array, left, right);
                        left++;
                        right--;
                        break;
                    }
                }
            } else {
                left++;
            }
        }
        return left;
    }

    public static int partitionStable4(final short[] array, final int start, final int endP1, final int mask, final short[] aux) {
        int left = start;
        int right = 0;
        for (int i = start; i < endP1; ++i) {
            short element = array[i];
            if ((element & mask) == 0) {
                array[left] = element;
                left++;
            } else {
                aux[right] = element;
                right++;
            }
        }
        System.arraycopy(aux, 0, array, left, right);
        return left;
    }

    public static int partitionReverseNotStable5(short[] array, int start, int endP1) {
        int left = start;
        int right = endP1 - 1;

        while (left <= right) {
            short element = array[left];
            if (element >= ZERO_SHORT) {
                while (left <= right) {
                    element = array[right];
                    if (element >= ZERO_SHORT) {
                        --right;
                    } else {
                        array[right] = array[left];
                        array[left] = element;
                        ++left;
                        --right;
                        break;
                    }
                }
            } else {
                left++;
            }
        }
        return left;
    }

    public static int[] partitionStableLastBits(short[] array, int start, IntSection section, short[] aux, int n) {
        final int mask = section.sortMask;
        final int endP1 = start + n;
        final int countLength = 1 << section.length;
        final int[] count = new int[countLength];
        for (int i = start; i < endP1; ++i) {
            count[array[i] & mask]++;
        }
        for (int i = 0, sum = 0; i < countLength; ++i) {
            int countI = count[i];
            count[i] = sum;
            sum += countI;
        }
        for (int i = start; i < endP1; ++i) {
            short element = array[i];
            aux[count[element & mask]++] = element;
        }
        return count;
    }

    public static int[] partitionStableOneGroupBits(short[] array, int start, IntSection section, short[] aux, int startAux, int n) {
        final int mask = section.sortMask;
        final int shiftRight = section.shiftRight;
        final int endP1 = start + n;
        final int countLength = 1 << section.length;
        final int[] count = new int[countLength];
        for (int i = start; i < endP1; ++i) {
            count[(array[i] & mask) >>> shiftRight]++;
        }
        for (int i = 0, sum = 0; i < countLength; ++i) {
            int countI = count[i];
            count[i] = sum;
            sum += countI;
        }
        if (startAux == 0) {
            for (int i = start; i < endP1; ++i) {
                short element = array[i];
                aux[count[(element & mask) >>> shiftRight]++] = element;
            }
        } else {
            for (int i = start; i < endP1; ++i) {
                short element = array[i];
                aux[count[(element & mask) >>> shiftRight]++ + startAux] = element;
            }
        }
        return count;
    }

    public static MaskInfoInt getMaskBit(final short[] array, final int start, final int endP1) {
        int p_mask = 0x0000;
        int i_mask = 0x0000;
        for (int i = start; i < endP1; i++) {
            int e = array[i];
            p_mask = p_mask | e;
            i_mask = i_mask | (~e);
        }
        MaskInfoInt m = new MaskInfoInt();
        m.p_mask = p_mask & 0x0000FFFF;
        m.i_mask = i_mask & 0x0000FFFF;
        return m;
    }

//    public static MaskInfoInt getMaskBit(short[] array, int start, int end) {
//        int p_mask = 0;
//        int i_mask = 0;
//
//        for(int i = start; i < end; ++i) {
//            int e = array[i];
//            p_mask |= e;
//            i_mask |= ~e;
//        }
//
//        MaskInfoInt m = new MaskInfoInt();
//        m.p_mask = p_mask;
//        m.i_mask = i_mask;
//        return m;
//    }

}
