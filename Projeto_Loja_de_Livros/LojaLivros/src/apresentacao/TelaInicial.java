package apresentacao;

import negocio.ServicoLivro;
import negocio.ServicoCliente;
import negocio.ServicoVenda;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    private ServicoLivro servicoLivro;
    private ServicoCliente servicoCliente;
    private ServicoVenda servicoVenda;

    public TelaInicial(ServicoLivro servicoLivro, ServicoCliente servicoCliente, ServicoVenda servicoVenda) {
        this.servicoLivro = servicoLivro;
        this.servicoCliente = servicoCliente;
        this.servicoVenda = servicoVenda;

        setTitle("Menu Principal - Loja de Livros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Sistema de Gestão da Loja de Livros", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        JButton btnIniciar = new JButton("Iniciar Aplicação");
        btnIniciar.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        add(btnIniciar, gbc);

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        add(btnSair, gbc);

        btnIniciar.addActionListener(e -> iniciarAplicacao());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void iniciarAplicacao() {
        // Fecha a tela inicial
        this.dispose();
        // Abre a tela principal do sistema
        new TelaPrincipal(this, servicoLivro, servicoCliente, servicoVenda).setVisible(true);
    }
}