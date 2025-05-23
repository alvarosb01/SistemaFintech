package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;

import java.util.List;

public interface AtivoFinanceiroDAO {
    void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException;


    List<AtivoFinanceiro> listarTodos() throws DBException;

    void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException;

    AtivoFinanceiro buscar(int id);

    void remover(int id) throws DBException;

    List<AtivoFinanceiro> listar();
}
