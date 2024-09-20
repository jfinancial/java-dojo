package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.model.Person;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jfinancial.dojo.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 **  Test Collectors.toMap()  function
 **  See {@linktourl https://www.baeldung.com/java-collectors-tomap}
 */
class ToMapTest {

    @Test
    void testCollectingToMapWhereNoDuplicates() {
        Map<String, Person> result = Stream.of(jane, brian, ahmad, stacy)
                .collect(Collectors.toMap(Person::getCountryCode, v -> v));
        assertThat(result.get("GBR")).isEqualTo(brian);
        assertThat(result.get("USA")).isEqualTo(jane);
        assertThat(result.get("IRL")).isEqualTo(stacy);
        assertThat(result.get("UAE")).isEqualTo(ahmad);
        var e = assertThrows(IllegalStateException.class, () -> Stream.of(jane, brian, debbie, ahmad, stacy)
                .collect(Collectors.toMap(Person::getCountryCode, v -> v)));
        assertThat(e.getMessage()).startsWith("Duplicate key GBR");
    }

    @Test
    void testCollectingToMapWhereDuplicatesAreAddedToASet() {
        //This is similar to groupingBy except here we use a set
        //second arg in toMap creates a set and implicitly does an addAll
        //third argument is the merge function which merges the two values (i.e. the sets) for the duplicate key
        Map<String, Set<Person>> result = Stream.of(jane, brian, debbie, ahmad, stacy)
                .collect(Collectors.toMap(
                        Person::getCountryCode,
                        v -> new HashSet<>(Collections.singleton(v)), // Create a new set with the person
                        (set1, set2) -> {  // Merge two sets when there are duplicate keys
                            set1.addAll(set2);
                            return set1;
                        }
                ));
        assertThat(result.get("GBR")).containsAll(Set.of(brian,debbie));
        assertThat(result.get("USA")).containsExactly(jane);
        assertThat(result.get("IRL")).containsExactly(stacy);
        assertThat(result.get("UAE")).containsExactly(ahmad);
    }

}