# Backend — Cosmetics ERP API

Java 21, Spring Boot 3.x, Spring Data JPA/Hibernate, Spring Security + JWT,
MySQL, Maven, Lombok.

## Convenções de código
- Pacotes por feature: `com.seudominio.erp.{modulo}.{controller,service,repository,dto,entity}`
- IDs como BIGINT (Long) em todas as PKs/FKs.
- DTOs separados de Entity sempre — nunca expor Entity direto no controller.
- Validação via Bean Validation (`@Valid`, `@NotNull`, `@NotBlank` etc.) nos
  DTOs de entrada.
- Tratamento de erro centralizado via `@ControllerAdvice` + classe de erro
  padronizada (timestamp, status, mensagem, path).
- Tabelas de auditoria (created_at/updated_at) em entidades de negócio.
- Não usar `@Data` do Lombok em entidades JPA (evitar problemas de
  equals/hashCode e lazy loading) — preferir `@Getter`/`@Setter` explícitos.

## Segurança
- Autenticação via JWT (JJWT), filtro customizado (`OncePerRequestFilter`),
  stateless (`SessionCreationPolicy.STATELESS`).
- Perfis de acesso: ADMIN, OPERADOR (definir como enum `Role`).
- Senhas sempre com BCrypt.

## Banco de dados
- MySQL local via Laragon em desenvolvimento.
- Usar Flyway para versionar o schema desde o início (scripts em
  `src/main/resources/db/migration`) — evita retrabalho de alteração manual
  de schema mais adiante.
- Nomeação de tabelas em snake_case, no plural (ex: `produtos`,
  `movimentacoes_estoque`).

## Testes
- Testes de integração com `@SpringBootTest` + banco H2 ou Testcontainers
  para os endpoints principais de cada módulo.
- Testes de service isolados com Mockito para regras de negócio críticas
  (ex: baixa de estoque por FEFO).
