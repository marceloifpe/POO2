// class Contact { //Código 01
//     public static void main(String[] args) throws Exception {
//         // CORREÇÃO: Inicialize a variável 'x' com 0.
//         int x = 0;

//         Contact[] contacts = new Contact[10];
//         while (x < 10) { // Agora o Java sabe que x começa em 0
//             contacts[x] = new Contact();
//             x = x + 1;
//         }
//         System.out.println("--- Imprimindo a lista um por um ---");

//         // Precisamos de um novo contador, pois 'x' agora vale 10.
//         int i = 0;
//         while (i < contacts.length) {
//             System.out.println("Índice [" + i + "]: " + contacts[i]);
//             i = i + 1; // Incrementa o novo contador
//         }
//     }

// }

class Contact { // Código 02
    public static void main(String[] args) throws Exception {
        int x = 0;
        Contact contactRef = null;

        while (x < 10) {
            contactRef = new Contact();
            x = x + 1;
        }
        System.out.println("--- Imprimindo o único contato que sobrou ---");
        System.out.println("A variável 'contactRef' aponta para o objeto: " + contactRef);
    }
}

/*
 * Resposta:
 * A primeira solução nesse caso é a melhor, porque é criado uma lista com 10
 * objetos do tipo Contact, ou seja ao fim da execução você tem 10 contatos
 * armazenados
 * em um Array para serem acessados novamente quando for necessário.
 * No segundo caso são criados objetos que a cada interação são apagados, pois
 * não estão em uma lista, com isso o garbage collector acaba pegando esses
 * objetos
 * não salvos, pois só irá salvar o objeto da última interação realizada, todos
 * os anteriores ficam sem utilização.
 */