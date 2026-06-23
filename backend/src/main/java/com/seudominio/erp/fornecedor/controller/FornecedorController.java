package com.seudominio.erp.fornecedor.controller;

import com.seudominio.erp.fornecedor.dto.FornecedorRequestDTO;
import com.seudominio.erp.fornecedor.dto.FornecedorResponseDTO;
import com.seudominio.erp.fornecedor.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criar(@Valid @RequestBody FornecedorRequestDTO dto) {
        FornecedorResponseDTO response = fornecedorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorResponseDTO>> listar(
            @RequestParam(required = false) String busca,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("razaoSocial"));
        return ResponseEntity.ok(fornecedorService.listar(busca, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id,
                                                           @Valid @RequestBody FornecedorRequestDTO dto) {
        return ResponseEntity.ok(fornecedorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        fornecedorService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
