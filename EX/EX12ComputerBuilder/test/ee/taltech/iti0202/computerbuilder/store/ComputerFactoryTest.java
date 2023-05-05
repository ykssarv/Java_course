package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Computer factory test.
 */
public class ComputerFactoryTest {

    /**
     * Test order.
     */
    @Test
    public void testOrder() {
        ComputerFactory factory = mock(ComputerFactory.class);
        Computer computer = mock(Computer.class);
        Map<Component.Type, Double> weights = new HashMap<>();

        when(factory.getWeightsFromUseCase("Use case")).thenReturn(weights);
        when(factory.generateAllComputers("Type")).thenReturn(Collections.singletonList(computer));
        when(computer.hasEnoughPower()).thenReturn(true);
        when(computer.getPrice()).thenReturn(1d);
        when(computer.getGoodness(weights)).thenReturn(1d);
        when(factory.order(10d, "Use case", "Type")).thenCallRealMethod();

        Optional<Computer> result = factory.order(10d, "Use case", "Type");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(computer, result.get());
    }

    /**
     * Test generate weights from use case gaming.
     */
    @Test
    public void testGenerateWeightsFromUseCaseGaming() {
        Store store = mock(Store.class);
        ComputerFactory factory = new ComputerFactory(store);
        Map<Component.Type, Double> map = factory.getWeightsFromUseCase("gaming");
        Assertions.assertEquals(1.3, map.get(Component.Type.GPU));
    }

    /**
     * Test generate weights from use case workstation.
     */
    @Test
    public void testGenerateWeightsFromUseCaseWorkstation() {
        Store store = mock(Store.class);
        ComputerFactory factory = new ComputerFactory(store);
        Map<Component.Type, Double> map = factory.getWeightsFromUseCase("workstation");
        Assertions.assertEquals(1.3, map.get(Component.Type.CPU));
    }

    /**
     * Test add component.
     */
    @Test
    public void testAddComponent() {
        Store store = mock(Store.class);
        ComputerFactory factory = new ComputerFactory(store);
        Computer computer = new Computer();
        Component cpu1 = mock(Component.class);
        Component cpu2 = mock(Component.class);

        when(store.getAvailableComponentsByType(Component.Type.CPU)).thenReturn(Arrays.asList(cpu1, cpu2));
        when(cpu1.getType()).thenReturn(Component.Type.CPU);
        when(cpu2.getType()).thenReturn(Component.Type.CPU);

        List<Computer> computers = factory.addComponent(
            Collections.singletonList(computer),
            Collections.singletonList(Component.Type.CPU)
        );
        Assertions.assertEquals(2, computers.size());
        Assertions.assertEquals(cpu1, computers.get(0).getCpu());
        Assertions.assertEquals(cpu2, computers.get(1).getCpu());
    }

    /**
     * Test generate all computers.
     */
    @Test
    public void testGenerateAllComputers() {
        ComputerFactory factory = mock(ComputerFactory.class);
        when(factory.generateAllComputers("laptop")).thenCallRealMethod();
        List<Computer> computers = factory.generateAllComputers("laptop");
        Assertions.assertEquals(Collections.emptyList(), computers);
    }
}
