package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemView {
    private Connection connection;

    public ItemView(Connection connection) {
        this.connection = connection;
    }

    public void addItem() {
        JFrame frame = new JFrame("Adicionar Item");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField nomeItemField = new JTextField();
        JTextField descricaoItemField = new JTextField();

        frame.add(new JLabel("Nome do Item:"));
        frame.add(nomeItemField);
        frame.add(new JLabel("Descrição do Item:"));
        frame.add(descricaoItemField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeItem = nomeItemField.getText();
                    String descricaoItem = descricaoItemField.getText();

                    String query = "INSERT INTO Item (nomeItem, descricaoItem) VALUES (?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, nomeItem);
                        stmt.setString(2, descricaoItem);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Item adicionado com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void removeItem() {
        JFrame frame = new JFrame("Remover Item");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField nomeItemField = new JTextField();

        frame.add(new JLabel("Nome do Item:"));
        frame.add(nomeItemField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeItem = nomeItemField.getText();

                    String query = "DELETE FROM Item WHERE nomeItem = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, nomeItem);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Item removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Item não encontrado.");
                        }
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayItems() {
        JFrame frame = new JFrame("Lista de Itens");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JTable itemTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(itemTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT * FROM Item";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Nome", "Descrição"}, 0);

            while (rs.next()) {
                String nomeItem = rs.getString("nomeItem");
                String descricaoItem = rs.getString("descricaoItem");

                model.addRow(new Object[]{nomeItem, descricaoItem});
            }
            itemTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar itens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
