// Aluno: Marcelo Augusto de Barros Araújo.
// Exercício: determine se cada um dos arquivos é compilável (se
// rodam/funcionam). Caso não compilem, como você os corrigiria?
class StreamingSong {
    String title;
    String artist;
    int duration;

    void play() {
        System.out.println("Playing song");
    }

    void printDetails() {
        System.out.println("This is " + title + " by " + artist);
    }
}

class StreamingSongTestDrive {
    public static void main(String[] args) {

        song.artist = "The Beatles";
        song.title = "Come Together";
        song.play();
        song.printDetails();
    }
}

/*
No arquivo A não é compilado, pois dentro do método main faltou ser instanciado
o objeto song ==> 'StreamingSong song = new StreamingSong();' por isso o código
não roda, pois está acessando métodos e atributos daquela classe sem ter o
correspondente do objeto daquela classe.
 */

class Episode {
    int seriesNumber;
    int episodeNumber;

    void skipIntro() {
        System.out.println("Skipping intro...");
    }

    void skipToNext() {
        System.out.println("Loading next episode...");
    }
}

class EpisodeTestDrive {
    public static void main(String[] args) {

        Episode episode = new Episode();
        episode.seriesNumber = 4;
        episode.play();
        episode.skipIntro();
    }
}
/*
No arquivo B acontece o erro no episode.play(); pois não existe o método
método play na classe Episode, a solução mais fácil seria excluir essa linha chamando este
método ou de fato implementar o método play.
 */