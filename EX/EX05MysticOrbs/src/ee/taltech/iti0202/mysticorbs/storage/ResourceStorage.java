package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources;

    /**
     * Resource storage.
     */
    public ResourceStorage() {
        this.resources = new HashMap<>();
    }

    /**
     * Is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return this.resources.values().stream().noneMatch(x -> x > 0);
    }

    /**
     * Add resource.
     * @param resource to add
     * @param amount of resources
     */
    public void addResource(String resource, int amount) {
        if (resource == null || resource.matches(" *") || amount < 0) {
           return;
        }
        resources.put(resource.toLowerCase(), resources.getOrDefault(resource.toLowerCase(), 0) + amount);
    }

    /**
     * Remove resource
     * @param resource to remove
     * @param amount of resources
     */
    public void removeResource(String resource, int amount) {
        if (resource == null || resource.matches(" *") || amount < 0) {
            return;
        }
        resources.put(resource.toLowerCase(), resources.getOrDefault(resource.toLowerCase(), 0) - amount);
    }

    /**
     * Get amount of resources,
     * @param resource amount
     * @return amount
     */
    public int getResourceAmount(String resource) {
        return this.resources.getOrDefault(resource.toLowerCase(), 0);
    }

    /**
     * Check if has enough resources.
     * @param resource amount
     * @param amount of resources
     * @return boolean
     */
    public boolean hasEnoughResource(String resource, int amount) {
        if (amount < 1) {
            return false;
        }
        return getResourceAmount(resource) >= amount;
    }

    /**
     * Take resource.
     * @param resource to take
     * @param amount of resources to take
     * @return boolean
     */
    public boolean takeResource(String resource, int amount) {
        if (!hasEnoughResource(resource, amount)) {
            return false;
        }
        removeResource(resource, amount);
        return true;
    }


}
