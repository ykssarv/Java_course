package ee.taltech.iti0202.tk1.art;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collector {
    private Map<String, Painting> paintings;

    /**
     * Collector.
     */
    public Collector() {
        this.paintings = new HashMap<>();
    }

    /**
     * Add painting.
     * @param painting of collector
     * @return boolean
     */
    public boolean addPainting(Painting painting) {
        if (paintings.containsKey(painting.getTitle())) {
            return false;
        }
        paintings.put(painting.getTitle(), painting);
        return true;
    }

    /**
     * Sell painting
     * @param painting to sell
     * @param fellowCollector other
     * @return boolean
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (
            this.paintings.containsValue(painting)
            && fellowCollector != this
            && fellowCollector.addPainting(painting)
        ) {
            this.paintings.remove(painting.getTitle());
            return true;
        }
        return false;
    }

    /**
     * Get paintings.
     * @return list
     */
    public List<Painting> getPaintings() {
        return new ArrayList<>(paintings.values());
    }
}
