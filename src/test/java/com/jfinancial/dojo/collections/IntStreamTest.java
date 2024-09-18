package com.jfinancial.dojo.collections;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IntStreamTest {

    @Test
    void testOddAndEvenNumbersGeneratedFromIntStream(){
        var even = IntStream.range(1, 11).filter(IntStreamTest::isEven)
                .boxed()
                .collect(Collectors.toSet());
        var odd = IntStream.range(1, 11).filter(IntStreamTest::isOdd)
                .boxed()
                .collect(Collectors.toSet());
        assertThat(even).doesNotContainAnyElementsOf(odd);
    }

    @Test
    void testCombiningTwoListsUsingFlatmap(){
        List<Integer> even = IntStream.range(1, 11).filter(IntStreamTest::isEven)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> odd = IntStream.range(1, 11).filter(IntStreamTest::isOdd)
                .boxed()
                .collect(Collectors.toList());
        var bothLists = Stream.of(even,odd).flatMap(Collection::stream).collect(Collectors.toList());
        assertThat(bothLists).isEqualTo(List.of(2,4,6,8,10,1,3,5,7,9));
    }

    private static boolean isOdd(int i) {
        return i % 2 > 0;
    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }
}
