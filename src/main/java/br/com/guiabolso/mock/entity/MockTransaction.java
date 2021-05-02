package br.com.guiabolso.mock.entity;

import java.util.Date;

public class MockTransaction {
    private String descricao;
    private String data;
    private int valor;

    public MockTransaction() {
    }

    public MockTransaction(String descricao, String data, int valor) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
