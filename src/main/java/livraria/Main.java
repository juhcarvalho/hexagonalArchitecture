package livraria;

import livraria.adapters.in.LivroController;
import livraria.infra.EmailSender;
import livraria.adapters.out.LivroRepositoryH2;
import livraria.infra.DbConnection;
import livraria.model.Livro;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnetion();
        try {
            LivroRepositoryH2 repository = new LivroRepositoryH2(conn);
            LivroController controller = new LivroController(repository);


            int option = 0;
            do {
                System.out.println("1. Cadastrar livro");
                System.out.println("2. Listar livro por id");
                System.out.println("3. Listar todos");
                System.out.println("0. Sair");
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> {
                        System.out.println("Titulo:");
                        String titulo = scanner.next();
                        System.out.println("Autor:");
                        String autor = scanner.next();
                        System.out.println("Digite o ano de publicação");
                        int anoPublicacao = scanner.nextInt();
                        controller.cadastrarLivro(new Livro(titulo, autor, anoPublicacao));
                        EmailSender.sendEmail("jucarvalho@outlook.com.br", "Cadastro realizado", "Olá, seu cadastro foi concluído com sucesso!");
                    }
                    case 2 -> {
                        System.out.println("Digite o id");
                        Long id = (long) scanner.nextInt();
                        Livro livro = controller.buscarLivroPorId(id);
                        System.out.println(livro.toString());
                    }
                    case 3 -> {
                        List<Livro> livros = controller.listarTodosLivros();
                        System.out.println("Total de registros: " + livros.size());
                        livros.forEach(System.out::println);
                    }
                }
            } while(option != 0);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
}