package persistence;

import model.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {
    private Connection connection;

    public TelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTelefone(Telefone telefone) throws SQLException {
        String sql = "INSERT INTO Telefone (telefone, codCliente, tipoTelefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefone.getTelefone());
            stmt.setInt(2, telefone.getCodCliente());
            stmt.setString(3, telefone.getTipoTelefone());
            stmt.executeUpdate();
        }
    }

    public List<Telefone> getAllTelefones() throws SQLException {
        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM Telefone";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setTelefone(rs.getString("telefone"));
                telefone.setCodCliente(rs.getInt("codCliente"));
                telefone.setTipoTelefone(rs.getString("tipoTelefone"));
                telefones.add(telefone);
            }
        }
        return telefones;
    }
}
