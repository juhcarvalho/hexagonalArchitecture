package livraria.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection getConnetion(){
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
