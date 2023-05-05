package ee.taltech.iti0202.coffee.resource;

import ee.taltech.iti0202.coffee.exception.UnableToMakeDrinkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ResourceStorageTest {

    private BasicResourceStorage resourceStorage;

    @BeforeEach
    public void setUp() {
        resourceStorage =
            (BasicResourceStorage) new ResourceStorageBuilder()
                .withResource("Water", 5)
                .withResource("Milk", 2)
                .withResource("Sugar", 1)
                .build();
    }

    @Test
    public void resourceStorageBuilderAddsDataToMap() {
        Map<String, Integer> resources = resourceStorage.getResources();
        Assertions.assertEquals(5, resources.get("Water"));
        Assertions.assertEquals(2, resources.get("Milk"));
        Assertions.assertEquals(1, resources.get("Sugar"));
    }

    @Test
    public void resourceStorageAddResourceAddsResource() {
        resourceStorage.addResource("Water", 2);
        Map<String, Integer> resources = resourceStorage.getResources();
        Assertions.assertEquals(7, resources.get("Water"));
        Assertions.assertEquals(1, resources.get("Sugar"));
    }

    @Test
    public void emptyResourceStorageFitsInEmptyResourceStorage() {
        BasicResourceStorage empty1 = (BasicResourceStorage) new ResourceStorageBuilder().build();
        BasicResourceStorage empty2 = (BasicResourceStorage) new ResourceStorageBuilder().build();
        Assertions.assertTrue(empty1.hasEnough(empty2.getResources()));
        Assertions.assertTrue(empty2.hasEnough(empty1.getResources()));
    }

    @Test
    public void smallResourceStorageFitsInLarge() {
        BasicResourceStorage smaller = (BasicResourceStorage) new ResourceStorageBuilder()
            .withResource("Water", 3)
            .withResource("Sugar", 1)
            .build();
        Assertions.assertTrue(resourceStorage.hasEnough(smaller.getResources()));
    }

    @Test
    public void largerResourceStorageDoesntFit() {
        BasicResourceStorage larger = (BasicResourceStorage) new ResourceStorageBuilder()
            .withResource("Water", 6)
            .withResource("Sugar", 1)
            .build();
        Assertions.assertFalse(resourceStorage.hasEnough(larger.getResources()));
    }

    @Test
    public void resourceStorageTakeRemovesResources() {
        BasicResourceStorage smaller = (BasicResourceStorage) new ResourceStorageBuilder()
            .withResource("Water", 3)
            .withResource("Sugar", 1)
            .build();
        resourceStorage.take(smaller.getResources());
        Map<String, Integer> resources = resourceStorage.getResources();
        Assertions.assertEquals(2, resources.get("Water"));
        Assertions.assertEquals(2, resources.get("Milk"));
        Assertions.assertEquals(0, resources.getOrDefault("Sugar", 0));
    }

    @Test
    public void testResourceStorageThrowsExceptionIfNotEnoughResources() {
        BasicResourceStorage larger = (BasicResourceStorage) new ResourceStorageBuilder()
            .withResource("Water", 6)
            .withResource("Sugar", 1)
            .build();
        Exception exception = Assertions.assertThrows(UnableToMakeDrinkException.class, () -> {
            resourceStorage.take(larger.getResources());
        });
        Assertions.assertEquals("Not enough Water: was asked 6, but only have 5", exception.getMessage());
    }
}
