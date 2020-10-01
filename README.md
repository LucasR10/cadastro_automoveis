
##### Objetivo: Criar um Sistema de Cadastro e Compra de Automóveis, que funcione através de uma web API ela deve ter os seguintes endpoints:
  - POST /cadastroAutomoveis
    - Cadastra o automóvel disponível para compra.
    - tributos: ID (gerado automaticamente), Marca, Modelo, Valor, data de cadastro
    - Dados enviados no Body em JSON.
    - Retorna 201 se criação for ok.
  - GET /automóveis
    - Retorna todos os automóveis cadastrados em JSON.
    - Retorna 200 se consulta for ok.
## Microsserviços criados para o sustema.
| Nome | Descrição | cloud | URL |
| ------ | ------ |------ |------ |  
| automoveis-api | CRUD | amazonaws |  [/v1/automoveis](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/) |
| boleto-api | CRUD | amazonaws | [/v1/boletos](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos)  |
| eureka-service-discovery | Service Discovery  | digitalocean | [/eureka/apps/](http://198.199.91.245:8761/eureka/apps/) |
| api-gateway-zuul | API Gateway| digitalocean | [/eureka/apps/](http://198.199.91.245:5555/actuator/routes) |
| authorization-server-auth | Criar fluxo de autorização / autenticação | digitalocean |[/v1/oauth/token/](http://198.199.91.245:8088/v1/oauth/token/) |
