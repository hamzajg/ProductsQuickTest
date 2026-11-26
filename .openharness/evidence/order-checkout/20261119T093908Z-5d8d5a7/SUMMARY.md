## Evidence — order-checkout

- verdict: **pass**
- run: `20261119T093908Z-5d8d5a7`
- head: `5d8d5a736f3d4703319a1dabecc0ea65e5077e4d`

order-checkout demo walked through gateway on docker-compose stack: reserve/release contract (reserve 3->7, over-reserve 409, release->10), in-stock order PLACED total 10.0 with stock 10->8, unknown customer 404, out-of-stock 409 with rollback (stock stays 8), order history list+by-id via gateway. Gateway remapped to host :18080 (taskflow dev server holds :8080).

- steps: 1 · artifacts: 4 · screenshots: 0 · recordings: 0

**Not verified:** webapp UI checkout button path; multi-line partial-failure release best-effort
