package controller;

import model.ChaleItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChaleItemController {
    private Connection connection;

    public ChaleItemController(Connection connection) {
        this.connection = connection;
    }

    public void addChaleItem(ChaleItem chaleItem) throws SQLException {
        String sql = "INSERT INTO chale_items (codChale, nomeItem) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, chaleItem.getCodChale());
            stmt.setString(2, chaleItem.getNomeItem());
            stmt.executeUpdate();
        }
    }

    public void removeChaleItem(int codChale, String nomeItem) throws SQLException {
        String sql = "DELETE FROM chale_items WHERE codChale = ? AND nomeItem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codChale);
            stmt.setString(2, nomeItem);
            stmt.executeUpdate();
        }
    }

    public List<ChaleItem> getChaleItems(int codChale) throws SQLException {
        List<ChaleItem> chaleItems = new ArrayList<>();
        String sql = "SELECT * FROM chale_items WHERE codChale = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codChale);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ChaleItem chaleItem = new ChaleItem();
                    chaleItem.setCodChale(rs.getInt("codChale"));
                    chaleItem.setNomeItem(rs.getString("nomeItem"));
                    chaleItems.add(chaleItem);
                }
            }
        }
        return chaleItems;
    }
}
