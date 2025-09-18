package livraria.adapters.out;

import livraria.model.Autor;

import java.util.List;

public interface AutorRepository {
    void cadastrarAutor(Autor autor);
    Autor buscarAutorPorId(Long id);
    List<Autor> listarTodos();
}
