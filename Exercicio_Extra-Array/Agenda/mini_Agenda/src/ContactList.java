public class ContactList {

    private Contact[] contacts;
    private int x;


    public ContactList() {
        this.contacts = new Contact[2];
        this.x = 0;
    }

    public void add(Contact e) {
        if (e == null){
            return;
        }
        //verifica se o array está completo
        if (x == contacts.length){
            Contact[] novo_array = new Contact[contacts.length * 2];
            for (int i = 0; i < contacts.length; i++){
                novo_array[i] = contacts[i];
            }
            contacts = novo_array;
        }
        contacts[x] = e;
        x++;
    }

    public void printAll() {
        System.out.println("\n---Imprime a lista de contatos da Agenda=> ");
        for (int i = 0; i < this.x; i++) {
            System.out.println("Indice [" + i + "]: Nome: " + contacts[i].name + ", Telefone: " + contacts[i].phone);
        }
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {

        ContactList minhaAgenda = new ContactList();

        Contact c1 = new Contact("Corinthians", "6666-7777");
        minhaAgenda.add(c1);
        minhaAgenda.add(new Contact("Ricardo", "1218-1111"));
        minhaAgenda.add(new Contact("Mike", "2143-2222"));
        minhaAgenda.add(new Contact("Felipe", "8790-3333"));

        System.out.println("Lista inicial:");
        minhaAgenda.printAll();

        //muda nome do contato original
        c1.name = "Paulista";

        System.out.println("Lista depois de modificar a referencia original=> ");
        minhaAgenda.printAll();
    }
}
//Explicação: O array contacts é como se fosse uma agenda telefônica de endereços e nos objetos new Contact(...) como ddd's de cada telefone. A agenda não guarda os ddd's, apenas os endereços delas (as referências).

// Problema: Sua agenda de endereços pode ficar cheia.

// Solução: Você pega uma agenda nova e maior para expandir. O laço for é fundamental para garantir que você copie cada endereço da agenda antiga para a nova. Você não está construindo novos ddd's, só está transferindo os endereços.

// Consequência: Se você não copiasse os endereços, você jogaria a agenda antiga fora. Como ela era a única que sabia a localização dos ddd's, o Coletor de Lixo do Java faz a varredura dos ddd's e destroi para liberar memória.