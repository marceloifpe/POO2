package apresentacao;

import modelo.Cliente;
import negocio.ServicoCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PainelCliente extends JPanel {
    private ServicoCliente servicoCliente;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;

    // Campos de formulário
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoEmail;
    private JTextField campoTelefone;

    public PainelCliente(ServicoCliente servicoCliente) {
        this.servicoCliente = servicoCliente;
        setLayout(new BorderLayout());

        // --- Painel de Formulário ---
        JPanel painelFormulario = new JPanel(new GridLayout(3, 4, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        campoId = new JTextField();
        campoId.setEditable(false);
        campoNome = new JTextField();
        campoCpf = new JTextField();
        campoEmail = new JTextField();
        campoTelefone = new JTextField();

        painelFormulario.add(new JLabel("ID:"));
        painelFormulario.add(campoId);
        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(campoNome);
        painelFormulario.add(new JLabel("CPF:"));
        painelFormulario.add(campoCpf);
        painelFormulario.add(new JLabel("Email:"));
        painelFormulario.add(campoEmail);
        painelFormulario.add(new JLabel("Telefone:"));
        painelFormulario.add(campoTelefone);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnInserir = new JButton("Inserir");
        JButton btnModificar = new JButton("Modificar");
        JButton btnApagar = new JButton("Apagar");
        JButton btnLimpar = new JButton("Limpar Campos");

        painelBotoes.add(btnInserir);
        painelBotoes.add(btnModificar);
        painelBotoes.add(btnApagar);
        painelBotoes.add(btnLimpar);

        // --- Tabela de Cliente ---
        String[] colunas = { "ID", "Nome", "CPF", "Email", "Telefone" };
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);

        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelFormulario, BorderLayout.NORTH);
        painelSuperior.add(painelBotoes, BorderLayout.CENTER);

        add(painelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        carregarTabela();

        btnInserir.addActionListener(e -> inserirCliente());
        btnModificar.addActionListener(e -> modificarCliente());
        btnApagar.addActionListener(e -> apagarCliente());
        btnLimpar.addActionListener(e -> limparCampos());

        tabelaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaClientes.getSelectedRow() != -1) {
                preencherCamposComSelecao();
            }
        });
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        List<Cliente> clientes = servicoCliente.listar();
        for (Cliente cliente : clientes) {
            modeloTabela.addRow(new Object[] {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getEmail(),
                    cliente.getTelefone()
            });
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        tabelaClientes.clearSelection();
    }

    private void preencherCamposComSelecao() {
        int linha = tabelaClientes.getSelectedRow();
        if (linha >= 0) {
            campoId.setText(modeloTabela.getValueAt(linha, 0).toString());
            campoNome.setText(modeloTabela.getValueAt(linha, 1).toString());
            campoCpf.setText(modeloTabela.getValueAt(linha, 2).toString());
            campoEmail.setText(modeloTabela.getValueAt(linha, 3).toString());
            campoTelefone.setText(modeloTabela.getValueAt(linha, 4).toString());
        }
    }

    private Cliente criarClienteDoFormulario(boolean isModificacao) {
        try {
            int id = isModificacao && !campoId.getText().isEmpty() ? Integer.parseInt(campoId.getText()) : 0;
            String nome = campoNome.getText();
            String cpf = campoCpf.getText();
            String email = campoEmail.getText();
            String telefone = campoTelefone.getText();

            if (nome.trim().isEmpty() || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome e CPF são obrigatórios.");
            }

            return new Cliente(id, nome, cpf, email, telefone);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void inserirCliente() {
        Cliente novoCliente = criarClienteDoFormulario(false);
        if (novoCliente != null) {
            try {
                servicoCliente.inserir(novoCliente);
                JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modificarCliente() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela ou preencha o ID para modificar.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Cliente clienteModificado = criarClienteDoFormulario(true);
        if (clienteModificado != null) {
            try {
                servicoCliente.modificar(clienteModificado);
                JOptionPane.showMessageDialog(this, "Cliente modificado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Negócio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void apagarCliente() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela ou preencha o ID para apagar.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(campoId.getText());
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja apagar o cliente ID: " + id + "?", "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                servicoCliente.apagar(id);
                JOptionPane.showMessageDialog(this, "Cliente apagado com sucesso!", "Sucesso",
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
}