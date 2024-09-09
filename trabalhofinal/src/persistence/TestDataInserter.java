package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDataInserter {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                insertClientes(connection);
                insertChales(connection);
                insertServicos(connection);
                insertHospedagens(connection);
                insertTelefones(connection);

                System.out.println("Dados de teste inseridos com sucesso!");
            } else {
                System.out.println("Falha na conexão com o banco de dados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertClientes(Connection connection) throws SQLException {
        String sql = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "João da Silva");
            stmt.setString(2, "123456789");
            stmt.setString(3, "Rua das Flores, 123");
            stmt.setString(4, "Centro");
            stmt.setString(5, "São Paulo");
            stmt.setString(6, "SP");
            stmt.setString(7, "01234-567");
            stmt.setDate(8, java.sql.Date.valueOf("1985-05-15"));
            stmt.executeUpdate();

            stmt.setString(1, "Maria Oliveira");
            stmt.setString(2, "987654321");
            stmt.setString(3, "Avenida Paulista, 456");
            stmt.setString(4, "Jardins");
            stmt.setString(5, "São Paulo");
            stmt.setString(6, "SP");
            stmt.setString(7, "12345-678");
            stmt.setDate(8, java.sql.Date.valueOf("1990-08-25"));
            stmt.executeUpdate();
        }
    }

    private static void insertChales(Connection connection) throws SQLException {
        String sql = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Chalé A");
            stmt.setInt(2, 4);
            stmt.setDouble(3, 250.00);
            stmt.setDouble(4, 150.00);
            stmt.executeUpdate();

            stmt.setString(1, "Chalé B");
            stmt.setInt(2, 6);
            stmt.setDouble(3, 300.00);
            stmt.setDouble(4, 180.00);
            stmt.executeUpdate();
        }
    }

    private static void insertServicos(Connection connection) throws SQLException {
        String sql = "INSERT INTO Servico (nomeServico, valorServico) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Limpeza");
            stmt.setDouble(2, 50.00);
            stmt.executeUpdate();

            stmt.setString(1, "Refeição");
            stmt.setDouble(2, 80.00);
            stmt.executeUpdate();
        }
    }

    private static void insertHospedagens(Connection connection) throws SQLException {
        String sql = "INSERT INTO Hospedagem (codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, 1); // codChale
            stmt.setInt(2, 1); // codCliente
            stmt.setString(3, "Confirmada");
            stmt.setDate(4, java.sql.Date.valueOf("2024-09-01"));
            stmt.setDate(5, java.sql.Date.valueOf("2024-09-10"));
            stmt.setInt(6, 2);
            stmt.setDouble(7, 10.00);
            stmt.setDouble(8, 440.00); // Valor após desconto
            stmt.executeUpdate();
        }
    }

    private static void insertTelefones(Connection connection) throws SQLException {
        String sql = "INSERT INTO Telefone (telefone, codCliente, tipoTelefone) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "111-2222");
            stmt.setInt(2, 1); // codCliente
            stmt.setString(3, "Celular");
            stmt.executeUpdate();

            stmt.setString(1, "333-4444");
            stmt.setInt(2, 2); // codCliente
            stmt.setString(3, "Residencial");
            stmt.executeUpdate();
        }
    }
}
