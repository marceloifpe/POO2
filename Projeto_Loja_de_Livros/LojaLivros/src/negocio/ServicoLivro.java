package negocio;

import modelo.Livro;
import persistencia.LivroBase;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoLivro {
    private LivroBase dao;

    public ServicoLivro() {
        this.dao = new LivroBase();
    }

    public void inserir(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título do livro não pode ser vazio.");
        }
        dao.inserir(livro);
    }

    public void modificar(Livro livro) {
        // Regras de negócio antes de modificar
        if (dao.consultar(livro.getId()) == null) {
            throw new IllegalArgumentException("Livro não encontrado para modificação.");
        }
        dao.modificar(livro);
    }

    public void apagar(int id) {
        dao.apagar(id);
    }

    public Livro consultar(int id) {
        return dao.consultar(id);
    }

    public List<Livro> listar() {
        return dao.listar();
    }

    public List<Livro> listarLivrosAbaixoEstoqueMinimo() {
        return dao.listar().stream()
                .filter(Livro::estaAbaixoEstoqueMinimo)
                .collect(Collectors.toList());
    }
}