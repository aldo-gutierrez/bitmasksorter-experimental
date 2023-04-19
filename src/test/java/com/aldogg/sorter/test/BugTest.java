package com.aldogg.sorter.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.aldogg.sorter.int_.experimental.IntSorterUtils2.partitionStableOneThirdMemory;

public class BugTest {
    @Test
    public void testPartitionStable() {
        int[] ori2 = new int[]{2, 0, 0, 0, 1, 1, 1};
        int[] target = new int[]{2, 0, 0, 0, 1, 1, 1};
        partitionStableOneThirdMemory(ori2, 1, 7, 1);
        Assertions.assertArrayEquals(target, ori2);
    }

}
