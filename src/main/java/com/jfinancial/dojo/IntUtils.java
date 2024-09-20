package com.jfinancial.dojo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IntUtils {

    public static boolean isOdd(int i) {
        return i % 2 > 0;
    }

    public static boolean isEven(int i) {
        return i % 2 == 0;
    }
}
