package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;

import java.util.Map;

public class CapsuleStorage extends BasicResourceStorage implements ResourceStorage{
    public CapsuleStorage(Map<String, Integer> resources) {
        super(resources);
    }

    public boolean hasEnough(String name, Recipe recipe) {
        Logger.log("Checking if storage has enough resources.");
        return resources.getOrDefault(name, 0) >= 1
            && resources.getOrDefault("water", 0)
            >= recipe.getIngredients().getOrDefault("water", 0);
    }

    public void take(String name, Recipe recipe) {
        int haveWater = resources.getOrDefault("water", 0);
        int askedWater = recipe.getIngredients().getOrDefault("water", 0);
        if (haveWater < askedWater) {
            throw new UnableToMakeDrinkException("water", askedWater, haveWater);
        }

        Logger.log("Taking resources from storage.");
        resources.put(name, resources.getOrDefault(name, 0) - 1);
        resources.put("water", haveWater - askedWater);
    }
}
