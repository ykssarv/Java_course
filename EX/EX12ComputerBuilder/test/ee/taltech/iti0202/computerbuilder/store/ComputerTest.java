package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Computer test.
 */
public class ComputerTest {

    private Laptop computer;

    private Component psu = mock(Component.class);
    private Component gpu = mock(Component.class);
    private Component cpu = mock(Component.class);
    private Component ram = mock(Component.class);
    private Component motherboard = mock(Component.class);
    private Component storage = mock(Component.class);
    private Component screen = mock(Component.class);
    private Component keyboard = mock(Component.class);
    private Component battery = mock(Component.class);
    private Component computerCase = mock(Component.class);
    private Component touchpad = mock(Component.class);

    private void generateComputer() {
        computer = new Laptop();
        computer.setPsu(psu);
        computer.setComputerCase(computerCase);
        computer.setStorage(storage);
        computer.setMotherboard(motherboard);
        computer.setRam(ram);
        computer.setGpu(gpu);
        computer.setCpu(cpu);
        computer.setBattery(battery);
        computer.setScreen(screen);
        computer.setTouchpad(touchpad);
        computer.setKeyboard(keyboard);
    }

    /**
     * Test setters and getters.
     */
    @Test
    public void testSettersAndGetters() {
        generateComputer();
        Assertions.assertEquals(psu, computer.getPsu());
        Assertions.assertEquals(computerCase, computer.getComputerCase());
        Assertions.assertEquals(storage, computer.getStorage());
        Assertions.assertEquals(motherboard, computer.getMotherboard());
        Assertions.assertEquals(ram, computer.getRam());
        Assertions.assertEquals(gpu, computer.getGpu());
        Assertions.assertEquals(cpu, computer.getCpu());
        Assertions.assertEquals(battery, computer.getBattery());
        Assertions.assertEquals(screen, computer.getScreen());
        Assertions.assertEquals(touchpad, computer.getTouchpad());
        Assertions.assertEquals(keyboard, computer.getKeyboard());
    }

    /**
     * Test universal setter.
     */
    @Test
    public void testUniversalSetter() {
        generateComputer();
        Component betterRam = mock(Component.class);
        Component betterCpu = mock(Component.class);
        Component betterGpu = mock(Component.class);
        Component betterStorage = mock(Component.class);
        Component betterScreen = mock(Component.class);
        Component betterBattery = mock(Component.class);
        Component betterTouchpad = mock(Component.class);
        Component betterKeyboard = mock(Component.class);
        Component betterPsu = mock(Component.class);
        Component betterCase = mock(Component.class);
        Component betterMotherboard = mock(Component.class);
        computer.setComponent(Component.Type.RAM, betterRam);
        computer.setComponent(Component.Type.CPU, betterCpu);
        computer.setComponent(Component.Type.GPU, betterGpu);
        computer.setComponent(Component.Type.SSD, betterStorage);
        computer.setComponent(Component.Type.SCREEN, betterScreen);
        computer.setComponent(Component.Type.BATTERY, betterBattery);
        computer.setComponent(Component.Type.TOUCHPAD, betterTouchpad);
        computer.setComponent(Component.Type.KEYBOARD, betterKeyboard);
        computer.setComponent(Component.Type.PSU, betterPsu);
        computer.setComponent(Component.Type.CASE, betterCase);
        computer.setComponent(Component.Type.MOTHERBOARD, betterMotherboard);
        Assertions.assertEquals(betterRam, computer.getRam());
        Assertions.assertEquals(betterCpu, computer.getCpu());
        Assertions.assertEquals(betterGpu, computer.getGpu());
        Assertions.assertEquals(betterStorage, computer.getStorage());
        Assertions.assertEquals(betterScreen, computer.getScreen());
        Assertions.assertEquals(betterBattery, computer.getBattery());
        Assertions.assertEquals(betterTouchpad, computer.getTouchpad());
        Assertions.assertEquals(betterKeyboard, computer.getKeyboard());
        Assertions.assertEquals(betterPsu, computer.getPsu());
        Assertions.assertEquals(betterCase, computer.getComputerCase());
        Assertions.assertEquals(betterMotherboard, computer.getMotherboard());
    }

    /**
     * Test copy.
     */
    @Test
    public void testCopy() {
        generateComputer();
        Laptop copy = computer.copy(new Laptop());
        Assertions.assertEquals(psu, copy.getPsu());
        Assertions.assertEquals(computerCase, copy.getComputerCase());
        Assertions.assertEquals(storage, copy.getStorage());
        Assertions.assertEquals(motherboard, copy.getMotherboard());
        Assertions.assertEquals(ram, copy.getRam());
        Assertions.assertEquals(gpu, copy.getGpu());
        Assertions.assertEquals(cpu, copy.getCpu());
        Assertions.assertEquals(battery, copy.getBattery());
        Assertions.assertEquals(screen, copy.getScreen());
        Assertions.assertEquals(touchpad, copy.getTouchpad());
        Assertions.assertEquals(keyboard, copy.getKeyboard());
    }

    /**
     * Test get power consumption.
     */
    @Test
    public void testGetPowerConsumption() {
        generateComputer();
        when(keyboard.getPowerConsumption()).thenReturn(3);
        when(touchpad.getPowerConsumption()).thenReturn(4);
        when(screen.getPowerConsumption()).thenReturn(10);
        when(cpu.getPowerConsumption()).thenReturn(12);
        when(gpu.getPowerConsumption()).thenReturn(17);
        when(ram.getPowerConsumption()).thenReturn(5);
        when(motherboard.getPowerConsumption()).thenReturn(10);
        when(storage.getPowerConsumption()).thenReturn(5);
        Assertions.assertEquals(66, computer.getPowerConsumption());
    }

    /**
     * Test get goodness.
     */
    @Test
    public void testGetGoodness() {
        generateComputer();
        when(keyboard.getPerformancePoints()).thenReturn(3);
        when(touchpad.getPerformancePoints()).thenReturn(4);
        when(screen.getPerformancePoints()).thenReturn(10);
        when(cpu.getPerformancePoints()).thenReturn(12);
        when(gpu.getPerformancePoints()).thenReturn(17);
        when(ram.getPerformancePoints()).thenReturn(5);
        when(motherboard.getPerformancePoints()).thenReturn(10);
        when(storage.getPerformancePoints()).thenReturn(5);
        Map<Component.Type, Double> map = new HashMap<>();
        Assertions.assertEquals(66.0, computer.getGoodness(map));
    }

    /**
     * Test get price.
     */
    @Test
    public void testGetPrice() {
        generateComputer();
        when(keyboard.getPrice()).thenReturn(3d);
        when(touchpad.getPrice()).thenReturn(4d);
        when(screen.getPrice()).thenReturn(10d);
        when(cpu.getPrice()).thenReturn(12d);
        when(gpu.getPrice()).thenReturn(17d);
        when(ram.getPrice()).thenReturn(5d);
        when(motherboard.getPrice()).thenReturn(10d);
        when(storage.getPrice()).thenReturn(5d);
        Assertions.assertEquals(66.0, computer.getPrice());
    }

    /**
     * Test get ids.
     */
    @Test
    public void testGetIds() {
        generateComputer();
        when(keyboard.getId()).thenReturn(1);
        when(touchpad.getId()).thenReturn(2);
        when(screen.getId()).thenReturn(3);
        when(cpu.getId()).thenReturn(4);
        when(gpu.getId()).thenReturn(5);
        when(ram.getId()).thenReturn(6);
        when(motherboard.getId()).thenReturn(7);
        when(storage.getId()).thenReturn(8);
        when(psu.getId()).thenReturn(9);
        when(computerCase.getId()).thenReturn(10);
        when(battery.getId()).thenReturn(11);
        List<Integer> ids = Arrays.asList(4, 5, 6, 7, 8, 10, 9, 1, 2, 3, 11);
        Assertions.assertEquals(ids, computer.getComponentIds());
    }
}
