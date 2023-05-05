package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import ee.taltech.iti0202.coffee.resource.ResourceStorage;

import java.util.Map;

public class BasicCoffeeMachine implements CoffeeMachine {

    private int waterLimit;
    protected int garbageLimit;
    protected int garbageAmount;
    private WaterContainer waterContainer;
    protected final Map<String, Recipe> recipes;
    protected final ResourceStorage resourceStorage;

    public BasicCoffeeMachine(
        Integer waterLimit,
        Integer garbageLimit,
        WaterContainer waterContainer,
        Map<String, Recipe> recipes,
        ResourceStorage resourceStorage
    ) {
        this.waterLimit = waterLimit;
        this.garbageLimit = garbageLimit;
        this.garbageAmount = 0;
        this.waterContainer = waterContainer;
        this.recipes = recipes;
        this.resourceStorage = resourceStorage;
    }

    public void startMachine() {
        Logger.log("Starting a coffee machine.");
        int currentWaterAmount = resourceStorage.amountOf("water");
        int waterNeeded = waterLimit - currentWaterAmount;
        if (!waterContainer.hasEnoughWater(waterNeeded)) {
            waterContainer.fill();
        }
        waterContainer.takeWater(waterNeeded);
        resourceStorage.addResource("water", waterNeeded);
    }

    public boolean canMakeDrink(String name) {
        if (garbageIsFull()) {
            Logger.log("Can't make " + name + ", because garbage container is full.");
            return false;
        }
        if (isUnknownRecipe(name)) {
            Logger.log("Can't make " + name + ", because coffee machine doesn't know recipe.");
            return false;
        }
        if (notEnoughResources(name)) {
            Logger.log("Can't make " + name + ", because coffee machine doesn't have the required resources.");
            return false;
        }
        Logger.log("Found machine that can make " + name);
        return true;
    }

    public boolean garbageIsFull() {
        return garbageLimit == garbageAmount;
    }

    public boolean isUnknownRecipe(String name) {
        return !recipes.containsKey(name);
    }

    public boolean notEnoughResources(String name) {
        return !resourceStorage.hasEnough(recipes.get(name));
    }

    public Drink makeDrink(String name) {
        if (!recipes.containsKey(name)) {
            throw new UnableToMakeDrinkException(name);
        }
        Logger.log("Making " + name);
        garbageAmount++;
        resourceStorage.take(recipes.get(name));
        return new Drink(name);
    }
}
