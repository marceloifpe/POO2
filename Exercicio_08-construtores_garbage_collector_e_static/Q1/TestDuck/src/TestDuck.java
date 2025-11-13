public class TestDuck {

    public static void main(String[] args) {

        int weight = 8;
        float density = 2.3F;
        String name = "Donald";
        long[] feathers = { 1, 2, 3, 4, 5, 6 };
        boolean canFly = true;
        int airspeed = 22;


        Duck[] d = new Duck[7];

        // Chamadas dos construtores
        d[0] = new Duck();
        d[1] = new Duck(density, weight);
        d[2] = new Duck(name, feathers);
        d[3] = new Duck(canFly);
        d[4] = new Duck(3.3F, airspeed);
        d[5] = new Duck(false);
        d[6] = new Duck(airspeed, density);
    }
}
/*
Chamadas                              |   Construtores

d[0] = new Duck();                   =>   a) public Duck()

d[1] = new Duck(density, weight);    =>   e) public Duck(float density, int max)

d[2] = new Duck(name, feathers);     =>   c) public Duck(String n, long[] f)

d[3] = new Duck(canFly);             =>   b) public Duck(boolean fly)

d[4] = new Duck(3.3F, airspeed);     =>   e) public Duck(float density, int max)

d[5] = new Duck(false);              =>   b) public Duck(boolean fly)

d[6] = new Duck(airspeed, density);  =>   d) public Duck(int w, float f)
*/