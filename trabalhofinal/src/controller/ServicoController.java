package controller;

import model.Servico;
import view.ServicoView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoController {
    private Connection connection;

    public ServicoController(Connection connection) {
        this.connection = connection;
    }

    public List<Servico> getAllServicos() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String query = "SELECT * FROM Servico"; // Nome correto da tabela
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setCodServico(rs.getInt("codServico")); // Nome correto da coluna
                servico.setNomeServico(rs.getString("nomeServico")); // Nome correto da coluna
                servico.setValorServico(rs.getDouble("valorServico")); // Nome correto da coluna
                servicos.add(servico);
            }
        }
        return servicos;
    }

    public void addServico(Servico servico) throws SQLException {
        String query = "INSERT INTO Servico (nomeServico, valorServico) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getValorServico());
            stmt.executeUpdate();
        }
    }

    public void updateServico(int codServico, String nomeServico, double valorServico) throws SQLException {
        String query = "UPDATE Servico SET nomeServico = ?, valorServico = ? WHERE codServico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomeServico);
            stmt.setDouble(2, valorServico);
            stmt.setInt(3, codServico);
            stmt.executeUpdate();
        }
    }

    public void removeServico(int codServico) throws SQLException {
        String query = "DELETE FROM Servico WHERE codServico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, codServico);
            stmt.executeUpdate();
        }
        
    }
    public void showAddServicoView() {
        ServicoView view = new ServicoView(connection);
        view.addServico();
    }

    public void showRemoveServicoView() {
        ServicoView view = new ServicoView(connection);
        view.removeServico();
    }

    public void showDisplayServicosView() {
        ServicoView view = new ServicoView(connection);
        view.displayServicos();
    }   
}
