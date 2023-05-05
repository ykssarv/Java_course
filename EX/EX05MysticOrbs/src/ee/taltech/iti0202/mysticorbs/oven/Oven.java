package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable {
    public static final int ORB_AMOUNT_BEFORE_BREAKING = 15;
    protected String name;
    protected ResourceStorage resourceStorage;
    protected int createdOrbsAmount;

    /**
     * Oven.
     * @param name of oven
     * @param resourceStorage of oven
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        this.createdOrbsAmount = 0;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbsAmount;
    }
    public boolean isBroken() {
        return this.createdOrbsAmount >= ORB_AMOUNT_BEFORE_BREAKING;
    }

    /**
     * Optional orb.
     * @return optional
     */
    public Optional<Orb> craftOrb() {
        if (isBroken()) {
            return Optional.empty();
        }
        if (resourceStorage.hasEnoughResource("silver", 1)
            && resourceStorage.hasEnoughResource("pearl", 1)) {
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            Orb orb = new Orb(this.name);
            orb.charge("silver", 1);
            orb.charge("pearl", 1);
            createdOrbsAmount += 1;
            return Optional.of(orb);
        }
        return Optional.empty();
    }

    @Override
    public int compareTo(Object o) {
        Oven oven = (Oven) o;
        if (this.isBroken() && !oven.isBroken()) {
            return -1;
        }
        if (!this.isBroken() && oven.isBroken()) {
            return 1;
        }

        int thisPriority = this instanceof SpaceOven ? 3 : (this instanceof MagicOven ? 2 : 1);
        int ovenPriority = oven instanceof SpaceOven ? 3 : (oven instanceof MagicOven ? 2 : 1);
        if (thisPriority < ovenPriority) {
            return -1;
        }
        if (thisPriority > ovenPriority) {
            return 1;
        }

        if (this instanceof MagicOven) {
            if (this.getCreatedOrbsAmount() % 2 == 1 && oven.getCreatedOrbsAmount() % 2 == 0) {
                return 1;
            }
            if (this.getCreatedOrbsAmount() % 2 == 0 && oven.getCreatedOrbsAmount() % 2 == 1) {
                return -1;
            }

            if (this.getCreatedOrbsAmount() == oven.getCreatedOrbsAmount()) {
                if (this instanceof InfinityMagicOven && !(oven instanceof InfinityMagicOven)) {
                    return 1;
                }
                if (!(this instanceof InfinityMagicOven) && oven instanceof InfinityMagicOven) {
                    return -1;
                }
            }
        }

        if (this.getCreatedOrbsAmount() < oven.getCreatedOrbsAmount()) {
            return 1;
        }
        if (this.getCreatedOrbsAmount() > oven.getCreatedOrbsAmount()) {
            return -1;
        }

        return this.getName().compareTo(oven.getName());
    }
}
