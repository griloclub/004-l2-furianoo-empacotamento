package com.seumanoel.empacotamento.dto;

import java.util.List;

public class CaixaDTO {
    private String id;
    private List<ProdutoDTO> produtos;
    private double volumeDisponivel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public double getVolumeDisponivel() {
        return volumeDisponivel;
    }

    public void setVolumeDisponivel(double volumeDisponivel) {
        this.volumeDisponivel = volumeDisponivel;
    }
}
