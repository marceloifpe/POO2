package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Cliente cliente;
    private LocalDate dataVenda;
    private List<ItemVenda> itens;

    // Construtor
    public Venda(int id, Cliente cliente, LocalDate dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.dataVenda = dataVenda;
        this.itens = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    // Método para calcular o valor total da venda
    public double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    // Método para gerar a fatura (representação em String)
    public String gerarFatura() {
        StringBuilder fatura = new StringBuilder();
        fatura.append("========================================\n");
        fatura.append("              FATURA DE VENDA           \n");
        fatura.append("========================================\n");
        fatura.append("ID da Venda: ").append(id).append("\n");
        fatura.append("Data: ").append(dataVenda).append("\n");
        fatura.append("Cliente: ").append(cliente.getNome()).append(" (CPF: ").append(cliente.getCpf()).append(")\n");
        fatura.append("----------------------------------------\n");
        fatura.append("Item | Livro | Qtd | Preco Unit. | Subtotal\n");
        fatura.append("----------------------------------------\n");

        for (int i = 0; i < itens.size(); i++) {
            ItemVenda item = itens.get(i);
            fatura.append(String.format("%-4d | %-15s | %-3d | %-11.2f | %.2f\n",
                    i + 1,
                    item.getLivro().getTitulo(),
                    item.getQuantidade(),
                    item.getPrecoUnitario(),
                    item.calcularSubtotal()));
        }

        fatura.append("----------------------------------------\n");
        fatura.append(String.format("TOTAL: %.2f\n", calcularTotal()));
        fatura.append("========================================\n");
        return fatura.toString();
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", dataVenda=" + dataVenda +
                ", total=" + calcularTotal() +
                '}';
    }
}