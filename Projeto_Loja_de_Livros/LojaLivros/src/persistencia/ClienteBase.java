package persistencia;

import modelo.Cliente;

public class ClienteBase extends PersistenciaSerializacao<Cliente> {

    private static final String NOME_ARQUIVO = "clientes.dat";
    private static int proximoId = 1;

    public ClienteBase() {
        super(NOME_ARQUIVO);
        // Garante que o proximoId seja maior que o maior ID existente ao carregar
        if (!dados.isEmpty()) {
            proximoId = dados.stream().mapToInt(Cliente::getId).max().orElse(0) + 1;
        }
    }

    @Override
    public void inserir(Cliente cliente) {
        cliente.setId(proximoId++);
        super.inserir(cliente);
    }

    @Override
    public void modificar(Cliente clienteModificado) {
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).getId() == clienteModificado.getId()) {
                dados.set(i, clienteModificado);
                super.modificar(clienteModificado); // Chama salvarDados()
                return;
            }
        }
    }

    @Override
    public Cliente consultar(int id) {
        return dados.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void apagar(int id) {
        dados.removeIf(cliente -> cliente.getId() == id);
        salvarDados();
    }

    // Filtro para buscar cliente por CPF
    public Cliente consultarPorCpf(String cpf) {
        return dados.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}