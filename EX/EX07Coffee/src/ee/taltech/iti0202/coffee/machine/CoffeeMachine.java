package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;

public interface CoffeeMachine {
    void startMachine();
    boolean canMakeDrink(String name);
    Drink makeDrink(String name);
}
