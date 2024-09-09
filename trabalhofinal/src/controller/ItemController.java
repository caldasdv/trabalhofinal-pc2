package controller;

import model.Item;
import view.ItemView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController {
    private Connection connection;
    private ItemView itemView;

    public ItemController(Connection connection) {
        this.connection = connection;
        this.itemView = new ItemView(connection);
    }

    public void showAddItemView() {
        itemView.addItem();
    }

    public void showRemoveItemView() {
        itemView.removeItem();
    }

    public void showDisplayItemsView() {
        itemView.displayItems();
    }

    public void addItem(Item item) throws SQLException {
        String query = "INSERT INTO Item (nomeItem, descricaoItem) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getNomeItem());
            stmt.setString(2, item.getDescricaoItem());
            stmt.executeUpdate();
        }
    }

    public void removeItem(String nomeItem) throws SQLException {
        String query = "DELETE FROM Item WHERE nomeItem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomeItem);
            stmt.executeUpdate();
        }
    }

    public Item getItem(String nomeItem) throws SQLException {
        String query = "SELECT * FROM Item WHERE nomeItem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomeItem);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setNomeItem(rs.getString("nomeItem"));
                    item.setDescricaoItem(rs.getString("descricaoItem"));
                    return item;
                } else {
                    return null;
                }
            }
        }
    }

    public void displayAllItems() throws SQLException {
        String query = "SELECT * FROM Item";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            // Processar os resultados e exibir na UI
        }
    }
}
