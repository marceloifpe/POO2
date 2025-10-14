// Arquivo: Surgeon.java

public class Surgeon extends Doctor {
    // A variável 'worksAtHospital' é herdada automaticamente.

    /**
     * Este método SOBRESCREVE o método treatPatient() da classe Doctor.
     * A anotação @Override é uma boa prática para indicar isso.
     */
    @Override
    public void treatPatient() {
        System.out.println("Cirurgião: Realizando uma cirurgia complexa.");
    }

    /**
     * Este é um método NOVO, exclusivo da classe Surgeon.
     */
    public void makeIncision() {
        System.out.println("Cirurgião: Fazendo uma incisão precisa.");
    }
}