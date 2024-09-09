package persistence;

import model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private Connection connection;

    public ServicoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO Servico (nomeServico, valorServico) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getValorServico());
            stmt.executeUpdate();
        }
    }

    public List<Servico> getAllServicos() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setCodServico(rs.getInt("codServico"));
                servico.setNomeServico(rs.getString("nomeServico"));
                servico.setValorServico(rs.getDouble("valorServico"));
                servicos.add(servico);
            }
        }
        return servicos;
    }
}
