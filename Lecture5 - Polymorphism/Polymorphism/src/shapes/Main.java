package shapes;

public class Main {

    public static void main(String[] args) {

        Shape circle = new Circle(5.0);
        System.out.println(circle.calculateArea());
        System.out.println(circle.calculatePerimeter());

        Shape rect = new Rectangle(2.0, 3.0);
        System.out.println(rect.calculateArea());
        System.out.println(rect.calculatePerimeter());

    }
}
