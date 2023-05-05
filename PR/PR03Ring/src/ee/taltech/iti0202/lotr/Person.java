package ee.taltech.iti0202.lotr;

import static ee.taltech.iti0202.lotr.Ring.Material.GOLD;
import static ee.taltech.iti0202.lotr.Ring.Type.THE_ONE;

/**
 * Person.
 */
public class Person {
    private String race;
    private String name;
    private Ring ring;

    /**
     * Person.
     * @param race of person
     * @param name of person
     * @param ring of person
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * Person.
     * @param race of person
     * @param name of person
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Set ring.
     * @param ring of ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Sauron.
     * @return is Sauron.
     */
    public String isSauron() {
        if (this.name.equals("Sauron")
            && this.ring != null
            && this.ring.getType() == THE_ONE
            && this.ring.getMaterial() == GOLD
        ) {
            return "Affirmative";
        } else if (this.name.equals("Sauron")
            && this.ring != null
            && this.ring.getType() == THE_ONE
        ) {
            return "No, the ring is fake!";
        } else if (this.ring != null
            && this.ring.getType() == THE_ONE
            && this.ring.getMaterial() == GOLD
        ) {
            return "No, he just stole the ring";
        } else if (this.name.equals("Sauron")) {
            return "No, but he's claiming to be";
        }
        return "No";
    }

    /**
     * Get race.
     * @return race
     */
    public String getRace() {
        return race;
    }

    /**
     * Get name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get ring.
     * @return ring
     */
    public Ring getRing() {
        return ring;
    }
}
