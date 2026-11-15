# Waves — order-checkout

Each wave IS an iteration: a time-boxed, independently shippable
increment — one branch, one PR, green tests, demoable outcome.
Do not start wave N+1 until wave N merges.

## Wave 1 — iteration 1

Scenarios proven by this wave:
- Reserve stock within availability
- Reserving beyond availability is rejected
- Releasing reserved stock restores availability

| Item | Title | Size | Deps | Notes |
|------|-------|------|------|-------|
| US1 | As a shop operator, I want to reserve and release product stock, so that an order can hold inventory without overselling | M | — | Adds `reserve`/`release` use cases + endpoints to the products service; the invariant lives on the `Product` aggregate. |

## Wave 2 — iteration 2

Scenarios proven by this wave:
- A customer places an in-stock order
- A placed order decrements stock
- An unknown customer cannot order
- An unknown product cannot be ordered
- Out-of-stock rolls the checkout back

| Item | Title | Size | Deps | Notes |
|------|-------|------|------|-------|
| US2 | As a customer, I want to place an order for one or more products, so that I can buy them | L | US1 | New `orders` service; its first service-to-service calls (validate customer, reserve stock) with release-based compensation. |

## Wave 3 — iteration 3

Scenarios proven by this wave:
- Order history is readable through the gateway
- An unknown order id is not found

| Item | Title | Size | Deps | Notes |
|------|-------|------|------|-------|
| US3 | As a shop operator, I want to list and review placed orders, so that I can fulfil them | M | US2 | Read model for orders behind the gateway; updates README/AGENTS/ARCHITECTURE and the compose smoke. |
