package br.com.fintech.dao.impl;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.AtivoFinanceiro;

import java.time.LocalDate;
import java.util.List;


public class AtivoFinanceiroImpl extends AtivoFinanceiro {
    private int quantidade;
    private LocalDate dataCompra;

    public AtivoFinanceiroImpl(String nome, int quantidade, double valor, LocalDate dataCompra) {
        super(nome, valor);
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

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
}

