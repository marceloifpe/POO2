package negocio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UtilitarioArquivo {

    public static boolean salvarConteudo(String nomeArquivo, String conteudo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(nomeArquivo))) {
            out.print(conteudo);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo " + nomeArquivo + ": " + e.getMessage());
            return false;
        }
    }
}