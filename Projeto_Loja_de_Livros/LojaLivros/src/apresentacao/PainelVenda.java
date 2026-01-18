package apresentacao;

import modelo.Cliente;
import modelo.Livro;
import modelo.Venda;
import modelo.ItemVenda;
import negocio.ServicoLivro;
import negocio.ServicoCliente;
import negocio.ServicoVenda;
import negocio.UtilitarioArquivo;
// import negocio.UtilitarioPDF; // Removido, pois não será mais usado

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PainelVenda extends JPanel {
    private ServicoLivro servicoLivro;
    private ServicoCliente servicoCliente;
    private ServicoVenda servicoVenda;

    private JTextField campoCpfCliente;
    private JLabel labelNomeCliente;
    private JTextField campoIdLivro;
    private JTextField campoQtdLivro;
    private JTable tabelaItens;
    private DefaultTableModel modeloTabelaItens;
    private JLabel labelTotalVenda;

    private Cliente clienteAtual;
    private List<ItemVenda> itensVenda;
    private int proximoItemId = 1;

    public PainelVenda(ServicoLivro servicoLivro, ServicoCliente servicoCliente, ServicoVenda servicoVenda) {
        this.servicoLivro = servicoLivro;
        this.servicoCliente = servicoCliente;
        this.servicoVenda = servicoVenda;
        this.itensVenda = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Painel Superior (Cliente e Livro) ---
        JPanel painelSuperior = new JPanel(new GridLayout(2, 1, 5, 5));
        painelSuperior.setBorder(BorderFactory.createTitledBorder("Nova Venda"));

        // Cliente
        JPanel painelCliente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoCpfCliente = new JTextField(15);
        JButton btnBuscarCliente = new JButton("Buscar Cliente (CPF)");
        labelNomeCliente = new JLabel("Cliente: Não Selecionado");
        painelCliente.add(new JLabel("CPF do Cliente:"));
        painelCliente.add(campoCpfCliente);
        painelCliente.add(btnBuscarCliente);
        painelCliente.add(labelNomeCliente);

        // Livro
        JPanel painelLivro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoIdLivro = new JTextField(5);
        campoQtdLivro = new JTextField(5);
        JButton btnAdicionarItem = new JButton("Adicionar Livro");
        painelLivro.add(new JLabel("ID do Livro:"));
        painelLivro.add(campoIdLivro);
        painelLivro.add(new JLabel("Quantidade:"));
        painelLivro.add(campoQtdLivro);
        painelLivro.add(btnAdicionarItem);

        painelSuperior.add(painelCliente);
        painelSuperior.add(painelLivro);

        // --- Tabela de Itens (Centro) ---
        String[] colunas = { "ID", "Livro", "Qtd", "Preço Unit.", "Subtotal" };
        modeloTabelaItens = new DefaultTableModel(colunas, 0);
        tabelaItens = new JTable(modeloTabelaItens);
        JScrollPane scrollPane = new JScrollPane(tabelaItens);

        // --- Painel Inferior (Total e Botões) ---
        JPanel painelInferior = new JPanel(new BorderLayout());
        labelTotalVenda = new JLabel("TOTAL: R$ 0.00", SwingConstants.RIGHT);
        labelTotalVenda.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnFinalizarVenda = new JButton("Finalizar Venda e Emitir Fatura");
        JButton btnCancelarVenda = new JButton("Cancelar Venda");
        painelBotoes.add(btnCancelarVenda);
        painelBotoes.add(btnFinalizarVenda);

        painelInferior.add(labelTotalVenda, BorderLayout.NORTH);
        painelInferior.add(painelBotoes, BorderLayout.SOUTH);

        // Adiciona ao painel principal
        add(painelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        btnBuscarCliente.addActionListener(e -> buscarCliente());
        btnAdicionarItem.addActionListener(e -> adicionarItem());
        btnFinalizarVenda.addActionListener(e -> finalizarVenda());
        btnCancelarVenda.addActionListener(e -> cancelarVenda());
    }

    private void buscarCliente() {
        String cpf = campoCpfCliente.getText().trim();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o CPF do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clienteAtual = servicoCliente.listar().stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (clienteAtual != null) {
            labelNomeCliente.setText("Cliente: " + clienteAtual.getNome());
            JOptionPane.showMessageDialog(this, "Cliente encontrado: " + clienteAtual.getNome(), "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            labelNomeCliente.setText("Cliente: Não Encontrado");
            JOptionPane.showMessageDialog(this, "Cliente com CPF " + cpf + " não encontrado.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarItem() {
        if (clienteAtual == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idLivro = Integer.parseInt(campoIdLivro.getText());
            int quantidade = Integer.parseInt(campoQtdLivro.getText());

            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Livro livro = servicoLivro.consultar(idLivro);
            if (livro == null) {
                JOptionPane.showMessageDialog(this, "Livro com ID " + idLivro + " não encontrado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (livro.getQuantidadeEstoque() < quantidade) {
                JOptionPane.showMessageDialog(this, "Estoque insuficiente. Disponível: " + livro.getQuantidadeEstoque(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ItemVenda novoItem = new ItemVenda(proximoItemId++, livro, quantidade);
            itensVenda.add(novoItem);
            atualizarTabelaItens();
            campoIdLivro.setText("");
            campoQtdLivro.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID do Livro e Quantidade devem ser números inteiros.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelaItens() {
        modeloTabelaItens.setRowCount(0);
        double total = 0;
        for (ItemVenda item : itensVenda) {
            modeloTabelaItens.addRow(new Object[] {
                    item.getId(),
                    item.getLivro().getTitulo(),
                    item.getQuantidade(),
                    String.format("%.2f", item.getPrecoUnitario()),
                    String.format("%.2f", item.calcularSubtotal())
            });
            total += item.calcularSubtotal();
        }
        labelTotalVenda.setText(String.format("TOTAL: R$ %.2f", total));
    }

    private void finalizarVenda() {
        if (clienteAtual == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para finalizar a venda.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (itensVenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adicione itens à venda.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Venda novaVenda = new Venda(0, clienteAtual, LocalDate.now());
        for (ItemVenda item : itensVenda) {
            novaVenda.adicionarItem(item);
        }

        try {
            String fatura = servicoVenda.registrarVenda(novaVenda);

            // Geração da Fatura em PDF
            String nomeArquivoTxt = "fatura_" + novaVenda.getId() + ".txt";

            if (UtilitarioArquivo.salvarConteudo(nomeArquivoTxt, fatura)) {
                JOptionPane.showMessageDialog(this, "Fatura salva como: " + nomeArquivoTxt, "Geração de Arquivo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar a fatura em arquivo.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso! Fatura emitida.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            cancelarVenda(); // Limpa a tela após a venda
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelarVenda() {
        clienteAtual = null;
        itensVenda.clear();
        proximoItemId = 1;
        campoCpfCliente.setText("");
        labelNomeCliente.setText("Cliente: Não Selecionado");
        campoIdLivro.setText("");
        campoQtdLivro.setText("");
        atualizarTabelaItens();
    }
}