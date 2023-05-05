package ee.taltech.iti0202.computerstore.components;
public class Component {
    private int id;
    private String name;
    private Type type;
    private double price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;
    private static int idCounter = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public enum Type {
        CPU, GPU, RAM, MOTHERBOARD, HDD, SSD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN
    }

    /**
     * Reset the id counter.
     */
    public static void reset() {
        idCounter = 0;
    }

    /**
     * Default component constructor.
     * @param name
     * @param type
     * @param price
     * @param manufacturer
     * @param performancePoints
     * @param powerConsumption
     */
    public Component(
        String name,
        Type type,
        double price,
        String manufacturer,
        int performancePoints,
        int powerConsumption
    ) {
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
    }

    /**
     * Constructor for copying a component.
     * @param component to copy.
     */
    public Component(Component component) {
        this.id = component.getId();
        this.name = component.getName();
        this.type = component.getType();
        this.price = component.getPrice();
        this.manufacturer = component.getManufacturer();
        this.performancePoints = component.getPerformancePoints();
        this.powerConsumption = component.getPowerConsumption();
    }

    /**
     * Increase the amount of this component.
     * @param amount
     */
    public void increaseStock(int amount) {
        this.amount += amount;
    }

    /**
     * Decrease the amount of this component.
     * @param amount
     */
    public void decreaseStock(int amount) {
        this.amount -= amount;
    }

    public static void setComponentsMadeAmount(int amount) {
        idCounter = amount;
    }
}
