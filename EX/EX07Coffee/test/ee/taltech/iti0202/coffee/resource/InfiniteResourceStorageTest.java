package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InfiniteResourceStorageTest {

    private InfiniteResourceStorage resourceStorage;
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        resourceStorage =
            (InfiniteResourceStorage) new ResourceStorageBuilder()
                .withResource("water", 5)
                .build(ResourceStorageBuilder.ResourceStorageType.INFINITE);
        recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 2)
            .withIngredient("beans", 2)
            .build();
    }

    @Test
    public void testInfiniteResourceStorageHasInfinityOfRandomResource() {
        Assertions.assertEquals(Integer.MAX_VALUE, resourceStorage.amountOf("beans"));
    }

    @Test
    public void testInfiniteResourceStorageHasCorrectAmountOfWater() {
        Assertions.assertEquals(5, resourceStorage.amountOf("water"));
    }

    @Test
    public void testInfiniteResourceStorageHasResourcesEvenWhenTheyWereNotAdded() {
        Assertions.assertTrue(resourceStorage.hasEnough(recipe));
    }

    @Test
    public void testInfiniteResourceStorageTakeRemovesWaterButNothingElse() {
        resourceStorage.take(recipe);
        Assertions.assertEquals(3, resourceStorage.amountOf("water"));
        Assertions.assertEquals(Integer.MAX_VALUE, resourceStorage.amountOf("beans"));
    }

    @Test
    public void testInfiniteResourceStorageThrowsExceptionIfNotEnoughWater() {
        recipe = new Recipe.RecipeBuilder()
            .withIngredient("water", 10)
            .withIngredient("beans", 2)
            .build();
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            resourceStorage.take(recipe.getIngredients());
        });
        Assertions.assertEquals("Not enough water: was asked 10, but only have 5", exception.getMessage());
    }
}
