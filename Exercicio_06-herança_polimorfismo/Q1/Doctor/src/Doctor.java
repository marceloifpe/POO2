// Arquivo: Doctor.java

public class Doctor {
    // 1. Variável de instância herdada por todas as subclasses
    // Usamos 'protected' para que as subclasses possam acessá-la diretamente.
    protected String worksAtHospital = "Hospital Central";

    // 2. Método de acesso a todos médicos
    public void treatPatient() {
        System.out.println("Doutor: Realizando um check-up geral no paciente.");
    }
}
/*
 How many instance variables does Surgeon have?
-É uma herança da variável worksAtHospital da superclasse Doctor.

How many instance variables does FamilyDoctor have?
-Herda worksAtHospital de Doctor e implementa sua própria variável, makesHouseCalls.

How many methods does Doctor have?
-É o método treatPatient()).

How many methods does Surgeon have?
-Ele herda e sobrescreve treatPatient() e adiciona o novo método makeIncision().

How many methods does FamilyDoctor have?
-Ele herda treatPatient() e adiciona o novo método giveAdvice().

Can a FamilyDoctor do treatPatient()?
-Sim. Por ser uma subclasse de Doctor, ele herda o método treatPatient().

Can a FamilyDoctor do makeIncision()?
-Não faz parte, pois esse método makeIncision() pertence apenas à classe Surgeon. Como FamilyDoctor e Surgeon
são classes com ligação com a superclasse doctor, uma não herda os métodos exclusivos da outra.
 */