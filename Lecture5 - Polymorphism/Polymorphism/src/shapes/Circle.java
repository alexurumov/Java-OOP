package shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.setRadius(radius);
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    protected Double calculatePerimeter() {
        return 2.0 * Math.PI * this.getRadius();
    }

    @Override
    protected Double calculateArea() {
        return Math.PI * this.getRadius() * this.getRadius();
    }
}
