package com.seumanoel.empacotamento.util;

import com.seumanoel.empacotamento.dto.CaixaDTO;
import com.seumanoel.empacotamento.dto.ProdutoDTO;
import com.seumanoel.empacotamento.dto.DimensaoDTO;
import com.seumanoel.empacotamento.model.CaixaEnum;

import java.util.ArrayList;
import java.util.List;

public class Empacotador {

    public List<CaixaDTO> empacotarProdutos(List<ProdutoDTO> produtos) {
        List<CaixaDTO> caixas = new ArrayList<>();

        for (ProdutoDTO produto : produtos) {
            boolean acomodado = false;

            for (CaixaDTO caixa : caixas) {
                if (comportaProduto(caixa, produto)) {
                    caixa.getProdutos().add(produto);
                    caixa.setVolumeDisponivel(caixa.getVolumeDisponivel() - calcularVolume(produto.getDimensao()));
                    acomodado = true;
                    break;
                }
            }

            if (!acomodado) {
                CaixaDTO novaCaixa = criarCaixa(produto);
                caixas.add(novaCaixa);
            }
        }

        return caixas;
    }

    private CaixaDTO criarCaixa(ProdutoDTO produto) {
        CaixaEnum tipoCaixa = selecionarCaixaAdequada(produto.getDimensao());

        CaixaDTO caixa = new CaixaDTO();
        caixa.setId(tipoCaixa.name());
        caixa.setProdutos(new ArrayList<>());
        caixa.getProdutos().add(produto);

        double volumeCaixa = calcularVolume(tipoCaixa.getDimensao());
        double volumeProduto = calcularVolume(produto.getDimensao());
        caixa.setVolumeDisponivel(volumeCaixa - volumeProduto);

        return caixa;
    }

    private boolean comportaProduto(CaixaDTO caixa, ProdutoDTO produto) {
        double volumeProduto = calcularVolume(produto.getDimensao());
        return caixa.getVolumeDisponivel() >= volumeProduto;
    }

    private double calcularVolume(DimensaoDTO dimensao) {
        return dimensao.getAltura() * dimensao.getLargura() * dimensao.getProfundidade();
    }

    private CaixaEnum selecionarCaixaAdequada(DimensaoDTO dimensaoProduto) {
        double volumeProduto = calcularVolume(dimensaoProduto);

        for (CaixaEnum tipo : CaixaEnum.values()) {
            double volumeCaixa = calcularVolume(tipo.getDimensao());
            if (volumeCaixa >= volumeProduto) {
                return tipo;
            }
        }

        // Se não couber em nenhuma caixa, usar a maior
        return CaixaEnum.GRANDE;
    }
}
