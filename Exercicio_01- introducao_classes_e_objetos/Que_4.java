// Exercício: pegue trechos de código da “piscina” e coloque-os nas
// linhas em branco do código para produzir o resultado abaixo.
class PoolPuzzleOne {
    public static void main(String [] args) {
        int x = 0;

        while (x < 4) { //espaço 1

            System.out.print("a");

            if (x < 1) {
                System.out.print(" "); //espaço 2
            }

            System.out.print("n"); // espaço 3

            if (x > 1) { //espaço 4
                System.out.print(" oyster");
                x = x + 2;
            }

            if (x == 1) {
                System.out.print("noys"); //espaço 5
            }

            if (x < 1) { //espaço 6
                System.out.print("oise");
            }

            System.out.println(); //espaço 6
            x = x + 1;
        }
    }
}