package persistence;

import model.ChaleItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChaleItemDAO {
    private Connection connection;

    public ChaleItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void addChaleItem(ChaleItem chaleItem) throws SQLException {
        String sql = "INSERT INTO Chale_Item (codChale, nomeItem) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, chaleItem.getCodChale());
            stmt.setString(2, chaleItem.getNomeItem());
            stmt.executeUpdate();
        }
    }

    public List<ChaleItem> getAllChaleItems() throws SQLException {
        List<ChaleItem> chaleItems = new ArrayList<>();
        String sql = "SELECT * FROM Chale_Item";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ChaleItem chaleItem = new ChaleItem();
                chaleItem.setCodChale(rs.getInt("codChale"));
                chaleItem.setNomeItem(rs.getString("nomeItem"));
                chaleItems.add(chaleItem);
            }
        }
        return chaleItems;
    }
}
