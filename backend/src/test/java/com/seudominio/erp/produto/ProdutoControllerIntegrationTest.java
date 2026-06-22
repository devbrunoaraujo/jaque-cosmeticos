package com.seudominio.erp.produto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seudominio.erp.produto.dto.ProdutoRequestDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarProduto() throws Exception {
        ProdutoRequestDTO dto = new ProdutoRequestDTO(
                "Batom Matte",
                "BAT-001",
                "Maquiagem",
                "MarcaX",
                null,
                new BigDecimal("10.00"),
                new BigDecimal("25.00"),
                "UN"
        );

        mockMvc.perform(post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Batom Matte"))
                .andExpect(jsonPath("$.sku").value("BAT-001"))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    void deveListarProdutos() throws Exception {
        mockMvc.perform(get("/api/produtos")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
