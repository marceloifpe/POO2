public class Surgeon extends Doctor {
    @Override
    public void treatPatient() {
        System.out.println("Cirurgião: Realizando uma cirurgia complexa.");
    }

    public void makeIncision() {
        System.out.println("Cirurgião: Fazendo uma incisão precisa.");
    }
}