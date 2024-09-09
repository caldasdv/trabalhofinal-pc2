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

public class ServicoView {

    private Connection connection;

    public ServicoView(Connection connection) {
        this.connection = connection;
    }

    public void addServico() {
        JFrame frame = new JFrame("Adicionar Serviço");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        JTextField codServicoField = new JTextField();
        JTextField nomeServicoField = new JTextField();
        JTextField valorServicoField = new JTextField();

        frame.add(new JLabel("Código do Serviço:"));
        frame.add(codServicoField);
        frame.add(new JLabel("Nome do Serviço:"));
        frame.add(nomeServicoField);
        frame.add(new JLabel("Valor do Serviço:"));
        frame.add(valorServicoField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codServico = Integer.parseInt(codServicoField.getText());
                    String nomeServico = nomeServicoField.getText();
                    double valorServico = Double.parseDouble(valorServicoField.getText());

                    String query = "INSERT INTO Servico (codServico, nomeServico, valorServico) VALUES (?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codServico);
                        stmt.setString(2, nomeServico);
                        stmt.setDouble(3, valorServico);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Serviço adicionado com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar serviço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void removeServico() {
        JFrame frame = new JFrame("Remover Serviço");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField codServicoField = new JTextField();

        frame.add(new JLabel("Código do Serviço:"));
        frame.add(codServicoField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codServico = Integer.parseInt(codServicoField.getText());

                    String query = "DELETE FROM Servico WHERE codServico = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codServico);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Serviço removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Serviço não encontrado.");
                        }
                        frame.dispose();
                    }
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover serviço: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayServicos() {
        JFrame frame = new JFrame("Lista de Serviços");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTable servicoTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(servicoTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT * FROM Servico";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Nome", "Valor"}, 0);

            while (rs.next()) {
                int codServico = rs.getInt("codServico");
                String nomeServico = rs.getString("nomeServico");
                double valorServico = rs.getDouble("valorServico");

                model.addRow(new Object[]{codServico, nomeServico, valorServico});
            }
            servicoTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar serviços: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
}
