package controller;

import model.Hospedagem;
import view.HospedagemView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedagemController {
    private Connection connection;

    public HospedagemController(Connection connection) {
        this.connection = connection;
    }

    public void addHospedagem(Hospedagem hospedagem) throws SQLException {
        String sql = "INSERT INTO Hospedagem (codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, hospedagem.getCodChale());
            stmt.setInt(2, hospedagem.getCodCliente());
            stmt.setString(3, hospedagem.getEstado());
            stmt.setDate(4, hospedagem.getDataInicio());
            stmt.setDate(5, hospedagem.getDataFim());
            stmt.setInt(6, hospedagem.getQtdPessoas());
            stmt.setDouble(7, hospedagem.getDesconto());
            stmt.setDouble(8, hospedagem.getValorFinal());
            stmt.executeUpdate();
        }
    }

    public void updateHospedagem(Hospedagem hospedagem) throws SQLException {
        String sql = "UPDATE Hospedagem SET codChale = ?, codCliente = ?, estado = ?, dataInicio = ?, dataFim = ?, qtdPessoas = ?, desconto = ?, valorFinal = ? WHERE codHospedagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, hospedagem.getCodChale());
            stmt.setInt(2, hospedagem.getCodCliente());
            stmt.setString(3, hospedagem.getEstado());
            stmt.setDate(4, hospedagem.getDataInicio());
            stmt.setDate(5, hospedagem.getDataFim());
            stmt.setInt(6, hospedagem.getQtdPessoas());
            stmt.setDouble(7, hospedagem.getDesconto());
            stmt.setDouble(8, hospedagem.getValorFinal());
            stmt.setInt(9, hospedagem.getCodHospedagem());
            stmt.executeUpdate();
        }
    }

    public void removeHospedagem(int codHospedagem) throws SQLException {
        String sql = "DELETE FROM Hospedagem WHERE codHospedagem = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codHospedagem);
            stmt.executeUpdate();
        }
    }

    public List<Hospedagem> getAllHospedagens() throws SQLException {
        List<Hospedagem> hospedagens = new ArrayList<>();
        String sql = "SELECT * FROM Hospedagem"; // Nome correto da tabela
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setCodHospedagem(rs.getInt("codHospedagem"));
                hospedagem.setCodChale(rs.getInt("codChale"));
                hospedagem.setCodCliente(rs.getInt("codCliente"));
                hospedagem.setEstado(rs.getString("estado"));
                hospedagem.setDataInicio(rs.getDate("dataInicio"));
                hospedagem.setDataFim(rs.getDate("dataFim"));
                hospedagem.setQtdPessoas(rs.getInt("qtdPessoas"));
                hospedagem.setDesconto(rs.getDouble("desconto"));
                hospedagem.setValorFinal(rs.getDouble("valorFinal"));
                hospedagens.add(hospedagem);
            }
        }
        return hospedagens;
    }


    public void showAddHospedagemView() {
        HospedagemView view = new HospedagemView(connection);
        view.addHospedagem();
    }

    public void showRemoveHospedagemView() {
        HospedagemView view = new HospedagemView(connection);
        view.removeHospedagem();
    }

    public void showDisplayHospedagensView() {
        HospedagemView view = new HospedagemView(connection);
        view.displayHospedagens();
    }
    public void showUpdateHospedagemView() {
        HospedagemView view = new HospedagemView(connection);
        view.updateHospedagem();
    }
}
