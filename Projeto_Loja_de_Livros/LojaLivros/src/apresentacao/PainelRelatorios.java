package apresentacao;

import modelo.Livro;
import negocio.ServicoVenda;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.YearMonth;

public class PainelRelatorios extends JPanel {
    private ServicoVenda servicoVenda;
    private JTextArea areaRelatorios;
    private JComboBox<String> comboMes;
    private JComboBox<String> comboAno;

    public PainelRelatorios(ServicoVenda servicoVenda) {
        this.servicoVenda = servicoVenda;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Painel de Controle ---
        JPanel painelControle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnGerarRelatorios = new JButton("Gerar Relatórios");

        comboMes = new JComboBox<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" });
        comboAno = new JComboBox<>();
        int anoAtual = LocalDate.now().getYear();
        for (int i = anoAtual; i >= anoAtual - 5; i--) {
            comboAno.addItem(String.valueOf(i));
        }
        comboMes.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
        comboAno.setSelectedItem(String.valueOf(anoAtual));

        painelControle.add(new JLabel("Mês:"));
        painelControle.add(comboMes);
        painelControle.add(new JLabel("Ano:"));
        painelControle.add(comboAno);
        painelControle.add(btnGerarRelatorios);

        // --- Área de TXT para Relatórios ---
        areaRelatorios = new JTextArea();
        areaRelatorios.setEditable(false);
        areaRelatorios.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaRelatorios);

        add(painelControle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnGerarRelatorios.addActionListener(e -> gerarRelatorios());
    }

    private void gerarRelatorios() {
        areaRelatorios.setText(""); // Limpa a área

        int mes = Integer.parseInt((String) comboMes.getSelectedItem());
        int ano = Integer.parseInt((String) comboAno.getSelectedItem());

        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("          RELATÓRIOS DE GESTÃO - LOJA DE LIVROS\n");
        sb.append("==================================================\n\n");

        // 1. Total de vendas do mês
        double totalVendas = servicoVenda.calcularTotalVendasMes(mes, ano);
        sb.append(String.format("1. Total de Vendas em %s/%d: R$ %.2f\n\n", mes, ano, totalVendas));

        // 2. Produto mais vendido
        Livro maisVendido = servicoVenda.produtoMaisVendido();
        sb.append("2. Produto Mais Vendido:\n");
        sb.append(maisVendido != null
                ? String.format("   - Título: %s (ID: %d)\n\n", maisVendido.getTitulo(), maisVendido.getId())
                : "   - Nenhum livro vendido.\n\n");

        // 3. Produto menos vendido
        Livro menosVendido = servicoVenda.produtoMenosVendido();
        sb.append("3. Produto Menos Vendido:\n");
        sb.append(menosVendido != null
                ? String.format("   - Título: %s (ID: %d)\n\n", menosVendido.getTitulo(), menosVendido.getId())
                : "   - Nenhum livro vendido.\n\n");

        // 4. Melhor cliente
        String melhorCliente = servicoVenda.melhorCliente();
        sb.append("4. Melhor Cliente (Maior Valor Gasto):\n");
        sb.append(String.format("   - Nome: %s\n\n", melhorCliente));

        // 5. Dia do mês em que houve mais vendas
        LocalDate diaMaisVendas = servicoVenda.diaComMaisVendas();
        sb.append("5. Dia com Mais Vendas:\n");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        sb.append(diaMaisVendas != null ? String.format("   - Data: %s\n\n", diaMaisVendas.format(formatador))
                : "   - Nenhuma venda registrada.\n\n");

        areaRelatorios.setText(sb.toString());
    }
}