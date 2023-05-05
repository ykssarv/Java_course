package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.machine.BasicCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CoffeeMachineBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class KitchenTest {

    private Kitchen kitchen;
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 2)
            .build();
    }

    @Test
    public void testKitchenWithNoMachinesDoesNotMakeDrink() {
        Optional<Drink> drink = kitchen.makeDrink("coffee");
        Assertions.assertTrue(drink.isEmpty());
    }

    @Test
    public void testKitchenThatCanMakeDrinkMakesDrink() {
        BasicCoffeeMachine coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withResource("water", 2)
            .withResource("beans", 2)
            .withRecipe("coffee", recipe)
            .build();
        kitchen.addCoffeeMachine(coffeeMachine);
        Optional<Drink> drink = kitchen.makeDrink("coffee");
        Assertions.assertTrue(drink.isPresent());
        Drink actualDrink = drink.get();
        Assertions.assertEquals("coffee", actualDrink.getName());
    }

    @Test
    public void testKitchenWithOneCoffeeMachineThanCanNotMakeDrinkDoesNotMakeDrink() {
        BasicCoffeeMachine coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withResource("water", 2)
            .withResource("beans", 2)
            .build();
        kitchen.addCoffeeMachine(coffeeMachine);
        Optional<Drink> drink = kitchen.makeDrink("coffee");
        Assertions.assertTrue(drink.isEmpty());
    }


}

