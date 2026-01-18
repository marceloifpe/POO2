package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class PersistenciaSerializacao<T> implements Base<T> {

    protected List<T> dados;
    protected String nomeArquivo;

    public PersistenciaSerializacao(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.dados = carregarDados();
    }

    @SuppressWarnings("unchecked")
    private List<T> carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Arquivo não existe, retorna lista vazia
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar dados do arquivo " + nomeArquivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    protected void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    @Override
    public void inserir(T entidade) {
        dados.add(entidade);
        salvarDados();
    }

    @Override
    public void modificar(T entidade) {
        // A modificação é feita diretamente na lista 'dados' se a entidade for mutável.
        // Para garantir a persistência, basta chamar salvarDados().
        salvarDados();
    }

    @Override
    public T consultar(int id) {
        // Implementação específica de consulta por ID será feita nas classes filhas
        return null;
    }

    @Override
    public void apagar(int id) {
        // Implementação específica de apagar por ID será feita nas classes filhas
    }

    @Override
    public List<T> listar() {
        return dados;
    }
}