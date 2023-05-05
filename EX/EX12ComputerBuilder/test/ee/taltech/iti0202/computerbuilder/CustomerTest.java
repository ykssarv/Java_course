package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.store.Computer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

import static org.mockito.Mockito.mock;

/**
 * The type Customer test.
 */
public class CustomerTest {

    private Customer customer;

    /**
     * Test getters and setters.
     */
    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer("Helena", 5d);
        Assertions.assertEquals("Helena", customer.getName());
        Assertions.assertEquals(5d, customer.getBalance());
        customer.setName("Krissu");
        Assertions.assertEquals("Krissu", customer.getName());
        customer.setBalance(10d);
        Assertions.assertEquals(10d, customer.getBalance());
    }

    /**
     * Test add to list.
     */
    @Test
    public void testAddToList() {
        customer = new Customer("Helena", 5d);
        Computer computer = mock(Computer.class);
        Component component = mock(Component.class);
        customer.addComponent(component);
        customer.addComputer(computer);
        Assertions.assertEquals(Collections.singletonList(computer), customer.getComputers());
        Assertions.assertEquals(Collections.singletonList(component), customer.getComponents());
    }

    /**
     * Test remove money.
     */
    @Test
    public void testRemoveMoney() {
        customer = new Customer("Helena", 10d);
        customer.removeMoney(5d);
        Assertions.assertEquals(5d, customer.getBalance());
    }
}
