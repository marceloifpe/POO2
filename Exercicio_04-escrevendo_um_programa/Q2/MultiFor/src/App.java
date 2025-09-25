// Exercício: reconstrua os trechos de código para criar um
// programa Java funcional que produza o resultado listado abaixo:

class MultiFor {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            for (int j = 4; j > 2; j--) {
                System.out.println(i + " " + j);
            }
            if (i == 1) {
                i++;
            }
        }
    }
}