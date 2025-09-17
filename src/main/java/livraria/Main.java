package livraria;

import livraria.adapters.LivroController;
import livraria.adapters.LivroRepositoryH2;
import livraria.infra.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = getConnectionH2();
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
                        controller.cadastrarLivro();
                    }
                    case 2 -> {
                        Livro livro = controller.buscarLivroPorId();
                    }
                    case 3 -> {
                        List<Livro> livros = controller.listarTodosLivros();
                    }
                }

            } while(option != 0);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    public static Connection getConnectionH2(){
        Properties config = ConfigLoader.loadProperties();
        Connection conn = null;
        try {
            String dbUrl = config.getProperty("db.url");
            String dbUser = config.getProperty("db.username");
            String dbPass = config.getProperty("db.password");

            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            System.out.println("Connected to H2!");
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return conn;
    }
}