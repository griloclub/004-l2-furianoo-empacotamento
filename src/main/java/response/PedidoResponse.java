package com.seumanoel.empacotamento.response;

import com.seumanoel.empacotamento.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {
    private String pedidoId;
    private int quantidadeProdutos;
    private int quantidadeCaixas;
    private int volumeTotal;
    private List<Produto> produtos;

    // Construtor personalizado para montar a resposta rapidamente
    public PedidoResponse(String pedidoId, List<Produto> produtos, int quantidadeCaixas, int volumeTotal) {
        this.pedidoId = pedidoId;
        this.produtos = produtos;
        this.quantidadeProdutos = produtos != null ? produtos.size() : 0;
        this.quantidadeCaixas = quantidadeCaixas;
        this.volumeTotal = volumeTotal;
    }
}
