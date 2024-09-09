package persistence;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Falha na conexão!");
        }
    }
}
