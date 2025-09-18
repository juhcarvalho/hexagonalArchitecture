package livraria.adapters.out;

import livraria.model.Livro;

import java.util.List;

public interface LivroRepository {
    void cadastrarLivro(Livro livro);
    Livro buscarLivroPorId(Long id);
    List<Livro> listarTodosLivros();
}
