package com.jfinancial.dojo.functional;

import com.jfinancial.dojo.model.Person;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.jfinancial.dojo.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class PairCollectingWithSimpleEntryTest {

    @Test
    void testCollectingPairsWithSimpleEntry(){
        var list = Stream.of(stacy, ahmad, jane, brian).toList();
        var entries = IntStream.range(0, list.size() - 1)
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(list.get(i), list.get(i + 1))).toList();
        var personToNextPerson = new HashMap<Person,Person>();
        entries.forEach(e -> personToNextPerson.put(e.getKey(), e.getValue()));
        assertThat(personToNextPerson.get(stacy)).isEqualTo(ahmad);
        assertThat(personToNextPerson.get(ahmad)).isEqualTo(jane);
        assertThat(personToNextPerson.get(jane)).isEqualTo(brian);
        assertThat(personToNextPerson.get(brian)).isEqualTo(null);

    }
}