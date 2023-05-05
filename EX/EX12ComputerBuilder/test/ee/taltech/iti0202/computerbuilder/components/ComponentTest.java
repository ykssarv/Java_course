package ee.taltech.iti0202.computerbuilder.components;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * The type Component test.
 */
public class ComponentTest {

    private Component component;

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor() {
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals("Prose", component.getName());
        Assertions.assertEquals(Component.Type.CPU, component.getType());
        Assertions.assertEquals(45.5, component.getPrice());
        Assertions.assertEquals("AMD", component.getManufacturer());
        Assertions.assertEquals(100, component.getPerformancePoints());
        Assertions.assertEquals(50, component.getPowerConsumption());
        Assertions.assertEquals(1, component.getAmount());
    }

    /**
     * Test id increment works.
     */
    @Test
    public void testIdIncrementWorks() {
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Component component2 = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals(0, component.getId());
        Assertions.assertEquals(1, component2.getId());
    }

    /**
     * Test setters.
     */
    @Test
    public void testSetters() {
        component = new Component("Prosa", Component.Type.GPU, 40.5, "Intel", 10, 500);
        component.setName("Prose");
        component.setType(Component.Type.CPU);
        component.setPrice(45.5);
        component.setManufacturer("AMD");
        component.setPerformancePoints(100);
        component.setPowerConsumption(50);
        component.setId(50);
        component.setAmount(10);
        Assertions.assertEquals("Prose", component.getName());
        Assertions.assertEquals(Component.Type.CPU, component.getType());
        Assertions.assertEquals(45.5, component.getPrice());
        Assertions.assertEquals("AMD", component.getManufacturer());
        Assertions.assertEquals(100, component.getPerformancePoints());
        Assertions.assertEquals(50, component.getPowerConsumption());
        Assertions.assertEquals(50, component.getId());
        Assertions.assertEquals(10, component.getAmount());
    }

    /**
     * Test copy.
     */
    @Test
    public void testCopy() {
        Component component2 = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        component = new Component(component2);
        Assertions.assertEquals("Prose", component.getName());
        Assertions.assertEquals(Component.Type.CPU, component.getType());
        Assertions.assertEquals(45.5, component.getPrice());
        Assertions.assertEquals("AMD", component.getManufacturer());
        Assertions.assertEquals(100, component.getPerformancePoints());
        Assertions.assertEquals(50, component.getPowerConsumption());
        Assertions.assertEquals(1, component.getAmount());
    }

    /**
     * Test modify amount.
     */
    @Test
    public void testModifyAmount() {
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals(1, component.getAmount());
        component.increaseStock(3);
        Assertions.assertEquals(4, component.getAmount());
        component.decreaseStock(2);
        Assertions.assertEquals(2, component.getAmount());
    }

    /**
     * Test counter modification.
     */
    @Test
    public void testCounterModification() {
        Component.reset();
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals(0, component.getId());
        Component.setComponentsMadeAmount(10);
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals(10, component.getId());
        Component.reset();
        component = new Component("Prose", Component.Type.CPU, 45.5, "AMD", 100, 50);
        Assertions.assertEquals(0, component.getId());
    }
}
