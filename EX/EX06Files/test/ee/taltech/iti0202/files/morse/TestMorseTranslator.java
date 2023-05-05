package ee.taltech.iti0202.files.morse;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestMorseTranslator {
    @Test
    public void testAddMorseCodes() {
        MorseTranslator morseTranslator = new MorseTranslator();
        Map<String, String> morse = morseTranslator.addMorseCodes(Arrays.asList("H ....", "+ .-.-."));
        Assert.assertEquals(morse.get("h"), "....");
        Assert.assertEquals(morse.get("+"), ".-.-.");
    }

    @Test
    public void testTranslateLinesToMorse() {
        MorseTranslator morseTranslator = new MorseTranslator();
        morseTranslator.addMorseCodes(Arrays.asList("H ....", "+ .-.-."));
        List<String> morseCode = morseTranslator.translateLinesToMorse(Arrays.asList("h+", "+ h"));
        Assert.assertEquals(morseCode, Arrays.asList(".... .-.-.", ".-.-.\t...."));
    }
    @Test
    public void testTranslateLinesFromMorse() {
        MorseTranslator morseTranslator = new MorseTranslator();
        morseTranslator.addMorseCodes(Arrays.asList("H ....", "+ .-.-."));
        List<String> morseCode = morseTranslator.translateLinesToMorse(Arrays.asList("h+", "+ h"));
        List<String> morse = morseTranslator.translateLinesFromMorse(morseCode);
        Assert.assertEquals(morse, Arrays.asList("h+", "+ h"));
    }

}
