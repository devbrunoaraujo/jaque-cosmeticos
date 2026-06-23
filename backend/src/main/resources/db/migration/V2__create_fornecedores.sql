CREATE TABLE fornecedores (
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    razao_social        VARCHAR(150)   NOT NULL,
    nome_fantasia       VARCHAR(150),
    cnpj                VARCHAR(14)    NOT NULL UNIQUE,
    telefone            VARCHAR(20),
    email               VARCHAR(150),
    logradouro          VARCHAR(150),
    numero              VARCHAR(10),
    bairro              VARCHAR(100),
    cidade              VARCHAR(100),
    estado              VARCHAR(2),
    cep                 VARCHAR(8),
    prazo_entrega_dias  INT,
    ativo               BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at          DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
