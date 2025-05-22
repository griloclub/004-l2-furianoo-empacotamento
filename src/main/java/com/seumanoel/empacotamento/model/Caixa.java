package com.seumanoel.empacotamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Caixa {
    private String caixa_id;
    private Dimensoes dimensoes;
    private List<Produto> produtos = new ArrayList<>();

    // Capacidade de volume
    public int volumeTotal() {
        return dimensoes.volume();
    }

    // Volume usado
    public int volumeUsado() {
        return produtos.stream().mapToInt(p -> p.getDimensoes().volume()).sum();
    }

    // Volume disponível
    public int volumeDisponivel() {
        return volumeTotal() - volumeUsado();
    }

    // Tenta adicionar produto se cabe
    public boolean adicionaProduto(Produto p) {
        if (!p.getDimensoes().cabeEm(this.dimensoes)) {
            return false;
        }
        if (volumeDisponivel() >= p.getDimensoes().volume()) {
            produtos.add(p);
            return true;
        }
        return false;
    }
}