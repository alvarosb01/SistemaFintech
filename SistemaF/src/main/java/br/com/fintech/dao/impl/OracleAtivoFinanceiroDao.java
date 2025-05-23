package br.com.fintech.dao.impl;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.ConnectionManager;
import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleAtivoFinanceiroDao implements AtivoFinanceiroDAO {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USER = "RM560229";
    private static final String PASSWORD = "301199";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException {
        String sql = "INSERT INTO TB_ATIVO_FINANCEIRO (id, NOME, VALOR, QUANTIDADE, DATA_COMPRA) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ativoFinanceiro.getNome());
            stmt.setDouble(2, ativoFinanceiro.getValor());

            // Cast para acessar métodos da subclasse AtivoFinanceiroImpl
            stmt.setInt(3, ((AtivoFinanceiroImpl) ativoFinanceiro).getQuantidade());
            stmt.setDate(4, Date.valueOf(((AtivoFinanceiroImpl) ativoFinanceiro).getDataCompra()));

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao cadastrar ativo financeiro.", e);
        }
    }


    @Override
    public List<AtivoFinanceiro> listarTodos() throws DBException {
        return List.of();
    }

    @Override
    public void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException {
        String sql = "UPDATE TB_ATIVO_FINANCEIRO SET NOME = ?, VALOR = ? WHERE ID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ativoFinanceiro.getNome());
            stmt.setDouble(2, ativoFinanceiro.getValor());
            stmt.setInt(3, ativoFinanceiro.getId());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao atualizar ativo financeiro.", e);
        }
    }

    @Override
    public AtivoFinanceiro buscar(int id) {
        return null;
    }

    @Override
    public void remover(int id) {
        String sql = "DELETE FROM TB_ATIVO_FINANCEIRO WHERE ID_ATIVO = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<AtivoFinanceiro> listar() {
        List<AtivoFinanceiro> lista = new ArrayList<>();
        String sql = "SELECT  NOME, VALOR, QUANTIDADE, DATA_COMPRA FROM TB_ATIVO_FINANCEIRO";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String nome = rs.getString("NOME");
                double valor = rs.getDouble("VALOR");
                int quantidade = rs.getInt("QUANTIDADE");
                Date dataCompraSQL = rs.getDate("DATA_COMPRA");
                LocalDate dataCompra = (dataCompraSQL != null) ? dataCompraSQL.toLocalDate() : null;

                AtivoFinanceiroImpl ativo = new AtivoFinanceiroImpl(nome, quantidade, valor, dataCompra);
                ativo.setId();

                lista.add(ativo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
