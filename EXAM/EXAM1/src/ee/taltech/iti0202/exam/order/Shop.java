package ee.taltech.iti0202.exam.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Shop.
 */
public class Shop {
    private List<Product> products;
    private HashMap<Integer, List<Product>> orders;
    private static int counter = 0;

    /**
     * Instantiates a new Shop.
     */
    public Shop() {
        products = new ArrayList<>();
        orders = new HashMap<>();

    }

    /**
     * Add product boolean.
     *
     * @param product the product
     * @return the boolean
     */
    public boolean addProduct(Product product) {
        if (products.contains(product)) {
            return false;
        }
        products.add(product);
        return true;

    }

    /**
     * Create new order int.
     *
     * @return the int
     */
    public int createNewOrder() {
        counter += 1;
        orders.put(counter, new ArrayList<>());
        return counter;

    }

    /**
     * Add product to order boolean.
     *
     * @param orderNumber the order number
     * @param itemName    the item name
     * @return the boolean
     */
    public boolean addProductToOrder(int orderNumber, String itemName) {
        if (!orders.containsKey(orderNumber)) {
          return false;
        }
        Product chosen = null;
        for (Product product: products) {
            if (!product.isAvailable()) {
                continue;
            }
            if (!product.getName().equals(itemName)) {
                continue;
            }
            if (chosen == null || product.getPrice() < chosen.getPrice()) {
                chosen = product;
            }
        }
        if (chosen == null) {
            return false;
        }
        chosen.setAvailable((false));
        orders.get(orderNumber).add(chosen);
        return true;

    }

    /**
     * Gets order sum.
     *
     * @param orderNumber the order number
     * @return the order sum
     */
    public int getOrderSum(int orderNumber) {
        if (!orders.containsKey(orderNumber)) {
            return -1;
        }
        return orders.get(orderNumber).stream().mapToInt(Product::getPrice).sum();

    }

    /**
     * Cancel order boolean.
     *
     * @param orderNumber the order number
     * @return the boolean
     */
    public boolean cancelOrder(int orderNumber) {
        return true;
    }

    /**
     * Gets available products.
     *
     * @return the available products
     */
    public List<Product> getAvailableProducts() {
        return new ArrayList<>();
    }

}
