// public class BooksTestDrive {
//     public static void main(String[] args) throws Exception {
//     Books[] myBooks = new Books[3];
//         int x = 0;
//         myBooks[0].title = "The Grapes of Java";
//         myBooks[1].title = "The Java Gatsby";
//         myBooks[2].title = "The Java Cookbook";
//         myBooks[0].author = "bob";
//         myBooks[1].author = "sue";
//         myBooks[2].author = "ian";

//         while (x < 3) {
//             System.out.print(myBooks[x].title);
//             System.out.print(" by ");
//             System.out.println(myBooks[x].author);
//             x = x + 1;
//         }
//     }
// }
/*
No arquivo A da imagem o código não é executado pois o array myBooks foi criado, entretanto nenhum objeto foi
instanciado e isso ocasiona um erro do tipo NullPointerException na hora de compilar para corrigir o código, seria
necessário instanciar os objetos em cada posição do array, segue abaixo o código com a correção:
*/
class BooksTestDrive {
    public static void main(String[] args) {
        Books[] myBooks = new Books[3];

        // Objeto instanciado em cada indice do array
        myBooks[0] = new Books();
        myBooks[1] = new Books();
        myBooks[2] = new Books();

        int x = 0;
        myBooks[0].title = "The Grapes of Java";
        myBooks[1].title = "The Java Gatsby";
        myBooks[2].title = "The Java Cookbook";
        myBooks[0].author = "bob";
        myBooks[1].author = "sue";
        myBooks[2].author = "ian";

        while (x < 3) {
            System.out.print(myBooks[x].title);
            System.out.print(" by ");
            System.out.println(myBooks[x].author);
            x = x + 1;
        }
    }
}


