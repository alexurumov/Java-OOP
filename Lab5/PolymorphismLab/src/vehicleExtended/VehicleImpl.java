package vehicleExtended;

import java.text.DecimalFormat;

abstract class VehicleImpl implements Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.tankCapacity = tankCapacity;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    protected double getTankCapacity() {
        return tankCapacity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public String drive(double distance) {
        double fuelNeeded = distance * this.getFuelConsumption();

        String result = "needs refueling";
        DecimalFormat df = new DecimalFormat("#.##");

        if (this.getFuelQuantity() >= fuelNeeded) {
            result = String.format("travelled %s km", df.format(distance));
            this.setFuelQuantity(this.getFuelQuantity() - fuelNeeded);
        }

        return result;
    }

    @Override
    public void refuel(double liters) {
        if (liters < 1.0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        } else if (this.getFuelQuantity() + liters > this.getTankCapacity()) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.setFuelQuantity(this.getFuelQuantity() + liters);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + String.format(": %.2f", this.fuelQuantity);
    }
}
