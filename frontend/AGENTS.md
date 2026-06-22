# Frontend — Cosmetics ERP SPA

React + Vite, consumindo a API REST do backend via JWT.

## Convenções
- Estrutura por feature: `src/features/{produtos,estoque,vendas,...}`,
  cada feature com seus próprios componentes, hooks e chamadas de API.
- Chamadas API centralizadas em `src/api/` (axios + interceptor de JWT
  para anexar o token e tratar 401 redirecionando ao login).
- Componentização: páginas em `src/pages/`, componentes reutilizáveis em
  `src/components/`.
- Gerenciamento de estado de servidor: React Query (`@tanstack/react-query`)
  para cache e sincronização de dados da API.
- Gerenciamento de estado local/global simples (ex: usuário logado): Context
  API — evitar bibliotecas pesadas de state management sem necessidade.
- Formulários: React Hook Form + validação com Zod ou Yup.

## UI
- Definir e manter uma única biblioteca de componentes (ex: Material UI,
  Mantine ou shadcn/ui) — não misturar bibliotecas de UI diferentes entre
  módulos.
- Layout padrão de ERP: sidebar de navegação fixa + área de conteúdo, com
  rotas protegidas (redirecionar para login se token ausente/expirado).

## Convenções de código
- Componentes funcionais com hooks, nomeados em PascalCase.
- Variáveis e funções em camelCase, arquivos de componente em PascalCase.tsx
  (se usar TypeScript) ou .jsx.
