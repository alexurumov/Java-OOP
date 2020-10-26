package systemSplit.hardware;

import static systemSplit.utils.Constants.*;

public class PowerHardware extends Hardware {

    public PowerHardware(String name, String type, int maxCapacity, int maxMemory) {
        super(name, type, maxCapacity, maxMemory);
    }

    @Override
    public void setMaxCapacity(int maxCapacity) {
        int newCapacity = maxCapacity - ((maxCapacity * 3) / 4);
        super.setMaxCapacity(newCapacity);
    }

    @Override
    public void setMaxMemory(int maxMemory) {
        int newMemory = maxMemory + ((maxMemory * 3) / 4);
        super.setMaxMemory(newMemory);
    }
}
