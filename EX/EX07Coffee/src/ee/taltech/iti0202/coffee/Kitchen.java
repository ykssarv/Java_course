package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.machine.BasicCoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Kitchen {

    private final List<BasicCoffeeMachine> machines;

    public Kitchen() {
        machines = new ArrayList<>();
    }

    public void addCoffeeMachine(BasicCoffeeMachine coffeeMachine) {
        Logger.log("Adding a machine to kitchen.");
        machines.add(coffeeMachine);
    }

    public Optional<Drink> makeDrink(String name) {
        Logger.log("Kitchen got an order to make " + name);
        for (BasicCoffeeMachine machine: machines) {
            if (machine.canMakeDrink(name)) {
                Logger.log("Found machine that can make " + name);
                return Optional.of(machine.makeDrink(name));
            }
        }
        Logger.log("Unable to find machine that can make " + name);
        return Optional.empty();
    }
}
