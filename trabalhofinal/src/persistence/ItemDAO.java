package persistence;

import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection;

    public ItemDAO(Connection connection) {
        this.connection = connection;
    }

    public void addItem(Item item) throws SQLException {
        String sql = "INSERT INTO Item (nomeItem, descricaoItem) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getNomeItem());
            stmt.setString(2, item.getDescricaoItem());
            stmt.executeUpdate();
        }
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Item item = new Item();
                item.setNomeItem(rs.getString("nomeItem"));
                item.setDescricaoItem(rs.getString("descricaoItem"));
                items.add(item);
            }
        }
        return items;
    }
}
