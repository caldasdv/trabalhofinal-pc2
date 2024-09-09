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

public class ChaleView {
    private Connection connection;

    public ChaleView(Connection connection) {
        this.connection = connection;
    }

    public void addChale() {
        JFrame frame = new JFrame("Adicionar Chalé");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 5, 5));

        JTextField localizacaoField = new JTextField();
        JTextField capacidadeField = new JTextField();
        JTextField valorAltaEstacaoField = new JTextField();
        JTextField valorBaixaEstacaoField = new JTextField();

        frame.add(new JLabel("Localização:"));
        frame.add(localizacaoField);
        frame.add(new JLabel("Capacidade:"));
        frame.add(capacidadeField);
        frame.add(new JLabel("Valor Alta Estação:"));
        frame.add(valorAltaEstacaoField);
        frame.add(new JLabel("Valor Baixa Estação:"));
        frame.add(valorBaixaEstacaoField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String localizacao = localizacaoField.getText();
                    int capacidade = Integer.parseInt(capacidadeField.getText());
                    double valorAltaEstacao = Double.parseDouble(valorAltaEstacaoField.getText());
                    double valorBaixaEstacao = Double.parseDouble(valorBaixaEstacaoField.getText());

                    String query = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, localizacao);
                        stmt.setInt(2, capacidade);
                        stmt.setDouble(3, valorAltaEstacao);
                        stmt.setDouble(4, valorBaixaEstacao);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Chalé adicionado com sucesso!");
                        frame.dispose(); // Fechar a janela após a adição
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar chalé: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void updateChale() {
        JFrame frame = new JFrame("Atualizar Chalé");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2, 5, 5));

        JTextField codChaleField = new JTextField();
        JTextField localizacaoField = new JTextField();
        JTextField capacidadeField = new JTextField();
        JTextField valorAltaEstacaoField = new JTextField();
        JTextField valorBaixaEstacaoField = new JTextField();

        frame.add(new JLabel("Código do Chalé:"));
        frame.add(codChaleField);
        frame.add(new JLabel("Nova Localização:"));
        frame.add(localizacaoField);
        frame.add(new JLabel("Nova Capacidade:"));
        frame.add(capacidadeField);
        frame.add(new JLabel("Novo Valor Alta Estação:"));
        frame.add(valorAltaEstacaoField);
        frame.add(new JLabel("Novo Valor Baixa Estação:"));
        frame.add(valorBaixaEstacaoField);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codChale = Integer.parseInt(codChaleField.getText());
                    String localizacao = localizacaoField.getText();
                    int capacidade = Integer.parseInt(capacidadeField.getText());
                    double valorAltaEstacao = Double.parseDouble(valorAltaEstacaoField.getText());
                    double valorBaixaEstacao = Double.parseDouble(valorBaixaEstacaoField.getText());

                    String query = "UPDATE Chale SET localizacao = ?, capacidade = ?, valorAltaEstacao = ?, valorBaixaEstacao = ? WHERE codChale = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, localizacao);
                        stmt.setInt(2, capacidade);
                        stmt.setDouble(3, valorAltaEstacao);
                        stmt.setDouble(4, valorBaixaEstacao);
                        stmt.setInt(5, codChale);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Chalé atualizado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Chalé não encontrado.");
                        }
                        frame.dispose(); // Fechar a janela após a atualização
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar chalé: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(updateButton);

        frame.setVisible(true);
    }

    public void removeChale() {
        JFrame frame = new JFrame("Remover Chalé");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField codChaleField = new JTextField();

        frame.add(new JLabel("Código do Chalé:"));
        frame.add(codChaleField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codChale = Integer.parseInt(codChaleField.getText());

                    String query = "DELETE FROM Chale WHERE codChale = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codChale);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Chalé removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Chalé não encontrado.");
                        }
                        frame.dispose(); // Fechar a janela após a remoção
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover chalé: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayChales() {
        JFrame frame = new JFrame("Lista de Chalés");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JTable chaleTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(chaleTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT * FROM Chale";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Localização", "Capacidade", "Valor Alta Estação", "Valor Baixa Estação"}, 0);

            while (rs.next()) {
                int codChale = rs.getInt("codChale");
                String localizacao = rs.getString("localizacao");
                int capacidade = rs.getInt("capacidade");
                double valorAltaEstacao = rs.getDouble("valorAltaEstacao");
                double valorBaixaEstacao = rs.getDouble("valorBaixaEstacao");

                model.addRow(new Object[]{codChale, localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao});
            }
            chaleTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar chalés: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
