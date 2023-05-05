package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CapsuleStorageTest {
    private CapsuleStorage capsuleStorage;
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        capsuleStorage =
            (CapsuleStorage) new ResourceStorageBuilder()
                .withResource("water", 5)
                .withResource("coffee", 1)
                .build(ResourceStorageBuilder.ResourceStorageType.CAPSULE);
        recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 2)
            .build();
    }

    @Test
    public void testCapsuleStorageHasEnoughForCoffee() {
        Assertions.assertTrue(capsuleStorage.hasEnough("coffee", recipe));
    }

    @Test
    public void testCapsuleStorageTakeRemovesCoffee() {
        capsuleStorage.take("coffee", recipe);
        Assertions.assertEquals(0, capsuleStorage.amountOf("coffee"));
        Assertions.assertEquals(3, capsuleStorage.amountOf("water"));
    }

    @Test
    public void testCapsuleStorageThrowsExceptionIfNotEnoughWater() {
        recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 10)
            .withIngredient("coffee", 1)
            .build();
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            capsuleStorage.take("coffee", recipe);
        });
        Assertions.assertEquals("Not enough water: was asked 10, but only have 5", exception.getMessage());
    }
}
