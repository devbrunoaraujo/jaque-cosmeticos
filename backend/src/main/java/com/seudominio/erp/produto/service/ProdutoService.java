package com.seudominio.erp.produto.service;

import com.seudominio.erp.produto.dto.ProdutoRequestDTO;
import com.seudominio.erp.produto.dto.ProdutoResponseDTO;
import com.seudominio.erp.produto.entity.Produto;
import com.seudominio.erp.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        if (produtoRepository.existsBySku(dto.sku())) {
            throw new IllegalArgumentException("SKU já cadastrado: " + dto.sku());
        }
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setSku(dto.sku());
        produto.setCategoria(dto.categoria());
        produto.setMarca(dto.marca());
        produto.setFornecedor(dto.fornecedor());
        produto.setPrecoCusto(dto.precoCusto());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setUnidadeMedida(dto.unidadeMedida());
        return ProdutoResponseDTO.of(produtoRepository.save(produto));
    }

    public Page<ProdutoResponseDTO> listar(String nome, Pageable pageable) {
        Page<Produto> page;
        if (nome != null && !nome.isBlank()) {
            page = produtoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = produtoRepository.findAll(pageable);
        }
        return page.map(ProdutoResponseDTO::of);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        return ProdutoResponseDTO.of(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        if (!produto.getSku().equals(dto.sku()) && produtoRepository.existsBySku(dto.sku())) {
            throw new IllegalArgumentException("SKU já cadastrado: " + dto.sku());
        }
        produto.setNome(dto.nome());
        produto.setSku(dto.sku());
        produto.setCategoria(dto.categoria());
        produto.setMarca(dto.marca());
        produto.setFornecedor(dto.fornecedor());
        produto.setPrecoCusto(dto.precoCusto());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setUnidadeMedida(dto.unidadeMedida());
        return ProdutoResponseDTO.of(produtoRepository.save(produto));
    }

    @Transactional
    public void desativar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }
}
