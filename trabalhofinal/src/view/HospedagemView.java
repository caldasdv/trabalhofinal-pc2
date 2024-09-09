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

public class HospedagemView {

    private Connection connection;

    public HospedagemView(Connection connection) {
        this.connection = connection;
    }

    public void addHospedagem() {
        JFrame frame = new JFrame("Adicionar Hospedagem");
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(9, 2, 5, 5));

        JTextField codHospedagemField = new JTextField();
        JTextField codChaleField = new JTextField();
        JTextField codClienteField = new JTextField();
        JTextField estadoField = new JTextField();
        JTextField dataInicioField = new JTextField();
        JTextField dataFimField = new JTextField();
        JTextField qtdPessoasField = new JTextField();
        JTextField descontoField = new JTextField();
        JTextField valorFinalField = new JTextField();

        frame.add(new JLabel("Código da Hospedagem:"));
        frame.add(codHospedagemField);
        frame.add(new JLabel("Código do Chalé:"));
        frame.add(codChaleField);
        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);
        frame.add(new JLabel("Estado:"));
        frame.add(estadoField);
        frame.add(new JLabel("Data de Início:"));
        frame.add(dataInicioField);
        frame.add(new JLabel("Data de Fim:"));
        frame.add(dataFimField);
        frame.add(new JLabel("Quantidade de Pessoas:"));
        frame.add(qtdPessoasField);
        frame.add(new JLabel("Desconto:"));
        frame.add(descontoField);
        frame.add(new JLabel("Valor Final:"));
        frame.add(valorFinalField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codHospedagem = Integer.parseInt(codHospedagemField.getText());
                    int codChale = Integer.parseInt(codChaleField.getText());
                    int codCliente = Integer.parseInt(codClienteField.getText());
                    String estado = estadoField.getText();
                    java.sql.Date dataInicio = java.sql.Date.valueOf(dataInicioField.getText());
                    java.sql.Date dataFim = java.sql.Date.valueOf(dataFimField.getText());
                    int qtdPessoas = Integer.parseInt(qtdPessoasField.getText());
                    double desconto = Double.parseDouble(descontoField.getText());
                    double valorFinal = Double.parseDouble(valorFinalField.getText());

                    String query = "INSERT INTO Hospedagem (codHospedagem, codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codHospedagem);
                        stmt.setInt(2, codChale);
                        stmt.setInt(3, codCliente);
                        stmt.setString(4, estado);
                        stmt.setDate(5, dataInicio);
                        stmt.setDate(6, dataFim);
                        stmt.setInt(7, qtdPessoas);
                        stmt.setDouble(8, desconto);
                        stmt.setDouble(9, valorFinal);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Hospedagem adicionada com sucesso!");
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar hospedagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(addButton);

        frame.setVisible(true);
    }

    public void removeHospedagem() {
        JFrame frame = new JFrame("Remover Hospedagem");
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(2, 2, 5, 5));

        JTextField codHospedagemField = new JTextField();

        frame.add(new JLabel("Código da Hospedagem:"));
        frame.add(codHospedagemField);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codHospedagem = Integer.parseInt(codHospedagemField.getText());

                    String query = "DELETE FROM Hospedagem WHERE codHospedagem = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setInt(1, codHospedagem);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "Hospedagem removida com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Hospedagem não encontrada.");
                        }
                        frame.dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao remover hospedagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(removeButton);

        frame.setVisible(true);
    }

    public void displayHospedagens() {
        JFrame frame = new JFrame("Lista de Hospedagens");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JTable hospedagemTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(hospedagemTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        String query = "SELECT codHospedagem, codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal FROM Hospedagem";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Código do Chalé", "Código do Cliente", "Estado", "Data Início", "Data Fim", "Qtd Pessoas", "Desconto", "Valor Final"}, 0);

            while (rs.next()) {
                int codHospedagem = rs.getInt("codHospedagem");
                int codChale = rs.getInt("codChale");
                int codCliente = rs.getInt("codCliente");
                String estado = rs.getString("estado");
                java.sql.Date dataInicio = rs.getDate("dataInicio");
                java.sql.Date dataFim = rs.getDate("dataFim");
                int qtdPessoas = rs.getInt("qtdPessoas");
                double desconto = rs.getDouble("desconto");
                double valorFinal = rs.getDouble("valorFinal");

                model.addRow(new Object[]{codHospedagem, codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal});
            }
            hospedagemTable.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar hospedagens: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        frame.setVisible(true);
    }
    public void updateHospedagem() throws NumberFormatException {
        // Criar um JFrame para a atualização
        JFrame frame = new JFrame("Atualizar Hospedagem");
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(10, 2, 5, 5));

        // Campos para o formulário
        JTextField codHospedagemField = new JTextField();
        JTextField codChaleField = new JTextField();
        JTextField codClienteField = new JTextField();
        JTextField estadoField = new JTextField();
        JTextField dataInicioField = new JTextField();
        JTextField dataFimField = new JTextField();
        JTextField qtdPessoasField = new JTextField();
        JTextField descontoField = new JTextField();
        JTextField valorFinalField = new JTextField();

        frame.add(new JLabel("Código da Hospedagem:"));
        frame.add(codHospedagemField);
        frame.add(new JLabel("Código do Chalé:"));
        frame.add(codChaleField);
        frame.add(new JLabel("Código do Cliente:"));
        frame.add(codClienteField);
        frame.add(new JLabel("Estado:"));
        frame.add(estadoField);
        frame.add(new JLabel("Data de Início:"));
        frame.add(dataInicioField);
        frame.add(new JLabel("Data de Fim:"));
        frame.add(dataFimField);
        frame.add(new JLabel("Quantidade de Pessoas:"));
        frame.add(qtdPessoasField);
        frame.add(new JLabel("Desconto:"));
        frame.add(descontoField);
        frame.add(new JLabel("Valor Final:"));
        frame.add(valorFinalField);

        // Preencher os campos com os dados atuais
        String codHospedagem = JOptionPane.showInputDialog(frame, "Digite o código da hospedagem a ser atualizada:");
        if (codHospedagem != null && !codHospedagem.trim().isEmpty()) {
            try {
                String query = "SELECT codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal FROM Hospedagem WHERE codHospedagem = ?";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setInt(1, Integer.parseInt(codHospedagem));
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            codChaleField.setText(String.valueOf(rs.getInt("codChale")));
                            codClienteField.setText(String.valueOf(rs.getInt("codCliente")));
                            estadoField.setText(rs.getString("estado"));
                            dataInicioField.setText(rs.getDate("dataInicio").toString());
                            dataFimField.setText(rs.getDate("dataFim").toString());
                            qtdPessoasField.setText(String.valueOf(rs.getInt("qtdPessoas")));
                            descontoField.setText(String.valueOf(rs.getDouble("desconto")));
                            valorFinalField.setText(String.valueOf(rs.getDouble("valorFinal")));
                        } else {
                            JOptionPane.showMessageDialog(frame, "Hospedagem não encontrada.");
                            frame.dispose();
                            return;
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao buscar hospedagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                return;
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Código da hospedagem não informado.");
            frame.dispose();
            return;
        }

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(e -> {
            try {
                int codChale = Integer.parseInt(codChaleField.getText());
                int codCliente = Integer.parseInt(codClienteField.getText());
                String estado = estadoField.getText();
                java.sql.Date dataInicio = java.sql.Date.valueOf(dataInicioField.getText());
                java.sql.Date dataFim = java.sql.Date.valueOf(dataFimField.getText());
                int qtdPessoas = Integer.parseInt(qtdPessoasField.getText());
                double desconto = Double.parseDouble(descontoField.getText());
                double valorFinal = Double.parseDouble(valorFinalField.getText());

                String updateQuery = "UPDATE Hospedagem SET codChale = ?, codCliente = ?, estado = ?, dataInicio = ?, dataFim = ?, qtdPessoas = ?, desconto = ?, valorFinal = ? WHERE codHospedagem = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, codChale);
                    updateStmt.setInt(2, codCliente);
                    updateStmt.setString(3, estado);
                    updateStmt.setDate(4, dataInicio);
                    updateStmt.setDate(5, dataFim);
                    updateStmt.setInt(6, qtdPessoas);
                    updateStmt.setDouble(7, desconto);
                    updateStmt.setDouble(8, valorFinal);
                    updateStmt.setInt(9, Integer.parseInt(codHospedagem));
                    int rowsAffected = updateStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Hospedagem atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Hospedagem não encontrada para atualização.");
                    }
                    frame.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao atualizar hospedagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, "Erro na entrada de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.add(updateButton);

        frame.setVisible(true);
    }


}
