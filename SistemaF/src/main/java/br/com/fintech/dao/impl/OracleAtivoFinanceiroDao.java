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
        // Reutilizando sua implementação do método listar()
        return listar();
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
    public AtivoFinanceiro buscarPorId(int id) throws DBException {
        String sql = "SELECT ID, NOME, VALOR, QUANTIDADE, DATA_COMPRA FROM TB_ATIVO_FINANCEIRO WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                double valor = rs.getDouble("VALOR");
                int quantidade = rs.getInt("QUANTIDADE");
                Date dataCompraSQL = rs.getDate("DATA_COMPRA");
                LocalDate dataCompra = (dataCompraSQL != null) ? dataCompraSQL.toLocalDate() : null;

                AtivoFinanceiroImpl ativo = new AtivoFinanceiroImpl(nome, quantidade, valor, dataCompra);
                // Usando reflexão para setar o ID, já que não há método setId(int)
                try {
                    java.lang.reflect.Field idField = AtivoFinanceiro.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(ativo, rs.getInt("ID"));
                } catch (Exception e) {
                    // Se não conseguir setar o ID, continua sem ele
                    System.err.println("Não foi possível setar o ID: " + e.getMessage());
                }

                return ativo;
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao buscar ativo por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean remover(int id) throws DBException {
        String sql = "DELETE FROM TB_ATIVO_FINANCEIRO WHERE ID = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);

            return linhasAfetadas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao remover ativo: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existe(int id) throws DBException {
        String sql = "SELECT COUNT(*) FROM TB_ATIVO_FINANCEIRO WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;

        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao verificar existência do ativo: " + e.getMessage(), e);
        }
    }

    @Override
    public List<AtivoFinanceiro> listar() {
        List<AtivoFinanceiro> lista = new ArrayList<>();
        String sql = "SELECT ID, NOME, VALOR, QUANTIDADE, DATA_COMPRA FROM TB_ATIVO_FINANCEIRO";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                double valor = rs.getDouble("VALOR");
                int quantidade = rs.getInt("QUANTIDADE");
                Date dataCompraSQL = rs.getDate("DATA_COMPRA");
                LocalDate dataCompra = (dataCompraSQL != null) ? dataCompraSQL.toLocalDate() : null;

                AtivoFinanceiroImpl ativo = new AtivoFinanceiroImpl(nome, quantidade, valor, dataCompra);
                // Usando reflexão para setar o ID, já que não há método setId(int)
                try {
                    java.lang.reflect.Field idField = AtivoFinanceiro.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(ativo, id);
                } catch (Exception e) {
                    // Se não conseguir setar o ID, continua sem ele
                    System.err.println("Não foi possível setar o ID: " + e.getMessage());
                }

                lista.add(ativo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return lista;
    }
}