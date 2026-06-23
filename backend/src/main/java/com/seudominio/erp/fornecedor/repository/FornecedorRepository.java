package com.seudominio.erp.fornecedor.repository;

import com.seudominio.erp.fornecedor.entity.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    boolean existsByCnpj(String cnpj);

    Page<Fornecedor> findByRazaoSocialContainingIgnoreCaseOrNomeFantasiaContainingIgnoreCase(
            String busca1, String busca2, Pageable pageable);
}
