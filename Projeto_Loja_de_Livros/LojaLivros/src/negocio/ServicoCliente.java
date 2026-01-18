package negocio;

import modelo.Cliente;
import persistencia.ClienteBase;
import java.util.List;

public class ServicoCliente {
    private ClienteBase dao;

    public ServicoCliente() {
        this.dao = new ClienteBase();
    }

    public void inserir(Cliente cliente) {
        if (dao.consultarPorCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }
        dao.inserir(cliente);
    }

    public void modificar(Cliente cliente) {
        if (dao.consultar(cliente.getId()) == null) {
            throw new IllegalArgumentException("Cliente não encontrado para modificação.");
        }
        dao.modificar(cliente);
    }

    public void apagar(int id) {
        dao.apagar(id);
    }

    public Cliente consultar(int id) {
        return dao.consultar(id);
    }

    public List<Cliente> listar() {
        return dao.listar();
    }
}