package ee.taltech.iti0202.computerstore.database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database database;

    private Database() {
        database = this;
    }

    /**
     * Get the database instance.
     * @return
     */
    public static Database getInstance() {
        if (database == null) {
            new Database();
        }
        return database;
    }

    /**
     * Save some component to the database.
     * @param component to save.
     * @throws ProductAlreadyExistsException
     */
    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    /**
     * Delete some component.
     * @param id of component.
     * @throws ProductNotFoundException
     */
    public void deleteComponent(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        components.remove(id);
    }

    /**
     * Get price of some component.
     * @param id of component.
     * @return
     * @throws ProductNotFoundException
     */
    public double getPrice(int id) throws ProductNotFoundException {
        if (!components.containsKey(id)) {
            throw new ProductNotFoundException();
        }
        return components.get(id).getPrice();
    }

    /**
     * Increase the amount of some component.
     * @param id of component.
     * @param amount to increase by.
     * @throws ProductNotFoundException
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
     * @param id of component.
     * @param amount to decrease by.
     * @throws OutOfStockException
     * @throws ProductNotFoundException
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
     * @param id
     * @return
     */
    public Component getCopy(int id) {
        return components.get(id);
    }

    public static void main(String[] args) throws ProductAlreadyExistsException {
        Database database = Database.getInstance();
        Component component = new Component(
            "Helena GPU",
            Component.Type.GPU,
            5400.34,
            "Krissu",
            10,
            5
        );
        database.saveComponent(component);
        database.saveToFile("andmed.txt");
        database.loadFromFile("andmed.txt");
        System.out.println(database.getComponents());
    }
}
