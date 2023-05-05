package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Fixable;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrbFactory {
    private  ResourceStorage resourceStorage;
    private List<Oven> ovens;
    private List<Orb> orbs;
    private List<Oven> unableToFix;

    /**
     * Orb factory
     * @param resourceStorage of factory
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
        this.ovens = new ArrayList<>();
        this.orbs = new ArrayList<>();
        this.unableToFix = new ArrayList<>();
    }

    /**
     * Add ove
     * @param oven to add
     */
    public void addOven(Oven oven) {
        if (ovens.contains(oven)) {
            return;
        }
        if (oven.getResourceStorage() != resourceStorage) {
            return;
        }
        ovens.add(oven);
    }
    public List<Oven> getOvens() {
        return this.ovens;
    }

    /**
     * get and clean produced orbs list
     * @return produced orbs list
     */
    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> copy = new ArrayList<>(orbs);
        this.orbs.clear();
        return copy;
    }

    /**
     * Produce orbs
     * @return amount of orbs
     */
    public int produceOrbs() {
        int amountBefore = orbs.size();
        for (Oven oven : this.ovens) {
            if (oven.isBroken()) {
                if (oven instanceof Fixable) {
                    try {
                        ((Fixable) oven).fix();
                    } catch (CannotFixException e) {
                        if (e.getReason() == CannotFixException.Reason.FIXED_MAXIMUM_TIMES) {
                            if (!unableToFix.contains(oven)) {
                                unableToFix.add(oven);
                            }
                        }
                    }
                } else {
                    if (!unableToFix.contains(oven)) {
                        unableToFix.add(oven);
                    }
                }
            }
            oven.craftOrb().ifPresent(x -> this.orbs.add(x));
        }
        return orbs.size() - amountBefore;
    }

    /**
     * produce orbs.
     * @param cycles of production
     * @return amount of orbs
     */
    public int produceOrbs(int cycles) {
        int amount = 0;
        for (int i = 0; i < cycles; i++) {
            amount += produceOrbs();
        }
        return amount;
    }

    /**
     * get ovens that cannot be fixed.
     * @return unable to fix
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        return unableToFix;
    }

    /**
     * Get rid of ovens that cannot be fixed.
     */
    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven : unableToFix) {
            ovens.remove(oven);
        }
        unableToFix.clear();
    }

    /**
     * Optimize ovens order.
     */
    public void optimizeOvensOrder() {
        ovens.sort(Collections.reverseOrder());
    }

}
