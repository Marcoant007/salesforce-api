# Salesforce API

A Salesforce API foi desenvolvida para gerenciar vendedores e suas filiais associadas. Esta API permite criar, atualizar, deletar e listar vendedores, bem como recuperar informações detalhadas sobre vendedores junto com suas respectivas filiais.

---
## Pré-requisitos

- Java 17+
- Docker
- Docker Compose
- Executar o projeto filiais api 

---

## Tecnologias Utilizadas
<img src="https://marcoantdeveloper.netlify.app/assets/img/icons/JAVA.png" width=80> <img src="https://marcoantdeveloper.netlify.app/assets/img/icons/SPRING.png" width=80> <img src="https://marcoantdeveloper.netlify.app/assets/img/icons/DOCKERZADA.png" width=80> <img src="https://marcoantdeveloper.netlify.app/assets/img/icons/POSTGRESQL.png" width=80> <img src="https://marcoantdeveloper.netlify.app/assets/img/icons/JUNIT.png" width=80> <img src="https://i.ibb.co/fXK7J0Q/GITACTIONSSSS.png" width=80>

- Spring Boot
- PostgreSQL
- Swagger
- Docker
- GitHub Actions (CI)
- Java 17
---

## 1- Executando o Projeto Filiais api

### 1.1 - application.properties
```shell script 
    server.port=8086
    spring.application.name=filials-api
    springdoc.api-docs.path=/api-docs
    springdoc.swagger-ui.path=/swagger-ui.html
```


### 1.2-  execute o clone do repositório:
```shell script 
    git clone https://github.com/Marcoant007/branch-api.git
```

> **IMPORTANTE** Para executar este projeto localmente, certifique-se de ter o Docker instalado na sua máquina. Em seguida, siga os passos abaixo:


### 1.3-  Execute o seguinte comando para iniciar a aplicação usando Docker Compose:

```shell script
docker-compose up --build
```

- Para acessar o swagger basta iniciar a aplicação e acessar a url:

```shell script
> http://localhost:8086/swagger-ui.html
```

---
## 2- Executando o Projeto SalesForce API

Para executar este projeto localmente, siga os passos abaixo:
### 2.1 Altere o arquivo application.properties
- Insira o ambiente de dev
```shell script
spring.profiles.active=dev

# Swagger
spring.application.name=vendedor-api
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# External service configuration
filial.api.url=http://localhost:8086/filial/
```

### 2.2 Execute o docker para subir o banco de dados PostgresSQL

- Execute o seguinte comando para iniciar a aplicação usando Docker Compose:
 
```shell script
 docker-compose up --build
```
### 2.3 Execute o projeto manualmente

```shell script
> mvn spring-boot:run
```
> Obs: tudo dando certo você verá essa tela e o projeto estará rodando na porta 8085

<img src="https://i.ibb.co/tMZcYB0/image.png">

> Applicação rodando com sucesso ✅

---
### 3. Execute os testes
Configure o perfil de teste no `application-test.properties` para usar o banco de dados H2.

Execute os testes usando o Maven:
```shell script
> mvn clean test
```
> A aplicação contém no total 31 testes 

<img src="https://i.ibb.co/LCNCHnR/image.png">

<img src="https://i.ibb.co/ccPTL4f/image.png">

---
### 3-  Endpoints | Swagger
Para acessar o Swagger, basta iniciar a aplicação e acessar a URL:
> Verifique o swagger para conferir os endpoints da aplicação.
```shell script
> http://localhost:8085/swagger-ui/index.html#/
```
<img src="https://i.ibb.co/68792zx/image.png">

---

## 4-  Integração Contínua (CI)

Este projeto utiliza GitHub Actions para CI. O pipeline de CI inclui etapas para:

- Fazer checkout do código
- Configurar o JDK 17
- Cachear pacotes Maven
- Construir o projeto
- Executar os testes
- Verificar a conformidade com o Checkstyle

O pipeline é executado automaticamente em cada push e pull request, garantindo que o código esteja sempre em conformidade com os padrões de qualidade.

<img src="https://i.ibb.co/br4Q5y4/Cigif.gif">

---


