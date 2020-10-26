package carTrip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    public static final String DEFAULT_CAR_MODEL = "Model";
    public static final double DEFAULT_TANK_CAPACITY = 60;
    public static final double DEFAULT_FUEL_AMOUNT = 12;
    public static final double DEFAULT_FUEL_CONSUMPTION = 5;
    private Car car;

    @Before
    public void setup() {
        this.car = new Car(DEFAULT_CAR_MODEL, DEFAULT_TANK_CAPACITY, DEFAULT_FUEL_AMOUNT, DEFAULT_FUEL_CONSUMPTION);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setModelShouldThrowExceptionWhenModelIsNull() {
        Car car = new Car(null, 12, 12, 12);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setModelShouldThrowExceptionWhenModelIsEmpty() {
        Car car = new Car("", 12, 12, 12);
    }

    @Test
    public void setModelShouldSetCorrectModel() {
        final String NEW_MODEL = "NewModel";
        this.car.setModel(NEW_MODEL);

        assertEquals(NEW_MODEL, this.car.getModel());
    }

    @Test
    public void setTankCapacityShouldSetCorrectCapacity() {
        final double NEW_TANK_CAPACITY = 70;
        this.car.setTankCapacity(NEW_TANK_CAPACITY);

        assertEquals(NEW_TANK_CAPACITY, this.car.getTankCapacity(), 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setFuelAmountShouldThrowExceptionWhenMoreThanMaxCapacity() {
        final double INCORRECT_FUEL_AMOUNT = 61;
        this.car.setFuelAmount(INCORRECT_FUEL_AMOUNT);
    }

    @Test
    public void setFuelAmountShouldSetProperlyFuelAmountWhenInMaxCapacityRange() {
        final double CORRECT_FUEL_AMOUNT = 55;
        this.car.setFuelAmount(CORRECT_FUEL_AMOUNT);

        assertEquals(CORRECT_FUEL_AMOUNT, this.car.getFuelAmount(), 0.0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setFuelConsumptionShouldThrowExceptionWhenLessOrEqualToZero() {
        final double INCORRECT_FUEL_CONSUMPTION = 0;
        this.car.setFuelConsumptionPerKm(INCORRECT_FUEL_CONSUMPTION);
    }

    @Test
    public void setFuelConsumptionShouldSetProperlyFuelConsumptionWhenCorrect() {
        final double CORRECT_FUEL_CONSUMPTION = 10;
        this.car.setFuelConsumptionPerKm(CORRECT_FUEL_CONSUMPTION);

        assertEquals(CORRECT_FUEL_CONSUMPTION, this.car.getFuelConsumptionPerKm(), 0.0);
    }

    @Test (expected = IllegalStateException.class)
    public void driveShouldThrowExceptionWhenNotEnoughFuelForTrip() {
        final double TOO_LONG_DISTANCE = 3;
        this.car.drive(TOO_LONG_DISTANCE);
    }

    @Test
    public void driveShouldReduceFuelAfterDistanceTravelled() {
        final double DRIVABLE_DISTANCE = 2;
        this.car.drive(DRIVABLE_DISTANCE);

        double actual = DEFAULT_FUEL_AMOUNT - (DRIVABLE_DISTANCE * DEFAULT_FUEL_CONSUMPTION);

        assertEquals(actual, this.car.getFuelAmount(), 0.0);
    }

    @Test
    public void driveShouldReturnCorrectMessageAfterDistanceTravelled() {
        final double DRIVABLE_DISTANCE = 2;
        String message = this.car.drive(DRIVABLE_DISTANCE);
        String actual = "Have a nice trip";

        assertEquals(actual, message);
    }

    @Test (expected = IllegalStateException.class)
    public void refuelShouldThrowExceptionWhenMaxCapacityIsCrossed() {
        final double TOO_MUCH_FUEL = 50;
        this.car.refuel(TOO_MUCH_FUEL);
    }

    @Test
    public void refuelShouldSetNewFuelCorrectly() {
        final double FUEL_IN_LIMITS = 48;
        this.car.refuel(FUEL_IN_LIMITS);

        double expected = DEFAULT_FUEL_AMOUNT + FUEL_IN_LIMITS;

        assertEquals(expected, this.car.getFuelAmount(), 0.0);
    }

}