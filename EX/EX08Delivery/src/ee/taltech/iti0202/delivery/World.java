package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class World {
    private Map<String, Location> locations;
    private Map<String, Courier> couriers;

    /**
     * Constructor
     */
    public World() {
        this.locations = new HashMap<>();
        this.couriers = new HashMap<>();
    }

    /**
     * Add location to world
     * @param name
     * @param otherLocations
     * @param distances
     * @return
     */
    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (locations.containsKey(name)) {
            return Optional.empty();
        }
        Set<String> locationSet = new HashSet<>(otherLocations);
        if (!locationSet.containsAll(locations.keySet())) {
            return Optional.empty();
        }
        Location location = new Location(name);
        locations.put(name, location);
        for (int i = 0; i < distances.size(); i++) {
            location.addDistance(otherLocations.get(i), distances.get(i));
            locations.get(otherLocations.get(i)).addDistance(name, distances.get(i));
        }

        return Optional.of(location);
    }

    /**
     * Add courier to world
     * @param name
     * @param to
     * @return
     */
    public Optional<Courier> addCourier(String name, String to) {
        if (couriers.containsKey(name)) {
            return Optional.empty();
        }
        if (!locations.containsKey(to)) {
            return Optional.empty();
        }
        Courier courier = new Courier(locations.get(to), name);
        couriers.put(name, courier);
        return Optional.of(courier);
    }

    /**
     * Give strategy to courier
     * @param name
     * @param strategy
     * @return if successful
     */
    public boolean giveStrategy(String name, Strategy strategy) {
        if (!couriers.containsKey(name)) {
            return false;
        }
        couriers.get(name).setStrategy(strategy);
        return true;
    }

    /**
     * Advanced time
     */
    public void tick() {
        couriers.values().forEach(courier -> {
            if (!courier.isMoving()) {
                courier.doAction();
            }
            courier.move();
            courier.checkAtLocation();
        });
    }
}
