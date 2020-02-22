package com.stone.meSalva.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Estabelecimentos")
public class Estabelecimento {

    @Id
    private String id;
    private String cnpj;
    private String razaoSocial;
    private GeoJsonPoint localizacao;

    public Estabelecimento(String cnpj, String razaoSocial, GeoJsonPoint localizacao) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.localizacao = localizacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public GeoJsonPoint getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(GeoJsonPoint localizacao) {
        this.localizacao = localizacao;
    }
}
