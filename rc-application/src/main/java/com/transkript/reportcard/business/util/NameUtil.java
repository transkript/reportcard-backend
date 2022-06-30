package com.transkript.reportcard.business.util;

public class NameUtil {
    /**
     * Remove all non-alphanumeric characters from the given string.
     *
     * @param name The string to be cleaned.
     * @return The cleaned string.
     */
    public static String clean(String name) {
        return name.replaceAll("[^a-zA-Z0-9]", "");
    }

    /**
     * Compares two strings and returns true if they are equal, ignoring case.
     *
     * @param name1 The first string to be compared.
     * @param name2 The second string to be compared.
     * @return True if the strings are equal, ignoring case.
     */
    public static boolean compare(String name1, String name2) {
        String name1Clean = clean(name1);
        String name2Clean = clean(name2);
        return name1Clean.equals(name2Clean);
    }

    public static boolean compareAll(String name, String... names) {
        for (String nameToCompare : names) {
            if (compare(name, nameToCompare)) {
                return true;
            }
        }
        return false;
    }
}
