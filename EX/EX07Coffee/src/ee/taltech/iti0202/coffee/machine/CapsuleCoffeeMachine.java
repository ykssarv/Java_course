package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import ee.taltech.iti0202.coffee.resource.CapsuleStorage;
import ee.taltech.iti0202.coffee.resource.ResourceStorage;

import java.util.Map;

public class CapsuleCoffeeMachine extends BasicCoffeeMachine implements CoffeeMachine {

    public CapsuleCoffeeMachine(Integer waterLimit, Integer garbageLimit, WaterContainer waterContainer, Map<String, Recipe> recipes, ResourceStorage resourceStorage) {
        super(waterLimit, garbageLimit, waterContainer, recipes, resourceStorage);
    }

    @Override
    public boolean notEnoughResources(String name) {
        return !((CapsuleStorage) resourceStorage).hasEnough(name, recipes.get(name));
    }

    @Override
    public Drink makeDrink(String name) {
        if (!recipes.containsKey(name)) {
            throw new UnableToMakeDrinkException(name);
        }
        garbageAmount++;
        ((CapsuleStorage) resourceStorage).take(name, recipes.get(name));
        if (resourceStorage.amountOf(name) == -1) {
            Logger.log("No capsules left for " + name + ", making water instead.");
            resourceStorage.addResource(name, 1);
            return new Drink("water");
        }
        Logger.log("Making " + name);
        return new Drink(name);
    }
}
