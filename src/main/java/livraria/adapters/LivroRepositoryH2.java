package livraria.adapters;

import livraria.Livro;
import livraria.LivroRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepositoryH2 implements LivroRepository {
    private final Connection connection;

    public LivroRepositoryH2(Connection connection) {
        this.connection = connection;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS livro (id IDENTITY PRIMARY KEY, titulo VARCHAR, autor VARCHAR, anoPublicacao int)");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    @Override
    public void cadastrarLivro(Livro livro) {
        System.out.println("Cadastrar livro...");
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO livro (titulo, autor, anoPublicacao) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getAnoPublicacao());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public Livro buscarLivroPorId(Long id) {

        Livro livro = null;
        try {
            Statement stmt = connection.createStatement();

            System.out.println("buscarLivroPorId..." + id.toString());
            ResultSet rs = stmt.executeQuery("SELECT * FROM livro WHERE id=" + id.toString());
            while (rs.next()) {
                System.out.println("livraria.Livro encontrado!");
                livro = new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("anoPublicacao"));
                System.out.println(livro.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return livro;
    }

    @Override
    public List<Livro> listarTodosLivros() {
        List<Livro> livros = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM livro");
            while (rs.next()) {
                livros.add(new Livro(rs.getLong("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("anoPublicacao")));
            }
            System.out.println(livros.size());
            livros.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return livros;
    }
}
