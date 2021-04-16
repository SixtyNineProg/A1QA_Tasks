package by.a1qa.klimov.framework.utils;

import java.util.List;

public class Checker {
    public static boolean listIsSortedByASC(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++)
            if (!(list.get(i) <= list.get(i + 1))) return false;
        return true;
    }
}
