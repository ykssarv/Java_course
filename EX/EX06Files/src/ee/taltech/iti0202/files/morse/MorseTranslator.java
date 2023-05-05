package ee.taltech.iti0202.files.morse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseTranslator {
    private final Map<String, String> lineMap = new HashMap<>();
    private final Map<String, String> morseMap = new HashMap<>();

    /**
     * Add morse codes
     * @param lines to add
     * @return Map of strings
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        lines.forEach(line -> {
            String symbol = line.substring(0, 1).toLowerCase(Locale.ROOT);
            String morse = line.substring(2);
            lineMap.put(symbol, morse);
            morseMap.put(morse, symbol);
        });
        return lineMap;
    }

    /**
     * Translate lines to morse
     * @param lines to translate
     * @return list of strings
     */
    public List<String> translateLinesToMorse(List<String> lines) {

        return lines.stream().map(this::translateLineToMorse).collect(Collectors.toList());
    }

    /**
     * Translate lines from morse
     * @param lines to translate
     * @return list of strings
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        return lines.stream().map(this::translateLineFromMorse).collect(Collectors.toList());
    }

    private String translateLineToMorse(String line) {
        return Arrays.stream(line.split(" ")).map(this::wordToMorse).collect(Collectors.joining("\t"));
    }

    private String translateLineFromMorse(String line) {
        return Arrays.stream(line.split("\t")).map(this::wordFromMorse).collect(Collectors.joining(" "));
    }

    private String wordToMorse(String word) {
        return Arrays.stream(word.split(""))
            .map(x -> lineMap.get(x.toLowerCase()))
            .collect(Collectors.joining(" "));
    }
    private String wordFromMorse(String word) {
        return Arrays.stream(word.split(" "))
            .map(morseMap::get)
            .collect(Collectors.joining());
    }
}
