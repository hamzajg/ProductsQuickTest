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
5. **Quarkus 1.10.3 services + Spring Boot 2.3 Zuul gateway.** Deliberate
   mix: JVM services on Quarkus, gateway on Spring Cloud Netflix. Both are
   EOL — accepted for the learning context; pinned to Java 11/Gradle 6.9.1.
6. **Yarn workspaces + webpack for webapp.** React 17 + CoreUI, CRA-style
   build inside `src/webapp`. Rationale: period-appropriate stack for the
   demo; no Vite/npm-workspace hybrid.

## Consequences

- No persistence means no data survives restarts — by design.
- EOL frameworks (Quarkus 1.10, Spring Boot 2.3, React 17, Gradle 6.9.1)
  constrain contributors to JDK 11-era tooling.
- Adding a third service means replicating the four-layer template —
  SharedKernel exists to keep that cheap.
