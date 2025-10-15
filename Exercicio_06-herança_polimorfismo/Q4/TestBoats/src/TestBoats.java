public class TestBoats {
    public static void main(String[] args) throws Exception {
        Boat b1 = new Boat();
        Sailboat b2 = new Sailboat();
        Rowboat b3 = new Rowboat();

        b2.setLength(32);

        b1.move(); // Chama o método de Boat=>drift.
        b3.move(); // Rowboat herda o método de Boat=>drift.
        b2.move(); // Sailboat sobrescreve o método=>hoist sail.
    }
}