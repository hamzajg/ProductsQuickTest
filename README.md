# ProductsQuickTest

A small products, customers and orders system split into three DDD-style
services behind an API gateway, with a React back office and a Docker Compose
file that runs the whole thing. The orders service checks out a basket by
calling the other two services.

It is a learning project: the domain and application layers are separated from
the web layer, commands and events go through handlers, and the repositories
are in memory, so the focus is the structure rather than persistence.

## Stack

| Piece | Technology |
|---|---|
| `products`, `customers`, `orders` services | Quarkus 3.40 LTS, RESTEasy + JSON-B, Java 25 |
| `orders` → `products`/`customers` | MicroProfile REST Client (`quarkus-resteasy-client`) |
| `gateway` | Spring Boot 3.5.16 with Spring Cloud Gateway |
| `webapp` | React 17 with CoreUI, built by webpack and Yarn workspaces |
| build | Gradle 9.9.1 multi-project (`settings.gradle` maps each module) |
| run | Docker Compose |

## Run it

```bash
docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d
docker-compose -f docker-compose.yml -f docker-compose.override.yml ps
```

| Service | Container | Port |
|---|---|---|
| gateway | `quicktest-api-gateway` | 8080 |
| products | `quicktest-products` | 8081 |
| customers | `quicktest-customers` | 8082 |
| orders | `quicktest-orders` | 8083 |
| webapp | `quicktest-webapp` | 3000 |

Open <http://localhost:3000> for the web app; the API is reachable through the
gateway on 8080. Stop everything with `docker-compose down`.

## API

The gateway (Spring Cloud Gateway) routes prefixes to the services:

| Prefix | Service |
|---|---|
| `/api/v1/products/**` | products, 8081 |
| `/api/v1/product-categories/**` | products, 8081 |
| `/api/v1/customers/**` | customers, 8082 |
| `/api/v1/orders/**` | orders, 8083 |

Each service exposes explicit use-case endpoints rather than a generic CRUD
resource. For example:

| Method | Path |
|---|---|
| `GET` | `/api/v1/products/all` |
| `GET` | `/api/v1/products/{productId}` |
| `POST` | `/api/v1/products/create` |
| `PUT` | `/api/v1/products/{productId}/update` |
| `DELETE` | `/api/v1/products/{productId}/delete` |
| `POST` | `/api/v1/products/{productId}/reserve` |
| `POST` | `/api/v1/products/{productId}/release` |
| `GET` | `/api/v1/products/category/{categoryId}` |
| `GET` | `/api/v1/product-categories/all` |
| `GET` | `/api/v1/product-categories/{productCategoryId}` |
| `POST` | `/api/v1/product-categories/create` |
| `PUT` | `/api/v1/product-categories/{productCategoryId}/update` |
| `DELETE` | `/api/v1/product-categories/{productCategoryId}/delete` |
| `GET` | `/api/v1/customers/all` |
| `GET` | `/api/v1/customers/{customerId}` |
| `POST` | `/api/v1/customers/create` |
| `PUT` | `/api/v1/customers/{customerId}/update` |
| `DELETE` | `/api/v1/customers/{customerId}/delete` |
| `GET` | `/api/v1/orders/all` |
| `GET` | `/api/v1/orders/{orderId}` |
| `POST` | `/api/v1/orders/create` |

The services also expose `/hello-resteasy` and, through
`quarkus-smallrye-openapi`, an OpenAPI description.

## Build and test

Java 25 is required: the toolchain targets it, and the wrapper is Gradle
8.14.3 (the Quarkus 3 plugin's floor is 8.14).

```bash
sh gradlew build
```

The build compiles every module and runs 28 test classes: domain tests for
`Product`, `ProductCategory` and `Order`, application tests for the order use
cases, REST Assured endpoint tests for every product, category, customer and
order use case, and the generated Quarkus greeting tests. The same command runs
in CI (`.github/workflows/ci.yml`, JDK 25).

Two more workflows cover the container side:

- `.github/workflows/docker.yml` builds each image (gateway, the three fast-jar
  services, webapp) without pushing.
- `.github/workflows/compose-smoke.yml` starts gateway + products + customers +
  orders with `docker compose` and drives them through the gateway on port 8080
  — `GET /api/v1/products/all`, `GET /api/v1/product-categories/all`, a
  `POST /api/v1/customers/create` followed by `GET /api/v1/customers/all` that
  must return the created record, and a full checkout: create a category, a
  product and a customer, `POST /api/v1/orders/create`, then read the order back
  from `GET /api/v1/orders/all` and `GET /api/v1/orders/{orderId}`. The checkout
  is what proves the orders service's calls to the other two services resolve
  inside the compose network. It fails if any of those routes do not reach the
  expected service.

## Layout

```
settings.gradle              maps each directory to a Gradle project
build.gradle                 shared group/version, Java plugin
src/ApiGateway/              Spring Boot + Spring Cloud Gateway routes
src/Product/
  Product.Domain/            entities, value objects, repository interfaces
  Product.Application/       use cases and services
  Product.Infrastructure/    in-memory repositories, command/event handlers
  Product.WebApi/            Quarkus endpoints, one package per use case
src/Customer/               the same four layers for customers
src/Order/                  the same four layers for orders
src/SharedKernel/           base types shared by the services
src/webapp/                 Yarn workspaces: @webapp/app and @webapp/ui
docker-compose*.yml         the services and their ports
```

## Notes

- Repositories are `InMemory*`, so restarting a service resets its data.
- There is no shared database. Only the orders service calls other services: on
  checkout it asks customers whether the customer exists and asks products to
  reserve stock (releasing it again if a later line fails), resolving them by
  their compose service names.
- The web app is a create-react-app-style project built with webpack inside a
  Yarn workspace.

## License

MIT — see [LICENSE](LICENSE).
