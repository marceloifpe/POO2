package persistencia;

import modelo.Venda;

public class VendaBase extends PersistenciaSerializacao<Venda> {

    private static final String NOME_ARQUIVO = "vendas.dat";
    private static int proximoId = 1;

    public VendaBase() {
        super(NOME_ARQUIVO);
        // Garante que o proximoId seja maior que o maior ID existente ao carregar
        if (!dados.isEmpty()) {
            proximoId = dados.stream().mapToInt(Venda::getId).max().orElse(0) + 1;
        }
    }

    @Override
    public void inserir(Venda venda) {
        venda.setId(proximoId++);
        super.inserir(venda);
    }

    @Override
    public void modificar(Venda vendaModificada) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == vendaModificada.getId()) {
                dados.set(i, vendaModificada);
                super.modificar(vendaModificada); // Chama salvarDados()
                return;
            }
        }
    }

    @Override
    public Venda consultar(int id) {
        return dados.stream()
                .filter(venda -> venda.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void apagar(int id) {
        dados.removeIf(venda -> venda.getId() == id);
        salvarDados();
    }
}