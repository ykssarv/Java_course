package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import ee.taltech.iti0202.coffee.machine.BasicCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CoffeeMachineBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WaterContainerTest {

    @Test
    public void testWaterContainerStartsFull() {
        WaterContainer container = new WaterContainer(5);
        Assertions.assertTrue(container.hasEnoughWater(5));
    }

    @Test
    public void testTakeWaterRemovesSomeWater() {
        WaterContainer container = new WaterContainer(5);
        container.takeWater(5);
        Assertions.assertFalse(container.hasEnoughWater(1));
    }

    @Test
    public void testFillWaterContainerFillsContainer() {
        WaterContainer container = new WaterContainer(5);
        container.takeWater(4);
        container.fill();
        Assertions.assertTrue(container.hasEnoughWater(5));
    }

    @Test
    public void twoCoffeeMachinesTakeWaterFromOneWaterContainer() {
        WaterContainer container = new WaterContainer(100);
        BasicCoffeeMachine coffeeMachine1 = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withWaterContainer(container)
            .withWaterLimit(90)
            .build();
        BasicCoffeeMachine coffeeMachine2 = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withWaterContainer(container)
            .withWaterLimit(20)
            .build();
        coffeeMachine1.startMachine();
        Assertions.assertTrue(container.hasEnoughWater(10));
        Assertions.assertFalse(container.hasEnoughWater(11));
        coffeeMachine2.startMachine();
        Assertions.assertTrue(container.hasEnoughWater(80));
        Assertions.assertFalse(container.hasEnoughWater(81));
    }

    @Test
    public void testWaterContainerThrowsExceptionIfTooMuchWaterIsAsked() {
        WaterContainer container = new WaterContainer(5);
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            container.takeWater(10);
        });
        Assertions.assertEquals("Was asked 10 water, but container only has 5", exception.getMessage());
    }
}
