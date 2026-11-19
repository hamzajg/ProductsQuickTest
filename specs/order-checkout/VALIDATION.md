# Validation — order-checkout

## Traceability

| AC | Criterion | Scenarios | Wave(s) | Test evidence |
|----|-----------|-----------|---------|---------------|
| 1 | Reserving stock within availability decrements… | Releasing reserved stock restores availability, Reserve stock within availability, Reserving beyond availability is rejected | 1 | ProductStockTest.java, ReleaseProductStockEndpointTest.java, ReserveProductStockEndpointTest.java |
| 2 | Reserving more than `availableQty` is rejected with `409`… | Releasing reserved stock restores availability, Reserve stock within availability, Reserving beyond availability is rejected | 1 | ProductStockTest.java, ReleaseProductStockEndpointTest.java, ReserveProductStockEndpointTest.java |
| 3 | Releasing a previously reserved quantity adds it back to… | Releasing reserved stock restores availability, Reserve stock within availability, Reserving beyond availability is rejected | 1 | ProductStockTest.java, ReleaseProductStockEndpointTest.java, ReserveProductStockEndpointTest.java |
| 4 | Placing an order for an existing customer and in-stock… | A customer places an in-stock order, A placed order decrements stock, An unknown customer cannot order, An unknown product cannot be ordered, Out-of-stock rolls the checkout back | 2 | CreateOrderEndpointTest.java, PlaceOrderUseCaseTest.java |
| 5 | A successful order decrements each ordered product's… | A customer places an in-stock order, A placed order decrements stock, An unknown customer cannot order, An unknown product cannot be ordered, Out-of-stock rolls the checkout back | 2 | CreateOrderEndpointTest.java, PlaceOrderUseCaseTest.java |
| 6 | An order for an unknown customer is rejected with `404… | A customer places an in-stock order, A placed order decrements stock, An unknown customer cannot order, An unknown product cannot be ordered, Out-of-stock rolls the checkout back | 2 | CreateOrderEndpointTest.java, PlaceOrderUseCaseTest.java |
| 7 | An order referencing an unknown product is rejected with… | A customer places an in-stock order, A placed order decrements stock, An unknown customer cannot order, An unknown product cannot be ordered, Out-of-stock rolls the checkout back | 2 | CreateOrderEndpointTest.java, PlaceOrderUseCaseTest.java |
| 8 | An order exceeding a product's stock is rejected with `409… | A customer places an in-stock order, A placed order decrements stock, An unknown customer cannot order, An unknown product cannot be ordered, Out-of-stock rolls the checkout back | 2 | CreateOrderEndpointTest.java, PlaceOrderUseCaseTest.java |
| 9 | Orders can be listed and fetched by id through the gateway… | An unknown order id is not found, Order history is readable through the gateway | 3 | GetOrdersEndpointTest.java |

## Scenario coverage

- Scenarios: 10 total, 10 with detected test evidence, 0 without
- ✓ `Reserve stock within availability` (@w1)
- ✓ `Reserving beyond availability is rejected` (@w1)
- ✓ `Releasing reserved stock restores availability` (@w1)
- ✓ `A customer places an in-stock order` (@w2)
- ✓ `A placed order decrements stock` (@w2)
- ✓ `An unknown customer cannot order` (@w2)
- ✓ `An unknown product cannot be ordered` (@w2)
- ✓ `Out-of-stock rolls the checkout back` (@w2)
- ✓ `Order history is readable through the gateway` (@w3)
- ✓ `An unknown order id is not found` (@w3)

## Demo script (manual walkthrough)

1. **Reserve stock within availability** — run the app, perform the scenario, observe the outcome.
2. **A customer places an in-stock order** — run the app, perform the scenario, observe the outcome.
3. **A placed order decrements stock** — run the app, perform the scenario, observe the outcome.
4. **Order history is readable through the gateway** — run the app, perform the scenario, observe the outcome.

## Gaps

- none — spec is fully traced

Note: 'no evidence' means neither the scenario name nor a
token match was found in test files — verify by hand before
treating it as a real gap. '~' marks weak token-match evidence.

## Outcome follow-up

Optional; this is a post-release learning record, not a finish gate.
- Status: not yet measured
- Owner and review date: (record when known)
- Signal and observed result: (record after release; do not invent)
- Learning or next action: (record after review)