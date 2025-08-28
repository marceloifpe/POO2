// class Hobbits {
//     String name;

//     public static void main(String[] args) {
//         Hobbits[] h = new Hobbits[3];
//         int z = 0;

//         while (z < 4) {
//             z = z + 1;
//             h[z] = new Hobbits();
//             h[z].name = "bilbo";
//             if (z == 1) {
//                 h[z].name = "frodo";
//             }
//             if (z == 2) {
//                 h[z].name = "sam";
//             }
//             System.out.print(h[z].name + " is a ");
//             System.out.println("good Hobbit name");
//         }
//     }
// }

/*
No arquivo B da imagem não é executado, pois o erro está no loop while que deveria ser z < 3 nesse array
de objetos o tamanho é 3 ou seja 0,1,2 os indices a partir da terceira interação o z assume o valor 3 e
excede o tamanho do array ocorrendo o erro ArrayIndexOutOfBoundsException, a solução seria apenas
ajustar a condicional do while e outro detalhe adicional seria melhorar a lógica, onde o incremento do z =z +1; para acontecer
na última linha para que o objeto do indice 0 seja instanciado também. Segue abaixo o código com a correção:
 */


class Hobbits {
    String name;

    public static void main(String[] args) {
        Hobbits[] h = new Hobbits[3];
        int z = 0;

        // tamanho do array no laço
        while (z < 3) {
            h[z] = new Hobbits();
            h[z].name = "bilbo";
            if (z == 1) {
                h[z].name = "frodo";
            }
            if (z == 2) {
                h[z].name = "sam";
            }
            System.out.print(h[z].name + " is a ");
            System.out.println("good Hobbit name");

            // incremento para o final da condicional
            z = z + 1;
        }
    }
}