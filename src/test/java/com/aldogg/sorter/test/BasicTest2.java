package com.aldogg.sorter.test;

import com.aldogg.sorter.byte_.SorterByte;
import com.aldogg.sorter.generators.GeneratorParams;
import com.aldogg.sorter.generators.IntGenerator;
import com.aldogg.sorter.short_.SorterShort;
import com.aldogg.sorter.test.unit.IntBasicTest;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BasicTest2 extends IntBasicTest {

    public void testSpeed(SorterByte[] sorters, int iterations, GeneratorParams params, TestAlgorithms testSortResults) {
        Function<GeneratorParams, int[]> function = IntGenerator.getGFunction(params.function);
        for (int iter = 0; iter < iterations; iter++) {
            int[] listInts = function.apply(params);
            byte[] list = new byte[listInts.length];
            for (int i = 0; i < listInts.length; i++) {
                list[i] = (byte) listInts[i];
            }
            testSort(list, sorters, testSortResults);
        }
    }

    public void testSort(byte[] list, SorterByte[] sorters, TestAlgorithms testSortResults) {
        byte[] baseListSorted = null;
        for (int i = 0; i < sorters.length; i++) {
            SorterByte sorter = sorters[i];
            byte[] listAux = Arrays.copyOf(list, list.length);
            try {
                long start = System.nanoTime();
                sorter.sort(listAux);
                long elapsed = System.nanoTime() - start;
                if (i == 0) {
                    baseListSorted = listAux;
                } else {
                    if (validateResult) {
                        assertArrayEquals(baseListSorted, listAux);
                    }
                }
                testSortResults.set(sorter.getName(), elapsed);
            } catch (Throwable ex) {
                testSortResults.set(sorter.getName(), 0);
                if (list.length <= 10000) {
                    System.err.println("Sorter " + sorter.getName());
                    String orig = Arrays.toString(list);
                    System.err.println("List orig: " + orig);
                    String failed = Arrays.toString(listAux);
                    System.err.println("List fail: " + failed);
                    String ok = Arrays.toString(baseListSorted);
                    System.err.println("List ok: " + ok);
                } else {
                    System.err.println("Sorter " + sorter.getName());
                    System.err.println("List order is not OK ");
                }
                ex.printStackTrace();
            }
        }
    }


    public void testSpeed(SorterShort[] sorters, int iterations, GeneratorParams params, TestAlgorithms testSortResults)  {
        Function<GeneratorParams, int[]> function = IntGenerator.getGFunction(params.function);
        for (int iter = 0; iter < iterations; iter++) {
            int[] listInts = function.apply(params);
            short[] list = new short[listInts.length];
            for (int i = 0; i < listInts.length; i++) {
                list[i] = (short) listInts[i];
            }
            testSort(list, sorters, testSortResults);
        }
    }

    public void testSort(short[] list, SorterShort[] sorters, TestAlgorithms testSortResults) {
        short[] baseListSorted = null;
        for (int i = 0; i < sorters.length; i++) {
            SorterShort sorter = sorters[i];
            short[] listAux = Arrays.copyOf(list, list.length);
            try {
                long start = System.nanoTime();
                sorter.sort(listAux);
                long elapsed = System.nanoTime() - start;
                if (i == 0) {
                    baseListSorted = listAux;
                } else {
                    if (validateResult) {
                        assertArrayEquals(baseListSorted, listAux);
                    }
                }
                testSortResults.set(sorter.getName(), elapsed);
            } catch (Throwable ex) {
                testSortResults.set(sorter.getName(), 0);
                if (list.length <= 10000) {
                    System.err.println("Sorter " + sorter.getName());
                    String orig = Arrays.toString(list);
                    System.err.println("List orig: " + orig);
                    String failed = Arrays.toString(listAux);
                    System.err.println("List fail: " + failed);
                    String ok = Arrays.toString(baseListSorted);
                    System.err.println("List ok: " + ok);
                } else {
                    System.err.println("Sorter " + sorter.getName());
                    System.err.println("List order is not OK ");
                }
                ex.printStackTrace();
            }
        }
    }


}
