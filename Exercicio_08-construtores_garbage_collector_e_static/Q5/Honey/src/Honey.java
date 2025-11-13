public class Honey {
    public static void main(String[] args) {
        Honey honeyPot = new Honey();
        Honey[] ha = {honeyPot, honeyPot, honeyPot, honeyPot};
        Bees bees = new Bees();
        bees.beeHoney = ha;
        Bear[] bears = new Bear[5];
        for (int i = 0; i < 5; i++) {
            bears[i] = new Bear();
            bears[i].hunny = honeyPot;
        }
        Kit kit = new Kit();
        kit.honey = honeyPot;
        Raccoon raccoon = new Raccoon();

        raccoon.rh = honeyPot;
        raccoon.rk = kit;
        kit = null;
    } // end of main
}
/*
O objeto honeyPot é o mais referenciado no código. Ao analisar qual das classes (Bees, Raccoon, Kit ou Bear) detém mais referências para ele,
pois é o que mais vezes ao longo do código foi referenciado tanto por variáveis locais, arrays e
atributos de outros objetos, portanto o conjunto de objetos Bear é que tem mais referências armazenadas que é 5.
 */