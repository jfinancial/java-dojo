package com.jfinancial.dojo.hamcrest;

import com.jfinancial.dojo.model.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jfinancial.dojo.TestFixture.*;
import static com.jfinancial.dojo.model.Person.Gender.FEMALE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HamcrestMatchersTest {

    @Test
    public void testStringMatching() {
        assertThat("foo", equalToIgnoringCase("FOO"));
        assertThat("foo ", equalToIgnoringWhiteSpace("FOO"));
        assertThat("foo ", containsString("oo"));
        assertThat("foo ", startsWith("f"));
    }

    @Test
    void testObjectMatcher(){
        var stacyName = stacy.toString();
        assertThat(stacy,hasToString(stacyName));
    }

    @Test
    void testBeanMatcher() {
        assertThrows(AssertionError.class, () -> assertThat(stacy, hasProperty("country")));
        assertThat(stacy, hasProperty("countryCode"));
        assertThat(stacy, hasProperty("countryCode", equalTo("IRL")));
    }

    @Test
    void testSamePropertyValuesAs() {
        final Person stacy2 = new Person("Stacy", FEMALE, 17, "IRL");
        assertThat(stacy, samePropertyValuesAs(stacy2));
    }

    @Test
    void testEmpty() {
        assertThat(Collections.emptyList(), empty());
    }

    @Test
    void testHasSize(){
        assertThat(List.of(stacy,ahmad), hasSize(2));
    }

    @Test
    void testContainsInAnyOrder(){
        assertThat(List.of(ahmad,stacy,jane), containsInAnyOrder(stacy, ahmad, jane));
    }

    @Test
    void testHasItemInArray(){
        Person[] folks = {ahmad, stacy, jane};
        assertThat(folks, hasItemInArray(ahmad));
        assertThat(stacy, isIn(Arrays.stream(folks).toList()));
    }

}