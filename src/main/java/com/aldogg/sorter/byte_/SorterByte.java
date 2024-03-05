package com.aldogg.sorter.byte_;

import com.aldogg.sorter.shared.NullHandling;
import com.aldogg.sorter.shared.Sorter;
import com.aldogg.sorter.FieldOptions;

import java.util.*;

public interface SorterByte extends Sorter {

    FieldOptions defaultOptions = new FieldOptions() {
    };


    void sort(byte[] array, int start, int endP1, FieldOptions options);

    default void sort(byte[] array) {
        sort(array, 0, array.length, defaultOptions);
    }

    default void sort(byte[] array, int start, int endP1) {
        sort(array, start, endP1, defaultOptions);
    }

    default void sort(List<Byte> list) {
        sort(list, 0, list.size(), defaultOptions);
    }

    default void sort(List<Byte> list, int start, int endP1) {
        sort(list, start, endP1, defaultOptions);
    }

    default void sort(Byte[] list) {
        sort(list, 0, list.length, defaultOptions);
    }

    default void sort(Byte[] list, int start, int endP1) {
        sort(list, start, endP1, defaultOptions);
    }

    default void sort(List<Byte> list, int start, int endP1, FieldOptions options) {
        int nulls = 0;
        boolean throwExceptionIfNull = options.getNullHandling().equals(NullHandling.NULLS_EXCEPTION);
        List<Byte> subList = start == 0 && endP1 == list.size() ? list : list.subList(start, endP1);
        if (!options.getNullHandling().equals(NullHandling.NULLS_EXCEPTION)) {
            for (Byte value : subList) {
                if (throwExceptionIfNull) {
                    if (value == null) {
                        throw new RuntimeException("Null found in Collection");
                    }
                }
                nulls++;
            }
        }
        int n = endP1 - start - nulls;
        byte[] a = new byte[n];
        int j = 0;
        if (nulls > 0) {
            for (Byte value : subList) {
                if (value != null) {
                    a[j] = value;
                    j++;
                }
            }
        } else {
            for (Byte value : subList) {
                a[j] = value;
                j++;
            }
        }
        sort(a, 0, n);
        j = 0;
        ListIterator<Byte> iterator = subList.listIterator();
        if (nulls > 0) {
            if (options.getNullHandling().equals(NullHandling.NULLS_FIRST)) {
                while (nulls > 0) {
                    iterator.next();
                    iterator.set(null);
                    nulls--;
                }
            }
            while (j < n) {
                iterator.next();
                iterator.set(a[j]);
                j++;
            }
            if (options.getNullHandling().equals(NullHandling.NULLS_LAST)) {
                while (nulls > 0) {
                    iterator.next();
                    iterator.set(null);
                    nulls--;
                }
            }

        } else {
            while (iterator.hasNext()) {
                iterator.next();
                iterator.set(a[j]);
                j++;
            }
        }
    }

    default void sort(Byte[] list, int start, int endP1, FieldOptions options) {
        int nulls = 0;
        boolean throwExceptionIfNull = options.getNullHandling().equals(NullHandling.NULLS_EXCEPTION);
        for (int i = start; i < endP1; i++) {
            if (list[i] == null) {
                if (throwExceptionIfNull) {
                    throw new RuntimeException("Null found in Collection");
                }
                nulls++;
            }
        }
        int n = endP1 - start - nulls;
        byte[] array = new byte[n];
        if (nulls > 0) {
            for (int i = start, j = 0; i < endP1; i++) {
                Byte e = list[i];
                if (e != null) {
                    array[j] = e;
                    j++;
                }
            }
        } else {
            for (int i = start, j = 0; i < endP1; i++, j++) {
                array[j] = list[i];
            }
        }
        sort(array, 0, n);
        if (nulls > 0) {
            int i = start;
            int j = 0;
            if (options.getNullHandling().equals(NullHandling.NULLS_FIRST)) {
                while (nulls > 0) {
                    list[i] = null;
                    i++;
                    nulls--;
                }
            }
            for (; j < n; i++, j++) {
                list[i] = array[j];
            }
            if (options.getNullHandling().equals(NullHandling.NULLS_LAST)) {
                while (nulls > 0) {
                    list[i] = null;
                    i++;
                    nulls--;
                }
            }
        } else {
            for (int i = start, j = 0; j < n; i++, j++) {
                list[i] = array[j];
            }
        }
    }

}