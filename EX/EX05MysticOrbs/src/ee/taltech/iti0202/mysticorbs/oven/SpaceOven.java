package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    public static final int STAR_FRAMGENT = 15;
    public static final int STAR_FRAGMENT = 15;
    public static final int CLAY = 25;
    public static final int SILVER_NEEDED = 40;
    private int timesFixed;
    private int orbsSinceFix;

    /**
     * Space oven
     * @param name of oven
     * @param resourceStorage of ovens
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        timesFixed = 0;
        orbsSinceFix = 0;
    }
    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()
            && resourceStorage.hasEnoughResource("meteorite stone", 1)
            && resourceStorage.hasEnoughResource("star fragment", STAR_FRAMGENT)) {
            resourceStorage.takeResource("meteorite stone", 1);
            resourceStorage.takeResource("star fragment", STAR_FRAGMENT);
            SpaceOrb orb = new SpaceOrb(this.name);
            createdOrbsAmount += 1;
            orbsSinceFix += 1;
            return Optional.of(orb);
        }
        if (resourceStorage.hasEnoughResource("silver", 1)
            && resourceStorage.hasEnoughResource("pearl", 1)) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            Orb orb = new Orb(this.name);
            orb.charge("silver", 1);
            orb.charge("pearl", 1);
            createdOrbsAmount += 1;
            orbsSinceFix += 1;
            return Optional.of(orb);
        }
        return Optional.empty();
    }
    @Override
    public boolean isBroken() {
        return timesFixed < 5 && orbsSinceFix >= CLAY;
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (timesFixed >= 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        int silverNeeded = SILVER_NEEDED;
        int essenceNeeded = 10;

        if (resourceStorage.hasEnoughResource("liquid silver", silverNeeded)) {
            orbsSinceFix = 0;
            timesFixed += 1;
            resourceStorage.removeResource("liquid silver", silverNeeded);
            return;
        }
        if (resourceStorage.hasEnoughResource("star essence", essenceNeeded)) {
            orbsSinceFix = 0;
            timesFixed += 1;
            resourceStorage.removeResource("star essence", essenceNeeded);
            return;
        }
        throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);

    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
