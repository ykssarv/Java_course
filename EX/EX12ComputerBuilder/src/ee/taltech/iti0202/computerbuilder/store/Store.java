package ee.taltech.iti0202.computerbuilder.store;
import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Store.
 */
public class Store {

    private String name;
    private double balance;
    private double profitMargin;
    private ComputerFactory factory;

    /**
     * Store constructor.
     *
     * @param name         the name
     * @param balance      the balance
     * @param profitMargin the profit margin
     */
    public Store(String name, double balance, double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.balance = balance;
        this.profitMargin = profitMargin;
        this.factory = new ComputerFactory(this);
    }

    /**
     * Purchase some component.
     *
     * @param id       the id
     * @param customer the customer
     * @return component
     * @throws OutOfStockException      the out of stock exception
     * @throws ProductNotFoundException the product not found exception
     * @throws NotEnoughMoneyException  the not enough money exception
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

    /**
     * Gets available components.
     *
     * @return the available components
     */
    public List<Component> getAvailableComponents() {
        return Database.getInstance().getComponents().values().stream()
            .filter(x -> x.getAmount() > 0)
            .collect(Collectors.toList());
    }

    /**
     * Gets components sorted by amount.
     *
     * @return the components sorted by amount
     */
    public List<Component> getComponentsSortedByAmount() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getAmount))
            .collect(Collectors.toList());
    }

    /**
     * Gets components sorted by name.
     *
     * @return the components sorted by name
     */
    public List<Component> getComponentsSortedByName() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getName))
            .collect(Collectors.toList());
    }

    /**
     * Gets components sorted by price.
     *
     * @return the components sorted by price
     */
    public List<Component> getComponentsSortedByPrice() {
        return Database.getInstance().getComponents().values().stream()
            .sorted(Comparator.comparing(Component::getPrice))
            .collect(Collectors.toList());
    }

    /**
     * Gets available components by type.
     *
     * @param type the type
     * @return the available components by type
     */
    public List<Component> getAvailableComponentsByType(Component.Type type) {
        return getAvailableComponents().stream()
            .filter(x -> x.getType() == type)
            .collect(Collectors.toList());
    }

    /**
     * Filter components by type.
     *
     * @param type the type
     * @return list
     */
    public List<Component> filterByType(Component.Type type) {
        return Database.getInstance().getComponents().values().stream()
            .filter(x -> x.getType() == type)
            .collect(Collectors.toList());
    }

    /**
     * Gets inventory value.
     *
     * @return the inventory value
     */
    public double getInventoryValue() {
        return Database.getInstance().getComponents().values().stream()
            .mapToDouble(x -> x.getPrice() * x.getAmount())
            .sum() * profitMargin;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets profit margin.
     *
     * @return the profit margin
     */
    public double getProfitMargin() {
        return this.profitMargin;
    }

    /**
     * Sets profit margin.
     *
     * @param profitMargin the profit margin
     */
    public void setProfitMargin(double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }

    /**
     * Order optional.
     *
     * @param order the order
     * @return the optional
     */
    public Optional<Computer> order(Order order) {
        Optional<Computer> computer = factory.order(
            order.getPrice() / profitMargin,
            order.getUseCase(),
            order.getType()
        );
        if (computer.isEmpty()) {
            return computer;
        }
        purchaseComputer(computer.get(), order.getCustomer());
        return computer;
    }

    private void purchaseComputer(Computer computer, Customer customer) {
        List<Integer> componentIds = computer.getComponentIds();
        componentIds.forEach(id -> {
            try {
                purchaseComponent(id, customer);
            } catch (OutOfStockException | ProductNotFoundException | NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        });
        customer.addComputer(computer);
    }

    /**
     * Sets factory.
     *
     * @param factory the factory
     */
    public void setFactory(ComputerFactory factory) {
        this.factory = factory;
    }
}
