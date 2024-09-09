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

public class ClienteView {
    private Connection connection;

    public ClienteView(Connection connection) {
        this.connection = connection;
    }

    public void addCliente() {
        JFrame frame = new JFrame("Adicionar Cliente");
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(9, 2, 5, 5));

        JTextField nomeClienteField = new JTextField();
        JTextField rgClienteField = new JTextField();
        JTextField enderecoClienteField = new JTextField();
        JTextField bairroClienteField = new JTextField();
        JTextField cidadeClienteField = new JTextField();
        JTextField estadoClienteField = new JTextField();
        JTextField cepClienteField = new JTextField();
        JTextField nascimentoClienteField = new JTextField(); // Utilize um formato de data adequado

        frame.add(new JLabel("Nome do Cliente:"));
        frame.add(nomeClienteField);
        frame.add(new JLabel("RG do Cliente:"));
        frame.add(rgClienteField);
        frame.add(new JLabel("Endereço do Cliente:"));
        frame.add(enderecoClienteField);
        frame.add(new JLabel("Bairro do Cliente:"));
        frame.add(bairroClienteField);
        frame.add(new JLabel("Cidade do Cliente:"));
        frame.add(cidadeClienteField);
        frame.add(new JLabel("Estado do Cliente:"));
        frame.add(estadoClienteField);
        frame.add(new JLabel("CEP do Cliente:"));
        frame.add(cepClienteField);
        frame.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"));
        frame.add(nascimentoClienteField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomeCliente = nomeClienteField.getText();
                    String rgCliente = rgClienteField.getText();
                    String enderecoCliente = enderecoClienteField.getText();
                    String bairroCliente = bairroClienteField.getText();
                    String cidadeCliente = cidadeClienteField.getText();
                    String estadoCliente = estadoClienteField.getText();
                    String cepCliente = cepClienteField.getText();
                    java.sql.Date nascimentoCliente = java.sql.Date.valueOf(nascimentoClienteField.getText());

                    String query = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, nomeCliente);
                        stmt.setString(2, rgCliente);
                        stmt.setString(3, enderecoCliente);
                        stmt.setString(4, bairroCliente);
                        stmt.setString(5, cidadeCliente);
                        stmt.setString(6, estadoCliente);
                        stmt.setString(7, cepCliente);
                        stmt.setDate(8, nascimentoCliente);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Cliente adicionado com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Data inválida. Use o formato YYYY-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void updateCliente() {
        JFrame frame = new JFrame("Atualizar Cliente");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(10, 2, 5, 5));

        JTextField codClienteField = new JTextField();
        JTextField nomeClienteField = new JTextField();
        JTextField rgClienteField = new JTextField();
        JTextField enderecoClienteField = new JTextField();
        JTextField bairroClienteField = new JTextField();
        JTextField cidadeClienteField = new JTextField();
        JTextField estadoClienteField = new JTextField();
        JTextField cepClienteField = new JTextField();
        JTextField nascimentoClienteField = new JTextField(); // Utilize um formato de data adequado

        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);
        frame.add(new JLabel("Novo Nome do Cliente:"));
        frame.add(nomeClienteField);
        frame.add(new JLabel("Novo RG do Cliente:"));
        frame.add(rgClienteField);
        frame.add(new JLabel("Novo Endereço do Cliente:"));
        frame.add(enderecoClienteField);
        frame.add(new JLabel("Novo Bairro do Cliente:"));
        frame.add(bairroClienteField);
        frame.add(new JLabel("Nova Cidade do Cliente:"));
        frame.add(cidadeClienteField);
        frame.add(new JLabel("Novo Estado do Cliente:"));
        frame.add(estadoClienteField);
        frame.add(new JLabel("Novo CEP do Cliente:"));
        frame.add(cepClienteField);
        frame.add(new JLabel("Nova Data de Nascimento (YYYY-MM-DD):"));
        frame.add(nascimentoClienteField);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codCliente = Integer.parseInt(codClienteField.getText());
                    String nomeCliente = nomeClienteField.getText();
                    String rgCliente = rgClienteField.getText();
                    String enderecoCliente = enderecoClienteField.getText();
                    String bairroCliente = bairroClienteField.getText();
                    String cidadeCliente = cidadeClienteField.getText();
                    String estadoCliente = estadoClienteField.getText();
                    String cepCliente = cepClienteField.getText();
                    java.sql.Date nascimentoCliente = java.sql.Date.valueOf(nascimentoClienteField.getText());

                    String query = "UPDATE Cliente SET nomeCliente = ?, rgCliente = ?, enderecoCliente = ?, bairroCliente = ?, cidadeCliente = ?, estadoCliente = ?, CEPCliente = ?, nascimentoCliente = ? WHERE codCliente = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, nomeCliente);
                        stmt.setString(2, rgCliente);
                        stmt.setString(3, enderecoCliente);
                        stmt.setString(4, bairroCliente);
                        stmt.setString(5, cidadeCliente);
                        stmt.setString(6, estadoCliente);
                        stmt.setString(7, cepCliente);
                        stmt.setDate(8, nascimentoCliente);
                        stmt.setInt(9, codCliente);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Cliente atualizado com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Data inválida. Use o formato YYYY-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(updateButton);

        frame.setVisible(true);
    }

    public void removeCliente() {
        JFrame frame = new JFrame("Remover Cliente");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField codClienteField = new JTextField();

        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codCliente = Integer.parseInt(codClienteField.getText());

                    String query = "DELETE FROM Cliente WHERE codCliente = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codCliente);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Cliente removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Cliente não encontrado.");
                        }
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayClientes() {
        JFrame frame = new JFrame("Lista de Clientes");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTable clienteTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clienteTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Nome", "RG", "Endereço", "Bairro", "Cidade", "Estado", "CEP", "Nascimento"}, 0);

            while (rs.next()) {
                int codCliente = rs.getInt("codCliente");
                String nomeCliente = rs.getString("nomeCliente");
                String rgCliente = rs.getString("rgCliente");
                String enderecoCliente = rs.getString("enderecoCliente");
                String bairroCliente = rs.getString("bairroCliente");
                String cidadeCliente = rs.getString("cidadeCliente");
                String estadoCliente = rs.getString("estadoCliente");
                String cepCliente = rs.getString("CEPCliente");
                java.sql.Date nascimentoCliente = rs.getDate("nascimentoCliente");

                model.addRow(new Object[]{codCliente, nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, cepCliente, nascimentoCliente});
            }
            clienteTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar clientes: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
