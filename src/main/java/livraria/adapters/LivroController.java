package livraria.adapters;

import livraria.model.Livro;
import livraria.LivroService;

import java.util.List;
import java.util.Scanner;

public class LivroController {
    LivroService service;
    Scanner scanner = new Scanner(System.in);

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
