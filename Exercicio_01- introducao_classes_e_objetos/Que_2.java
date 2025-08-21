// Exercício: determine se cada um dos arquivos é compilável (se
// rodam/funcionam). Caso não compilem, como você os corrigiria?
class Exercisela {
    public static void main(String[] args) {
        int x = 1;
        while(x<10){
            if(x>3){
                System.out.println("big x");
            }
        }
    }
}
/*Para esse arquivo A o programa é executado normalmente, entretanto ele fica preso no loop do

While, para corrigir e sair do loop seria necessário que dentro do while, após o if tenha um

incrementador x++ ou x += 1; para que seja atualizado o valor de x permitindo que saia do loop,
se não esse programa roda infinitamente.
segue como ficaria o código com a corrção:
 class Exercisela {
    public static void main(String[] args) {
        int x = 1;
        while (x < 10) {
            if (x > 3) {
                System.out.println("big x");
            }
            x++;
        }
    }
}*/

public static void main(String [] args) {
    int x = 5;
    while (x > 1) {
        x = x - 1;
        if (x < 3) {
            System.out.println("small x");
        }
    }
}

/*Nesse arquivo B o programa não é executado, pois o método main precisa está dentro de uma
classe e desta forma é como se não tivesse funcionalidade perdido, a solução seria criar uma classe e colocar todo
o código dentro do escopo da classe, pois a classe é uma estrutura de dados que define um tipo de objeto,
especificando os atributos e métodos, segue abaixo a solução:*/
// class Arquivob {
//     public static void main(String [] args) {
//         int x = 5;
//         while (x > 1) {
//             x = x - 1;
//             if (x < 3) {
//                 System.out.println("small x");
//             }
//         }
//     }
// }

class Exercise1c {
    int x = 5;
    while (x > 1) {
        x = x - 1;
        if (x < 3) {
            System.out.println("small x");
        }
    }
}

/*Nesse Arquivo C não é compilado, pois para que todas as operações sejam executadas precisam
estar dentro de um método nesse caso poderia ser um método como o 'main', nesse caso está apenas o escopo
 da classe por isso não chega a executar.
*/