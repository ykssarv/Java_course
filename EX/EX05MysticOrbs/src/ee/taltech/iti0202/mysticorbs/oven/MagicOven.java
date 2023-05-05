package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    public static final int CLAY_AMOUNT = 25;
    public static final int POWDER_AMOUNT = 100;
    private int timesFixed;
    private int orbsSinceFix;

    /**
     * Magic oven.
     * @param name of oven
     * @param resourceStorage of ovens
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        timesFixed = 0;
        orbsSinceFix = 0;
    }
    @Override
    public Optional<Orb> craftOrb() {
        if (isBroken()) {
            return Optional.empty();
        }
        if (resourceStorage.hasEnoughResource("gold", 1)
            && resourceStorage.hasEnoughResource("dust", 3)) {
            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
            Orb orb = createdOrbsAmount % 2 == 0 ? new Orb(name) : new MagicOrb(name);
            orb.charge("gold", 1);
            orb.charge("dust", 3);
            createdOrbsAmount += 1;
            orbsSinceFix += 1;
            return Optional.of(orb);
        }
        return Optional.empty();
    }
    @Override
    public boolean isBroken() {
        return orbsSinceFix >= 5;
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
        if (timesFixed >= 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        }
        int clayNeeded = (1 + timesFixed) * CLAY_AMOUNT;
        int powderNeeded = (1 + timesFixed) * POWDER_AMOUNT;
        if (!resourceStorage.hasEnoughResource("clay", clayNeeded)
            || !resourceStorage.hasEnoughResource("freezing powder", powderNeeded)) {
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        orbsSinceFix = 0;
        timesFixed += 1;
        resourceStorage.removeResource("clay", clayNeeded);
        resourceStorage.removeResource("freezing powder", powderNeeded);
    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
