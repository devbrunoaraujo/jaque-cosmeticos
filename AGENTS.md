# Cosmetics ERP

ERP para loja de cosméticos. Backend Java 21 + Spring Boot 3.x (API REST) +
MySQL. Frontend React + Vite (SPA consumindo a API via JWT).

## Estrutura
- `backend/` — API Spring Boot (ver backend/AGENTS.md)
- `frontend/` — SPA React (ver frontend/AGENTS.md)
- `docs/domain.md` — regras de negócio detalhadas do domínio

## Domínio do negócio
- Produtos têm categoria, marca, fornecedor, preço de custo/venda e
  **lote + validade** (item crítico em cosméticos — controlar vencimento).
- Estoque é controlado por movimentações (entrada, saída, ajuste, perda),
  nunca por update direto de quantidade.
- Vendas (PDV) baixam estoque por lote (FEFO — first expire, first out).
- Módulos esperados: Autenticação, Produtos, Estoque/Lotes, Fornecedores,
  Clientes, Vendas/PDV, Financeiro (contas a pagar/receber), Relatórios.

## Convenções gerais
- Commits no padrão Conventional Commits.
- Nunca alterar `application.properties`/`.env` com credenciais reais.
- Toda entidade nova precisa de: entity, repository, DTO, service,
  controller, e teste de integração mínimo do endpoint principal.
- Antes de implementar um módulo novo, mapear as entidades e relacionamentos
  envolvidos e confirmar com o usuário se houver ambiguidade de regra de
  negócio.

## Build & Test
- Backend: `cd backend && mvn clean install` / `mvn test`
- Frontend: `cd frontend && npm install` / `npm run dev` / `npm run build`
