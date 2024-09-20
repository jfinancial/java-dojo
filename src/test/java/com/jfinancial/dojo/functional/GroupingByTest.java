package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jfinancial.dojo.TestFixture.*;
import static java.util.stream.Collectors.counting;
import static org.assertj.core.api.Assertions.assertThat;

class GroupingByTest {

    @Test
    void testGroupByCountryCode(){
        Map<String, List<Person>> groupingByCountry = Stream.of(jane, brian, debbie, ahmad, stacy)
                .collect(Collectors.groupingBy(Person::getCountryCode));
        assertThat(groupingByCountry.get("GBR")).containsExactly(brian, debbie);
        assertThat(groupingByCountry.get("UAE")).containsExactly(ahmad);
    }

    @Test
    void testGroupByCountryCodeWithCounnting(){
        Map<String, Long> countByCountryCode = Stream.of(jane, brian, debbie, ahmad, stacy)
                .collect(Collectors.groupingBy(Person::getCountryCode, counting()));
        assertThat(countByCountryCode.get("GBR")).isEqualTo(2);
        assertThat(countByCountryCode.get("UAE")).isEqualTo(1);
    }


}