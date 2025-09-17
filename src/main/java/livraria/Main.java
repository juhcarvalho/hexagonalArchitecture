package livraria;

import livraria.adapters.LivroController;
import livraria.adapters.LivroRepositoryH2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            System.out.println("Connected to H2!");

            LivroRepositoryH2 repository = new LivroRepositoryH2(conn);
            LivroController controller = new LivroController(repository);

            controller.cadastrarLivro(new Livro("ABC", "SDFS", 2521));
            controller.cadastrarLivro(new Livro("Dom Casmurro", "Machado de Assis", 1862));

            Livro livro = controller.buscarLivroPorId(1L);
            System.out.println(livro.toString());

            List<Livro> livros = controller.listarTodosLivros();
            livros.forEach(System.out::println);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
}