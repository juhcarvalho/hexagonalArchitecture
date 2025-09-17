package livraria;

import java.util.List;

public interface LivroRepository {
    void cadastrarLivro(Livro livro);
    Livro buscarLivroPorId(Long id);
    List<Livro> listarTodosLivros();
}
