package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/AppPC2"; // URL do banco de dados
    private static final String USER = "postgres"; // Usuário do banco de dados
    private static final String PASSWORD = "bancodedados"; // Senha do banco de dados

    public static Connection getConnection() {
        try {
            // Carregar o driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Estabelecer a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
