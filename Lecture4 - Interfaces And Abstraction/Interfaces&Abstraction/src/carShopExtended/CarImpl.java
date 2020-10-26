package carShopExtended;

public class CarImpl implements Car {
    private String model;
    private String color;
    private Integer horsePower;

    public CarImpl(String model, String color, Integer horsePower) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }
}
