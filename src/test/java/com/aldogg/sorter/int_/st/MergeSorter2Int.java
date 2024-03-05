package com.aldogg.sorter.int_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.int_.SorterInt;

import static com.aldogg.sorter.BitSorterParams.VERY_SMALL_N_SIZE;

/*
  MergeSort implementation
  improvements:
  - Small list with Sorting Networks
  - use of aux array created once
 */
public class MergeSorter2Int implements SorterInt {

    @Override
    public void sort(int[] array, int start, int endP1, FieldOptions options) {
        int n = endP1 - start;
        int[] aux = new int[n];
        mergeSort(array, aux, start, endP1, options);
    }

    public static void mergeSort(int[] array, int[] aux, int start, int endP1, FieldOptions options) {
        int n = endP1 - start;
        if (n < 2) {
            return;
        }
        if (n <= VERY_SMALL_N_SIZE) {
            SortingNetworksInt.getInstance().sort(array, start, endP1, options);
            return;
        }

        int mid = n / 2;
        int midIndex = start + mid;
        mergeSort(array, aux, start, midIndex, options);
        mergeSort(array, aux, midIndex, endP1, options);

        merge(array, aux, start, endP1, midIndex);
    }


    public static void merge(int[] a, int[] aux, int start, int endP1, int midIndex) {
        int i = start, j = midIndex, k = start;
        while (i < midIndex && j < endP1) {
            if (a[i] <= a[j]) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        while (i < midIndex) {
            aux[k++] = a[i++];
        }
        while (j < endP1) {
            aux[k++] = a[j++];
        }
        System.arraycopy(aux, start, a, start, endP1 - start);
    }

}
