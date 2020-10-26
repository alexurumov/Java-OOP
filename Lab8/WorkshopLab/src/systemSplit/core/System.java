package systemSplit.core;

import systemSplit.hardware.Hardware;
import systemSplit.software.Software;

import java.util.LinkedHashMap;
import java.util.Map;

import static systemSplit.utils.Constants.*;

public class System {
    private Map<String, Hardware> hardwareComponents;
//    private Map<String, Hardware> dump;

    public System() {
        this.hardwareComponents = new LinkedHashMap<>();
//        this.dump= new LinkedHashMap<>();
    }

//    public Map<String, Hardware> getDump() {
//        return dump;
//    }

    public Map<String, Hardware> getHardwareComponents() {
        return hardwareComponents;
    }


    public void addHardware(Hardware hardware) {
        this.hardwareComponents.putIfAbsent(hardware.getName(), hardware);
    }

    public Hardware removeHardware(String hardware) {
        return this.hardwareComponents.remove(hardware);
    }

    public void addSoftware(String hardware, Software software) {
        if (this.hardwareComponents.containsKey(hardware)) {
            this.hardwareComponents.get(hardware).addSoftware(software);
        }
    }

    public void removeSoftware(String hardware, String software) {
        if (this.hardwareComponents.containsKey(hardware)) {
            this.hardwareComponents.get(hardware).removeSoftware(software);
        }
    }

    public int getSoftwareComponentsCount() {
        int total = 0;
        for (Hardware hardware : hardwareComponents.values()) {
            total += hardware.getSoftwareComponents().size();
        }

        return total;
    }

    public int getTotalUsedMemory() {
        int total = 0;
        for (Hardware hardware : hardwareComponents.values()) {
            total += hardware.getUsedMemory();
        }

        return total;
    }

    public int getTotalMaxMemory() {
        int total = 0;
        for (Hardware hardware : hardwareComponents.values()) {
            total += hardware.getMaxMemory();
        }

        return total;
    }

    public int getTotalUsedCapacity() {
        int total = 0;
        for (Hardware hardware : hardwareComponents.values()) {
            total += hardware.getUsedCapacity();
        }

        return total;
    }

    public int getTotalMaxCapacity() {
        int total = 0;
        for (Hardware hardware : hardwareComponents.values()) {
            total += hardware.getMaxCapacity();
        }

        return total;
    }

    public String analyse() {

        StringBuilder sb = new StringBuilder();
        sb.append("System Analysis").append(java.lang.System.lineSeparator())
                .append("Hardware Components: ").append(this.hardwareComponents.size()).append(java.lang.System.lineSeparator())
                .append("Software Components: ").append(this.getSoftwareComponentsCount()).append(java.lang.System.lineSeparator())
                .append("Total Operational Memory: ").append(this.getTotalUsedMemory()).append(" / ").append(this.getTotalMaxMemory()).append(java.lang.System.lineSeparator())
                .append("Total Capacity Taken: ").append(this.getTotalUsedCapacity()).append(" / ").append(this.getTotalMaxCapacity());

        return sb.toString();
    }

    public String split() {
        StringBuilder sb = new StringBuilder();

        this.getHardwareComponents()
                .values()
                .stream()
                .filter(hardware -> hardware.getType()
                        .equals(POWER_TYPE))
                .forEach(hardware -> java.lang.System.out.println(hardware.toString()));

        this.getHardwareComponents()
                .values()
                .stream()
                .filter(hardware -> hardware.getType()
                        .equals(HEAVY_TYPE))
                .forEach(hardware -> java.lang.System.out.println(hardware.toString()));

        return sb.toString();
    }
}
