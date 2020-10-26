package systemSplit.hardware;

import systemSplit.software.Software;
import systemSplit.utils.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

import static systemSplit.utils.Constants.*;

public abstract class Hardware {
    private String name;
    private String type;
    private int maxCapacity;
    private int maxMemory;
    private Map<String, Software> softwareComponents;
    private int usedCapacity;
    private int usedMemory;

    public Hardware(String name, String type, int maxCapacity, int maxMemory) {
        this.setName(name);
        this.setType(type);
        this.setMaxCapacity(maxCapacity);
        this.setMaxMemory(maxMemory);

        this.softwareComponents = new LinkedHashMap<>();
        this.usedCapacity = DEFAULT_USED_CAPACITY;
        this.usedMemory = DEFAULT_USED_MEMORY;
    }

    public Map<String, Software> getSoftwareComponents() {
        return softwareComponents;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(int maxMemory) {
        this.maxMemory = maxMemory;
    }

    private boolean hasFreeCapacity(int capacity) {
        return this.getMaxCapacity() - (this.getUsedCapacity() + capacity) >= 0;
    }

    private boolean hasFreeMemory(int memory) {
        return this.getMaxMemory() - (this.getUsedMemory()+ memory) >= 0;
    }

    public void addSoftware(Software software) {
        if (hasFreeCapacity(software.getCapacityConsumption()) && hasFreeMemory(software.getMemoryConsumption())) {
            this.softwareComponents.putIfAbsent(software.getName(), software);
            this.usedCapacity += software.getCapacityConsumption();
            this.usedMemory += software.getMemoryConsumption();
        }
    }

    public void removeSoftware(String software) {
        this.usedCapacity -= this.softwareComponents.get(software).getCapacityConsumption();
        this.usedMemory -= this.softwareComponents.get(software).getMemoryConsumption();
        this.softwareComponents.remove(software);
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Hardware Component - ").append(this.getName()).append(java.lang.System.lineSeparator())
                .append("Express Software Components - ").append(this.getSoftwareComponents()
                .values()
                .stream()
                .filter(software -> software.getType().equals(EXPRESS_TYPE))
                .count())
                .append(java.lang.System.lineSeparator())
                .append("Light Software Components - ").append(this.getSoftwareComponents()
                .values()
                .stream()
                .filter(software -> software.getType().equals(LIGHT_TYPE))
                .count())
                .append(java.lang.System.lineSeparator())
                .append("Memory Usage: ").append(this.getUsedMemory()).append(" / ").append(this.getMaxMemory()).append(java.lang.System.lineSeparator())
                .append("Capacity Usage: ").append(this.getUsedCapacity()).append(" / ").append(this.getMaxCapacity()).append(java.lang.System.lineSeparator())
                .append("Type: ").append(this.getType()).append(java.lang.System.lineSeparator())
                .append("Software Components: ");

        if (this.getSoftwareComponents().size() == 0) {
            sb.append("None");
        } else {
            this.getSoftwareComponents().values().stream().forEach(software -> sb.append(software.getName()).append(", "));
            sb.deleteCharAt(sb.lastIndexOf(","));

        }
        return sb.toString();
    }
}
