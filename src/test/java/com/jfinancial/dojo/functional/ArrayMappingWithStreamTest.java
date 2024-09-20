package com.jfinancial.dojo.functional;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayMappingWithStreamTest {

    @Test
    void testArrayMapping(){
        int[][] originalArray2D = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Stream<int[]> streamOfRows = Arrays.stream(originalArray2D);
        int[][] resultArray2D = streamOfRows.toArray(int[][]::new);
        assertArrayEquals(originalArray2D, resultArray2D);
    }
}
