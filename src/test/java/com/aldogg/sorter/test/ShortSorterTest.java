package com.aldogg.sorter.test;

import com.aldogg.sorter.generators.GeneratorFunctions;
import com.aldogg.sorter.generators.GeneratorParams;
import com.aldogg.sorter.short_.ShortSorter;
import com.aldogg.sorter.short_.st.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import static com.aldogg.sorter.test.BaseTest.*;

public class ShortSorterTest extends BasicTest2 {

    @Test
    public void speedTestPositiveShortST() throws IOException {
        ShortSorter[] sorters = new ShortSorter[]{new JavaSorterShort(), new RadixByteSorterShort(), new RadixWordSorterShort(), new ShortSorterTreeMap()};
        BufferedWriter writer = getWriter("test-results/speed_positiveShort_st_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");
        TestAlgorithms<ShortSorter> testAlgorithms;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 80000;
        params.limitLow = -0;
        params.limitHigh = 100;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, null);
        System.out.println("----------------------");

        params.random = new Random(SEED);
        Short[] limitHigh = new Short[]{100, 1000, 10000};

        for (Short limitH : limitHigh) {
            params.limitHigh = limitH;

            for (int size : new int[]{10000, 100000, 1000000, 10000000, 40000000}) {
                testAlgorithms = new TestAlgorithms<>(sorters);
                params.size = size;
                testSpeed(sorters, ITERATIONS, params, testAlgorithms);
                testAlgorithms.printTestSpeed(params, writer);
            }

            System.out.println("----------------------");
        }
        System.out.println();
        writer.close();
    }

    @Test
    public void speedTestSignedShortST() throws IOException {
        ShortSorter[] sorters = new ShortSorter[]{new JavaSorterShort(), new RadixWordSorterShort(), new RadixByteSorterShort()};
        BufferedWriter writer = getWriter("test-results/speed_signedShort_st_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");

        TestAlgorithms<ShortSorter> testAlgorithms;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 16;
        params.limitLow = -10;
        params.limitHigh = 10;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, writer);

        System.out.println("----------------------");

        params.random = new Random(SEED);
        Short[] limitHigh = new Short[]{1000, 10000};

        for (Short limitH : limitHigh) {
            params.limitLow = -limitH;
            params.limitHigh = limitH;

            for (int size : new int[]{10000, 100000, 1000000, 10000000, 40000000}) {
                testAlgorithms = new TestAlgorithms<>(sorters);
                params.size = size;
                testSpeed(sorters, ITERATIONS, params, testAlgorithms);
                testAlgorithms.printTestSpeed(params, writer);
            }

            System.out.println("----------------------");
        }
        System.out.println();
        writer.close();
    }

    @Test
    public void speedTestSignedShortST2() throws IOException {
        ShortSorter[] sorters = new ShortSorter[]{new Partition1(), new Partition2(), new Partition3(), new Partition4(), new Partition5()};
        BufferedWriter writer = getWriter("test-results/speed_test_st_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");

        TestAlgorithms<ShortSorter> testAlgorithms;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 16;
        params.limitLow = -10;
        params.limitHigh = 10;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, null);
        System.out.println("----------------------");

        params.random = new Random(SEED);
        Short[] limitHigh = new Short[]{100, 1000, 10000};

        for (Short limitH : limitHigh) {
            params.limitLow = -limitH;
            params.limitHigh = limitH;
            for (int size : new int[]{10000, 100000, 1000000, 10000000, 40000000}) {
                testAlgorithms = new TestAlgorithms<>(sorters);
                params.size = size;
                testSpeed(sorters, ITERATIONS, params, testAlgorithms);
                testAlgorithms.printTestSpeed(params, writer);
            }
            System.out.println("----------------------");
        }
        System.out.println();
        writer.close();
    }

}
