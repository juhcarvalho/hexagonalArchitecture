package livraria.adapters;

import livraria.Livro;
import livraria.LivroService;

import java.util.List;

public class LivroController {
    LivroService service;

    public LivroController(LivroRepositoryH2 repository) {
        this.service = new LivroService(repository);
    }

    public void cadastrarLivro(Livro livro) {
        service.cadastrarLivro(livro);
    }
    public Livro buscarLivroPorId(Long id) {
        return service.buscarLivroPorId(id);
    }
    public List<Livro> listarTodosLivros() {
        return service.listarTodosLivros();
    }
}
