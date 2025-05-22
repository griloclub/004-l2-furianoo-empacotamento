package com.seumanoel.empacotamento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dimensoes {
    private int altura;
    private int largura;
    private int comprimento;

    public int volume() {
        return altura * largura * comprimento;
    }

    public boolean cabeEm(Dimensoes caixa) {
        int[] produto = new int[]{altura, largura, comprimento};
        int[] caixaDims = new int[]{caixa.altura, caixa.largura, caixa.comprimento};

        java.util.Arrays.sort(produto);
        java.util.Arrays.sort(caixaDims);

        return (produto[0] <= caixaDims[0]) &&
                (produto[1] <= caixaDims[1]) &&
                (produto[2] <= caixaDims[2]);
    }
}
