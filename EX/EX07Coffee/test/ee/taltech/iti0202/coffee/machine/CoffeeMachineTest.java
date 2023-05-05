package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoffeeMachineTest {

    private BasicCoffeeMachine coffeeMachine;

    @Test
    public void testCoffeeMachineCanMakeDrink() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .withResource("beans", 5)
            .build();
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCoffeeMachineCanMakeDrinkIsFalseWhenDoesNotKnowRecipe() {
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withResource("water", 5)
            .withResource("beans", 5)
            .build();
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCoffeeMachineCanMakeDrinkIsFalseWhenNotEnoughResources() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 1)
            .withResource("beans", 5)
            .build();
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCoffeeMachineCanMakeDrinkIsFalseWhenGarbageLimitReached() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(0)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .withResource("beans", 5)
            .build();
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCoffeeMachineStartButtonTakesWater() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        WaterContainer waterContainer = new WaterContainer(100);
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("beans", 5)
            .withWaterContainer(waterContainer)
            .build();
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
        coffeeMachine.startMachine();
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testMakeDrinkThrowsErrorIfUnknownRecipe() {
        coffeeMachine = (BasicCoffeeMachine) new CoffeeMachineBuilder()
            .build();
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            coffeeMachine.makeDrink("coffee");
        });
        Assertions.assertEquals("Unknown recipe: coffee", exception.getMessage());
    }
}
