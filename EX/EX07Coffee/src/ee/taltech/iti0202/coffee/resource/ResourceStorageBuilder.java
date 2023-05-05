package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Logger;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorageBuilder {
    private final Map<String, Integer> resources;

    public enum ResourceStorageType {
        NORMAL, INFINITE, CAPSULE
    }

    public ResourceStorageBuilder() {
        resources = new HashMap<>();
    }

    public ResourceStorageBuilder withResource(String resource, Integer amount) {
        resources.put(resource, resources.getOrDefault(resource, 0) + amount);
        return this;
    }

    public ResourceStorage build() {
        return build(ResourceStorageType.NORMAL);
    }

    public ResourceStorage build(ResourceStorageType type) {
        if (type == ResourceStorageType.INFINITE) {
            Logger.log("Creating an infinite resource storage.");
            return new InfiniteResourceStorage(resources);
        }
        if (type == ResourceStorageType.CAPSULE) {
            Logger.log("Creating a capsule resource storage.");
            return new CapsuleStorage(resources);
        }
        Logger.log("Creating a basic resource storage.");
        return new BasicResourceStorage(resources);
    }
}
