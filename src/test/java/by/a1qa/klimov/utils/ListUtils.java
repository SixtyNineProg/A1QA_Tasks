package by.a1qa.klimov.utils;

import java.util.List;

public class ListUtils {
    public static boolean listIsSortedByASC(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++)
            if (!(list.get(i) <= list.get(i + 1))) return false;
        return true;
    }
}
