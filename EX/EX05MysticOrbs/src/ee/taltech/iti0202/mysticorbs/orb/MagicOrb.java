package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {
    /**
     * Magic orb.
     * @param creator
     */
    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        super.charge(resource, amount * 2);
    }

    @Override
    public String toString() {
        return "MagicOrb by " + creator;
    }
}
