package persistence;

import model.Hospedagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedagemDAO {
    private Connection connection;

    public HospedagemDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertHospedagem(Hospedagem hospedagem) throws SQLException {
        String sql = "INSERT INTO Hospedagem (codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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

    public List<Hospedagem> getAllHospedagens() throws SQLException {
        List<Hospedagem> hospedagens = new ArrayList<>();
        String sql = "SELECT * FROM Hospedagem";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setCodHospedagem(rs.getInt("codHospedagem"));
                hospedagem.setCodChale(rs.getInt("codChale"));
                hospedagem.setCodCliente(rs.getInt("codCliente")); // Ajuste aqui
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
}
