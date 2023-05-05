package ee.taltech.iti0202.computerbuilder.database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The type Database.
 */
public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database database;

    private Database() {
        database = this;
    }

    /**
     * Get the database instance.
     *
     * @return instance
     */
    public static Database getInstance() {
        if (database == null) {
            new Database();
        }
        return database;
    }

    /**
     * Save some component to the database.
     *
     * @param component to save.
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    /**
     * Delete some component.
     *
     * @param id of component.
     * @throws ProductNotFoundException the product not found exception
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        components.remove(id);
    }

    /**
     * Get price of some component.
     *
     * @param id of component.
     * @return price
     * @throws ProductNotFoundException the product not found exception
     */
    public double getPrice(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        return components.get(id).getPrice();
    }

    /**
     * Increase the amount of some component.
     *
     * @param id     of component.
     * @param amount to increase by.
     * @throws ProductNotFoundException the product not found exception
     */
    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        components.get(id).increaseStock(amount);
    }

    /**
     * Decrease the amount of some component.
     *
     * @param id     of component.
     * @param amount to decrease by.
     * @throws OutOfStockException      the out of stock exception
     * @throws ProductNotFoundException the product not found exception
     */
    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        if (components.get(id).getAmount() < amount) {
            throw new OutOfStockException();
        }
        components.get(id).decreaseStock(amount);
    }

    /**
     * Gets components.
     *
     * @return the components
     */
    public Map<Integer, Component> getComponents() {
        return components;
    }

    /**
     * Reset the database.
     */
    public void resetEntireDatabase() {
        components.clear();
        Component.reset();
    }

    /**
     * Save database to file.
     *
     * @param location to save to.
     */
    public void saveToFile(String location) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(database);
        try {
            FileWriter myWriter = new FileWriter(location);
            myWriter.write(json);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load database from file.
     *
     * @param location to load from.
     */
    public void loadFromFile(String location) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(location);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\\Z");
            String json = scanner.next();
            this.resetEntireDatabase();
            database = gson.fromJson(json, Database.class);
            database.components.forEach((id, component) -> {
                component.setId(id);
            });
            Component.setComponentsMadeAmount(
                database.components.keySet().stream()
                    .max(Comparator.naturalOrder())
                    .orElse(0)
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Should return a copy of the component,
     * but because the tests require an unsecure implementation,
     * it returns the component itself.
     *
     * @param id the id
     * @return copy
     */
    public Component getCopy(int id) {
        return new Component(components.get(id));
    }
}
