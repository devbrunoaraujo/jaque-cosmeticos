# Regras de Negócio — Cosmetics ERP

Este documento detalha as regras de domínio. É carregado automaticamente
pelo MiMo Code via `mimocode.json`.

## Produtos
- Atributos: nome, SKU/código de barras, categoria, marca, fornecedor
  padrão, preço de custo, preço de venda, unidade de medida.
- Um produto pode ter múltiplos lotes em estoque, cada lote com sua própria
  data de validade e quantidade.

## Estoque e Lotes
- Toda alteração de quantidade em estoque passa por uma movimentação
  registrada (entrada, saída, ajuste, perda/avaria), nunca update direto.
- Baixa de estoque em vendas segue a regra FEFO (first expire, first out):
  consome primeiro o lote com validade mais próxima.
- Produtos próximos do vencimento (configurável, ex: 30/60/90 dias) devem
  ser sinalizáveis em relatório.

## Fornecedores
- Cadastro com CNPJ, contato, prazo médio de entrega.
- Histórico de compras vinculado ao fornecedor.

## Clientes
- Cadastro com CPF/CNPJ opcional, contato, histórico de compras.

## Vendas / PDV
- Uma venda tem um ou mais itens (produto + lote + quantidade + preço
  unitário no momento da venda).
- Ao confirmar a venda, gera movimentação de saída de estoque por lote
  (FEFO) e lançamento financeiro de entrada (a receber ou recebido,
  dependendo da forma de pagamento).
- Formas de pagamento: dinheiro, cartão (débito/crédito), PIX.

## Financeiro
- Contas a pagar (compras de fornecedores, despesas fixas) e contas a
  receber (vendas a prazo).
- Fluxo de caixa simplificado por período.

## Relatórios
- Vendas por período, produto e categoria.
- Produtos com estoque baixo.
- Produtos próximos do vencimento.
- Curva ABC de produtos (mais/menos vendidos).
