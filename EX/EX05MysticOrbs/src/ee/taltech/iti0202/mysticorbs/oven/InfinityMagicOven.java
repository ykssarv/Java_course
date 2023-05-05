package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;


public class InfinityMagicOven extends MagicOven {
    /**
     * Infinity magic oven.
     * @param name of oven
     * @param resourceStorage of ovens
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }
    @Override
    public boolean isBroken() {
        return false;
    }
}
