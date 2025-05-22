package com.seumanoel.empacotamento.model;

import com.seumanoel.empacotamento.dto.DimensaoDTO;

public enum CaixaEnum {
    PEQUENA(30, 40, 80),
    MEDIA(80, 50, 40),
    GRANDE(80, 80, 40);

    private final double altura;
    private final double largura;
    private final double comprimento;

    CaixaEnum(double altura, double largura, double comprimento) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public double getVolume() {
        return altura * largura * comprimento;
    }

    // ✅ Método necessário para o Empacotador
    public DimensaoDTO getDimensao() {
        return new DimensaoDTO(altura, largura, comprimento);
    }
}
