package br.com.fintech.dao;

import br.com.fintech.dao.AtivoFinanceiroDAO;
import br.com.fintech.dao.impl.OracleAtivoFinanceiroDao;

public class DAOFactory {

    public static AtivoFinanceiroDAO getAtivoFinanceiroDAO() {
        return new OracleAtivoFinanceiroDao();
    }

    // Você pode adicionar outros DAOs aqui futuramente
    // public static UsuarioDAO getUsuarioDAO() { return new OracleUsuarioDao(); }

}
