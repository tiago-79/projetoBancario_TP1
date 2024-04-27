package com.bancoeconomico.model;

import java.io.Serializable;

public class ClientePJ extends Cliente {

    private String cnpj;

    public ClientePJ(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getId() {
        return getCnpj();
    }

    public String toString(){
        return getNome() + "," +
                getCnpj() + "," +
                getClass().getName().substring(32) + "," +
                getContas().get(0).getSaldo();
    }
}
