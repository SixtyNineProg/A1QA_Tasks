package by.a1qa.klimov.utils;

import by.a1qa.klimov.dao.entity.Test;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestsSimulations {
    public static void simulateRunTests(List<Test> tests) {
        long time = tests.stream().mapToLong(
                test -> test.getEndTime().getTime() - test.getStartTime().getTime())
                .sum();
        time -= 8240000L;
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new ConcurrentModificationException("Sleep " + time + " exception");
        }
    }
}
