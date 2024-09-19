package com.jfinancial.dojo.functional;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionalTests {

    private static final Integer[] INTEGERS = IntStream.range(1, 11).boxed().toArray(Integer[]::new);

    @Test
    void testFunctionalSumming() {
        var summedByReduce = Arrays.stream(INTEGERS).reduce(0, Integer::sum);var summedBySum = Arrays.stream(INTEGERS).mapToInt(Integer::intValue).sum();
        assertThat(summedByReduce).isEqualTo(summedBySum);
        assertThat(summedByReduce).isEqualTo(55);
    }

    @Test
    void testFunctionalSummingUsingLambda() {
        int result = Arrays.stream(INTEGERS)
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertThat(result).isEqualTo(55);
    }

    @Test
    void testSummingUsingParallelStream(){
        var result = Arrays.asList(INTEGERS).parallelStream()
                .reduce(0, Integer::sum, Integer::sum);
        assertThat(result).isEqualTo(55);
    }

}