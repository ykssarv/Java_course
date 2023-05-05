package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Location {
    private String name;
    private Map<String, Packet> packets;
    private Map<String, Integer> distances;

    /**
     * Constructor
     * @param name
     */
    public Location(String name) {
        this.name = name;
        this.packets = new HashMap<>();
        this.distances = new HashMap<>();
    }

    /**
     * Get name
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get distance
     * @param name
     * @return
     */
    public Integer getDistanceTo(String name) {
        if (!distances.containsKey(name)) {
            return Integer.MAX_VALUE;
        }
        return distances.get(name);
    }

    /**
     * Add packet
     * @param packet
     */
    public void addPacket(Packet packet) {
        packets.put(packet.getName(), packet);
    }

    /**
     * Get packet
     * @param name
     * @return
     */
    public Optional<Packet> getPacket(String name) {
        if (!packets.containsKey(name)) {
            return Optional.empty();
        }
        return Optional.of(packets.remove(name));
    }

    /**
     * Add distance
     * @param location
     * @param distance
     */
    public void addDistance(String location, int distance) {
        distances.put(location, distance);
    }
}
