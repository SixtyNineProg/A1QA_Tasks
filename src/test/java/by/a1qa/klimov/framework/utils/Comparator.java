package by.a1qa.klimov.framework.utils;

import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;

import static by.a1qa.klimov.framework.utils.Constants.ELEMENT_SIZES_INCORRECT;

@Log4j
public class Comparator {
    public static String compareListsAndReturnDifferenceElement (List<String> firstList, List<String> secondList) {
        if (firstList.size() <= secondList.size()) {
            for (String secondElement : secondList) {
                boolean find = false;
                if (firstList.stream().anyMatch(secondElement::equals)) find = true;
                if (!find) return secondElement;
            }
        } else log.error(ELEMENT_SIZES_INCORRECT);
        return null;
    }

    public static boolean isExistPartsInText(String text, String... parts) {
        return Arrays.stream(parts).allMatch((text::contains));
    }
}
