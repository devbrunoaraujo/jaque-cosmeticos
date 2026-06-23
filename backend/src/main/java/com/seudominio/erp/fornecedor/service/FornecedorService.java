package com.seudominio.erp.fornecedor.service;

import com.seudominio.erp.fornecedor.dto.FornecedorRequestDTO;
import com.seudominio.erp.fornecedor.dto.FornecedorResponseDTO;
import com.seudominio.erp.fornecedor.entity.Fornecedor;
import com.seudominio.erp.fornecedor.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Transactional
    public FornecedorResponseDTO criar(FornecedorRequestDTO dto) {
        if (fornecedorRepository.existsByCnpj(dto.cnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado: " + dto.cnpj());
        }
        Fornecedor fornecedor = new Fornecedor();
        mapearDtoParaEntity(dto, fornecedor);
        return FornecedorResponseDTO.of(fornecedorRepository.save(fornecedor));
    }

    public Page<FornecedorResponseDTO> listar(String busca, Pageable pageable) {
        Page<Fornecedor> page;
        if (busca != null && !busca.isBlank()) {
            page = fornecedorRepository
                    .findByRazaoSocialContainingIgnoreCaseOrNomeFantasiaContainingIgnoreCase(
                            busca, busca, pageable);
        } else {
            page = fornecedorRepository.findAll(pageable);
        }
        return page.map(FornecedorResponseDTO::of);
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado: " + id));
        return FornecedorResponseDTO.of(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado: " + id));
        if (!fornecedor.getCnpj().equals(dto.cnpj()) && fornecedorRepository.existsByCnpj(dto.cnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado: " + dto.cnpj());
        }
        mapearDtoParaEntity(dto, fornecedor);
        return FornecedorResponseDTO.of(fornecedorRepository.save(fornecedor));
    }

    @Transactional
    public void desativar(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado: " + id));
        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }

    private void mapearDtoParaEntity(FornecedorRequestDTO dto, Fornecedor fornecedor) {
        fornecedor.setRazaoSocial(dto.razaoSocial());
        fornecedor.setNomeFantasia(dto.nomeFantasia());
        fornecedor.setCnpj(dto.cnpj());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
        fornecedor.setLogradouro(dto.logradouro());
        fornecedor.setNumero(dto.numero());
        fornecedor.setBairro(dto.bairro());
        fornecedor.setCidade(dto.cidade());
        fornecedor.setEstado(dto.estado());
        fornecedor.setCep(dto.cep());
        fornecedor.setPrazoEntregaDias(dto.prazoEntregaDias());
    }
}
