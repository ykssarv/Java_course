package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Computer.
 */
public class Computer {
    /**
     * The Cpu.
     */
    protected Component cpu;
    /**
     * The Gpu.
     */
    protected Component gpu;
    /**
     * The Ram.
     */
    protected Component ram;
    /**
     * The Motherboard.
     */
    protected Component motherboard;
    /**
     * The Storage.
     */
    protected Component storage;
    /**
     * The Psu.
     */
    protected Component psu;
    /**
     * The Case.
     */
    protected Component computerCase;

    /**
     * Instantiates a new Computer.
     */
    public Computer() { }

    /**
     * Copy computer.
     *
     * @param other the other
     * @return the computer
     */
    public Computer copy(Computer other) {
        other.setCpu(getCpu());
        other.setGpu(getGpu());
        other.setRam(getRam());
        other.setMotherboard(getMotherboard());
        other.setStorage(getStorage());
        other.setPsu(getPsu());
        other.setComputerCase(getComputerCase());
        return other;
    }

    /**
     * Sets component.
     *
     * @param type      the type
     * @param component the component
     */
    public void setComponent(Component.Type type, Component component) {
        if (type == Component.Type.CPU) {
            this.setCpu(component);
        } else if (type == Component.Type.GPU) {
            this.setGpu(component);
        } else if (type == Component.Type.RAM) {
            this.setRam(component);
        } else if (type == Component.Type.MOTHERBOARD) {
            this.setMotherboard(component);
        } else if (type == Component.Type.SSD) {
            this.setStorage(component);
        } else if (type == Component.Type.HDD) {
            this.setStorage(component);
        } else if (type == Component.Type.CASE) {
            this.setComputerCase(component);
        } else if (type == Component.Type.PSU) {
            this.setPsu(component);
        }
    }

    /**
     * Sets cpu.
     *
     * @param cpu the cpu
     */
    public void setCpu(Component cpu) {
        this.cpu = cpu;
    }

    /**
     * Sets gpu.
     *
     * @param gpu the gpu
     */
    public void setGpu(Component gpu) {
        this.gpu = gpu;
    }

    /**
     * Sets ram.
     *
     * @param ram the ram
     */
    public void setRam(Component ram) {
        this.ram = ram;
    }

    /**
     * Sets motherboard.
     *
     * @param motherboard the motherboard
     */
    public void setMotherboard(Component motherboard) {
        this.motherboard = motherboard;
    }

    /**
     * Sets storage.
     *
     * @param storage the storage
     */
    public void setStorage(Component storage) {
        this.storage = storage;
    }

    /**
     * Sets psu.
     *
     * @param psu the psu
     */
    public void setPsu(Component psu) {
        this.psu = psu;
    }

    /**
     * Sets case.
     *
     * @param computerCase the case
     */
    public void setComputerCase(Component computerCase) {
        this.computerCase = computerCase;
    }

    /**
     * Gets cpu.
     *
     * @return the cpu
     */
    public Component getCpu() {
        return cpu;
    }

    /**
     * Gets gpu.
     *
     * @return the gpu
     */
    public Component getGpu() {
        return gpu;
    }

    /**
     * Gets ram.
     *
     * @return the ram
     */
    public Component getRam() {
        return ram;
    }

    /**
     * Gets motherboard.
     *
     * @return the motherboard
     */
    public Component getMotherboard() {
        return motherboard;
    }

    /**
     * Gets storage.
     *
     * @return the storage
     */
    public Component getStorage() {
        return storage;
    }

    /**
     * Gets psu.
     *
     * @return the psu
     */
    public Component getPsu() {
        return psu;
    }

    /**
     * Gets case.
     *
     * @return the case
     */
    public Component getComputerCase() {
        return computerCase;
    }

    /**
     * Gets power consumption.
     *
     * @return the power consumption
     */
    protected int getPowerConsumption() {
        return cpu.getPowerConsumption()
            + gpu.getPowerConsumption()
            + ram.getPowerConsumption()
            + motherboard.getPowerConsumption()
            + storage.getPowerConsumption();
    }

    /**
     * Has enough power boolean.
     *
     * @return the boolean
     */
    public boolean hasEnoughPower() {
        return psu.getPowerConsumption() >= this.getPowerConsumption();
    }

    /**
     * Gets goodness.
     *
     * @param weights the weights
     * @return the goodness
     */
    public double getGoodness(Map<Component.Type, Double> weights) {
        return cpu.getPerformancePoints() * weights.getOrDefault(Component.Type.CPU, 1.0)
            + gpu.getPerformancePoints() * weights.getOrDefault(Component.Type.GPU, 1.0)
            + ram.getPerformancePoints() * weights.getOrDefault(Component.Type.RAM, 1.0)
            + motherboard.getPerformancePoints() * weights.getOrDefault(Component.Type.MOTHERBOARD, 1.0)
            + getStorageGoodness(weights);
    }

    /**
     * Gets storage goodness.
     *
     * @param weights the weights
     * @return the storage goodness
     */
    public double getStorageGoodness(Map<Component.Type, Double> weights) {
        if (storage.getType() == Component.Type.HDD) {
            return storage.getPerformancePoints() * weights.getOrDefault(Component.Type.HDD, 1.0);
        }
        return storage.getPerformancePoints() * weights.getOrDefault(Component.Type.SSD, 1.0);
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return cpu.getPrice()
            + gpu.getPrice()
            + ram.getPrice()
            + motherboard.getPrice()
            + storage.getPrice()
            + computerCase.getPrice()
            + psu.getPrice();
    }

    /**
     * Gets component ids.
     *
     * @return the component ids
     */
    public List<Integer> getComponentIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(cpu.getId());
        ids.add(gpu.getId());
        ids.add(ram.getId());
        ids.add(motherboard.getId());
        ids.add(storage.getId());
        ids.add(computerCase.getId());
        ids.add(psu.getId());
        return ids;
    }
}
