package com.seumanoel.empacotamento.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {
    private String id;
    private String nome;
    private Dimensoes dimensoes;
    private double peso;

    public Produto() {}

    public Produto(String id, String nome, Dimensoes dimensoes, double peso) {
        this.id = id;
        this.nome = nome;
        this.dimensoes = dimensoes;
        this.peso = peso;
    }

    public int getVolume() {
        return dimensoes.volume();
    }

    public String getProduto_id() {
        return "";
    }
}
