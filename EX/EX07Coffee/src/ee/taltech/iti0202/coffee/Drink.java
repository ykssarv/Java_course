package ee.taltech.iti0202.coffee;

public class Drink {

    private final String name;

    public Drink(String name) {
        Logger.log("Creating " + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
