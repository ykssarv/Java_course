package ee.taltech.iti0202.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Idk.
 */
public class DataStructures {

    HashMap<String, Integer> grades;

    /**
     * Idk vol2.
     */
    public DataStructures() {
        this.grades = new HashMap<>();
    }

    /**
     * Given String is a sentence with some words.
     * There are only single whitespace between every word and no punctuation marks.
     * Also there are no capital letters in input string.
     * <p>
     * Return the longest word from the input sentence.
     * <p>
     * If there are more than one word with the same length then return the word which comes alphabetically first.
     * <p>
     * Hints:
     * You can split words into an array using "str.split()"
     * Sorting the list with the longest words can definitely help you to find the word which comes alphabetically.
     *
     * @param sentence input String to find the longest words
     * @return the longest String from input
     */
    public static String findLongestWord(String sentence) {
        Optional<String> sentence2 = Arrays.stream(sentence.split(" "))
                .sorted()
                .max(Comparator.comparingInt(String::length));
        return sentence2.orElse("");
    }

    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> answerMap = new HashMap<>();
        for (String word: sentence) {
            if (!answerMap.containsKey(word)) {
                answerMap.put(word, 0);
            }
            Integer previous = answerMap.get(word);
            answerMap.put(word, previous + 1);
        }
        return answerMap;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that the string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        Map<String, Integer> helperMap = new HashMap<>();
        List<String> answerList = new ArrayList<>();
        for (String word: words) {
            if (!helperMap.containsKey(word)) {
                helperMap.put(word, 0);
            }
            Integer previous = helperMap.get(word);
            helperMap.put(word, previous + 1);
            if (helperMap.get(word) % 2 == 0) {
                answerList.add(word);
            }
        }
        return answerList;
    }

    /**
     * Method to save student and student's grade(you should use a Map here).
     * Only add student if his/hers grade is in the range of 0-5.
     *
     * @param studentInfo String with a pattern (name:grade)
     */
    public void addStudent(String studentInfo) {
        // Siin see add ja getgrade peavad muutma ühte ja sama mapi, nüüd vaja, et see nagu klassi külje jääks
        // Nii et teeme konstruktori mis see parseint oli
        // Võtab sisendiks stringi ja teeb selle int-iks
        String[] data = studentInfo.split(":");
        int grade = Integer.parseInt(data[1]);
        if (grade > 5 || grade < 0) {
            return; // Kui mingi keelatud hinne, siis edasi ei lähe
        }
        this.grades.put(data[0], grade); // Aga kui sobib, siis paneme talle hinde kirja
    }

    /**
     * Method to get student's grade.
     * Return the student's grade by his/hers name.
     * You can assume that the user is already added(previous function with student's info already called).
     *
     * @param name String students name
     * @return int student's grade.
     */
    public int getStudentGrade(String name) {
       if (!this.grades.containsKey(name)) {
           return -1; // Kui tudengit pole meie hinnete mapis, siis tagastame -1
       }
       return this.grades.get(name); // Kui on olemas, siis tagastame tema hinde
    }

    /**
     * Main.
     * @param args Commend line arguments.
     */
    public static void main(String[] args) {
    }
}
