package controller;

import model.Chale;
import view.ChaleView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChaleController {
    private Connection connection;
    private ChaleView chaleView;

    public ChaleController(Connection connection) {
        this.connection = connection;
        this.chaleView = new ChaleView(connection);
    }

    public void showAddChaleView() {
        chaleView.addChale();
    }

    public void showUpdateChaleView() {
        chaleView.updateChale();
    }

    public void showRemoveChaleView() {
        chaleView.removeChale();
    }

    public void showDisplayChalesView() {
        chaleView.displayChales();
    }

    public void addChale(Chale chale) throws SQLException {
        String query = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chale.getLocalizacao());
            stmt.setInt(2, chale.getCapacidade());
            stmt.setDouble(3, chale.getValorAltaEstacao());
            stmt.setDouble(4, chale.getValorBaixaEstacao());
            stmt.executeUpdate();
        }
    }

    public void updateChale(Chale chale) throws SQLException {
        String query = "UPDATE Chale SET localizacao = ?, capacidade = ?, valorAltaEstacao = ?, valorBaixaEstacao = ? WHERE codChale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, chale.getLocalizacao());
            stmt.setInt(2, chale.getCapacidade());
            stmt.setDouble(3, chale.getValorAltaEstacao());
            stmt.setDouble(4, chale.getValorBaixaEstacao());
            stmt.setInt(5, chale.getCodChale());
            stmt.executeUpdate();
        }
    }

    public void removeChale(int codChale) throws SQLException {
        String query = "DELETE FROM Chale WHERE codChale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codChale);
            stmt.executeUpdate();
        }
    }

    public Chale getChale(int codChale) throws SQLException {
        String query = "SELECT * FROM Chale WHERE codChale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codChale);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Chale chale = new Chale();
                    chale.setCodChale(rs.getInt("codChale"));
                    chale.setLocalizacao(rs.getString("localizacao"));
                    chale.setCapacidade(rs.getInt("capacidade"));
                    chale.setValorAltaEstacao(rs.getDouble("valorAltaEstacao"));
                    chale.setValorBaixaEstacao(rs.getDouble("valorBaixaEstacao"));
                    return chale;
                } else {
                    return null;
                }
            }
        }
    }

    public void displayAllChales() throws SQLException {
        String query = "SELECT * FROM Chale";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            // Criar e definir um modelo de tabela aqui para exibir todos os chalés na UI
        }
    }
}
