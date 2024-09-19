package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.model.Person;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jfinancial.dojo.model.Person.Gender.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToMapTest {

    private final Person jane = new Person("Jane", FEMALE, 41, "USA");
    private final Person brian = new Person("Brian", MALE, 66, "GBR");
    private final Person debbie = new Person("Debbie", FEMALE, 56, "GBR");
    private final Person ahmad = new Person("Ahmad", MALE, 22, "UAE");
    private final Person stacy = new Person("Stacy", FEMALE, 17, "IRL");

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
        Map<String, Set<Person>> result = Stream.of(jane, brian, debbie, ahmad, stacy)
                .collect(Collectors.toMap(
                        Person::getCountryCode,
                        v -> new HashSet<>(Collections.singleton(v)), // Create a new set with the person
                        (set1, set2) -> {  // Merge two sets when there are duplicate keys
                            set1.addAll(set2);
                            return set1;
                        }
                ));
        assertThat(result.get("GBR")).containsExactly(brian,debbie);
        assertThat(result.get("USA")).containsExactly(jane);
        assertThat(result.get("IRL")).containsExactly(stacy);
        assertThat(result.get("UAE")).containsExactly(ahmad);
    }
}

