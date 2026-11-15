# ADR 0002 — Quarkus 3.40 LTS and the Jakarta EE 10 namespace

Date: 2026-11-15
Status: accepted (describes what is built)

## Context

ADR 0001 put both services on Quarkus 2.16.12, the last release line built on
Jakarta EE 8. That line is out of community support, so dependency and security
fixes require Quarkus 3 — and every 3.x release is Jakarta EE 10, where the API
namespace moved from `javax.*` to `jakarta.*`. There is no Quarkus 3 that keeps
`javax.*`.

Quarkus 3.40 is the current LTS (2026-09, supported 12 months); 3.33 was the
older alternative with a shorter window.

The gateway is not affected: it is Spring Boot 2.7 + Spring Cloud Gateway and
never used `javax` APIs through this codebase.

## Decisions

1. **Quarkus 3.40 LTS (`3.40.1.2`)** for `Product.WebApi` and `Customer.WebApi`.
   Platform BOM is `io.quarkus.platform:quarkus-bom` — the
   `io.quarkus:quarkus-universe-bom` coordinate does not exist in 3.x.
2. **Gradle wrapper 7.6.4 → 8.14.3**, because the Quarkus 3 Gradle plugin
   requires Gradle 8.14 or newer. The Docker build images follow
   (`gradle:8.14.3-jdk17`), including `ApiGateway`'s so the whole compose stack
   builds with one Gradle line.
3. **`javax.*` → `jakarta.*` in 70 files** across all four layers of both
   services (`javax.inject`, `javax.enterprise`, `javax.ws.rs`,
   `javax.json.bind`).
4. **CDI 4.0 in the non-Quarkus modules.** `Product.Application`,
   `Product.Infrastructure`, `Customer.Application` and
   `Customer.Infrastructure` declared `jakarta.enterprise.cdi-api:2.0.2` — the
   CDI 2.0 API, which is `javax`-namespaced despite the group id — so they move
   to `4.0.1`.
5. **Weld SE 3.0.1 → 5.0.1 in those modules' tests.** Their specs bootstrap a
   CDI SE container; a Weld 3.x runtime does not recognise `jakarta`
   annotations, so it starts and resolves nothing. Weld 5 implements CDI 4.0.
6. **`quarkus.http.cors=true` → `quarkus.http.cors.enabled=true`** in both
   services' `application.properties`. Quarkus 3 only *warns* about unknown
   keys, so leaving it would have silently disabled CORS for the React
   back office — the kind of breakage that compiles and tests green.
7. **RESTEasy Classic kept** (`quarkus-resteasy` + `quarkus-resteasy-jsonb`);
   moving to RESTEasy Reactive is a behavioural change (blocking/`@Blocking`
   semantics) with nothing to gain in these services.

## Consequences / open questions

- Both services now run on Jakarta EE 10, which is what Quarkus 4 builds on;
  the `javax` blocker is gone.
- Gradle 8.14.3 is the floor for the Quarkus 3 plugin; the wrapper upgrade also
  made the build forward-compatible with the gateway's Spring Boot 2.7 plugin,
  which the local build exercises.
- Spring Boot 2.7 on the gateway and React 17 in the webapp are the remaining
  EOL pieces; neither is part of this change.
- The domain, application and infrastructure layers stay in-memory and
  framework-free at the domain level — the namespace move touched annotations
  only.
