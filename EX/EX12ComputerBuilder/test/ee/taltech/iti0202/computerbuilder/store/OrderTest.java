package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.Customer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.mock;

/**
 * The type Order test.
 */
public class OrderTest {

    private Order order;

    /**
     * Test setters and getters.
     */
    @Test
    public void testSettersAndGetters() {
        Customer customer = mock(Customer.class);
        Order order = new Order()
            .withPrice(5d)
            .withType("Type")
            .withUseCase("Use case")
            .withCustomer(customer);
        Assertions.assertEquals(5d, order.getPrice());
        Assertions.assertEquals("Type", order.getType());
        Assertions.assertEquals("Use case", order.getUseCase());
        Assertions.assertEquals(customer, order.getCustomer());
    }
}
