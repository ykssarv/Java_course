
package ee.taltech.iti0202.exam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Exam.
 */
public class Exam {
    /**
     * Given a list of score information,
     * return a map with name and the
     * corresponding sum of score for that name.
     * <p>
     * Each line is in format: "score:name1,name2,name3".
     * Score part is always non-negative integer.
     * Names do not contain spaces.
     * There can be one or more names.
     * One line does not have duplicate names.
     * <p>
     * One line indicates the score for the given names.
     * If the same name (case-sensitive) is present in another line,
     * the score is added.
     * <p>
     * The order of the output Map is not important.
     * Map should not contain names with 0 score.
     * <p>
     * Example:
     * "10:Ago,Mati"
     * "20:Ago,Kati"
     * =>
     * {Ago=30, Kati=20, Mati=10}
     * <p>
     * "1:Ago"
     * "2:ago"
     * =>
     * {Ago=1, ago=2}
     * <p>
     * "0:Ago"
     * =>
     * {}
     *
     * @param scores the scores
     * @return the map
     */
    public static Map<String, Integer> sumScoresFromText(List<String> scores) {
        Map<String, Integer> points = new HashMap<>();
        for (String string: scores) {
            String[] splitString = string.split(":");
            Integer point = Integer.parseInt(splitString[0]);
            if (point == 0) {
                continue;
            }
            for (String name: splitString[1].split(",")) {
                if (!points.containsKey(name)) {
                    points.put(name, 0);
                }
                points.put(name, point + points.get(name));

            }
        }

        return points;
    }

    /**
     * Write a method that finds from given array two elements of which sum of cross sum is the largest.
     * Cross sum is sum of all numbers in the element (23 => 2 + 3 = 5).
     * In given method you have to apply cross sum, until it is smaller than 10 (one-digit).
     * For example cross sum of 123 is 1+2+3 = 6.
     * Cross sum on 991 is 9+9+1=19, this number has 2 digits, so we apply cross sum again,
     * 1+9=10, and again, 1+0 = 1. So the final cross sum is 1.
     * <p>
     * Two elements are always in the same order as in the list:
     * in case 1,2,3 you have to consider pairs:
     * 1,2
     * 1,3
     * 2,3
     * <p>
     * 3,2 is NOT allowed
     * <p>
     * combineNumbers([1, 2, 3]) => 23 (2+3 is largest)
     * combineNumbers([1]) => 0 (only one element)
     * combineNumbers([1, 2, 3, 12, 66]) => 312 (3+1+2=6 is largest or
     * for example 1266 => 1+2+6+6=15 => 6. return one that is first in the array)
     * combineNumbers([1, 2, 1, 3]) => 23 (2+3 is largest)
     *
     * @param numbers the numbers
     * @return the int
     */
    public static int combineNumbers(int[] numbers) {
        int record = -1;
        int recordValue = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                String first = String.valueOf(numbers[i]);
                String second = String.valueOf((numbers[j]));
                int combined = Integer.parseInt(first + second);
                int initial = combined;
                while (combined != crossSum(combined)) {
                    combined = crossSum(combined);
                }
                if (combined > record) {
                    record = combined;
                    recordValue = initial;
                }
            }
        }

        return recordValue;
    }

    /**
     * Cross sum int.
     *
     * @param i the
     * @return the int
     */
    public static int crossSum(int i) {
        String iString = String.valueOf(i);
        int total = 0;
        for (String chara: iString.split("")) {
            total += Integer.parseInt(chara);
        }
        return total;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println(sumScoresFromText(List.of("10:Ago", "20:Ago,Mari"))); // {Ago=30, Mari=20}
        System.out.println(combineNumbers(new int[]{1, 2, 3}));  // 23
    }

}


