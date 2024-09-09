package view;

import controller.ChaleItemController;
import model.ChaleItem;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChaleItemView {
    private ChaleItemController chaleItemController;

    public ChaleItemView(Connection connection) {
        this.chaleItemController = new ChaleItemController(connection);
    }

    public void addChaleItem() {
        JFrame frame = new JFrame("Adicionar Item ao Chalé");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblCodChale = new JLabel("Código do Chalé:");
        JLabel lblNomeItem = new JLabel("Nome do Item:");

        JTextField txtCodChale = new JTextField();
        JTextField txtNomeItem = new JTextField();

        JButton btnSave = new JButton("Salvar");
        JButton btnCancel = new JButton("Cancelar");

        frame.add(lblCodChale);
        frame.add(txtCodChale);
        frame.add(lblNomeItem);
        frame.add(txtNomeItem);
        frame.add(btnSave);
        frame.add(btnCancel);

        btnSave.addActionListener(e -> {
            try {
                ChaleItem chaleItem = new ChaleItem();
                chaleItem.setCodChale(Integer.parseInt(txtCodChale.getText()));
                chaleItem.setNomeItem(txtNomeItem.getText());

                chaleItemController.addChaleItem(chaleItem);
                JOptionPane.showMessageDialog(frame, "Item adicionado com sucesso!");
                frame.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao adicionar o item.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    public void removeChaleItem() {
        String codChaleStr = JOptionPane.showInputDialog("Digite o código do chalé:");
        String nomeItem = JOptionPane.showInputDialog("Digite o nome do item:");

        try {
            chaleItemController.removeChaleItem(Integer.parseInt(codChaleStr), nomeItem);
            JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao remover o item.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayChaleItems() {
        String codChaleStr = JOptionPane.showInputDialog("Digite o código do chalé para exibir itens:");
        int codChale = Integer.parseInt(codChaleStr);

        try {
            List<ChaleItem> chaleItems = chaleItemController.getChaleItems(codChale);
            JFrame frame = new JFrame("Itens do Chalé");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);

            String[] columnNames = {"Código do Chalé", "Nome do Item"};
            Object[][] data = new Object[chaleItems.size()][2];

            for (int i = 0; i < chaleItems.size(); i++) {
                ChaleItem chaleItem = chaleItems.get(i);
                data[i][0] = chaleItem.getCodChale();
                data[i][1] = chaleItem.getNomeItem();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);

            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao exibir itens do chalé.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
