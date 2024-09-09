package persistence;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, CEPCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
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
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
