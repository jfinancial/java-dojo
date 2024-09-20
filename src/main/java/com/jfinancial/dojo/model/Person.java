package com.jfinancial.dojo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Person {

    private final String name;
    private final Gender gender;
    private final Integer age;
    private final String countryCode;

    public enum Gender {
        MALE, FEMALE
    }
}
