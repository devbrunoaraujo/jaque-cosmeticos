package com.seudominio.erp.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank @Size(max = 150) String nome,
        @NotBlank @Size(max = 50) String sku,
        @Size(max = 100) String categoria,
        @Size(max = 100) String marca,
        @Size(max = 150) String fornecedor,
        @NotNull @Positive BigDecimal precoCusto,
        @NotNull @Positive BigDecimal precoVenda,
        @NotBlank @Size(max = 20) String unidadeMedida
) {
}
