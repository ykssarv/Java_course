package ee.taltech.iti0202.pokemon;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Multipliers.
 */
public class Multipliers {

    private static final String MULTIPLIER_FILEPATH = "data/multipliers.txt";
    private static final Map<String, Map<String, Double>> MULTIPLIERS = new HashMap<>();

    private static boolean multipliersLoaded = false;

    /**
     * Gets multipliers.
     *
     * @return the multipliers
     */
    public static Map<String, Map<String, Double>> getMultipliers() {
        if (!multipliersLoaded) {
            loadMultipliers();
        }
        return MULTIPLIERS;
    }

    private static void loadMultipliers() {
        try {
            String content = Request.readFromFile(MULTIPLIER_FILEPATH);
            parseMultipliers(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No multiplier file present.");
        }
    }

    private static void parseMultipliers(String content) {
        List<String> lines = Arrays.stream(content.split("\n")).collect(Collectors.toList());
        List<String> secondLevelKeys = splitLine(lines.get(0));
        List<List<String>> multipliers = lines.subList(1, lines.size()).stream()
            .map(Multipliers::splitLine)
            .collect(Collectors.toList());
        multipliers.forEach(list -> {
            Map<String, Double> thisMap = new HashMap<>();
            for (int i = 0; i < secondLevelKeys.size(); i++) {
                thisMap.put(secondLevelKeys.get(i), Double.parseDouble(list.get(i + 1)));
            }
            Multipliers.MULTIPLIERS.put(list.get(0), thisMap);
        });
    }

    private static List<String> splitLine(String line) {
        return Arrays.stream(line.split("[\t|\n| |\r]+"))
            .filter(x -> x.length() > 0)
            .collect(Collectors.toList());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Map<String, Map<String, Double>> multipliers = Multipliers.getMultipliers();
        multipliers.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            entry.getValue().entrySet().forEach(subEntry -> {
                System.out.println("   - " + subEntry.getKey() + " : " + subEntry.getValue());
            });
        });
    }
}
