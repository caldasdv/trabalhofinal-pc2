package controller;

import model.Cliente;
import view.ClienteView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteController {
    private Connection connection;
    private ClienteView clienteView;

    public ClienteController(Connection connection) {
        this.connection = connection;
        this.clienteView = new ClienteView(connection);
    }

    public void showAddClienteView() {
        clienteView.addCliente();
    }

    public void showUpdateClienteView() {
        clienteView.updateCliente();
    }

    public void showRemoveClienteView() {
        clienteView.removeCliente();
    }

    public void showDisplayClientesView() {
        clienteView.displayClientes();
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getRgCliente());
            stmt.setString(3, cliente.getEnderecoCliente());
            stmt.setString(4, cliente.getBairroCliente());
            stmt.setString(5, cliente.getCidadeCliente());
            stmt.setString(6, cliente.getEstadoCliente());
            stmt.setString(7, cliente.getCEPCliente());
            stmt.setDate(8, cliente.getNascimentoCliente());
            stmt.executeUpdate();
        }
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE Cliente SET nomeCliente = ?, rgCliente = ?, enderecoCliente = ?, bairroCliente = ?, cidadeCliente = ?, estadoCliente = ?, CEPCliente = ?, nascimentoCliente = ? WHERE codCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getRgCliente());
            stmt.setString(3, cliente.getEnderecoCliente());
            stmt.setString(4, cliente.getBairroCliente());
            stmt.setString(5, cliente.getCidadeCliente());
            stmt.setString(6, cliente.getEstadoCliente());
            stmt.setString(7, cliente.getCEPCliente());
            stmt.setDate(8, cliente.getNascimentoCliente());
            stmt.setInt(9, cliente.getCodCliente());
            stmt.executeUpdate();
        }
    }

    public void removeCliente(int codCliente) throws SQLException {
        String query = "DELETE FROM Cliente WHERE codCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codCliente);
            stmt.executeUpdate();
        }
    }

    public Cliente getCliente(int codCliente) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE codCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("codCliente"));
                    cliente.setNomeCliente(rs.getString("nomeCliente"));
                    cliente.setRgCliente(rs.getString("rgCliente"));
                    cliente.setEnderecoCliente(rs.getString("enderecoCliente"));
                    cliente.setBairroCliente(rs.getString("bairroCliente"));
                    cliente.setCidadeCliente(rs.getString("cidadeCliente"));
                    cliente.setEstadoCliente(rs.getString("estadoCliente"));
                    cliente.setCEPCliente(rs.getString("CEPCliente"));
                    cliente.setNascimentoCliente(rs.getDate("nascimentoCliente"));
                    return cliente;
                } else {
                    return null;
                }
            }
        }
    }

    public void displayAllClientes() throws SQLException {
        String query = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            // Criar e definir um modelo de tabela aqui para exibir todos os clientes na UI
        }
    }
}
