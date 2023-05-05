package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Store test.
 */
public class StoreTest {

    private Store store;

    /**
     * Test setters and getters.
     */
    @Test
    public void testSettersAndGetters() {
        store = new Store("Oomipood", 10d, 1.1);
        Assertions.assertEquals("Oomipood", store.getName());
        Assertions.assertEquals(10d, store.getBalance());
        Assertions.assertEquals(1.1, store.getProfitMargin());
        store.setName("Pood");
        store.setBalance(100d);
        store.setProfitMargin(1.5);
        Assertions.assertEquals("Pood", store.getName());
        Assertions.assertEquals(100d, store.getBalance());
        Assertions.assertEquals(1.5, store.getProfitMargin());
    }

    /**
     * Test get database.
     */
    @Test
    public void testGetDatabase() {
        Database database = Database.getInstance();
        Assertions.assertNotNull(database);
    }

    /**
     * Test purchase component.
     *
     * @throws ProductNotFoundException      the product not found exception
     * @throws NotEnoughMoneyException       the not enough money exception
     * @throws OutOfStockException           the out of stock exception
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testPurchaseComponent() throws
        ProductNotFoundException,
        NotEnoughMoneyException,
        OutOfStockException,
        ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);
        Customer customer = mock(Customer.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        database.saveComponent(component);

        store.purchaseComponent(5, customer);
    }

    /**
     * Test get available components.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetAvailableComponents() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        database.saveComponent(component);

        Assertions.assertEquals(Collections.singletonList(component), store.getAvailableComponents());
    }

    /**
     * Test get components sorted by amount.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetComponentsSortedByAmount() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        database.saveComponent(component);

        Assertions.assertEquals(Collections.singletonList(component), store.getComponentsSortedByAmount());
    }

    /**
     * Test get components sorted by name.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetComponentsSortedByName() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        when(component.getName()).thenReturn("Midagi");
        database.saveComponent(component);

        Assertions.assertEquals(Collections.singletonList(component), store.getComponentsSortedByName());
    }

    /**
     * Test get components sorted by price.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetComponentsSortedByPrice() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        when(component.getPrice()).thenReturn(7d);
        database.saveComponent(component);

        Assertions.assertEquals(Collections.singletonList(component), store.getComponentsSortedByPrice());
    }

    /**
     * Test get available components by type.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetAvailableComponentsByType() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        when(component.getType()).thenReturn(Component.Type.CPU);
        database.saveComponent(component);

        Assertions.assertEquals(
            Collections.singletonList(component),
            store.getAvailableComponentsByType(Component.Type.CPU)
        );
    }

    /**
     * Test filter by type.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testFilterByType() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(0);
        when(component.getType()).thenReturn(Component.Type.CPU);
        database.saveComponent(component);

        Assertions.assertEquals(Collections.singletonList(component), store.filterByType(Component.Type.CPU));
    }

    /**
     * Test get inventory value.
     *
     * @throws ProductAlreadyExistsException the product already exists exception
     */
    @Test
    public void testGetInventoryValue() throws ProductAlreadyExistsException {
        store = new Store("Oomipood", 10d, 1.1);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Component component = mock(Component.class);

        when(component.getId()).thenReturn(5);
        when(component.getAmount()).thenReturn(1);
        when(component.getPrice()).thenReturn(10d);
        database.saveComponent(component);

        Assertions.assertEquals(11d, store.getInventoryValue());
    }

    /**
     * Test order.
     */
    @Test
    public void testOrder() {
        store = new Store("Oomipood", 10d, 1.5);
        Database database = Database.getInstance();
        database.resetEntireDatabase();
        Customer customer = new Customer("Helena", 10d);
        Order order = new Order()
            .withPrice(7.5d)
            .withType("Type")
            .withUseCase("Use case")
            .withCustomer(customer);
        ComputerFactory factory = mock(ComputerFactory.class);
        store.setFactory(factory);
        Computer computer = mock(Computer.class);
        when(factory.order(5d, "Use case", "Type")).thenReturn(Optional.of(computer));

        Optional<Computer> maybeComputer = store.order(order);
        Assertions.assertTrue(maybeComputer.isPresent());
        Assertions.assertEquals(computer, maybeComputer.get());
        Assertions.assertEquals(Collections.singletonList(computer), customer.getComputers());
    }
}
