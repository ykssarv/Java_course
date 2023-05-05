package ee.taltech.iti0202.coffee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RecipeTest {

    @Test
    public void recipeBuilderAddsDataToMap() {
        Recipe recipe =
            new Recipe.RecipeBuilder()
            .withIngredient("Water", 5)
            .withIngredient("Milk", 2)
            .withIngredient("Sugar", 1)
            .build();
        Map<String, Integer> ingredients = recipe.getIngredients();
        Assertions.assertEquals(5, ingredients.get("Water"));
        Assertions.assertEquals(2, ingredients.get("Milk"));
        Assertions.assertEquals(1, ingredients.get("Sugar"));
    }
}
