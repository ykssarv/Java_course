package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private List<String> deposit;
    private List<String> take;
    private Location goTo;

    /**
     * Action constructor.
     * @param goTo
     */
    public Action(Location goTo) {
        this.goTo = goTo;
        this.deposit = new ArrayList<>();
        this.take = new ArrayList<>();
    }

    public List<String> getDeposit() {
        return deposit;
    }

    public List<String> getTake() {
        return take;
    }

    public Location getGoTo() {
        return goTo;
    }

    /**
     * Add to deposit.
     * @param packetName
     */
    public void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    void addTake(String packetName) {
        take.add(packetName);
    }
}
