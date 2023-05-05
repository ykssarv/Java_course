package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Logger;
import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;

import java.util.Map;

public class InfiniteResourceStorage extends BasicResourceStorage implements ResourceStorage {
    public InfiniteResourceStorage(Map<String, Integer> resources) {
        super(resources);
    }

    @Override
    public int amountOf(String resource) {
        if ("water".equals(resource)) {
            Logger.log("Checking water amount in storage.");
            return super.amountOf(resource);
        }
        Logger.log("Infinite storage always has a lot of " + resource);
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean hasEnough(Map<String, Integer> resources) {
        Logger.log("Checking if storage has enough resources.");
        return resources.getOrDefault("water", 0) <= this.resources.getOrDefault("water", 0);
    }

    @Override
    public void take(Map<String, Integer> resources) {
        int have = this.resources.getOrDefault("water", 0);
        int asked = resources.getOrDefault("water", 0);

        if (have < asked) {
            throw new UnableToMakeDrinkException("water", asked, have);
        }

        Logger.log("Taking resources from storage.");
        this.resources.put("water", have - asked);
    }
}
