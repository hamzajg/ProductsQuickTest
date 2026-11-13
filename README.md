# ProductsQuickTest

A small products-and-customers system split into two DDD-style services behind
an API gateway, with a React back office and a Docker Compose file that runs
the whole thing.

It is a learning project: the domain and application layers are separated from
the web layer, commands and events go through handlers, and the repositories
are in memory, so the focus is the structure rather than persistence.

## Stack

| Piece | Technology |
|---|---|
| `products`, `customers` services | Quarkus 1.10.3, RESTEasy + JSON-B, Java 11 |
| `gateway` | Spring Boot 2.3.2 with Spring Cloud Netflix Zuul 2.2.6 |
| `webapp` | React 17 with CoreUI, built by webpack and Yarn workspaces |
| build | Gradle multi-project (`settings.gradle` maps each module) |
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
| webapp | `quicktest-webapp` | 3000 |

Open <http://localhost:3000> for the web app; the API is reachable through the
gateway on 8080. Stop everything with `docker-compose down`.

## API

The gateway (Zuul) routes prefixes to the services:

| Prefix | Service |
|---|---|
| `/api/v1/products/**` | products, 8081 |
| `/api/v1/product-categories/**` | products, 8081 |
| `/api/v1/customers/**` | customers, 8082 |

Each service exposes explicit use-case endpoints rather than a generic CRUD
resource. For example:

| Method | Path |
|---|---|
| `GET` | `/api/v1/products/all` |
| `GET` | `/api/v1/products/{productId}` |
| `POST` | `/api/v1/products/create` |
| `PUT` | `/api/v1/products/{productId}/update` |
| `DELETE` | `/api/v1/products/{productId}/delete` |
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

The services also expose `/hello-resteasy` and, through
`quarkus-smallrye-openapi`, an OpenAPI description.

## Build and test

Java 11 is required: Quarkus 1.10 and the Spring Boot 2.3 gateway both target
it, and the wrapper is Gradle 6.9.1.

```bash
sh gradlew build
```

The build compiles every module and runs 20 test classes: domain tests for
`Product` and `ProductCategory`, REST Assured endpoint tests for every product,
category and customer use case, and the generated Quarkus greeting tests. The
same command runs in CI (`.github/workflows/ci.yml`, JDK 11).

## Layout

```
settings.gradle              maps each directory to a Gradle project
build.gradle                 shared group/version, Java plugin
src/ApiGateway/              Spring Boot + Zuul gateway and its routes
src/Product/
  Product.Domain/            entities, value objects, repository interfaces
  Product.Application/       use cases and services
  Product.Infrastructure/    in-memory repositories, command/event handlers
  Product.WebApi/            Quarkus endpoints, one package per use case
src/Customer/               the same four layers for customers
src/SharedKernel/           base types shared by both services
src/webapp/                 Yarn workspaces: @webapp/app and @webapp/ui
docker-compose*.yml         the four services and their ports
```

## Notes

- Repositories are `InMemory*`, so restarting a service resets its data.
- The services run independently of each other; there is no shared database
  and no service-to-service call yet.
- The web app is a create-react-app-style project built with webpack inside a
  Yarn workspace.

## License

MIT — see [LICENSE](LICENSE).
