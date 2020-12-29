# ProductsQuickTest
## RUN Services
`
docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d
`
## List Services
`
docker-compose -f docker-compose.yml -f docker-compose.override.yml ps
`
## Output
```console
Name                       Command               State           Ports
---------------------------------------------------------------------------------------
quicktest-api-gateway   java -jar ApiGateway-0.0.1 ...   Up      0.0.0.0:8080->8080/tcp
quicktest-customers     /deployments/run-java.sh         Up      0.0.0.0:8082->8082/tcp
quicktest-products      /deployments/run-java.sh         Up      0.0.0.0:8081->8081/tcp
quicktest-webapp        yarn workspace @webapp/ui  ...   Up      0.0.0.0:3000->3000/tcp
```