# Exercício de Java / Spring Boot

## Tecnologias e Feramentas:

| Name | version | description |
| :--- | :--- | :--- |
| JAVA  | 1.8| Usando JDK 8  | 
| Maven | 3.6.3  | EMBEDDED do Eclise |
| Springf boot | 2.3.5.BUILD-SNAPSHOT| Setup da aplicação |
| Spring data jpa | 2.3.4 | Pesistencia de dados |
| Spring cloud netflix eureka server | x | Service Discovery |
| Spring cloud netflix eureka-client | 2.2 |acessar e Registrar nos servicos do eureka-server|
| Spring cloud netflix zuul | x | Api Gateway |
| Spring cloud oauth2 | 2.2.4 | Autenticação e Autorização do servicos |
| Spring security| 5.3.4 | Autenticação e Autorização do servicos|
| Hibernate validator| 5.1.3 | Validação de campos |
| Flyway-core | 6.4.4  |Para migração de dados|
| MySQL | 5.7 | Banco de dados utilizado |
| Lombok | 1.8 | Almentar produtividade |
| Spring Tool Suite  | 4 | IDE |
 

##### Objetivo: Criar um Sistema de Cadastro e Compra de Automóveis, que funcione através de uma web API ela deve ter os seguintes endpoints:
  - POST /cadastroAutomoveis
    - Cadastra o automóvel disponível para compra.
    - tributos: ID (gerado automaticamente), Marca, Modelo, Valor, data de cadastro
    - Dados enviados no Body em JSON.
    - Retorna 201 se criação for ok.
   - GET /automóveis
    - Retorna todos os automóveis cadastrados em JSON.
    - Retorna 200 se consulta for ok.
#### Microsserviços criados durante o desenvolvimento.

| Nome | Descrição | cloud | URL |
| ------ | ------ |------ |------ |  
| automoveis-api | CRUD | amazonaws |  [/v1/automoveis](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/) |
| boleto-api | CRUD | amazonaws | [/v1/boletos](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos)  |
| eureka-service-discovery | Service Discovery com Eureka  | digitalocean | [/eureka/apps/](http://198.199.91.245:8761/eureka/apps/) |
| api-gateway-zuul | API Gateway| digitalocean | [/eureka/apps/](http://198.199.91.245:5555/actuator/routes) |
| authorization-server-auth | Criar fluxo de autorização / autenticação | digitalocean |[/v1/oauth/token/](http://198.199.91.245:8088/v1/oauth/token/) |

 #### Client Service da API 

| **Usuario** | **Escopo** |
| :--- | :--- |
| automoveis-api | web, mobile |
| boletos-api | web, mobile |

 #### Usuarios da API

| **Usuario** | **Senha** | **Papel** |
| :--- | :--- | :--- |
| itau | 12345 | USER  |
| admin| 12345 | ADMIN  |

#  Chamada dos endpoints Usando Postman:
 
 ### Authorization
No Authorization do `postman` adiconer tipo de autenticação `Basic Auth` adicione o `Usuário Cliente` e  `Senha do Cliente`
Para autenticar na API, você deve fornecer `os parametos` da API no cabeçalho da solicitação. As solicitações para a API gera um `token`. Para gerar uma  `token`, vai até o `endpoint`:
```http
POST http://198.199.91.245:8088/v1/oauth/token/
```

Adicione no ` Body => Form-data : Add os sequintes parametros.`
| Parametro | Ttipo | valor | obrigatório ️ |
| :--- | :--- | :--- | :--- |
| `scope` | `string` |  web | **sim** |
| `grant_type` | `string` | password   | **sim** |
| `username` | `string`   | Usuário do Sistema | **sim** |
| `password` | `string`   |  Senha do Usuário | **sim**|

Está operação vai retornar um `access_token` => `4ece1bf0-d545-4bcc-81e9-915a2e2483e25242`  para acessar a `APIs`

```javascript
{
    "access_token": "4ece1bf0-d545-4bcc-81e9-915a2e2483e25242",
    "token_type": "bearer",
    "expires_in": 3633,
    "scope": "web"
}
```
 ## Para acessar o endpoints automoveis
  
| Método| URI | parametro | Status Code | descrição |
| :--- | :--- | :--- | :--- | :--- |
| GET |[/v1/automoveis](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/)| NÂO  | 200 | `OK` |
| GET |[/v1/automoveis/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/)| codigo |200| `OK` |
| POST |[/v1/automoveis](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/)| JSON  | 201 | `CREATED` |
| PUT |[/v1/automoveis/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/)|JSON| 200 | `OK` |
| DELETE |[/v1/automoveis/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/)|JSON| 204 | ` No Content` |

 ### Para buscar todos os automoveis:
```http
GET  http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/
```
```javascript
[
  {
    "codigo": "1",
    "marca": "VW",
    "modelo": "JETTA GLI",
    "valor": 40000,
    "dataCadastro": "2020-09-30"
  },   
  {
    "codigo": "1"
    "marca": "VW",
    "modelo": "JETTA GLI",
    "valor": 40000,
    "dataCadastro": "2020-09-30"
  }
]
``` 
 ### Para buscar automóvel por código:
 Add o código do automóvel a ser listado na `URL` com a baixo: :
```http
GET  http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/1
```
```javascript
  {
    "codigo": "1",
    "marca": "VW",
    "modelo": "JETTA GLI",
    "valor": 40000,
    "dataCadastro": "2020-09-30"
  }  
``` 
 ### Para cadastrar um automóvel.
 Add no `Body`  da requisição  o json a baixo:
```http
POST  http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/
```
```javascript
  {
    "marca": "VW",
    "modelo": "JETTA GLI",
    "valor": 40000,
  }  
``` 
 ### Para atualizar um automóvel.
 Add o código do automóvel a ser atualizado na `URL` com a baixo:
```http
PUT  http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/1
```
```javascript
  {
    "marca": "VW",
    "modelo": "JETTA GLI",
    "valor": 40000,
  }  
``` 
 ### Para excluir um automóvel.
 Add o código do automóvel a ser excluido na `URL` com a baixo:
```http
DELETE  http://ec2-18-206-127-97.compute-1.amazonaws.com:8080/v1/automoveis/1
```
```javascript

``` 

## Para acessar o endpoints boletos
  <br />
| Método| URI | parametro | Status Code | descrição |
| :--- | :--- | :--- | :--- | :--- |
| GET |[/v1/boletos](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/)| NÂO  | 200 | `OK` |
| GET |[/v1/boletos/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/)| codigo |200| `OK` |
| POST |[/v1/boletos](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/)| JSON  | 201 | `CREATED` |
| PUT |[//v1/boletos/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/)|JSON| 200 | `OK` |
| DELETE |[/v1/boletos/{codigo}](http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/)|JSON| 204 | ` No Content` |

 ### Para buscar todos os boletos:
```http
GET  http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/
```
```javascript
[
{
      "codigo": 1,
      "valor": 7000.0,
      "dataVencimento": "2020-09-30"
    },
    {
      "codigo": 1,
      "valor": 7000.0,
      "dataVencimento": "2020-09-30"
    }
]
``` 
 ### Para buscar boleto por código:
 Add o código do boleto a ser listado na `URL` com a baixo: :
```http
GET  http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/1
```
```javascript
{
  "codigo": 1,
  "valor": 7000.0,
  "dataVencimento": "2020-09-30"
}
``` 
 ### Para cadastrar um boleto.
 Add no `Body`  da requisição  o json a baixo:
```http
POST  http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/
```
```javascript
   {
    "codigo": 1,
    "marca": "vw",
    "modelo": "jetta New ",
    "valor": 7000.0,
    "dataVencimento": "2020-09-30"
  }
``` 
 ### Para atualizar um boleto.
 Add o código do boleto a ser atualizado na `URL` com a baixo:
```http
PUT  http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/1
```
```javascript
   {
    "marca": "vw",
    "modelo": "jetta New ",
    "valor": 4000.0,
    "dataVencimento": "2020-09-30"
  }
``` 
 ### Para excluir um boleto.
 Add o código do boleto a ser excluido na `URL` com a baixo:
```http
DELETE  http://ec2-18-206-127-97.compute-1.amazonaws.com:8181/v1/boletos/1
```
```javascript

``` 
## Para acessar o endpoints eureka-service-discovery 

Implementado o Service discovery e Service registry com Eureka:
 * **Service registry** é um servidor central, onde todos os microsserviços ficam cadastrados `(nome e IP/porta)` **Service discovery** é um mecanismo de descoberta do IP do microsserviço pelo nome Dessa forma, nenhum microsserviço fica acoplado ao outro pelo IP/porta
* A implementação do service registry através do **Eureka Server**
* A resolução do IP/porta através do nome do microsserviço nas requisições

 ### para acessar todos os app cadastrado eureka
```http
 GET  http://198.199.91.245:8761/eureka/apps/
```
## Para acessar o endpoints eureka-service-discovery 

Implementado o Service discovery e Service registry com Eureka:
 * **Service registry** é um servidor central, onde todos os microsserviços ficam cadastrados `(nome e IP/porta)` **Service discovery** é um mecanismo de descoberta do IP do microsserviço pelo nome Dessa forma, nenhum microsserviço fica acoplado ao outro pelo IP/porta
* A implementação do service registry através do **Eureka Server**
* A resolução do IP/porta através do nome do microsserviço nas requisições

## Para acessar o endpoints api-gateway-zuul
* O uso do Api Gateway, para criar um único ponto de acesso à aplicação.
* O uso da implementação Zuul como **Api Gateway**.
* A integração do Zuul com o Eureka, para o descobrimento automático das instâncias disponíveis da nossa aplicação.
* A alteração das requisições para os nossos microsserviços, com o uso do Zuul.

**URLpara listar os apps cadastrados**:
```http
 GET  http://198.199.91.245:5555/actuator/routes
```
**URL Base** :
```http
http://198.199.91.245:5555/**
```
 * `POST`
 * `GET`
 * `PUT`
 * `DELETE`


Exemplos de como  acessar os bolestos via **Api Gateway**. podemos acessae qualquer servios via **Api Gateway**
```http
GET http://localhost:5555/boletos-api/v1/boletos/2
```
```javascript
   {
    "marca": "vw",
    "modelo": "jetta New ",
    "valor": 4000.0,
    "dataVencimento": "2020-09-30"
  }
``` 
## Para acessar o endpoints authorization-server-auth 
Autenticação e autorização com microsserviços usando Spring Cloud OAuth2

 * Foi  criado um servidor de autenticação com **Spring Security** e **Spring Cloud OAuth2**
* implementação da integração entre o **Spring Security** e o **Spring Cloud OAuth2**
* Como gerar e validar `tokens`  no servidor de autenticação
* A integração dos microsserviços com o servidor de autenticação
* Como repassar o token de autenticação através do **Zuul**

**URL para gerar token**:
```http
 POST  http://198.199.91.245:8088/v1/oauth/token/
```

```javascript
{
    "access_token": "4ece1bf0-d545-4bcc-81e9-915a2e2483e25242",
    "token_type": "bearer",
    "expires_in": 3633,
    "scope": "web"
}
```

**URL para validar o token**:
```http
 GET  http://198.199.91.245:8088/v1/oauth/user
```
```javascript
{
  "authorities": [
    {
      "authority": "ROLE_ADMIN"
    }
  ],
  "details": {
    "remoteAddress": "0:0:0:0:0:0:0:1",
    "sessionId": null,
    "tokenValue": "0f18f846-b751-4b5c-ad3d-2c8821c18dss9",
    "tokenType": "Bearer",
    "decodedDetails": null
  }, 
  ......
 }
```

## Códigos de status Utilizados na API:

`APIs` retorna os seguintes códigos de status::

| Status Code | Descrição |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 2004 | `NO_CONTENT` |
| 400 | `BAD_REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |
## Autor:
### **[Lucas Carvalho](https://www.linkedin.com/in/lucas-carvalho-793609134/)**










