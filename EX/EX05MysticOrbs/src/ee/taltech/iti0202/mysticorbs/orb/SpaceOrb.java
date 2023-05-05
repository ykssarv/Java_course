package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    /**
     * Space orb.
     * @param creator of space orb
     */
    public SpaceOrb(String creator) {
        super(creator);
        energy = 100;
    }
    @Override
    public void charge(String resource, int amount) {

    }

    @Override
    public String toString() {
        return "SpaceOrb by " + creator;
    }

    /**
     * Absorb.
     * @param orb to absorb
     * @return boolean
     */
    public boolean absorb(Orb orb) {
        if (orb.getEnergy() < energy) {
            energy += orb.getEnergy();
            orb.setEnergy(0);
            return true;
        }
        return false;


    }
}
