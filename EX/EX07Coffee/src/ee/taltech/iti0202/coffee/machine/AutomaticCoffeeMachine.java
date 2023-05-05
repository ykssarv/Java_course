package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Recipe;
import ee.taltech.iti0202.coffee.WaterContainer;
import ee.taltech.iti0202.coffee.resource.ResourceStorage;

import java.util.Map;

public class AutomaticCoffeeMachine extends BasicCoffeeMachine implements CoffeeMachine {
    public AutomaticCoffeeMachine(
        Integer waterLimit,
        Integer garbageLimit,
        WaterContainer waterContainer,
        Map<String, Recipe> recipes,
        ResourceStorage resourceStorage
    ) {
        super(waterLimit, garbageLimit, waterContainer, recipes, resourceStorage);
    }
}
