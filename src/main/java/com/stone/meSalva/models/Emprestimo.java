package com.stone.meSalva.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Emprestimos")
public class Emprestimo {

    @Id
    private String id;
    private String cpf;
    private Double valor;
    private Date dt_criacao;
    private Date dt_retirada;
    private Date dt_quitacao;
    private Boolean quitada;
    private String cnpjConcedente;
    private String cnpjRecebedor;

    public Emprestimo(String cpf, Double valor, Date dt_criacao, Date dt_retirada, Date dt_quitacao, Boolean quitada, String cnpjConcedente, String cnpjRecebedor) {
        this.cpf = cpf;
        this.valor = valor;
        this.dt_criacao = dt_criacao;
        this.dt_retirada = dt_retirada;
        this.dt_quitacao = dt_quitacao;
        this.quitada = quitada;
        this.cnpjConcedente = cnpjConcedente;
        this.cnpjRecebedor = cnpjRecebedor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    public Date getDt_retirada() {
        return dt_retirada;
    }

    public void setDt_retirada(Date dt_retirada) {
        this.dt_retirada = dt_retirada;
    }

    public Boolean getQuitada() {
        return quitada;
    }

    public void setQuitada(Boolean quitada) {
        this.quitada = quitada;
    }

    public Date getDt_quitacao() {
        return dt_quitacao;
    }

    public void setDt_quitacao(Date dt_quitacao) {
        this.dt_quitacao = dt_quitacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpjConcedente() {
        return cnpjConcedente;
    }

    public void setCnpjConcedente(String cnpjConcedente) {
        this.cnpjConcedente = cnpjConcedente;
    }

    public String getCnpjRecebedor() {
        return cnpjRecebedor;
    }

    public void setCnpjRecebedor(String cnpjRecebedor) {
        this.cnpjRecebedor = cnpjRecebedor;
    }
}
