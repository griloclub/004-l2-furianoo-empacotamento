package com.seumanoel.empacotamento.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pedido {
    private String id;
    private List<Produto> produtos;

    public Pedido() {}

    public Pedido(String id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }
}
