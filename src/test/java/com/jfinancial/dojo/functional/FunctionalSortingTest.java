package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.stream.Stream;

import static com.jfinancial.dojo.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class FunctionalSortingTest {

    @Test
    void testSortingAndLambdaComparator(){
        var sorted = Stream.of(jane, brian, debbie, ahmad, stacy)
                .sorted( Comparator.comparingInt(Person::getAge))
                .toList();
        assertThat(sorted.getFirst().getAge()).isEqualTo(17);
        assertThat(sorted.getLast().getAge()).isEqualTo(66);
    }

    @Test
    void testReversedSortingAndLambdaComparator(){
        var sorted = Stream.of(jane, brian, debbie, ahmad, stacy)
                .sorted( Comparator.comparingInt(Person::getAge).reversed())
                .toList();
        assertThat(sorted.getFirst().getAge()).isEqualTo(66);
        assertThat(sorted.getLast().getAge()).isEqualTo(17);
    }

    @Test
    void testSortingAndLambdaCompareTo(){
        var sorted = Stream.of(jane, brian, debbie, ahmad, stacy)
                .sorted( (a,b) -> a.getAge().compareTo(b.getAge()))
                .toList();
        assertThat(sorted.getFirst().getAge()).isEqualTo(17);
        assertThat(sorted.getLast().getAge()).isEqualTo(66);
    }
}
