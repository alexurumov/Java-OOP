package shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.setHeight(height);
        this.setWidth(width);
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    public final Double getHeight() {
        return height;
    }

    public final Double getWidth() {
        return width;
    }

    @Override
    protected Double calculatePerimeter() {
        return 2.0 * this.getHeight() + 2.0 * this.getWidth();
    }

    @Override
    protected Double calculateArea() {
        return this.getHeight() * this.getWidth();
    }
}
