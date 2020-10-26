package systemSplit.hardware;

public class HeavyHardware extends Hardware {

    public HeavyHardware(String name, String type, int maxCapacity, int maxMemory) {
        super(name, type, maxCapacity, maxMemory);
    }

    @Override
    public void setMaxCapacity(int maxCapacity) {
        super.setMaxCapacity(maxCapacity * 2);
    }

    @Override
    public void setMaxMemory(int maxMemory) {
        int newMemory = maxMemory - (maxMemory / 4);
        super.setMaxMemory(newMemory);
    }
}
