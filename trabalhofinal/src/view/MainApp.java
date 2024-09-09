
package view;

import controller.ClienteController;
import controller.TelefoneController;
import controller.HospedagemController;
import controller.ServicoController;
import controller.ChaleController;
import controller.ItemController;
import persistence.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.*;

public class MainApp {

    private static Connection connection;
    private static ClienteController clienteController;
    private static TelefoneController telefoneController;
    private static HospedagemController hospedagemController;
    private static ServicoController servicoController;
    private static ChaleController chaleController;
    private static ItemController itemController;

    public static void main(String[] args) throws SQLException {
        // Conectar ao banco de dados usando DatabaseConnection
        connection = DatabaseConnection.getConnection();

        // Inicializar controladores
        clienteController = new ClienteController(connection);
        telefoneController = new TelefoneController(connection);
        hospedagemController = new HospedagemController(connection);
        servicoController = new ServicoController(connection);
        chaleController = new ChaleController(connection);
        itemController = new ItemController(connection);

        // Criar e exibir a interface do usuário
        SwingUtilities.invokeLater(MainApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sistema de Gestão");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Adicionar abas para cada categoria
        tabbedPane.addTab("Clientes", createClientPanel());
        tabbedPane.addTab("Telefones", createTelefonePanel());
        tabbedPane.addTab("Hospedagens", createHospedagemPanel());
        tabbedPane.addTab("Serviços", createServicoPanel());
        tabbedPane.addTab("Chalés", createChalePanel());
        tabbedPane.addTab("Itens", createItemPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private static JPanel createClientPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddCliente = new JButton("Adicionar Cliente");
        JButton btnRemoveCliente = new JButton("Remover Cliente");
        JButton btnUpdateCliente = new JButton("Atualizar Cliente");
        JButton btnDisplayClientes = new JButton("Listar Clientes");

        btnAddCliente.addActionListener(e -> clienteController.showAddClienteView());
        btnRemoveCliente.addActionListener(e -> clienteController.showRemoveClienteView());
        btnUpdateCliente.addActionListener(e -> clienteController.showUpdateClienteView());
        btnDisplayClientes.addActionListener(e -> clienteController.showDisplayClientesView());

        panel.add(btnAddCliente);
        panel.add(btnRemoveCliente);
        panel.add(btnUpdateCliente);
        panel.add(btnDisplayClientes);

        return panel;
    }

    private static JPanel createTelefonePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddTelefone = new JButton("Adicionar Telefone");
        JButton btnRemoveTelefone = new JButton("Remover Telefone");
        JButton btnDisplayTelefones = new JButton("Listar Telefones");

        btnAddTelefone.addActionListener(e -> telefoneController.showAddTelefoneView());
        btnRemoveTelefone.addActionListener(e -> telefoneController.showRemoveTelefoneView());
        btnDisplayTelefones.addActionListener(e -> telefoneController.showDisplayTelefonesView());

        panel.add(btnAddTelefone);
        panel.add(btnRemoveTelefone);
        panel.add(btnDisplayTelefones);

        return panel;
    }

    private static JPanel createHospedagemPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddHospedagem = new JButton("Adicionar Hospedagem");
        JButton btnRemoveHospedagem = new JButton("Remover Hospedagem");
        JButton btnUpdateHospedagem = new JButton("Atualizar Hospedagem");
        JButton btnDisplayHospedagens = new JButton("Listar Hospedagens");

        btnAddHospedagem.addActionListener(e -> hospedagemController.showAddHospedagemView());
        btnRemoveHospedagem.addActionListener(e -> hospedagemController.showRemoveHospedagemView());
        btnDisplayHospedagens.addActionListener(e -> hospedagemController.showDisplayHospedagensView());
        btnUpdateHospedagem.addActionListener(e -> hospedagemController.showUpdateHospedagemView());


        panel.add(btnAddHospedagem);
        panel.add(btnRemoveHospedagem);
        panel.add(btnUpdateHospedagem);
        panel.add(btnDisplayHospedagens);

        return panel;
    }

    private static JPanel createServicoPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddServico = new JButton("Adicionar Serviço");
        JButton btnRemoveServico = new JButton("Remover Serviço");
        JButton btnDisplayServicos = new JButton("Listar Serviços");

        btnAddServico.addActionListener(e -> servicoController.showAddServicoView());
        btnRemoveServico.addActionListener(e -> servicoController.showRemoveServicoView());
        btnDisplayServicos.addActionListener(e -> servicoController.showDisplayServicosView());

        panel.add(btnAddServico);
        panel.add(btnRemoveServico);
        panel.add(btnDisplayServicos);

        return panel;
    }

    private static JPanel createChalePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddChale = new JButton("Adicionar Chalé");
        JButton btnRemoveChale = new JButton("Remover Chalé");
        JButton btnUpdateChale = new JButton("Atualizar Chalé");
        JButton btnDisplayChales = new JButton("Listar Chalés");

        btnAddChale.addActionListener(e -> chaleController.showAddChaleView());
        btnRemoveChale.addActionListener(e -> chaleController.showRemoveChaleView());
        btnUpdateChale.addActionListener(e -> chaleController.showUpdateChaleView());
        btnDisplayChales.addActionListener(e -> chaleController.showDisplayChalesView());

        panel.add(btnAddChale);
        panel.add(btnRemoveChale);
        panel.add(btnUpdateChale);
        panel.add(btnDisplayChales);

        return panel;
    }

    private static JPanel createItemPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton btnAddItem = new JButton("Adicionar Item");
        JButton btnRemoveItem = new JButton("Remover Item");
        JButton btnDisplayItems = new JButton("Listar Itens");

        btnAddItem.addActionListener(e -> itemController.showAddItemView());
        btnRemoveItem.addActionListener(e -> itemController.showRemoveItemView());
        btnDisplayItems.addActionListener(e -> itemController.showDisplayItemsView());

        panel.add(btnAddItem);
        panel.add(btnRemoveItem);
        panel.add(btnDisplayItems);

        return panel;
    }
}
