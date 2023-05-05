package ee.taltech.iti0202.kt2;

import java.util.List;
import java.util.Locale;

public class Exam {
    /**
     * Given two strings,
     * find if one string is a rotation of another string.
     * Comparison should be case insensitive ("A" and "a" are the same).
     *
     * rotatedString("piimavunts", "ntspiimavu") => true
     * rotatedString("ABC", "cab") => true
     * rotatedString("kurgid", "gikur") => false
     */
    public static boolean rotatedString(String word1, String word2) {
        word1 = word1.toLowerCase(Locale.ROOT);
        word2 = word2.toLowerCase(Locale.ROOT);
        for (int i = 0; i < word1.length(); i++) {
            String version = word1.substring(i, word1.length()) + word1.substring(0, i);
            if (version.equals(word2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a list of integers,
     * return true if the value 3 appears in the list exactly 3 times,
     * and no 3's are next to each other.
     *
     * haveThree([3, 1, 3, 1, 3]) => true
     * haveThree([3, 1, 3, 3]) => false
     * haveThree([3, 4, 3, 3, 4]) => false
     */
    public static boolean haveThree(List<Integer> integers) {
        boolean wasThree = false;
        int counter = 0;
        for (Integer i: integers) {
            if (i == 3) {
                if (wasThree) {
                    return false;
                }
                wasThree = true;
                counter += 1;
            } else {
                wasThree = false;
            }
        }
        return counter == 3;
    }
}
