package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;

import java.util.Map;

public class BasicResourceStorage implements ResourceStorage {

    protected final Map<String, Integer> resources;

    public BasicResourceStorage(Map<String, Integer> resources) {
        this.resources = resources;
    }

    public Map<String, Integer> getResources() {
        return resources;
    }

    public void addResource(String resource, Integer amount) {
        Logger.log("Added " + amount + " " + resource + " to storage.");
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
    }

    public int amountOf(String resource) {
        Logger.log("Checking amount of " + resource);
        return resources.getOrDefault(resource, 0);
    }

    public boolean hasEnough(Map<String, Integer> resources) {
        Logger.log("Checking if storage has enough resources.");
        for(Map.Entry<String, Integer> entry : resources.entrySet()) {
            if (this.resources.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                Logger.log("Not enough " + entry.getKey());
                return false;
            }
        }
        Logger.log("Storage has enough resources.");
        return true;
    }

    public boolean hasEnough(Recipe recipe) {
        return hasEnough(recipe.getIngredients());
    }

    public void take(Map<String, Integer> resources) {
        Logger.log("Taking resources from storage.");
        for(Map.Entry<String, Integer> entry : resources.entrySet()) {
            int have = this.resources.getOrDefault(entry.getKey(), 0);
            int asked = entry.getValue();
            if (have < asked) {
                throw new UnableToMakeDrinkException(entry.getKey(), asked, have);
            }
            this.resources.put(entry.getKey(), have - asked);
        }
    }

    public void take(Recipe recipe) {
        take(recipe.getIngredients());
    }
}
