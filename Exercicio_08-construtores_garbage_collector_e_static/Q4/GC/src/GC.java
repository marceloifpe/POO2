public class GC {
    public static GC doStuff() {
        GC newGC = new GC();
        doStuff2(newGC);
        return newGC;
    }
    public static void doStuff2(GC copyGC) {
        GC localGC = copyGC;
    }
    public static void main(String[] args) {
        GC gc1;
        GC gc2 = new GC();
        GC gc3 = new GC();
        GC gc4 = gc3;
        gc1 = doStuff();
    }
}
/*
 ----Resposta: Três linhas de código (2, 4 e 8)--- ativaria o Garbage Collector,  pois as variávies de referência perde sua referência para null,
 * ou por apontar para um objeto que foi atribuído a outro.
 */