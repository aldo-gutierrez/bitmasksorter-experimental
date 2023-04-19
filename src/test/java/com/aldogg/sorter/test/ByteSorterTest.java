package com.aldogg.sorter.test;

import com.aldogg.sorter.Sorter;
import com.aldogg.sorter.byte_.ByteSorter;
import com.aldogg.sorter.byte_.st.JavaSorterByte;
import com.aldogg.sorter.byte_.st.RadixByteSorterByte;
import com.aldogg.sorter.generators.GeneratorFunctions;
import com.aldogg.sorter.generators.GeneratorParams;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import static com.aldogg.sorter.test.BaseTest.*;

public class ByteSorterTest extends BasicTest2 {
    @Test
    public void speedTestPositiveByteST() throws IOException {
        ByteSorter[] sorters = new ByteSorter[]{new JavaSorterByte(), new RadixByteSorterByte()};
        BufferedWriter writer = getWriter("test-results/speed_positiveByte_st_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");

        TestAlgorithms<ByteSorter> testAlgorithms;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 80000;
        params.limitLow = 0;
        params.limitHigh = 127;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, null);

        System.out.println("----------------------");

        params.random = new Random(SEED);
        long[] limitHigh = new long[]{15, 31, 63, 127};

        for (long limitH : limitHigh) {
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
    public void speedTestSignedByteST() throws IOException {
        ByteSorter[] sorters = new ByteSorter[]{new JavaSorterByte(), new RadixByteSorterByte()};
        BufferedWriter writer = getWriter("test-results/speed_signedByte_st_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");

        TestAlgorithms<ByteSorter> testAlgorithms;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 80000;
        params.limitLow = -128;
        params.limitHigh = 127;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, null);

        System.out.println("----------------------");

        params.random = new Random(SEED);
        long[] limitHigh = new long[]{15, 31, 63, 127};

        for (long limitH : limitHigh) {
            params.limitLow = (int) -limitH;
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
    public void speedTestUnsigned() throws IOException {
        BufferedWriter writer = getWriter("test-results/speed_unsignedByte_" + branch + ".csv");
        writer.write("\"Size\"" + "," + "\"Range\"" + "," + "\"Sorter\"" + "," + "\"Time\"" + "\n");
        ByteSorter[] sorters = new ByteSorter[]{new RadixByteSorterByte()};

        for (Sorter sorter : sorters) {
            sorter.setUnsigned(true);
        }

        TestAlgorithms<ByteSorter> testAlgorithms;

        GeneratorParams params = new GeneratorParams();
        params.random = new Random(SEED);
        params.size = 80000;
        params.limitLow = -100;
        params.limitHigh = 100;
        params.function = GeneratorFunctions.RANDOM_INTEGER_RANGE;

        //heatup
        testAlgorithms = new TestAlgorithms<>(sorters);
        testSpeed(sorters, HEAT_ITERATIONS, params, testAlgorithms);
        testAlgorithms.printTestSpeed(params, null);

        params.random = new Random(SEED);
        System.out.println("----------------------");
        {
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
