package vehicleExtended;

import java.text.DecimalFormat;

public class Bus extends VehicleImpl implements Vehicle {

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    protected void setFuelConsumption(double fuelConsumption) {
        super.setFuelConsumption(fuelConsumption + 1.4);
    }

    @Override
    public String drive(double distance) {
        return "Bus " + super.drive(distance);
    }

    public String driveEmpty(double distance) {
        double fuelNeeded = distance * (this.getFuelConsumption() - 1.4);

        String result = "needs refueling";
        DecimalFormat df = new DecimalFormat("#.##");

        if (this.getFuelQuantity() >= fuelNeeded) {
            result = String.format("travelled %s km", df.format(distance));
            this.setFuelQuantity(this.getFuelQuantity() - fuelNeeded);
        }

        return "Bus " + result;
    }
}
