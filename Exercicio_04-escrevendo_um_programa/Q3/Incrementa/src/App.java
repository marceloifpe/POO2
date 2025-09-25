public class App {
    public static void main(String[] args) throws Exception {
        int x = 0;
        int y = 30;
        for (int outer = 0; outer < 3; outer++) {
            for (int inner = 4; inner > 1; inner--) {

                x = x + 3; //Candidato

                y = y - 2;
                if (x == 6) {
                    break;
                }
                x = x + 3;
            }
            y = y - 2;
        }
        System.out.println(x + " " + y);
    }
}
/*
Resposta:
Candidatos   |   Resultados

1. x = x + 3;    => 54 6
2. x = x + 6;    => 60 10
3. x = x + 2;    => 45 6
4. x++;          => 36 6
5. x--;          => 18 6
6. x = x + 0;    => 6 14

*/