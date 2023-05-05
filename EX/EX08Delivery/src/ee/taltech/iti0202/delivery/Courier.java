package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Courier {
    private String name;
    private Location location;
    private Map<String, Packet> packets;
    private Strategy strategy;
    private boolean isMoving;
    private int movedAmount;
    private int needsToMove;

    /**
     * Constructor
     * @param location
     * @param name
     */
    public Courier(Location location, String name) {
        this.location = location;
        this.packets = new HashMap<>();
        this.isMoving = false;
        this.movedAmount = 0;
        this.needsToMove = 0;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    /**
     * Get location
     * @return
     */
    public Optional<Location> getLocation() {
        if (this.isMoving) {
            return Optional.empty();
        }
        return Optional.of(this.location);
    }
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public Strategy getStrategy() {
        return this.strategy;
    }

    /**
     * Is moving
     * @return
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Move
     */
    public void move() {
        this.movedAmount++;
    }

    /**
     * Check if arrived
     */
    public void checkAtLocation() {
        if (this.movedAmount == this.needsToMove) {
            this.isMoving = false;
            this.movedAmount = 0;
        }
    }

    /**
     * Get action and do it
     */
    public void doAction() {
        Action action = strategy.getAction();
        action.getDeposit().forEach(x -> {
            Packet packet = packets.remove(x);
            location.addPacket(packet);
        });
        action.getTake().forEach(x -> location.getPacket(x).ifPresent(value -> packets.put(x, value)));
        isMoving = true;
        this.needsToMove = action.getGoTo().getDistanceTo(location.getName());
        location = action.getGoTo();
    }
}
