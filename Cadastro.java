package com.example.dev.fretex;

import java.io.Serializable;

/**
 * Created by dev on 14/03/2018.
 */

public class Cadastro implements Serializable{
  private String estado;
  private String localidade;
  private String bairro;
  private String logradouro;
  private String numero;
  private String cep;
  private String email;
  private String telefone;
  private String uf;

  Cadastro(){}

  public Cadastro(String estado, String localidade, String bairro, String logradouro) {
    this.estado = estado;
    this.localidade = localidade;
    this.bairro = bairro;
    this.logradouro = logradouro;
  }


  public Cadastro( String estado, String localidade, String bairro, String logradouro, String numero, String cep, String email, String telefone) {
    this.estado = estado;
    this.localidade = localidade;
    this.bairro = bairro;
    this.logradouro = logradouro;
    this.numero = numero;
    this.cep = cep;
    this.email = email;
    this.telefone = telefone;
  }

  public String getUf(){
    return uf;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String cidade) {
    this.localidade = localidade;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
}
