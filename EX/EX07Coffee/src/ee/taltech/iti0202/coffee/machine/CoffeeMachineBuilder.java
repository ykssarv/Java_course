package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.resource.ResourceStorageBuilder;
import ee.taltech.iti0202.coffee.WaterContainer;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachineBuilder {

    private int garbageLimit = -1;
    private int waterLimit = 10;
    private WaterContainer waterContainer;
    private final Map<String, Recipe> recipes;
    private final ResourceStorageBuilder resourceStorageBuilder;

    public enum CoffeeMachineType {
        NORMAL, AUTOMATIC, CAPSULE
    }

    public CoffeeMachineBuilder() {
        recipes = new HashMap<>();
        resourceStorageBuilder = new ResourceStorageBuilder();
    }

    public CoffeeMachineBuilder withGarbageLimit(int limit) {
        this.garbageLimit = limit;
        return this;
    }

    public CoffeeMachineBuilder withWaterLimit(int limit) {
        this.waterLimit = limit;
        return this;
    }

    public CoffeeMachineBuilder withWaterContainer(WaterContainer waterContainer) {
        this.waterContainer = waterContainer;
        return this;
    }

    public CoffeeMachineBuilder withRecipe(String name, Recipe recipe) {
        this.recipes.put(name, recipe);
        return this;
    }

    public CoffeeMachineBuilder withResource(String name, Integer amount) {
        resourceStorageBuilder.withResource(name, amount);
        return this;
    }

    public CoffeeMachine build() {
        return build(CoffeeMachineType.NORMAL);
    }

    public CoffeeMachine build(CoffeeMachineType type) {
        if (type == CoffeeMachineType.AUTOMATIC) {
            Logger.log("Making an automatic coffee machine.");
            return new AutomaticCoffeeMachine(waterLimit, garbageLimit > -1 ? garbageLimit : 5, waterContainer, recipes, resourceStorageBuilder.build(ResourceStorageBuilder.ResourceStorageType.INFINITE));
        }
        if (type == CoffeeMachineType.CAPSULE) {
            Logger.log("Making a capsule coffee machine.");
            return new CapsuleCoffeeMachine(waterLimit, garbageLimit > -1 ? garbageLimit : 10, waterContainer, recipes, resourceStorageBuilder.build(ResourceStorageBuilder.ResourceStorageType.CAPSULE));
        }
        Logger.log("Making a normal coffee machine.");
        return new BasicCoffeeMachine(waterLimit, garbageLimit > -1 ? garbageLimit : 5, waterContainer, recipes, resourceStorageBuilder.build());
    }
}
