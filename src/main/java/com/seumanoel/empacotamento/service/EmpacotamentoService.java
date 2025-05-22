package com.seumanoel.empacotamento.service;

import com.seumanoel.empacotamento.model.Caixa;
import com.seumanoel.empacotamento.model.Pedido;
import com.seumanoel.empacotamento.model.Produto;
import com.seumanoel.empacotamento.model.Dimensoes; // Ajustado para importar corretamente
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmpacotamentoService {

    // Caixas disponíveis (constantes)
    private static final List<Caixa> CAIXAS_MODELOS = List.of(
            new Caixa("Caixa 1", new Dimensoes(30, 40, 80), new ArrayList<>()),
            new Caixa("Caixa 2", new Dimensoes(80, 50, 40), new ArrayList<>()),
            new Caixa("Caixa 3", new Dimensoes(50, 80, 60), new ArrayList<>())
    );

    public List<PedidoCaixas> empacotarPedidos(List<Pedido> pedidos) {
        List<PedidoCaixas> resultado = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoCaixas pedidoCaixas = empacotarPedido(pedido);
            resultado.add(pedidoCaixas);
        }

        return resultado;
    }

    private PedidoCaixas empacotarPedido(Pedido pedido) {
        List<Caixa> caixasUsadas = new ArrayList<>();
        List<Produto> produtosNaoEmpacotados = new ArrayList<>();

        for (Produto produto : pedido.getProdutos()) {
            boolean encaixado = false;

            // Tenta encaixar nas caixas já abertas
            for (Caixa caixa : caixasUsadas) {
                if (caixa.adicionaProduto(produto)) {
                    encaixado = true;
                    break;
                }
            }

            if (!encaixado) {
                // Tenta abrir nova caixa
                Caixa caixaNova = encontrarCaixaParaProduto(produto);
                if (caixaNova != null) {
                    caixaNova.adicionaProduto(produto);
                    caixasUsadas.add(caixaNova);
                } else {
                    // Produto não cabe em nenhuma caixa
                    produtosNaoEmpacotados.add(produto);
                }
            }
        }

        return new PedidoCaixas(pedido.getId(), caixasUsadas, produtosNaoEmpacotados);
    }

    // Retorna uma nova caixa para o produto se couber, escolhendo a caixa com menor volume que caiba
    private Caixa encontrarCaixaParaProduto(Produto produto) {
        return CAIXAS_MODELOS.stream()
                .filter(c -> produto.getDimensoes().cabeEm(c.getDimensoes()))
                .sorted(Comparator.comparingInt(c -> c.getDimensoes().volume()))
                .findFirst()
                .map(c -> new Caixa(c.getCaixa_id(), c.getDimensoes(), new ArrayList<>()))
                .orElse(null);
    }

    // Classe interna para resultado
    public static class PedidoCaixas {
        private String pedido_id;
        private List<Caixa> caixas;
        private List<Produto> produtosNaoEmpacotados;

        public PedidoCaixas(String pedido_id, List<Caixa> caixas, List<Produto> naoEmpacotados) {
            this.pedido_id = pedido_id;
            this.caixas = caixas;
            this.produtosNaoEmpacotados = naoEmpacotados;
        }

        public String getPedido_id() {
            return pedido_id;
        }

        public List<Caixa> getCaixas() {
            return caixas;
        }

        public List<Produto> getProdutosNaoEmpacotados() {
            return produtosNaoEmpacotados;
        }
    }
}
