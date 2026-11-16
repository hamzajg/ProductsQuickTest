# Spec: order-checkout

> Spec-driven development artifact. This file is the contract: implementation
> is done when every acceptance criterion maps to a scenario in
> `EXAMPLES.feature` and every scenario has a passing check.

## Problem

ProductsQuickTest ships two DDD services that never talk to each other: a
`customers` service and a `products` service with no way to buy anything.
`AGENTS.md` states both gaps outright — *"No service-to-service calls; the
gateway is the only coupling point"* — so the "product" cannot actually be
used as a product. A customer record and a catalogue exist side by side, but
there is no transaction that combines them, and the inventory field
(`Product.availableQty`) is decorative: nothing ever decrements it.

For a shop operator this means the back office is a data-entry demo, not a
storefront. For a developer reading the repo this means the most instructive
part of a DDD system — a cross-service use case with compensation — is simply
absent.

## Users

- **Shop operator** — runs the back office; needs stock to move when an order
  is placed, and needs a list of orders to fulfil.
- **Customer** — places an order for one or more products.
- **Developer** — reads the repo as a learning codebase; the delivery shows
  how a fourth service joins the system and calls the others.

## Scope (in)

- A new **`orders` service** (four-layer DDD module: `Order.Domain`,
  `Order.Application`, `Order.Infrastructure`, `Order.WebApi`), reachable
  through the gateway at `/api/v1/orders/**` on port **8083**.
- **First real service-to-service calls**: `orders` → `customers` (validate the
  customer) and `orders` → `products` (reserve stock, release on failure).
- **Stock reservation in the products service**: `reserve` and `release` use
  cases with a real availability invariant (`availableQty`), exposed as
  use-case-shaped endpoints consistent with the existing API style.
- **Order storage** in the orders service, consistent with the repo's
  deliberate in-memory repository style.
- **Compensation**: when any line of a checkout fails, previously reserved
  lines are released so stock is not left held by a failed order.
- Gateway route + Docker Compose service + `compose-smoke` coverage so the
  loop is proven end to end through the gateway.

## Scope (out)

- **Persistence** — repositories stay in memory (the repo's own ADR-level
  decision). Data still resets on restart; that is the *durable persistence*
  feature, not this one.
- **Pricing rules** — `Product.discount` is recorded on the line but not
  applied to the total; line amount = `qty × unitPrice`.
- **Payment, shipping, cancellation, refunds, order status transitions**
  beyond the initial `PLACED`.
- **Idempotency / dedupe** of repeated checkouts (no idempotency key).
- **Distributed transactions** — compensation is best-effort HTTP release, not
  a two-phase commit; the limitation is documented, not hidden.
- **Auth** — no authentication on any endpoint (unchanged from today).
- **A webapp checkout page** — the loop is proven through the API/gateway;
  UI is left as a follow-up.

## Acceptance criteria

<!-- Scenario(s): use scenario names verbatim from EXAMPLES.feature and/or
     @w<N> tags — ship_validate resolves both for traceability. -->

| # | Criterion | Scenario(s) |
|---|-----------|-------------|
| 1 | Reserving stock within availability decrements `availableQty` by the requested amount. | Reserve stock within availability |
| 2 | Reserving more than `availableQty` is rejected with `409` and leaves `availableQty` unchanged. | Reserving beyond availability is rejected |
| 3 | Releasing a previously reserved quantity adds it back to `availableQty`. | Releasing reserved stock restores availability |
| 4 | Placing an order for an existing customer and in-stock products stores the order and returns id, status, line items and total. | A customer places an in-stock order |
| 5 | A successful order decrements each ordered product's `availableQty`. | A placed order decrements stock |
| 6 | An order for an unknown customer is rejected with `404 CUSTOMER_NOT_FOUND` and no order is stored. | An unknown customer cannot order |
| 7 | An order referencing an unknown product is rejected with `404 PRODUCT_NOT_FOUND` and no stock is left reserved. | An unknown product cannot be ordered |
| 8 | An order exceeding a product's stock is rejected with `409 PRODUCT_OUT_OF_STOCK`, no order is stored, and lines reserved earlier in the same checkout are released. | Out-of-stock rolls the checkout back |
| 9 | Orders can be listed and fetched by id through the gateway; an unknown id returns `404`. | Order history is readable through the gateway, An unknown order id is not found |

## Contract

### products service (new endpoints, same use-case shape as the rest)

| Method | Path | Body | Success | Failure |
|---|---|---|---|---|
| `POST` | `/api/v1/products/{productId}/reserve` | `{"qty": <int>}` | `200 {"productId","availableQty"}` | `404` unknown product; `409 {"error":"PRODUCT_OUT_OF_STOCK"}`; `400` qty ≤ 0 |
| `POST` | `/api/v1/products/{productId}/release` | `{"qty": <int>}` | `200 {"productId","availableQty"}` | `404` unknown product; `400` qty ≤ 0 |

### orders service

| Method | Path | Body | Success | Failure |
|---|---|---|---|---|
| `POST` | `/api/v1/orders/create` | `{"customerId":"<uuid>","items":[{"productId":"<uuid>","qty":<int>}]}` | `200 {"orderId","customerId","status":"PLACED","total","items":[{"productId","qty","unitPrice"}]}` | `404 CUSTOMER_NOT_FOUND`; `404 PRODUCT_NOT_FOUND`; `409 PRODUCT_OUT_OF_STOCK`; `400` malformed/empty items |
| `GET` | `/api/v1/orders/all` | — | `200 {"items":[...]}` | — |
| `GET` | `/api/v1/orders/{orderId}` | — | `200 {order}` | `404 ORDER_NOT_FOUND` |

Gateway (Spring Cloud Gateway) adds: `/api/v1/orders/**` → `http://orders:8083`.

## Decisions

- **D1 — A new `orders` service rather than extending `customers` or
  `products`.** Ordering is its own bounded context; putting it in either
  existing service would make that service depend on the other, which is what
  the gateway was introduced to avoid. A fourth module also shows how the
  `settings.gradle` `findProject(...)` wiring scales. (One-way door: service
  split — reviewed against KISS; the alternative, an order aggregate inside
  `customers`, was rejected because it would couple customers to the product
  catalogue.)
- **D2 — Stock reservation lives in the products service, not orders.** The
  invariant `availableQty ≥ 0` belongs to the aggregate that owns
  `availableQty`. Orders calls it; orders never writes product state directly.
- **D3 — Compensation, not a distributed transaction.** A failed checkout
  releases the lines it already reserved via the `release` endpoint. This is
  best-effort (a release can itself fail); the limitation is documented in
  SPEC.md rather than hidden behind a fake transaction.
- **D4 — In-memory order repository.** Consistent with the repo's stated
  learning-project decision; persistence is a separate feature with its own
  ADR.
- **D5 — HTTP client is Quarkus REST Client (classic), matching the classic
  `quarkus-resteasy` + JSON-B stack** already used by both services.
- **D6 — `discount` is not applied to the total.** Pricing rules are out of
  scope; the line records `unitPrice` as stored.

## Research notes

- Gateway prefixes and the "full path forwarded unchanged" rule come from
  `README.md` / `AGENTS.md`; new services follow the same shape.
- Compose service names are the DNS names inside the network
  (`products`, `customers`, `gateway`) — the orders client uses
  `http://products:8081` and `http://customers:8082`, mirroring the gateway's
  `uri:` values.
- `Product` already exposes `changeAvailableQty(int)` and emits
  `ProductAvailableQtyChanged`; reservation reuses that event rather than
  inventing a parallel one.
