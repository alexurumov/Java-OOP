package carShop;

public class Main {

    public static void main(String[] args) {

        Seat seat = new Seat("Leon", "Grey", 110, "Spain");

        System.out.println(String.format("%s is %s color and have %s horse power",
                seat.getModel(),
                seat.getColor(),
                seat.getHorsePower()));
        System.out.println(seat.toString());
    }
}
