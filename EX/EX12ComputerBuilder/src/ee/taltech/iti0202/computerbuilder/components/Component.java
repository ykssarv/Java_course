package ee.taltech.iti0202.computerbuilder.components;

/**
 * The type Component.
 */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets manufacturer.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets manufacturer.
     *
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets performance points.
     *
     * @return the performance points
     */
    public int getPerformancePoints() {
        return performancePoints;
    }

    /**
     * Sets performance points.
     *
     * @param performancePoints the performance points
     */
    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    /**
     * Gets power consumption.
     *
     * @return the power consumption
     */
    public int getPowerConsumption() {
        return powerConsumption;
    }

    /**
     * Sets power consumption.
     *
     * @param powerConsumption the power consumption
     */
    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Cpu type.
         */
        CPU,
        /**
         * Gpu type.
         */
        GPU,
        /**
         * Ram type.
         */
        RAM,
        /**
         * Motherboard type.
         */
        MOTHERBOARD,
        /**
         * Hdd type.
         */
        HDD,
        /**
         * Ssd type.
         */
        SSD,
        /**
         * Psu type.
         */
        PSU,
        /**
         * Keyboard type.
         */
        KEYBOARD,
        /**
         * Touchpad type.
         */
        TOUCHPAD,
        /**
         * Screen type.
         */
        SCREEN,
        /**
         * Battery type.
         */
        BATTERY,
        /**
         * Case type.
         */
        CASE
    }

    /**
     * Reset the id counter.
     */
    public static void reset() {
        idCounter = 0;
    }

    /**
     * Default component constructor.
     *
     * @param name              the name
     * @param type              the type
     * @param price             the price
     * @param manufacturer      the manufacturer
     * @param performancePoints the performance points
     * @param powerConsumption  the power consumption
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
     *
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
     *
     * @param amount the amount
     */
    public void increaseStock(int amount) {
        this.amount += amount;
    }

    /**
     * Decrease the amount of this component.
     *
     * @param amount the amount
     */
    public void decreaseStock(int amount) {
        this.amount -= amount;
    }

    /**
     * Sets components made amount.
     *
     * @param amount the amount
     */
    public static void setComponentsMadeAmount(int amount) {
        idCounter = amount;
    }
}
