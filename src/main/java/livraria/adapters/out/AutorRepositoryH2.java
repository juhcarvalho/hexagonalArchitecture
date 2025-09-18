package livraria.adapters.out;

import livraria.model.Autor;
import livraria.model.Livro;

import java.sql.*;
import java.util.List;

public class AutorRepositoryH2 implements AutorRepository {
    private final Connection connection;

    public AutorRepositoryH2(Connection connection) {
        this.connection = connection;
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS autor (id IDENTITY PRIMARY KEY, nome VARCHAR)");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
    }

    @Override
    public void cadastrarAutor(Autor autor) {
        System.out.println("Cadastrar autor...");
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO autor (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, autor.getNome());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                autor.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public Autor buscarAutorPorId(Long id) {
        Autor autor = null;
        try {
            Statement stmt = connection.createStatement();

            System.out.println("buscarAutorPorId..." + id.toString());
            ResultSet rs = stmt.executeQuery("SELECT * FROM autor WHERE id=" + id.toString());
            while (rs.next()) {
                System.out.println("Autor encontrado!");
                autor = new Autor(rs.getLong("id"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return autor;
    }

    @Override
    public List<Autor> listarTodos() {
        return List.of();
    }
}
