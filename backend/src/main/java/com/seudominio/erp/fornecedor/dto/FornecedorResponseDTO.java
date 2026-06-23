package com.seudominio.erp.fornecedor.dto;

import com.seudominio.erp.fornecedor.entity.Fornecedor;

import java.time.LocalDateTime;

public record FornecedorResponseDTO(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String telefone,
        String email,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado,
        String cep,
        Integer prazoEntregaDias,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static FornecedorResponseDTO of(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getRazaoSocial(),
                fornecedor.getNomeFantasia(),
                fornecedor.getCnpj(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.getLogradouro(),
                fornecedor.getNumero(),
                fornecedor.getBairro(),
                fornecedor.getCidade(),
                fornecedor.getEstado(),
                fornecedor.getCep(),
                fornecedor.getPrazoEntregaDias(),
                fornecedor.getAtivo(),
                fornecedor.getCreatedAt(),
                fornecedor.getUpdatedAt()
        );
    }
}
