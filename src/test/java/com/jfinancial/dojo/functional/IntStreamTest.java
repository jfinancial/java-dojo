package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.IntUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class IntStreamTest {

    @Test
    void testOddAndEvenNumberRangesGeneratedFromIntStream(){
        var even = IntStream.range(1, 11).filter(IntUtils::isEven)
                .boxed()
                .collect(Collectors.toSet());
        var odd = IntStream.range(1, 11).filter(IntUtils::isOdd)
                .boxed()
                .collect(Collectors.toSet());
        assertThat(even).doesNotContainAnyElementsOf(odd);
    }

}