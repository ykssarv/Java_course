package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CapsuleCoffeeMachineTest {

    private CapsuleCoffeeMachine coffeeMachine;

    @Test
    public void testCapsuleCoffeeMachineCanMakeDrink() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .withResource("coffee", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineCanMakeDrinkIsFalseWhenDoesNotKnowRecipe() {
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withResource("water", 5)
            .withResource("coffee", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineCanMakeDrinkIsFalseWhenNotEnoughResources() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 1)
            .withResource("coffee", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineCanMakeDrinkIsFalseWhenGarbageLimitReached() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(0)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .withResource("coffee", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineStartButtonTakesWater() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        WaterContainer waterContainer = new WaterContainer(100);
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("coffee", 5)
            .withWaterContainer(waterContainer)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
        coffeeMachine.startMachine();
        Assertions.assertTrue(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineMakeDrinkRemovesResources() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .withResource("coffee", 1)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        coffeeMachine.makeDrink("coffee");
        Assertions.assertFalse(coffeeMachine.canMakeDrink("coffee"));
    }

    @Test
    public void testCapsuleCoffeeMachineGivesWaterWhenThereIsNoCapsule() {
        Recipe recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 1)
            .build();
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .withGarbageLimit(10)
            .withRecipe("coffee", recipe)
            .withResource("water", 5)
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Drink drink = coffeeMachine.makeDrink("coffee");
        Assertions.assertEquals("water", drink.getName());
    }

    @Test
    public void testMakeDrinkThrowsErrorIfUnknownRecipe() {
        coffeeMachine = (CapsuleCoffeeMachine) new CoffeeMachineBuilder()
            .build(CoffeeMachineBuilder.CoffeeMachineType.CAPSULE);
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            coffeeMachine.makeDrink("coffee");
        });
        Assertions.assertEquals("Unknown recipe: coffee", exception.getMessage());
    }
}
