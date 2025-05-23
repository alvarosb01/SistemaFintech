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

    // NOVO MÉTODO ADICIONADO
    public void setId(int id) {
        super.setId(id); // Chama o método da classe pai
    }

    @Override
    public void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException {
        // Implementação vazia - estes métodos não deveriam estar aqui
        // pois a responsabilidade é do DAO
    }

    @Override
    public void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException {
        // Implementação vazia - responsabilidade do DAO
    }

    @Override
    public void remover(int codigo) throws DBException {
        // Implementação vazia - responsabilidade do DAO
    }

    @Override
    public AtivoFinanceiro buscar(int id) {
        // Implementação vazia - responsabilidade do DAO
        return null;
    }

    @Override
    public List<AtivoFinanceiro> listar() {
        // Implementação vazia - responsabilidade do DAO
        return List.of();
    }
}