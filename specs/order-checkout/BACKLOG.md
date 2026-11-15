# Backlog: order-checkout

Items are USER STORIES in the parseable format below — keep the heading
shape `## US<n> — <story>` and the field lines intact so `wave_plan` can
parse them. Write the story as: "As a <role>, I want <goal>, so that
<value>" — the title after the dash IS the story.

## US1 — As a shop operator, I want to reserve and release product stock, so that an order can hold inventory without overselling
- Scenarios: @w1
- Size: M
- Notes: Adds `reserve`/`release` use cases + endpoints to the products service; the invariant lives on the `Product` aggregate.

## US2 — As a customer, I want to place an order for one or more products, so that I can buy them
- Scenarios: @w2
- Depends: US1
- Size: L
- Notes: New `orders` service; its first service-to-service calls (validate customer, reserve stock) with release-based compensation.

## US3 — As a shop operator, I want to list and review placed orders, so that I can fulfil them
- Scenarios: @w3
- Depends: US2
- Size: M
- Notes: Read model for orders behind the gateway; updates README/AGENTS/ARCHITECTURE and the compose smoke.
