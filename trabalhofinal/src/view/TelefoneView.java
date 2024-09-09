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

public class TelefoneView {
    private Connection connection;

    public TelefoneView(Connection connection) {
        this.connection = connection;
    }

    public void addTelefone() {
        JFrame frame = new JFrame("Adicionar Telefone");
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JTextField telefoneField = new JTextField();
        JTextField codClienteField = new JTextField();
        JTextField tipoTelefoneField = new JTextField();

        frame.add(new JLabel("Telefone:"));
        frame.add(telefoneField);
        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);
        frame.add(new JLabel("Tipo de Telefone:"));
        frame.add(tipoTelefoneField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String telefone = telefoneField.getText();
                    int codCliente = Integer.parseInt(codClienteField.getText());
                    String tipoTelefone = tipoTelefoneField.getText();

                    String query = "INSERT INTO Telefone (telefone, codCliente, tipoTelefone) VALUES (?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, telefone);
                        stmt.setInt(2, codCliente);
                        stmt.setString(3, tipoTelefone);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Telefone adicionado com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar telefone: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void removeTelefone() {
        JFrame frame = new JFrame("Remover Telefone");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JTextField telefoneField = new JTextField();
        JTextField codClienteField = new JTextField();

        frame.add(new JLabel("Telefone:"));
        frame.add(telefoneField);
        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String telefone = telefoneField.getText();
                    int codCliente = Integer.parseInt(codClienteField.getText());

                    String query = "DELETE FROM Telefone WHERE telefone = ? AND codCliente = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, telefone);
                        stmt.setInt(2, codCliente);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Telefone removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Telefone não encontrado.");
                        }
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover telefone: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayTelefones() {
        JFrame frame = new JFrame("Lista de Telefones");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTable telefoneTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(telefoneTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT * FROM Telefone";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Telefone", "Código do Cliente", "Tipo de Telefone"}, 0);

            while (rs.next()) {
                String telefone = rs.getString("telefone");
                int codCliente = rs.getInt("codCliente");
                String tipoTelefone = rs.getString("tipoTelefone");

                model.addRow(new Object[]{telefone, codCliente, tipoTelefone});
            }
            telefoneTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar telefones: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
