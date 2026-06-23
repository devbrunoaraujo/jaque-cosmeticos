package com.seudominio.erp.fornecedor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorRequestDTO(
        @NotBlank @Size(max = 150) String razaoSocial,
        @Size(max = 150) String nomeFantasia,
        @NotBlank @Size(max = 14) String cnpj,
        @Size(max = 20) String telefone,
        @Email @Size(max = 150) String email,
        @Size(max = 150) String logradouro,
        @Size(max = 10) String numero,
        @Size(max = 100) String bairro,
        @Size(max = 100) String cidade,
        @Size(max = 2) String estado,
        @Size(max = 8) String cep,
        Integer prazoEntregaDias
) {
}
