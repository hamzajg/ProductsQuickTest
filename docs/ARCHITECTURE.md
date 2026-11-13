# Architecture — ProductsQuickTest

Current-state map. Descriptive, not aspirational; see `docs/adr/` for why.

## System shape

```
Browser ──► webapp :3000 (React 17 + CoreUI, webpack/Yarn workspaces)
              │ /api/v1/*
              ▼
        ApiGateway :8080 (Spring Boot 2.3 + Zuul)
          ├─ /api/v1/products/**          → products :8081
          ├─ /api/v1/product-categories/** → products :8081
          └─ /api/v1/customers/**          → customers :8082
              │
   ┌──────────┴───────────┐
   ▼                      ▼
Product.WebApi        Customer.WebApi   (Quarkus 1.10.3, use-case endpoints)
   │ Application            │ Application   (use cases/services)
   │ Infrastructure         │ Infrastructure (InMemory* repos, handlers)
   │ Domain                 │ Domain        (entities, VOs, repo interfaces)
   └──────── SharedKernel ──┘               (base types both services share)
```

## Per-service layering

Both `Product` and `Customer` follow identical four-layer DDD:

| Layer | Project | Contents |
|---|---|---|
| Domain | `*.Domain` | entities, value objects, repository interfaces — framework-free |
| Application | `*.Application` | use cases + services |
| Infrastructure | `*.Infrastructure` | `InMemory*` repositories, command/event handlers |
| WebApi | `*.WebApi` | Quarkus REST endpoints, one package per use case |

## Runtime topology

Four containers via docker-compose: gateway (8080), products (8081),
customers (8082), webapp (3000). **No database, no service-to-service
calls** — repositories are in-memory; each service owns its data only.
Restart = reset.

## API style

Use-case endpoints (`/all`, `/{id}`, `/create`, `/{id}/update`,
`/{id}/delete`, `/category/{id}`) rather than generic CRUD resources;
OpenAPI generated per service via quarkus-smallrye-openapi.

## Frontend ↔ backend contract

`webapp/` (Yarn workspaces `@webapp/app` + `@webapp/ui`) calls the gateway
prefixes above. Contract = the endpoint tables in README + each service's
OpenAPI doc. Adding an endpoint = add to WebApi use-case package AND keep
the gateway route prefix aligned.
