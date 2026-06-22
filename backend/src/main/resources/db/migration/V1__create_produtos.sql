CREATE TABLE produtos (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(150)   NOT NULL,
    sku             VARCHAR(50)    NOT NULL UNIQUE,
    categoria       VARCHAR(100),
    marca           VARCHAR(100),
    fornecedor      VARCHAR(150),
    preco_custo     DECIMAL(10,2)  NOT NULL,
    preco_venda     DECIMAL(10,2)  NOT NULL,
    unidade_medida  VARCHAR(20)    NOT NULL,
    ativo           BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
