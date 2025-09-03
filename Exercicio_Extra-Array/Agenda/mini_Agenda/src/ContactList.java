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