package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutomaticCoffeeMachineTest {

    private AutomaticCoffeeMachine coffeeMachine;

    @Test
    public void testAutomaticCoffeeMachineCanMakeDrink() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (AutomaticCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.AUTOMATIC);
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testAutomaticCoffeeMachineCanMakeDrinkIsFalseWhenDoesNotKnowRecipe() {
        coffeeMachine = (AutomaticCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withResource("water", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.AUTOMATIC);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testAutomaticCoffeeMachineCanMakeDrinkIsFalseWhenNotEnoughResources() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (AutomaticCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 1)
            .build(CoffeeMachineBuilder.CoffeeMachineType.AUTOMATIC);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testAutomaticCoffeeMachineCanMakeDrinkIsFalseWhenGarbageLimitReached() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (AutomaticCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(0)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.AUTOMATIC);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testAutomaticCoffeeMachineStartButtonTakesWater() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        WaterContainer waterContainer = new WaterContainer(100);
        coffeeMachine = (AutomaticCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withWaterContainer(waterContainer)
            .build(CoffeeMachineBuilder.CoffeeMachineType.AUTOMATIC);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
        coffeeMachine.startMachine();
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }
}
