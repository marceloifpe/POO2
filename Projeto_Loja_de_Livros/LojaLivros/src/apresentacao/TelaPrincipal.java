package apresentacao;

import negocio.ServicoLivro;
import negocio.ServicoCliente;
import negocio.ServicoVenda;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private JFrame telaAnterior;
    private ServicoLivro servicoLivro;
    private ServicoCliente servicoCliente;
    private ServicoVenda servicoVenda;

    public TelaPrincipal(JFrame telaAnterior, ServicoLivro servicoLivro, ServicoCliente servicoCliente,
            ServicoVenda servicoVenda) {
        this.telaAnterior = telaAnterior;
        this.servicoLivro = servicoLivro;
        this.servicoCliente = servicoCliente;
        this.servicoVenda = servicoVenda;

        setTitle("Sistema de Gestão - Loja de Livros");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação do painel principal com abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Painel de Livros (CRUD)
        PainelLivro painelLivro = new PainelLivro(servicoLivro);
        tabbedPane.addTab("Gestão de Livros", painelLivro);

        // 2. Painel de Clientes (CRUD)
        PainelCliente painelCliente = new PainelCliente(servicoCliente);
        tabbedPane.addTab("Gestão de Clientes", painelCliente);

        // 3. Painel de Vendas (Demonstração de Venda e Fatura)
        PainelVenda painelVenda = new PainelVenda(servicoLivro, servicoCliente, servicoVenda);
        tabbedPane.addTab("Registro de Vendas", painelVenda);

        // 4. Painel de Relatórios (Consultas de Gestão Vendas)
        PainelRelatorios painelRelatorios = new PainelRelatorios(servicoVenda);
        tabbedPane.addTab("Relatórios de Gestão", painelRelatorios);

        add(tabbedPane, BorderLayout.CENTER);

        // Botão Voltar para Tela Inicial
        JButton btnVoltar = new JButton("Voltar para o Menu Principal");
        btnVoltar.addActionListener(e -> voltarParaTelaInicial());
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSul.add(btnVoltar);
        add(painelSul, BorderLayout.SOUTH);
    }

    private void voltarParaTelaInicial() {
        this.dispose();
        telaAnterior.setVisible(true);
    }
}