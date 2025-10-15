public class Hospital {

    public static void main(String[] args) {
        System.out.println("--- Chamando o Cirurgião ---");
        Surgeon cirurgiao = new Surgeon();
        System.out.println("Trabalha no => " + cirurgiao.worksAtHospital);
        cirurgiao.treatPatient();
        cirurgiao.makeIncision();

        System.out.println("\n--- Chamando o Médico da Família ---");
        FamilyDoctor medicoFamilia = new FamilyDoctor();
        System.out.println("Trabalha no => " + medicoFamilia.worksAtHospital);
        medicoFamilia.treatPatient();
        medicoFamilia.giveAdvice();
        medicoFamilia.printInfo();

    }
}