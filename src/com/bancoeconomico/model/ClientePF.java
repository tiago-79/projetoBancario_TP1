package com.bancoeconomico.model;

import java.io.Serializable;

public class ClientePF extends Cliente {

    private String cpf;

    public ClientePF(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getId() {
        return getCpf();
    }

    public String toString(){
        return getNome() + "," +
                getCpf() + "," +
                getClass().getName().substring(32) + "," +
                getContas().get(0).getSaldo();
    }
}
