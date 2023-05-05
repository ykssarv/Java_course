package ee.taltech.iti0202.coffee;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

    private final Map<String, Integer> ingredients;

    private Recipe(Map<String, Integer> ingredients) {
        Logger.log("Created a recipe.");
        this.ingredients = ingredients;
    }

    public static class RecipeBuilder {
        private final Map<String, Integer> ingredients;

        public RecipeBuilder() {
            ingredients = new HashMap<>();
        }

        public RecipeBuilder withIngredient(String ingredient, Integer amount) {
            ingredients.put(ingredient, amount);
            return this;
        }

        public Recipe build() {
            return new Recipe(ingredients);
        }
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

}
