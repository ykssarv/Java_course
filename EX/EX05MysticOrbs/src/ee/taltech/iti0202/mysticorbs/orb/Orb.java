package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {
    protected String creator;
    protected Integer energy;

    /**
     * Orb.
     * @param creator of orb
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    /**
     * Charge
     * @param resource needed
     * @param amount of resources
     */
    public void charge(String resource, int amount) {
        if (resource.toLowerCase().equals("dust") || resource.matches(" +") || amount < 0) {
            return;
        }
        this.energy += resource.length() * amount;
    }
    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Orb by " + this.creator;
    }
}
