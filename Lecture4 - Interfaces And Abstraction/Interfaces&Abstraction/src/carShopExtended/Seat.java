package carShopExtended;

public class Seat extends CarImpl implements Sellable{

    private String countryProduced;
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        super(model, color, horsePower);
        this.countryProduced = countryProduced;
        this.price = price;
    }

    String countryProduced() {
        return this.countryProduced;
    }



    private String getCountryProduced() {
        return countryProduced;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires%n" +
                "%s sells for %.6f" , this.getModel(), this.getCountryProduced(), Car.TYRES,
                this.getModel(), this.getPrice());
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
