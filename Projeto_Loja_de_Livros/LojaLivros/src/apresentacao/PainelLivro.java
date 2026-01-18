package apresentacao;

import modelo.Livro;
import negocio.ServicoLivro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelLivro extends JPanel {
    private ServicoLivro servicoLivro;
    private JTable tabelaLivros;
    private DefaultTableModel modeloTabela;

    // Campos de formulário
    private JTextField campoId;
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoIsbn;
    private JTextField campoEditora;
    private JTextField campoEdicao;
    private JTextField campoPreco;
    private JTextField campoEstoque;
    private JTextField campoEstoqueMinimo;

    public PainelLivro(ServicoLivro servicoLivro) {
        this.servicoLivro = servicoLivro;
        setLayout(new BorderLayout());

        // --- Painel de Formulário (Norte) ---
        JPanel painelFormulario = new JPanel(new GridLayout(5, 4, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Livro"));

        campoId = new JTextField();
        campoId.setEditable(false);
        campoTitulo = new JTextField();
        campoAutor = new JTextField();
        campoIsbn = new JTextField();
        campoEditora = new JTextField();
        campoEdicao = new JTextField();
        campoPreco = new JTextField();
        campoEstoque = new JTextField();
        campoEstoqueMinimo = new JTextField();

        painelFormulario.add(new JLabel("ID:"));
        painelFormulario.add(campoId);
        painelFormulario.add(new JLabel("Título:"));
        painelFormulario.add(campoTitulo);
        painelFormulario.add(new JLabel("Autor:"));
        painelFormulario.add(campoAutor);
        painelFormulario.add(new JLabel("ISBN:"));
        painelFormulario.add(campoIsbn);
        painelFormulario.add(new JLabel("Editora:"));
        painelFormulario.add(campoEditora);
        painelFormulario.add(new JLabel("Edição:"));
        painelFormulario.add(campoEdicao);
        painelFormulario.add(new JLabel("Preço:"));
        painelFormulario.add(campoPreco);
        painelFormulario.add(new JLabel("Estoque:"));
        painelFormulario.add(campoEstoque);
        painelFormulario.add(new JLabel("Estoque Mínimo:"));
        painelFormulario.add(campoEstoqueMinimo);

        // --- Painel de Botões (Centro) ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnInserir = new JButton("Inserir");
        JButton btnModificar = new JButton("Modificar");
        JButton btnApagar = new JButton("Apagar");
        JButton btnConsultar = new JButton("Consultar por ID");
        JButton btnLimpar = new JButton("Limpar Campos");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnModificar);
        painelBotoes.add(btnApagar);
        painelBotoes.add(btnConsultar);
        painelBotoes.add(btnLimpar);

        // --- Tabela de Livros (Sul) ---
        // CORREÇÃO APLICADA AQUI: Adicionando "Editora"
        String[] colunas = { "ID", "Título", "Autor", "ISBN", "Editora", "Preço", "Estoque", "Estoque Mínimo" };
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaLivros = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);

        // Adiciona os painéis ao PainelLivro
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelFormulario, BorderLayout.NORTH);
        painelSuperior.add(painelBotoes, BorderLayout.CENTER);

        add(painelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Carrega os dados iniciais
        carregarTabela();

        // --- Listeners ---
        btnInserir.addActionListener(e -> inserirLivro());
        btnModificar.addActionListener(e -> modificarLivro());
        btnApagar.addActionListener(e -> apagarLivro());
        btnConsultar.addActionListener(e -> consultarLivro());
        btnLimpar.addActionListener(e -> limparCampos());

        tabelaLivros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaLivros.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        List<Livro> livros = servicoLivro.listar();
        for (Livro livro : livros) {
            // CORREÇÃO APLICADA AQUI: Adicionando livro.getEditora()
            modeloTabela.addRow(new Object[] {
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getIsbn(),
                    livro.getEditora(), // <-- NOVO
                    livro.getPreco(),
                    livro.getQuantidadeEstoque(),
                    livro.getEstoqueMinimo()
            });
        }
        // Alerta de estoque mínimo
        List<Livro> alertas = servicoLivro.listarLivrosAbaixoEstoqueMinimo();
        if (!alertas.isEmpty()) {
            StringBuilder sb = new StringBuilder("ALERTA DE ESTOQUE MÍNIMO:\n");
            for (Livro livro : alertas) {
                sb.append("- ").append(livro.getTitulo()).append(" (Estoque: ").append(livro.getQuantidadeEstoque())
                        .append(")\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Alerta de Estoque", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoTitulo.setText("");
        campoAutor.setText("");
        campoIsbn.setText("");
        campoEditora.setText("");
        campoEdicao.setText("");
        campoPreco.setText("");
        campoEstoque.setText("");
        campoEstoqueMinimo.setText("");
        tabelaLivros.clearSelection();
    }

    private void preencherCamposComSelecao() {
        int linha = tabelaLivros.getSelectedRow();
        if (linha >= 0) {
            campoId.setText(modeloTabela.getValueAt(linha, 0).toString());
            campoTitulo.setText(modeloTabela.getValueAt(linha, 1).toString());
            // Busca o objeto completo para preencher os campos não visíveis na tabela
            Livro livro = servicoLivro.consultar((int) modeloTabela.getValueAt(linha, 0));
            if (livro != null) {
                campoAutor.setText(livro.getAutor());
                campoIsbn.setText(livro.getIsbn());
                campoEditora.setText(livro.getEditora());
                campoEdicao.setText(String.valueOf(livro.getEdicao()));
                campoPreco.setText(String.valueOf(livro.getPreco()));
                campoEstoque.setText(String.valueOf(livro.getQuantidadeEstoque()));
                campoEstoqueMinimo.setText(String.valueOf(livro.getEstoqueMinimo()));
            }
        }
    }

    private Livro criarLivroDoFormulario(boolean isModificacao) {
        try {
            int id = isModificacao && !campoId.getText().isEmpty() ? Integer.parseInt(campoId.getText()) : 0;
            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            String isbn = campoIsbn.getText();
            String editora = campoEditora.getText();
            int edicao = Integer.parseInt(campoEdicao.getText());
            double preco = Double.parseDouble(campoPreco.getText());
            int estoque = Integer.parseInt(campoEstoque.getText());
            int estoqueMinimo = Integer.parseInt(campoEstoqueMinimo.getText());

            return new Livro(id, titulo, autor, isbn, editora, edicao, preco, estoque, estoqueMinimo);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro de formato numérico. Verifique Preço, Edição, Estoque e Estoque Mínimo.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void inserirLivro() {
        Livro novoLivro = criarLivroDoFormulario(false);
        if (novoLivro != null) {
            try {
                servicoLivro.inserir(novoLivro);
                JOptionPane.showMessageDialog(this, "Livro inserido com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificarLivro() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela ou preencha o ID para modificar.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Livro livroModificado = criarLivroDoFormulario(true);
        if (livroModificado != null) {
            try {
                servicoLivro.modificar(livroModificado);
                JOptionPane.showMessageDialog(this, "Livro modificado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void apagarLivro() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela ou preencha o ID para apagar.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(campoId.getText());
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja apagar o livro ID: " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                servicoLivro.apagar(id);
                JOptionPane.showMessageDialog(this, "Livro apagado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarLivro() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do livro para consultar:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Livro livro = servicoLivro.consultar(id);
                if (livro != null) {
                    campoId.setText(String.valueOf(livro.getId()));
                    campoTitulo.setText(livro.getTitulo());
                    campoAutor.setText(livro.getAutor());
                    campoIsbn.setText(livro.getIsbn());
                    campoEditora.setText(livro.getEditora());
                    campoEdicao.setText(String.valueOf(livro.getEdicao()));
                    campoPreco.setText(String.valueOf(livro.getPreco()));
                    campoEstoque.setText(String.valueOf(livro.getQuantidadeEstoque()));
                    campoEstoqueMinimo.setText(String.valueOf(livro.getEstoqueMinimo()));
                } else {
                    JOptionPane.showMessageDialog(this, "Livro com ID " + id + " não encontrado.", "Consulta",
                            JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}