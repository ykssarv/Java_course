package ee.taltech.iti0202.computerbuilder.database;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * The type Database test.
 */
public class DatabaseTest {

    /**
     * Test load to and from file.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testLoadToAndFromFile() throws ProductAlreadyExistsException {
        Component component = new Component("Prose", Component.Type.CPU, 10d, "AMD", 100, 10);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        database.saveComponent(component);
        database.saveToFile("test.txt");
        database.resetEntireDatabase();
        database.loadFromFile("test.txt");
        database = Database.getInstance();
        component = database.getCopy(database.getComponents().keySet().stream().findAny().get());
        Assertions.assertEquals("Prose", component.getName());
        Assertions.assertEquals(Component.Type.CPU, component.getType());
        Assertions.assertEquals(10d, component.getPrice());
        Assertions.assertEquals("AMD", component.getManufacturer());
        Assertions.assertEquals(100, component.getPerformancePoints());
        Assertions.assertEquals(10, component.getPowerConsumption());
    }
}
