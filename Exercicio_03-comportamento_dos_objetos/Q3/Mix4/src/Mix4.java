// Candidatos                     |   Resultado

// while (i < 9) / if (index < 5)  =>   14 1


// while (i < 20) / if (index < 5) =>  25 1


// while (i < 7) / if (index < 7)  =>  14 1


// while (i < 19) / if (index < 1) => 20 1

public class Mix4 {
    int counter = 0;

    public static void main(String[] args) {
        int count = 0;
        Mix4[] mixes = new Mix4[20];
        int i = 0;

        while (i < 20) {
            mixes[i] = new Mix4();
            mixes[i].counter = mixes[i].counter + 1;
            count = count + 1;
            count = count + mixes[i].maybeNew(i);
            i = i + 1;
        }
        System.out.println(count + " " + mixes[1].counter);
    }

    public int maybeNew(int index) {
        if (index < 5) {
            Mix4 mix = new Mix4();
            mix.counter = mix.counter + 1;
            return 1;
        }
        return 0;
    }
}