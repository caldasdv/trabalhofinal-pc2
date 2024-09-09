package persistence;

import model.Chale;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChaleDAO {
    private Connection connection;

    public ChaleDAO(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("A conexão não pode ser nula.");
        }
        this.connection = connection;
    }

    public void addChale(Chale chale) throws SQLException {
        String sql = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chale.getLocalizacao());
            stmt.setInt(2, chale.getCapacidade());
            stmt.setDouble(3, chale.getValorAltaEstacao());
            stmt.setDouble(4, chale.getValorBaixaEstacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar chale: " + e.getMessage());
            throw e; // Re-throw exception after logging it
        }
    }

    public List<Chale> getAllChales() throws SQLException {
        List<Chale> chales = new ArrayList<>();
        String sql = "SELECT * FROM Chale";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Chale chale = new Chale();
                chale.setCodChale(rs.getInt("codChale"));
                chale.setLocalizacao(rs.getString("localizacao"));
                chale.setCapacidade(rs.getInt("capacidade"));
                chale.setValorAltaEstacao(rs.getDouble("valorAltaEstacao"));
                chale.setValorBaixaEstacao(rs.getDouble("valorBaixaEstacao"));
                chales.add(chale);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter chales: " + e.getMessage());
            throw e; // Re-throw exception after logging it
        }
        return chales;
    }
}
