package ee.taltech.iti0202.lotr;

/**
 * Ring.
 */
public class Ring {
    /**
     * Type.
     */
    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    /**
     * Material.
     */
    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }
    private Type type;
    private Material material;

    /**
     * Ring constructor.
     * @param type of ring
     * @param material of ring
     */
    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    /**
     * Get type.
     * @return type.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Get material.
     * @return material.
     */
    public Material getMaterial() {
        return this.material;
    }
}
