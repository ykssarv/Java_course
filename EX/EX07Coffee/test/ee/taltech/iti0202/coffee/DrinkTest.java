package ee.taltech.iti0202.coffee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrinkTest {

    @Test
    public void testDrinkCreation() {
        Drink coca = new Drink("Coca-cola");
        Assertions.assertEquals("Coca-cola", coca.getName());
    }
}
