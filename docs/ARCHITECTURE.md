# Architecture — ProductsQuickTest

Current-state map. Descriptive, not aspirational; see `docs/adr/` for why.

## System shape

```
Browser ──► webapp :3000 (React 17 + CoreUI, webpack/Yarn workspaces)
              │ /api/v1/*
              ▼
        ApiGateway :8080 (Spring Boot 2.7 + Spring Cloud Gateway)
          ├─ /api/v1/products/**           → products :8081
          ├─ /api/v1/product-categories/** → products :8081
          ├─ /api/v1/customers/**          → customers :8082
          └─ /api/v1/orders/**             → orders :8083
              │
   ┌──────────┼───────────┐
   ▼          ▼           ▼
Product.WebApi  Customer.WebApi  Order.WebApi  (Quarkus 3.40, use-case endpoints)
   │ Application   │ Application     │ Application   (use cases/services)
   │ Infrastructure│ Infrastructure  │ Infrastructure (InMemory* repos, handlers)
   │ Domain        │ Domain          │ Domain        (entities, VOs, repo interfaces)
   └──────────── SharedKernel ───────────┘          (base types the services share)

Order.WebApi ─► products :8081 (reserve/release stock) · customers :8082 (exists?)
                 the only service-to-service calls in the system
```

## Per-service layering

`Product`, `Customer` and `Order` follow identical four-layer DDD:

| Layer | Project | Contents |
|---|---|---|
| Domain | `*.Domain` | entities, value objects, repository interfaces — framework-free |
| Application | `*.Application` | use cases + services |
| Infrastructure | `*.Infrastructure` | `InMemory*` repositories, command/event handlers |
| WebApi | `*.WebApi` | Quarkus REST endpoints, one package per use case |

## Runtime topology

Five containers via docker-compose: gateway (8080), products (8081),
customers (8082), orders (8083), webapp (3000). **No database** —
repositories are in-memory, so a restart resets data. The only
service-to-service coupling is **orders → products/customers**: on checkout it
asks customers whether the customer exists, asks products to reserve stock per
line (releasing it again if a later line fails), and can be read back through
the gateway. Those calls use the compose service names (`products:8081`,
`customers:8082`) and the MicroProfile REST Client.

## API style

Use-case endpoints (`/all`, `/{id}`, `/create`, `/{id}/update`,
`/{id}/delete`, `/category/{id}`) rather than generic CRUD resources;
OpenAPI generated per service via quarkus-smallrye-openapi. Orders exposes
`POST /create`, `GET /all` and `GET /{id}` (an unknown id is a
`404 ORDER_NOT_FOUND`).

## Frontend ↔ backend contract

`webapp/` (Yarn workspaces `@webapp/app` + `@webapp/ui`) calls the gateway
prefixes above. Contract = the endpoint tables in README + each service's
OpenAPI doc. Adding an endpoint = add to WebApi use-case package AND keep
the gateway route prefix aligned.
