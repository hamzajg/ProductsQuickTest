# AGENTS.md — ProductsQuickTest

Guidance for AI agents (and humans) working in this repository.

## Project

ProductsQuickTest is a small products, customers and orders system split into
three DDD-style services behind an API gateway, plus a React back office — all
runnable via Docker Compose. **Learning project**: domain/application
layers separated from web, commands/events through handlers,
in-memory repositories — structure over persistence.
Stack: Java 25, Quarkus 3.40 LTS (products/customers/orders services, RESTEasy
+ JSON-B, `jakarta.*`), Spring Boot 2.7.18 + Spring Cloud Gateway, React 17 +
CoreUI (webpack/Yarn workspaces), Gradle 9.9.1 multi-project.

## Commands

Verified 2026-11-13 against CI (`.github/workflows/ci.yml`) and README.

| Task | Command |
|---|---|
| Build + test | `sh gradlew build --no-daemon` (CI: JDK 25) |
| Run everything | `docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d` |
| Stop | `docker-compose -f docker-compose.yml -f docker-compose.override.yml down` |

Requires: **JDK 25** — `clean build` green on 17.0.2. Ports: gateway 8080,
products 8081, customers 8082, orders 8083, webapp 3000.

## Architecture

```
src/
  ApiGateway/            Spring Boot 2.7 + Spring Cloud Gateway — prefix routing
  SharedKernel/          base types shared by the services
  Product/
    Product.Domain/      entities, value objects, repository interfaces
    Product.Application/ use cases and services
    Product.Infrastructure/  InMemory* repositories, command/event handlers
    Product.WebApi/      Quarkus endpoints — one package per use case
  Customer/              same four layers for customers
  Order/                 same four layers for orders (calls the other two)
  webapp/                Yarn workspaces: @webapp/app + @webapp/ui (React 17)
settings.gradle          maps each src/* dir to a Gradle project
docker-compose*.yml      the services
```

### API contract (gateway → services)

Spring Cloud Gateway routes prefixes (gateway :8080):

| Prefix | Backend |
|---|---|
| `/api/v1/products/**` | products :8081 |
| `/api/v1/product-categories/**` | products :8081 |
| `/api/v1/customers/**` | customers :8082 |
| `/api/v1/orders/**` | orders :8083 |

Gateway routes forward the full `/api/v1/...`
path unchanged (backends serve the same prefix). Explicit use-case endpoints (not generic CRUD): e.g. products expose
`GET /all`, `GET /{id}`, `POST /create`, `PUT /{id}/update`,
`DELETE /{id}/delete`, `GET /category/{categoryId}`,
`POST /{id}/reserve` and `POST /{id}/release` (stock held/released for a
checkout); same shape for
`product-categories`, `customers` and `orders` (the last adds `POST /create`,
`GET /all` and `GET /{id}`). Quarkus-smallrye-openapi serves an
OpenAPI description per service; each also has `/hello-resteasy`.

## Conventions

- **Layered DDD per service**: `*.Domain` (framework-free) →
  `*.Application` (use cases) → `*.Infrastructure` (InMemory repos,
  handlers) → `*.WebApi` (endpoints). Shared types in `SharedKernel`.
- Endpoints are **use-case shaped** (`/create`, `/update`, `/delete`),
  not REST-resource CRUD — match that style for new endpoints.
- In-memory repositories are deliberate — restarting resets data; no DB
  exists. Don't add one without an ADR-level reason.
- Only the orders service calls other services — on checkout it asks customers
  whether the customer exists and asks products to reserve/release stock, by
  compose service name, through the MicroProfile REST Client. The other
  services stay behind the gateway.
- KISS/SOLID/DRY: this is a learning codebase — clarity of layering is
  the deliverable.

## Landmines

- **Default branch is `main`.**
- Modernized 2026-11: Quarkus 1.10→3.40 LTS, Boot 2.3→2.7, Gradle 6.9→8.14.3,
  **Zuul→Spring Cloud Gateway** (Zuul is abandoned and incompatible with Boot
  2.7), and `javax.*`→`jakarta.*` (see ADR 0002). Java 25 throughout.
- Domain/Application/Infrastructure tests bootstrap a CDI SE container, so they
  need Weld **5** (`weld-se-core:5.0.1.Final`) — a 3.x Weld understands only
  `javax.*` and resolves no beans against this code.
- Local clones may sit on stale feature branches (`docs/readme` seen) —
  always branch from fresh `origin/main`.
- `settings.gradle` uses the `findProject(...)` include trick — module
  names ≠ directory names; check it before assuming paths.
- webapp uses Yarn workspaces + webpack — not npm/vite.
- `Order.Application`/`Order.Infrastructure` are plain library jars, so
  `Order.WebApi/src/main/resources/application.properties` lists them under
  `quarkus.index-dependency.*` — without that Arc discovers neither
  `PlaceOrderUseCase` nor the `@RestClient` gateways.

## Agent rules

- Branch from `main`; name branches `feat/*`, `fix/*`, `chore/*`, `docs/*`.
- Docs-only changes still go through a PR; never commit to `main` directly.
- Verify before claiming: `sh gradlew build --no-daemon` on JDK 25 and
  report the real result.
- Keep changes minimal and consistent with the four-layer pattern. No
  drive-by refactors.
- Update AGENTS.md / docs/ARCHITECTURE.md when endpoints, modules, ports,
  or the gateway routing change.
