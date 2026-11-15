# Gates: order-checkout

> Preconditions for phase transitions. Each `## G<n> — <title>` block is a
> gate. `ship_loop` refuses to advance past a phase while a blocking gate is
> pending. Definitions live here; status and evidence live in `LOOP.md`.
>
> Gate phases: `spec` (before writing the spec), `plan` (before planning
> waves), `wave:<n>` (before starting wave n), `validate` (before validation),
> `finish` (before closing the feature).
>
> Gate types: decision | artifact | external | technical | quality | data.
> Resolver: `human` (needs a person to decide/provide something) or `agent`
> (the agent can satisfy it). Check: `manual`, `file:<repo-relative-path>`, or
> `command:<exact command>` (the agent runs it and records the output as
> evidence — the oracle never executes it).

## G1 — Reserve/Release API contract frozen
- Before: plan
- Type: decision
- Owner: tech-lead
- Resolver: agent
- Blocking: yes
- Check: manual
- Why: W1 ships the products `reserve`/`release` endpoints and W2's orders client consumes them; paths, payloads and status codes must be agreed before either is written. Frozen in SPEC.md → Contract.

## G2 — Orders module integrates into the multi-project build
- Before: wave:3
- Type: technical
- Owner: agent
- Resolver: agent
- Blocking: yes
- Check: command:sh gradlew :Order.WebApi:build --no-daemon
- Why: the new `src/Order/**` module and its gateway/compose wiring must compile and test inside the existing Gradle build before the read model is added on top.

## G3 — Compose smoke proves checkout through the gateway
- Before: validate
- Type: quality
- Owner: agent
- Resolver: agent
- Blocking: yes
- Check: file:.github/workflows/compose-smoke.yml
- Why: the whole point of the feature is a service-to-service call resolved by container name; CI must boot gateway + products + customers + orders and drive a checkout through the gateway (evidence: the green `compose-smoke` run on the PR).

## G4 — Human acceptance of the checkout demo
- Before: finish
- Type: quality
- Owner: product-owner
- Resolver: human
- Blocking: yes
- Check: manual
- Why: AC-4/AC-8 are the demo's money shot (an order placed, stock moved, a bad order rolled back); a person walks the VALIDATION.md demo script before the feature is declared shipped.
