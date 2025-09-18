package livraria;

import livraria.adapters.out.LivroRepositoryH2;
import livraria.model.Livro;

import java.util.List;

public class LivroService {
    LivroRepositoryH2 repository;

    public LivroService(LivroRepositoryH2 repository) {
        this.repository = repository;
    }

    public void cadastrarLivro(Livro livro) {
        repository.cadastrarLivro(livro);
    }
    public Livro buscarLivroPorId(Long id) {
        return repository.buscarLivroPorId(id);
    }
    public List<Livro> listarTodosLivros() {
        return repository.listarTodosLivros();
    }
}
