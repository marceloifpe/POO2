class BazEx extends Exception {}
class FooEx extends BazEx {}
class BarEx extends FooEx {}
class BiffEx extends FooEx {}
class BoinkEx extends BiffEx {}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=> 1.Utilizando(Exception)---");
        try {
            // Simulando um erro específico do diagrama (BoinkEx)
            System.out.println("Tentando executar código...");
            throw new BoinkEx();

        } catch (Exception e) {
            // TODAS as exceções
            System.out.println("1. Ocorreu um erro adicione uma (Exception): " + e.getClass().getSimpleName());

        } finally {

            System.out.println("1. Finally: Isso roda sempre, independente de erro.");
        }

        System.out.println("\n--------------------------------------------------\n");

        System.out.println("=>2.Utilizando Classe Principal do Diagrama (BazEx)---");
        try {
            System.out.println("Tentando executar código...");
            throw new BarEx();

        } catch (BazEx e) {
            System.out.println("2. Erro específico da hierarquia Baz capturado: " + e.getClass().getSimpleName());

        } finally {
            System.out.println("2. Finalize a execução!.");
        }
    }
}