package br.com.fintech.model;

import br.com.fintech.exception.DBException;

import java.util.List;

public abstract class AtivoFinanceiro {
    private int id;
    private String nome;
    private double valor;

    public AtivoFinanceiro() {}

    public AtivoFinanceiro(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public AtivoFinanceiro(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public AtivoFinanceiro(int i, String nomeAtivo, String ticker, double precoAtual) {

    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public abstract void cadastrar(AtivoFinanceiro ativoFinanceiro) throws DBException;

    public abstract void atualizar(AtivoFinanceiro ativoFinanceiro) throws DBException;

    public abstract void remover(int codigo) throws DBException;

    public abstract AtivoFinanceiro buscar(int id);

    public abstract List<AtivoFinanceiro> listar();
}
