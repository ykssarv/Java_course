package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Computer factory.
 */
public class ComputerFactory {

    private Store store;

    /**
     * Instantiates a new Computer factory.
     *
     * @param store the store
     */
    public ComputerFactory(Store store) {
        this.store = store;
    }

    /**
     * Order optional.
     *
     * @param price   the price
     * @param useCase the use case
     * @param type    the type
     * @return the optional
     */
    public Optional<Computer> order(double price, String useCase, String type) {
        Map<Component.Type, Double> weights = this.getWeightsFromUseCase(useCase);
        List<Computer> allComputers = this.generateAllComputers(type);
        return allComputers.stream()
            .filter(Computer::hasEnoughPower)
            .filter(computer -> computer.getPrice() <= price)
            .max(Comparator.comparingDouble(computer -> computer.getGoodness(weights)));
    }

    /**
     * Generate all computers list.
     *
     * @param type the type
     * @return the list
     */
    public List<Computer> generateAllComputers(String type) {
        List<Computer> computers = Collections.singletonList(new Computer());
        computers = addComponent(computers, Component.Type.CPU);
        computers = addComponent(computers, Component.Type.GPU);
        computers = addComponent(computers, Component.Type.RAM);
        computers = addComponent(computers, Component.Type.MOTHERBOARD);
        computers = addComponent(computers, Arrays.asList(Component.Type.SSD, Component.Type.HDD));
        computers = addComponent(computers, Component.Type.PSU);
        computers = addComponent(computers, Component.Type.CASE);
        if (type.equals("PC")) {
            return computers;
        }
        computers = addComponent(computers, Component.Type.KEYBOARD);
        computers = addComponent(computers, Component.Type.TOUCHPAD);
        computers = addComponent(computers, Component.Type.SCREEN);
        computers = addComponent(computers, Component.Type.BATTERY);
        return computers;
    }

    /**
     * Add component list.
     *
     * @param previous the previous
     * @param type     the type
     * @return the list
     */
    public List<Computer> addComponent(List<Computer> previous, Component.Type type) {
        return addComponent(previous, Collections.singletonList(type));
    }

    /**
     * Add component list.
     *
     * @param previous the previous
     * @param types    the types
     * @return the list
     */
    public List<Computer> addComponent(List<Computer> previous, List<Component.Type> types) {
        List<Computer> next = new ArrayList<>();
        List<Component> components = new ArrayList<>();
        types.forEach(type -> components.addAll(store.getAvailableComponentsByType(type)));

        previous.forEach(computer -> components.forEach(component -> {
            Computer copy = computer.copy(new Computer());
            copy.setComponent(component.getType(), component);
            next.add(copy);
        }));
        return next;
    }

    /**
     * Gets weights from use case.
     *
     * @param useCase the use case
     * @return the weights from use case
     */
    public Map<Component.Type, Double> getWeightsFromUseCase(String useCase) {
        Map<Component.Type, Double> map = new HashMap<>();
        if (useCase.equals("")) {
            return map;
        }
        if (useCase.equals("gaming")) {
            map.put(Component.Type.GPU, 1.3);
        }
        if (useCase.equals("workstation")) {
            map.put(Component.Type.CPU, 1.3);
        }
        return map;
    }
}
