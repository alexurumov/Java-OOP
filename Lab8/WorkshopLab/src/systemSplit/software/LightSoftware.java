package systemSplit.software;

public class LightSoftware extends Software {

    public LightSoftware(String name, String type, int capacityConsumption, int memoryConsumption) {
        super(name, type, capacityConsumption, memoryConsumption);
    }

    @Override
    public void setCapacityConsumption(int capacityConsumption) {
        int newCapacityConsumption = capacityConsumption + (capacityConsumption / 2);
        super.setCapacityConsumption(newCapacityConsumption);
    }

    @Override
    public void setMemoryConsumption(int memoryConsumption) {
        int newMemoryConsumption = memoryConsumption - (memoryConsumption / 2);
        super.setMemoryConsumption(newMemoryConsumption);
    }
}
