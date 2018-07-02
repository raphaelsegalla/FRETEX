package com.example.dev.fretex;

import java.io.Serializable;

/**
 * Created by dev on 26/03/2018.
 */

public class Usuario implements Serializable{
    private  String nome;
    private String cpf;
    private String senha;
    private Cadastro cadastro;

    Usuario(){}

    public Usuario(String nome,String cpf,String senha, Cadastro cadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.cadastro = cadastro;
    }
}
