package livraria.adapters;

import livraria.Livro;
import livraria.LivroService;

import java.util.List;
import java.util.Scanner;

public class LivroController {
    LivroService service;
    Scanner scanner = new Scanner(System.in);

    public LivroController(LivroRepositoryH2 repository) {
        this.service = new LivroService(repository);
    }

    public void cadastrarLivro() {
        System.out.println("Titulo:");
        String titulo = scanner.next();
        System.out.println("Autor:");
        String autor = scanner.next();
        System.out.println("Digite o id");
        int anoPublicacao = scanner.nextInt();

        service.cadastrarLivro(new Livro(titulo, autor, anoPublicacao));
    }
    public Livro buscarLivroPorId() {
        System.out.println("Digite o id");
        return service.buscarLivroPorId((long) scanner.nextInt());
    }
    public List<Livro> listarTodosLivros() {
        return service.listarTodosLivros();
    }
}
