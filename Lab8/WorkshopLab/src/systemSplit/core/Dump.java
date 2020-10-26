package systemSplit.core;

import systemSplit.hardware.Hardware;

import static systemSplit.utils.Constants.*;

public class Dump extends System {

    @Override
    public String analyse() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dump Analysis").append(java.lang.System.lineSeparator())
                .append("Power Hardware Components: ")
                .append(this.getHardwareComponents().values()
                        .stream()
                        .filter(hardware -> hardware.getType()
                                .equals(POWER_TYPE))
                        .count())
                .append(java.lang.System.lineSeparator())
                .append("Heavy Hardware Components: ")
                .append(this.getHardwareComponents().values()
                        .stream()
                        .filter(hardware -> hardware.getType()
                                .equals(HEAVY_TYPE))
                        .count()).append(java.lang.System.lineSeparator());

        int expressCount = 0;

        for (Hardware dumpHardware : this.getHardwareComponents().values()) {
            expressCount += dumpHardware
                    .getSoftwareComponents()
                    .values()
                    .stream()
                    .filter(software -> software.getType()
                            .equals(EXPRESS_TYPE))
                    .count();
        }

        sb.append("Express Software Components: ").append(expressCount).append(java.lang.System.lineSeparator());

        int lightCount = 0;

        for (Hardware dumpHardware : this.getHardwareComponents().values()) {
            lightCount += dumpHardware
                    .getSoftwareComponents()
                    .values()
                    .stream()
                    .filter(software -> software.getType()
                            .equals(LIGHT_TYPE))
                    .count();
        }

        sb.append("Light Software Components: ").append(lightCount).append(java.lang.System.lineSeparator())
                .append("Total Dumped Memory: ").append(this.getTotalUsedMemory()).append(java.lang.System.lineSeparator())
                .append("Total Dumped Capacity: ").append(this.getTotalUsedCapacity());

        return sb.toString();
    }
}