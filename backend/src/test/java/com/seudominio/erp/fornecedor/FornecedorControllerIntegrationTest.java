package com.seudominio.erp.fornecedor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seudominio.erp.fornecedor.dto.FornecedorRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FornecedorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarFornecedor() throws Exception {
        FornecedorRequestDTO dto = new FornecedorRequestDTO(
                "Distribuidora Beleza LTDA",
                "Beleza",
                "12345678000190",
                "11999998888",
                "contato@beleza.com",
                "Rua das Flores",
                "123",
                "Centro",
                "Sao Paulo",
                "SP",
                "01001000",
                7
        );

        mockMvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.razaoSocial").value("Distribuidora Beleza LTDA"))
                .andExpect(jsonPath("$.cnpj").value("12345678000190"))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    void deveListarFornecedores() throws Exception {
        mockMvc.perform(get("/api/fornecedores")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void deveBuscarFornecedorPorId() throws Exception {
        FornecedorRequestDTO dto = new FornecedorRequestDTO(
                "Fornecedor Teste LTDA", null, "98765432000110",
                null, null, null, null, null, null, null, null, 5
        );

        String response = mockMvc.perform(post("/api/fornecedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andReturn().getResponse().getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/api/fornecedores/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.razaoSocial").value("Fornecedor Teste LTDA"));
    }
}
