package com.seumanoel.empacotamento.controller;

import com.seumanoel.empacotamento.model.Caixa;
import com.seumanoel.empacotamento.model.Pedido;
import com.seumanoel.empacotamento.model.Produto;
import com.seumanoel.empacotamento.service.EmpacotamentoService;
import com.seumanoel.empacotamento.service.EmpacotamentoService.PedidoCaixas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/empacotamento")
@Tag(name = "Empacotamento", description = "API para empacotamento de pedidos")
public class PedidoController {

    private final EmpacotamentoService empacotamentoService;

    public PedidoController(EmpacotamentoService empacotamentoService) {
        this.empacotamentoService = empacotamentoService;
    }

    // DTO para entrada
    public static class PedidoRequest {
        public List<Pedido> pedidos;

        public List<Pedido> getPedidos() {
            return pedidos;
        }

        public void setPedidos(List<Pedido> pedidos) {
            this.pedidos = pedidos;
        }
    }

    // DTO para resposta conforme saída desejada
    public static class PedidoResponse {
        public List<PedidoResult> pedidos;

        public PedidoResponse(List<PedidoResult> pedidos) {
            this.pedidos = pedidos;
        }

        public List<PedidoResult> getPedidos() {
            return pedidos;
        }

        public void setPedidos(List<PedidoResult> pedidos) {
            this.pedidos = pedidos;
        }
    }

    public static class PedidoResult {
        public String pedido_id;
        public List<CaixaResult> caixas;

        public PedidoResult(String pedido_id, List<CaixaResult> caixas) {
            this.pedido_id = pedido_id;
            this.caixas = caixas;
        }

        public String getPedido_id() {
            return pedido_id;
        }

        public void setPedido_id(String pedido_id) {
            this.pedido_id = pedido_id;
        }

        public List<CaixaResult> getCaixas() {
            return caixas;
        }

        public void setCaixas(List<CaixaResult> caixas) {
            this.caixas = caixas;
        }
    }

    public static class CaixaResult {
        public String caixa_id;
        public List<String> produtos;
        public String observacao;

        public CaixaResult(String caixa_id, List<String> produtos) {
            this.caixa_id = caixa_id;
            this.produtos = produtos;
        }

        public CaixaResult(String caixa_id, List<String> produtos, String observacao) {
            this.caixa_id = caixa_id;
            this.produtos = produtos;
            this.observacao = observacao;
        }

        public String getCaixa_id() {
            return caixa_id;
        }

        public void setCaixa_id(String caixa_id) {
            this.caixa_id = caixa_id;
        }

        public List<String> getProdutos() {
            return produtos;
        }

        public void setProdutos(List<String> produtos) {
            this.produtos = produtos;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }
    }

    @Operation(summary = "Empacota os pedidos informados")
    @PostMapping("/pedidos")
    public ResponseEntity<PedidoResponse> empacotarPedidos(@RequestBody PedidoRequest request) {
        List<PedidoCaixas> pedidosCaixas = empacotamentoService.empacotarPedidos(request.getPedidos());

        List<PedidoResult> respostaPedidos = new ArrayList<>();

        for (PedidoCaixas pedidoCaixas : pedidosCaixas) {
            List<CaixaResult> caixasRes = new ArrayList<>();

            // Se algum produto não coube
            if (!pedidoCaixas.getProdutosNaoEmpacotados().isEmpty()) {
                List<String> produtosNaoCouberam = new ArrayList<>();
                for (Produto p : pedidoCaixas.getProdutosNaoEmpacotados()) {
                    produtosNaoCouberam.add(p.getProduto_id());
                }
                caixasRes.add(new CaixaResult(null, produtosNaoCouberam,
                        "Produto não cabe em nenhuma caixa disponível."));
            }

            for (Caixa caixa : pedidoCaixas.getCaixas()) {
                List<String> produtosIds = new ArrayList<>();
                for (Produto p : caixa.getProdutos()) {
                    produtosIds.add(p.getProduto_id());
                }
                caixasRes.add(new CaixaResult(caixa.getCaixa_id(), produtosIds));
            }

            respostaPedidos.add(new PedidoResult(pedidoCaixas.getPedido_id(), caixasRes));
        }

        return ResponseEntity.ok(new PedidoResponse(respostaPedidos));
    }
}
