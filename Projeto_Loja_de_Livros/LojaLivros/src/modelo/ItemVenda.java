package modelo;

import java.io.Serializable;

public class ItemVenda implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Livro livro;
    private int quantidade;
    private double precoUnitario;

    // Construtor
    public ItemVenda(int id, Livro livro, int quantidade) {
        this.id = id;
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoUnitario = livro.getPreco(); // Preço do livro no momento da venda
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
        this.precoUnitario = livro.getPreco(); // Atualiza o preço unitário se o livro for alterado
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    // Método para calcular o subtotal
    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "id=" + id +
                ", livro=" + livro.getTitulo() +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", subtotal=" + calcularSubtotal() +
                '}';
    }
}