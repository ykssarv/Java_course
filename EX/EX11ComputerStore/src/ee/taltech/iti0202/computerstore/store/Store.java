package ee.taltech.iti0202.computerstore.store;
import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private String name;
    private double balance;
    private double profitMargin;

    /**
     * Store constructor.
     * @param name
     * @param balance
     * @param profitMargin
     */
    public Store(String name, double balance, double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.balance = balance;
        this.profitMargin = profitMargin;
    }

    /**
     * Purchase some component.
     * @param id
     * @param customer
     * @return
     * @throws OutOfStockException
     * @throws ProductNotFoundException
     * @throws NotEnoughMoneyException
     */
    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
        ProductNotFoundException,
        NotEnoughMoneyException {
        double price = Database.getInstance().getPrice(id) * this.profitMargin;
        if (customer.getBalance() < price) {
            throw new NotEnoughMoneyException();
        }
        Database.getInstance().decreaseComponentStock(id, 1);
        balance += price;
        customer.removeMoney(price);
        Component component = Database.getInstance().getCopy(id);
        customer.addComponent(component);
        return component;
    }

    public List<Component> getAvailableComponents() {
        return Database.getInstance().getComponents().values().stream()
            .filter(x -> x.getAmount() > 0)
            .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getAmount))
            .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getName))
            .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getPrice))
            .collect(Collectors.toList());
    }

    /**
     * Filter components by type.
     * @param type
     * @return
     */
    public List<Component> filterByType(Component.Type type) {
        return Database.getInstance().getComponents().values().stream()
            .filter(x -> x.getType() == type)
            .collect(Collectors.toList());
    }

    public double getInventoryValue() {
        return Database.getInstance().getComponents().values().stream()
            .mapToDouble(x -> x.getPrice() * x.getAmount())
            .sum() * profitMargin;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfitMargin() {
        return this.profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}
