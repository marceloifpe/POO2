package negocio;

import modelo.Venda;
import modelo.Livro;
import modelo.ItemVenda;
import persistencia.VendaBase;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicoVenda {
    private VendaBase dao;
    private ServicoLivro servicoLivro; // Para atualizar estoque e consultar lista total

    public ServicoVenda(ServicoLivro servicoLivro) {
        this.dao = new VendaBase();
        this.servicoLivro = servicoLivro;
    }

    public String registrarVenda(Venda venda) {
        // 1. Processar itens e atualizar estoque
        for (ItemVenda item : venda.getItens()) {
            Livro livro = item.getLivro();
            int novaQuantidade = livro.getQuantidadeEstoque() - item.getQuantidade();

            if (novaQuantidade < 0) {
                throw new IllegalArgumentException("Estoque insuficiente para o livro: " + livro.getTitulo());
            }

            livro.setQuantidadeEstoque(novaQuantidade);
            servicoLivro.modificar(livro); // Persiste a alteração no estoque

            // 2. Verificar alerta de estoque mínimo
            if (livro.estaAbaixoEstoqueMinimo()) {
                System.out.println("ALERTA DE ESTOQUE MÍNIMO: O livro '" + livro.getTitulo() + "' está com "
                        + novaQuantidade + " unidades.");
            }
        }

        // 3. Persistir a venda
        dao.inserir(venda);

        // 4. Retornar a fatura
        return venda.gerarFatura();
    }

    public Venda consultar(int id) {
        return dao.consultar(id);
    }

    public List<Venda> listar() {
        return dao.listar();
    }

    // -------------------------------------------------------------------
    // Relatórios de Gestão
    // -------------------------------------------------------------------

    public double calcularTotalVendasMes(int mes, int ano) {
        return dao.listar().stream()
                .filter(v -> v.getDataVenda().getMonthValue() == mes && v.getDataVenda().getYear() == ano)
                .mapToDouble(Venda::calcularTotal)
                .sum();
    }

    public Livro produtoMaisVendido() {
        Map<Livro, Integer> contagem = dao.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .collect(Collectors.groupingBy(ItemVenda::getLivro,
                        Collectors.summingInt(ItemVenda::getQuantidade)));

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Livro produtoMenosVendido() {
        // 1. Cria um mapa de ID do Livro -> Quantidade Vendida
        Map<Integer, Integer> vendasPorId = dao.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getLivro().getId(),
                        Collectors.summingInt(ItemVenda::getQuantidade)));

        // 2. Busca TODOS os livros do sistema (Estoque)
        List<Livro> todosOsLivros = servicoLivro.listar();

        if (todosOsLivros.isEmpty())
            return null;

        // 3. Compara qual livro tem a menor quantidade no mapa
        return todosOsLivros.stream()
                .min(Comparator.comparingInt(livro -> vendasPorId.getOrDefault(livro.getId(), 0)))
                .orElse(null);
    }

    public String melhorCliente() {
        Map<String, Double> totalGastoPorCliente = dao.listar().stream()
                .collect(Collectors.groupingBy(v -> v.getCliente().getNome(),
                        Collectors.summingDouble(Venda::calcularTotal)));

        return totalGastoPorCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Nenhum cliente encontrado.");
    }

    public LocalDate diaComMaisVendas() {
        Map<LocalDate, Long> contagemPorDia = dao.listar().stream()
                .collect(Collectors.groupingBy(Venda::getDataVenda, Collectors.counting()));

        return contagemPorDia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}