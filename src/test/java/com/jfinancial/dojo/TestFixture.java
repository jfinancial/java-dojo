package com.jfinancial.dojo;

import com.jfinancial.dojo.model.Person;

import static com.jfinancial.dojo.model.Person.Gender.FEMALE;
import static com.jfinancial.dojo.model.Person.Gender.MALE;

public class TestFixture {

    public static final Person jane = new Person("Jane", FEMALE, 41, "USA");
    public static final Person brian = new Person("Brian", MALE, 66, "GBR");
    public static final Person debbie = new Person("Debbie", FEMALE, 56, "GBR");
    public static final Person ahmad = new Person("Ahmad", MALE, 22, "UAE");
    public static final Person stacy = new Person("Stacy", FEMALE, 17, "IRL");

}
