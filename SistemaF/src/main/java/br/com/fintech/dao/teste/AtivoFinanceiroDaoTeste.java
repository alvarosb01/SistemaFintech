package br.com.fintech.dao.teste;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.DAOFactory;
import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;

import java.util.List;

public class AtivoFinanceiroDaoTeste {
    public static void main(String[] args) {

        AtivoFinanceiroDAO dao = DAOFactory.getAtivoFinanceiroDAO();

        // Cadastrar um ativo
        AtivoFinanceiro ativo = new AtivoFinanceiro(0, "Tesouro Nacional", 1100.00) {
            @Override
            public void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException {

            }

            @Override
            public void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException {

            }

            @Override
            public void remover(int codigo) throws DBException {

            }

            @Override
            public AtivoFinanceiro buscar(int id) {
                return null;
            }

            @Override
            public List<AtivoFinanceiro> listar() {
                return List.of();
            }
        };
        try {
            dao.cadastrar(ativo);
            System.out.println("Ativo financeiro cadastrado.");
        } catch (DBException e) {
            e.printStackTrace();
        }

        // Buscar um ativo pelo ID e atualizar
        ativo = dao.buscar(1); // Altere esse ID conforme o que existe no banco
        if (ativo != null) {
            ativo.setNome("CDB Banco Inter");
            ativo.setValor(1500.00);
            try {
                dao.atualizar(ativo);
                System.out.println("Ativo financeiro atualizado.");
            } catch (DBException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ativo com ID 1 não encontrado.");
        }

        // Listar os ativos
        List<AtivoFinanceiro> lista = dao.listar();
        for (AtivoFinanceiro item : lista) {
            System.out.println(
                    "ID: " + item.getId() + " | " +
                            "Nome: " + item.getNome() + " | " +
                            "Valor: " + item.getValor());
        }

        // Remover um ativo
        try {
            dao.remover(1); // Altere o ID conforme necessário
            System.out.println("Ativo financeiro removido.");
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
