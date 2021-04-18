package by.a1qa.klimov.framework.utils;

import java.util.Random;

public class Randomizer {
    public static String generateRandomText(int length) {
        Random random = new Random();
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static int generateRandomNumFromRange(int lowBound, int upperBound) {
        return (int) Math.floor(Math.random() * (upperBound - lowBound + 1) + lowBound);
    }
}
