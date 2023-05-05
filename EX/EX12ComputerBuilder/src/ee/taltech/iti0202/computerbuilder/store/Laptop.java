package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.util.List;
import java.util.Map;

/**
 * The type Laptop.
 */
public class Laptop extends Computer {
    private Component keyboard;
    private Component touchpad;
    private Component screen;
    private Component battery;

    /**
     * Instantiates a new Laptop.
     */
    public Laptop() { }

    /**
     * Copy laptop.
     *
     * @param other the other
     * @return the laptop
     */
    public Laptop copy(Laptop other) {
        other = (Laptop) super.copy(other);
        other.setKeyboard(getKeyboard());
        other.setTouchpad(getTouchpad());
        other.setScreen(getScreen());
        other.setBattery(getBattery());
        return other;
    }

    public void setComponent(Component.Type type, Component component) {
        super.setComponent(type, component);
        if (type == Component.Type.KEYBOARD) {
            this.setKeyboard(component);
        } else if (type == Component.Type.TOUCHPAD) {
            this.setTouchpad(component);
        } else if (type == Component.Type.SCREEN) {
            this.setScreen(component);
        } else if (type == Component.Type.BATTERY) {
            this.setBattery(component);
        }
    }

    /**
     * Sets keyboard.
     *
     * @param keyboard the keyboard
     */
    public void setKeyboard(Component keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Sets touchpad.
     *
     * @param touchpad the touchpad
     */
    public void setTouchpad(Component touchpad) {
        this.touchpad = touchpad;
    }

    /**
     * Sets screen.
     *
     * @param screen the screen
     */
    public void setScreen(Component screen) {
        this.screen = screen;
    }

    /**
     * Sets battery.
     *
     * @param battery the battery
     */
    public void setBattery(Component battery) {
        this.battery = battery;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public Component getKeyboard() {
        return keyboard;
    }

    /**
     * Gets touchpad.
     *
     * @return the touchpad
     */
    public Component getTouchpad() {
        return touchpad;
    }

    /**
     * Gets screen.
     *
     * @return the screen
     */
    public Component getScreen() {
        return screen;
    }

    /**
     * Gets battery.
     *
     * @return the battery
     */
    public Component getBattery() {
        return battery;
    }

    protected int getPowerConsumption() {
        return super.getPowerConsumption()
            + keyboard.getPowerConsumption()
            + touchpad.getPowerConsumption()
            + screen.getPowerConsumption()
            + battery.getPowerConsumption();
    }

    /**
     * Get goodness
     * @param weights the weights
     * @return goodness
     */
    public double getGoodness(Map<Component.Type, Double> weights) {
        return super.getGoodness(weights)
            + keyboard.getPerformancePoints() * weights.getOrDefault(Component.Type.KEYBOARD, 1.0)
            + touchpad.getPerformancePoints() * weights.getOrDefault(Component.Type.TOUCHPAD, 1.0)
            + screen.getPerformancePoints() * weights.getOrDefault(Component.Type.SCREEN, 1.0);
    }

    public double getPrice() {
        return super.getPrice()
            + keyboard.getPrice()
            + touchpad.getPrice()
            + screen.getPrice()
            + battery.getPrice();
    }

    /**
     * Get component id-ss
     * @return component id-s
     */
    public List<Integer> getComponentIds() {
        List<Integer> ids = super.getComponentIds();
        ids.add(keyboard.getId());
        ids.add(touchpad.getId());
        ids.add(screen.getId());
        ids.add(battery.getId());
        return ids;
    }
}
