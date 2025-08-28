class HeapQuiz {
    int id = 0;

    public static void main(String[] args) throws Exception {
        int x = 0;
        HeapQuiz[] hq = new HeapQuiz[5];

        while (x < 3) {
            hq[x] = new HeapQuiz();
            hq[x].id = x;
            x = x + 1;
        }
        hq[3] = hq[1];
        System.out.println("hq[3] agora aponta para o objeto com id: " + hq[3].id); // Vai imprimir 1

        hq[4] = hq[1];
        System.out.println("hq[4] agora aponta para o objeto com id: " + hq[4].id); // Vai imprimir 1

        hq[3] = null;
        System.out.println("hq[3] agora aponta para: " + hq[3]); // Vai imprimir "null"

        hq[4] = hq[0];
        System.out.println("hq[4] agora aponta para o objeto com id: " + hq[4].id); // Vai imprimir 0

        hq[0] = hq[3];
        System.out.println("hq[0] agora aponta para: " + hq[0]); // Vai imprimir "null"

        hq[3] = hq[2];
        System.out.println("hq[3] agora aponta para o objeto com id: " + hq[3].id); // Vai imprimir 2

        hq[2] = hq[0];
        System.out.println("hq[2] agora aponta para: " + hq[2]); // Vai imprimir "null"
    }
}
// Resposta:
// Variáveis referencia | Objeto(id)

// hq[0]                  => nulo ou seja NullPointerException

// hq[1]                  => id = 1

// hq[2]                  => nulo u seja NullPointerException

// hq[3]                  => id = 2

// hq[4]                  => id = 0