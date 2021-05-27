package by.a1qa.klimov.utils;

import java.util.Random;

public class Randomizer {
    private static final int LOW_CODE_ASCII_CHAR = 93;
    private static final int HIGH_CODE_ASCII_CHAR = 122;

    public static String generateRandomText(int length) {
        return new Random().ints(LOW_CODE_ASCII_CHAR, HIGH_CODE_ASCII_CHAR)
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String generateRandomText(int length, int lowCodeAsciiChar, int highCodeAsciiChar) {
        return new Random().ints(lowCodeAsciiChar, highCodeAsciiChar)
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static int generateRandomNumFromRange(int lowBound, int upperBound) {
        return (int) Math.floor(Math.random() * (upperBound - lowBound + 1) + lowBound);
    }
}
