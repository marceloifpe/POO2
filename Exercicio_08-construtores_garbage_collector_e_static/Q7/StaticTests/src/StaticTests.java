public class StaticTests extends StaticSuper {

    static int rand;

    static {
        rand = (int) (Math.random() * 6);
        System.out.println("static block " + rand);
    }

    StaticTests() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        System.out.println("in main");
        StaticTests st = new StaticTests();
    }
}
/*
A opção correta é a letra B.
O código acima compila normalmente e sua saída é:
super static block
static block 2
in main
super constructor
constructor
 */