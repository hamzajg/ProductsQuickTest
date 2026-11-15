# ADR 0001 — Initial architecture

Date: 2026-11-13 (baseline recorded retroactively)
Status: accepted (describes what is built)

## Context

A learning project demonstrating DDD layering, use-case endpoints, and a
gateway-fronted multi-service topology — runnable end-to-end via Docker
Compose.

## Decisions

1. **Two DDD services + gateway + webapp.** `Product` and `Customer`
   services with identical four-layer structure behind a Zuul gateway;
   React back office on top. Rationale: demonstrates service decomposition
   and routing without distributed-systems complexity.
2. **Strict four-layer DDD per service.** Domain (framework-free) →
   Application (use cases) → Infrastructure (InMemory repos, handlers) →
   WebApi (endpoints). `SharedKernel` holds cross-service base types.
   Rationale: the layering IS the lesson.
3. **Use-case endpoints, not CRUD resources.** `/create`, `/update`,
   `/delete`, `/all`, `/category/{id}` — intention-revealing routes.
   Rationale: teaches API design by use case; matches the DDD vocabulary.
4. **In-memory repositories, no database.** `InMemory*` implementations;
   restart resets state. Rationale: keeps focus on structure; persistence
   deliberately out of scope.
5. **Quarkus services + Spring Cloud Gateway.** Deliberate mix: JVM services
   on Quarkus, gateway on Spring Cloud. Originally Quarkus 1.10.3 + Boot 2.3
   Zuul on Java 11; both were EOL, so the stack moved to Quarkus 2.16.12,
   Boot 2.7.18 + Spring Cloud Gateway, Gradle 7.6.4 and Java 17 (Zuul has no
   Boot 2.7 line, so the gateway swap was required, not cosmetic).
6. **Yarn workspaces + webpack for webapp.** React 17 + CoreUI, CRA-style
   build inside `src/webapp`. Rationale: period-appropriate stack for the
   demo; no Vite/npm-workspace hybrid.

## Consequences

- No persistence means no data survives restarts — by design.
- The backend is on Java 17 with Quarkus 2.16.12 / Boot 2.7.18 / Gradle
  7.6.4. React 17 in the webapp is the remaining EOL piece.
- Adding a third service means replicating the four-layer template —
  SharedKernel exists to keep that cheap.
