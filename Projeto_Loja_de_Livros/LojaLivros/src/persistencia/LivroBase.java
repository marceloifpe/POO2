package persistencia;

import modelo.Livro;
import java.util.List;
import java.util.stream.Collectors;

public class LivroBase extends PersistenciaSerializacao<Livro> {

    private static final String NOME_ARQUIVO = "livros.dat";
    private static int proximoId = 1;

    public LivroBase() {
        super(NOME_ARQUIVO);
        // Garante que o proximoId seja maior que o maior ID existente ao carregar
        if (!dados.isEmpty()) {
            proximoId = dados.stream().mapToInt(Livro::getId).max().orElse(0) + 1;
        }
    }

    @Override
    public void inserir(Livro livro) {
        livro.setId(proximoId++);
        super.inserir(livro);
    }

    @Override
    public void modificar(Livro livroModificado) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == livroModificado.getId()) {
                dados.set(i, livroModificado);
                super.modificar(livroModificado); // Chama salvarDados()
                return;
            }
        }
    }

    @Override
    public Livro consultar(int id) {
        return dados.stream()
                .filter(livro -> livro.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void apagar(int id) {
        dados.removeIf(livro -> livro.getId() == id);
        salvarDados();
    }

    // Filtro para buscar livros por título (necessário na hora da venda)
    public List<Livro> buscarPorTitulo(String titulo) {
        return dados.stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }
}