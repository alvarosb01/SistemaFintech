package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;

import java.util.List;

public interface AtivoFinanceiroDAO {

    void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException;

    List<AtivoFinanceiro> listarTodos() throws DBException;

    void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException;

    AtivoFinanceiro buscarPorId(int id) throws DBException;

    boolean remover(int id) throws DBException;

    boolean existe(int id) throws DBException;
}