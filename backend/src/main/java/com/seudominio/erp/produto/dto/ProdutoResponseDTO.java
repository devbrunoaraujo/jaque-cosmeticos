package com.seudominio.erp.produto.dto;

import com.seudominio.erp.produto.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String sku,
        String categoria,
        String marca,
        String fornecedor,
        BigDecimal precoCusto,
        BigDecimal precoVenda,
        String unidadeMedida,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProdutoResponseDTO of(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getSku(),
                produto.getCategoria(),
                produto.getMarca(),
                produto.getFornecedor(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda(),
                produto.getUnidadeMedida(),
                produto.getAtivo(),
                produto.getCreatedAt(),
                produto.getUpdatedAt()
        );
    }
}
