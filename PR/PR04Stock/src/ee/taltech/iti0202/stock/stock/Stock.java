package ee.taltech.iti0202.stock.stock;
import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The stock class.
 * <p>
 * Each stock has a name, list of products that are currently stored in it
 * and the maximum amount of products that stock can store.
 * <p>
 * If adding a product to the stock is not possible, due to exceeding the maximum limit of products
 * OR stock already contains a product, a StockException must be thrown,
 * otherwise product must be added to the stock.
 * <p>
 * When getting (not removing) a product from our stock,
 * it is important to find the product with the lowest price first.
 */

public class Stock {
    private String name;
    private int maxCapacity;
    private List<Product> products;
    /**
     * Create a new stock with the given name and the max capacity for the products.
     *
     * @param name the name of the stock.
     * @param maxCapacity max amount of products allowed in the stock.
     */
    public Stock(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.products = new ArrayList<>();
    }

    /**
     * Add a product to the stock, if stock does not contain the product and is not full yet.
     * <p>
     * Check in following order:
     * If stock already contains a product, throw an StockException with a STOCK_ALREADY_CONTAINS_PRODUCT reason.
     * If stock is full, throw a StockException with a STOCK_IS_FULL reason.
     *
     * @param product to be added
     * @throws StockException STOCK_ALREADY_CONTAINS_PRODUCT, STOCK_IS_FULL
     */

    public void addProduct(Product product) throws StockException {
        if(this.products.contains(product)){
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        }
        if(this.products.size() == this.maxCapacity){
            throw new StockException(StockException.Reason.STOCK_IS_FULL);
        }
        this.products.add(product);
    }

    /**
     * Get a product from a stock by name with the lowest price.
     *
     * If there are several products with the same name and price,
     * returns the product with the lowest id.
     *
     * @param name the product's name
     * @return Optional
     */
    public Optional<Product> getProduct(String name) {
        return this.products.stream()
            .filter(x -> x.getName().equals(name))
            .sorted(Comparator.comparing(Product::getId))
            .min(Comparator.comparing(Product::getPrice));
    }

    /**
     * Remove and return a product from a stock,
     * if stock has a given product.
     *
     * Use getProduct() method to get the product.
     *
     * If there is nothing to remove, return Optional.empty()
     *
     * @param name Name of the product to be removed
     * @return Optional
     */

    public Optional<Product> removeProduct(String name) {
        Optional<Product> product = getProduct(name);
        product.ifPresent(value -> products.remove(value));
        return product;
    }

    /**
     * Get a list of current products in the stock.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * Get a list of current products in the stock filtered by name.
     *
     * Order the products by price ascending. In case of the same price, by id ascending.
     *
     * @param name Name to be filtered.
     * @return List
     */
    public List<Product> getProducts(String name) {
        return this.products.stream()
            .filter(x -> x.getName().equals(name))
            .sorted(Comparator.comparing(Product::getId))
            .sorted(Comparator.comparing(Product::getPrice))
            .collect(Collectors.toList());
    }

    /**
     * Get total price of all the products.
     *
     * @return Total price.
     */
    public int getTotalPrice() {
        return this.products.stream().mapToInt(Product::getPrice).sum();
    }

    /**
     * Check if stock is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return this.products.size() == this.maxCapacity;
    }

}
