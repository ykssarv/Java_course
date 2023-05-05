package ee.taltech.iti0202.introduction;
import java.util.List;
import java.util.stream.Collectors;

public class Introduction {


    /**
     * Method gets two numbers as parameters.
     * Method must answer if the given pair gives bad, normal or good outcome.
     * Outcome is "bad" if any of values is less than 5.
     * Outcome is "good" if one value equals doubled second value.
     * Outcome is "ok" if both values equal at least 5.
     * The priority is as follows: "bad", "good", "ok" (if several cases apply, take the higher / earlier).
     *
     * @param valueOne int
     * @param valueTwo int
     * @return String based on the values of valueOne and valueTwo
     */
    public String howIsOutcome(int valueOne, int valueTwo) {
        if (valueOne < 5 || valueTwo < 5) {
            return "bad";
        } else if (valueOne == valueTwo * 2 || valueTwo == valueOne * 2) {
            return "good";
        } else {
            return "ok";
        }
    }

    /**
     * Method gets a list of numbers.
     * Return a list containing only even numbers of the given list.
     * If the given list does not contain any even numbers, return an empty list.
     *
     * @param numbers given list that contains numbers.
     * @return list of even numbers.
     */
    public List<Integer> findEvenNumbersList(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
    }

    /**
     * Method gets an array of numbers.
     * Return an array containing only even numbers of the given array.
     * If the given array does not contain any even numbers, return an empty array.
     *
     * You must not use the previous function in this function!
     *
     * @param numbers given array that contains numbers.
     * @return array of even numbers.
     */
    public int[] findEvenNumbersArray(int[] numbers) {
        int counter = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                counter++;
            }
        }
        int[] name = new int[counter];
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] % 2 == 0) {
                counter--;
                name[counter] = numbers[i];
            }
        }
        return name;
    }

    /**
     * Run tests.
     * @param args info.
     */
    public static void main(String[] args) {
    }
}
