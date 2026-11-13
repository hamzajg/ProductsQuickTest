# AGENTS.md — ProductsQuickTest

Guidance for AI agents (and humans) working in this repository.

## Project

ProductsQuickTest is a small products-and-customers system split into two
DDD-style services behind an API gateway, plus a React back office — all
runnable via Docker Compose. **Learning project**: domain/application
layers separated from web, commands/events through handlers,
in-memory repositories — structure over persistence.
Stack: Java 11, Quarkus 1.10.3 (products/customers services, RESTEasy +
JSON-B), Spring Boot 2.3.2 + Zuul gateway, React 17 + CoreUI (webpack/Yarn
workspaces), Gradle multi-project.

## Commands

Verified 2026-11-13 against CI (`.github/workflows/ci.yml`) and README.

| Task | Command |
|---|---|
| Build + test | `sh gradlew build --no-daemon` (CI: JDK 11; ~20 test classes) |
| Run everything | `docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d` |
| Stop | `docker-compose -f docker-compose.yml -f docker-compose.override.yml down` |

Requires: **JDK 11** (Quarkus 1.10 + Spring Boot 2.3 both target it; Gradle
wrapper is 6.9.1 — newer Gradle/JDK may not run it). Ports: gateway 8080,
products 8081, customers 8082, webapp 3000.

## Architecture

```
src/
  ApiGateway/            Spring Boot 2.3 + Zuul 2.2.6 — prefix routing
  SharedKernel/          base types shared by both services
  Product/
    Product.Domain/      entities, value objects, repository interfaces
    Product.Application/ use cases and services
    Product.Infrastructure/  InMemory* repositories, command/event handlers
    Product.WebApi/      Quarkus endpoints — one package per use case
  Customer/              same four layers for customers
  webapp/                Yarn workspaces: @webapp/app + @webapp/ui (React 17)
settings.gradle          maps each src/* dir to a Gradle project
docker-compose*.yml      the four services
```

### API contract (gateway → services)

Zuul routes prefixes (gateway :8080):

| Prefix | Backend |
|---|---|
| `/api/v1/products/**` | products :8081 |
| `/api/v1/product-categories/**` | products :8081 |
| `/api/v1/customers/**` | customers :8082 |

Explicit use-case endpoints (not generic CRUD): e.g. products expose
`GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}/update`,
`DELETE /{id}/delete`, `GET /category/{categoryId}`; same shape for
`product-categories` and `customers`. Quarkus-smallrye-openapi serves an
OpenAPI description per service; each also has `/hello-resteasy`.

## Conventions

- **Layered DDD per service**: `*.Domain` (framework-free) →
  `*.Application` (use cases) → `*.Infrastructure` (InMemory repos,
  handlers) → `*.WebApi` (endpoints). Shared types in `SharedKernel`.
- Endpoints are **use-case shaped** (`/create`, `/update`, `/delete`),
  not REST-resource CRUD — match that style for new endpoints.
- In-memory repositories are deliberate — restarting resets data; no DB
  exists. Don't add one without an ADR-level reason.
- No service-to-service calls; the gateway is the only coupling point.
- KISS/SOLID/DRY: this is a learning codebase — clarity of layering is
  the deliverable.

## Landmines

- **Default branch is `main`.**
- **JDK 11 + Gradle 6.9.1 are hard requirements** — the wrapper is old;
  don't bump the wrapper or toolchain casually (Quarkus 1.10 era).
- Quarkus 1.10.3 and Spring Boot 2.3.2 are both EOL — accepted for a
  learning project; modernising is a separate, large effort.
- Local clones may sit on stale feature branches (`docs/readme` seen) —
  always branch from fresh `origin/main`.
- `settings.gradle` uses the `findProject(...)` include trick — module
  names ≠ directory names; check it before assuming paths.
- webapp uses Yarn workspaces + webpack — not npm/vite.

## Agent rules

- Branch from `main`; name branches `feat/*`, `fix/*`, `chore/*`, `docs/*`.
- Docs-only changes still go through a PR; never commit to `main` directly.
- Verify before claiming: `sh gradlew build --no-daemon` on JDK 11 and
  report the real result.
- Keep changes minimal and consistent with the four-layer pattern. No
  drive-by refactors.
- Update AGENTS.md / docs/ARCHITECTURE.md when endpoints, modules, ports,
  or the gateway routing change.
