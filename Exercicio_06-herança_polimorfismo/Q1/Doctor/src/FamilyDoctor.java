// Arquivo: FamilyDoctor.java

public class FamilyDoctor extends Doctor {
    // A variável 'worksAtHospital' é herdada automaticamente.

    // 1. Adiciona uma NOVA variável de instância, exclusiva desta classe.
    private boolean makesHouseCalls = true;

    // Esta classe NÃO sobrescreve treatPatient(), então ela usa a versão da classe Doctor.

    /**
     * Este é um método NOVO, exclusivo da classe FamilyDoctor.
     */
    public void giveAdvice() {
        System.out.println("Médico da Família: Dando conselhos sobre estilo de vida.");
    }

    public void printInfo() {
        System.out.println("Este médico de família faz visitas em casa? " + makesHouseCalls);
    }
}