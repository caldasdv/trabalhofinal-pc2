package controller;

import model.Telefone;
import view.TelefoneView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefoneController {
    private Connection connection;
    private TelefoneView telefoneView;

    public TelefoneController(Connection connection) {
        this.connection = connection;
        this.telefoneView = new TelefoneView(connection);
    }

    public void showAddTelefoneView() {
        telefoneView.addTelefone();
    }

    public void showRemoveTelefoneView() {
        telefoneView.removeTelefone();
    }

    public void showDisplayTelefonesView() {
        telefoneView.displayTelefones();
    }

    public void addTelefone(Telefone telefone) throws SQLException {
        String query = "INSERT INTO Telefone (telefone, codCliente, tipoTelefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, telefone.getTelefone());
            stmt.setInt(2, telefone.getCodCliente());
            stmt.setString(3, telefone.getTipoTelefone());
            stmt.executeUpdate();
        }
    }

    public void removeTelefone(String telefone, int codCliente) throws SQLException {
        String query = "DELETE FROM Telefone WHERE telefone = ? AND codCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, telefone);
            stmt.setInt(2, codCliente);
            stmt.executeUpdate();
        }
    }

    public Telefone getTelefone(String telefone, int codCliente) throws SQLException {
        String query = "SELECT * FROM Telefone WHERE telefone = ? AND codCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, telefone);
            stmt.setInt(2, codCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Telefone telefoneObj = new Telefone();
                    telefoneObj.setTelefone(rs.getString("telefone"));
                    telefoneObj.setCodCliente(rs.getInt("codCliente"));
                    telefoneObj.setTipoTelefone(rs.getString("tipoTelefone"));
                    return telefoneObj;
                } else {
                    return null;
                }
            }
        }
    }

    public void displayAllTelefones() throws SQLException {
        String query = "SELECT * FROM Telefone";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            // Criar e definir um modelo de tabela aqui para exibir todos os telefones na UI
        }
    }
}
