// Aluno: Marcelo Augusto de Barros Araújo.
// Exercício: reorganize os trechos de código para criar um programa Java
// funcional que produza o resultado abaixo.

class DrumKit {
    boolean topHat = true;
    boolean snare = true;

    void playSnare() {
        System.out.println("bang bang ba-bang");
    }

    void playTopHat() {
        System.out.println("ding ding da-ding");
    }
}

class DrumKitTestDrive {
    public static void main(String[] args) {
        DrumKit d = new DrumKit();

        if (d.snare == true) {
            d.playSnare();
        }

        d.snare = false;
        d.playTopHat();
    }
}
