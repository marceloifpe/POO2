// Arquivo: Hospital.java

public class Hospital {

    public static void main(String[] args) {
        System.out.println("--- Testando o Cirurgião ---");
        Surgeon cirurgiao = new Surgeon();
        System.out.println("Trabalha no: " + cirurgiao.worksAtHospital); // Acessando variável herdada
        cirurgiao.treatPatient();  // Chama a versão SOBRESCRITA do método
        cirurgiao.makeIncision();  // Chama o método exclusivo do cirurgião

        System.out.println("\n--- Testando o Médico da Família ---");
        FamilyDoctor medicoFamilia = new FamilyDoctor();
        System.out.println("Trabalha no: " + medicoFamilia.worksAtHospital); // Acessando variável herdada
        medicoFamilia.treatPatient(); // Chama a versão ORIGINAL herdada de Doctor
        medicoFamilia.giveAdvice();    // Chama o método exclusivo do médico da família
        medicoFamilia.printInfo();     // Mostra a variável exclusiva

        // A linha abaixo causaria um ERRO de compilação, porque um FamilyDoctor não pode fazer uma incisão.
        // medicoFamilia.makeIncision();
    }
}