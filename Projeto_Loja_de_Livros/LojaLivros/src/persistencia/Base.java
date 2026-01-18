package persistencia;

import java.util.List;

public interface Base<T> {
    void inserir(T entidade);

    void modificar(T entidade);

    void apagar(int id);

    T consultar(int id);

    List<T> listar();
}