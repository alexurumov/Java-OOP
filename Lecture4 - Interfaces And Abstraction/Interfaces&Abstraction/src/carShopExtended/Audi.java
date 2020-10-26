package carShopExtended;

public class Audi extends CarImpl implements Rentable {
    private String countryProduced;
    private Integer minRentDay;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced, Integer minRentDay, Double pricePerDay) {
        super(model, color, horsePower);
        this.countryProduced = countryProduced;
        this.minRentDay = minRentDay;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Integer getMinRentDay() {
        return this.minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return pricePerDay;
    }

    public String getCountryProduced() {
        return countryProduced;
    }

    public String toString() {
        return String.format("This is %s produced in %s and have %d tires%n" +
                "Minimum rental period of %d days. Price per day %.6f", this.getModel(), this.getCountryProduced(), Car.TYRES,
                this.getMinRentDay(), this.getPricePerDay());
    }
}
