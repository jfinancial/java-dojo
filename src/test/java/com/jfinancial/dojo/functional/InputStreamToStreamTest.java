package com.jfinancial.dojo.functional;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputStreamToStreamTest {

    public static final String FILE = "/TextToStream.txt";

    @Test
    void givenInputStream_whenConvertingWithBufferedReader_thenConvertInputStreamToStringStream() throws IOException {
        var resource = Objects.requireNonNull(getClass().getResourceAsStream(FILE));
        try (InputStreamReader isr = new InputStreamReader(resource)){
             BufferedReader reader = new BufferedReader(isr);
            // reduce function -> start with empty string and concatenate
            var result = reader.lines().reduce("", (s1, s2) -> s1 + s2);

            assertEquals("HelloWorldThisisatest", result);
        }
    }
}
