package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;

public class WaterContainer {

    private int waterLimit;
    private int waterAmount;

    public WaterContainer(int waterLimit) {
        this.waterLimit = waterLimit;
        this.waterAmount = waterLimit;
        Logger.log("Created a water container.");
    }

    public void takeWater(int amount) {
        if (amount > this.waterAmount) {
            throw new UnableToMakeDrinkException(amount, this.waterAmount);
        }
        Logger.log("Taking " + amount + " from water container.");
        this.waterAmount -= amount;
    }

    public boolean hasEnoughWater(int amount) {
        return this.waterAmount >= amount;
    }

    public void fill() {
        Logger.log("Refilling water container.");
        this.waterAmount = this.waterLimit;
    }
}
