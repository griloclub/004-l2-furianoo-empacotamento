package com.seumanoel.empacotamento.dto;

public class ProdutoDTO {

    private String nome;
    private double peso;
    private DimensaoDTO dimensao;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public DimensaoDTO getDimensao() {
        return dimensao;
    }
    public void setDimensao(DimensaoDTO dimensao) {
        this.dimensao = dimensao;
    }
}
