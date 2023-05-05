package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.Recipe;

public interface ResourceStorage {

    int amountOf(String resource);
    void addResource(String resource, Integer amount);
    boolean hasEnough(Recipe recipe);
    void take(Recipe recipe);
}
