package com.aldogg.sorter.short_.st;

import com.aldogg.sorter.FieldOptions;
import com.aldogg.sorter.short_.SorterShort;

import java.util.Map;
import java.util.TreeMap;

public class ShortSorterTreeMap implements SorterShort {

    @Override
    public void sort(short[] array, int start, int endP1, FieldOptions options) {
        TreeMap<Short, Integer> treeMap = new TreeMap<>();
        for (int i = start; i < endP1; i++) {
            Short element = array[i];
            if (!treeMap.containsKey(element)) {
                treeMap.put(element, 1);
            } else {
                treeMap.put(element, treeMap.get(element) + 1);
            }
        }
        int i = start;
        for (Map.Entry<Short, Integer> entry : treeMap.entrySet()) {
            int c = entry.getValue();
            short element = entry.getKey();
            for (int j = 0; j < c; j++) {
                array[i] = element;
                i++;
            }
        }
    }
}
