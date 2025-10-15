public class FamilyDoctor extends Doctor {
    private boolean makesHouseCalls = true;
    public void giveAdvice() {
        System.out.println("Médico da Família: Dando conselhos sobre estilo de vida.");
    }

    public void printInfo() {
        System.out.println("Este médico de família faz visitas em domicílio? " + makesHouseCalls);
    }
}