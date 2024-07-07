# Salesforce API

A Salesforce API foi desenvolvida para gerenciar vendedores e suas filiais associadas. Esta API permite criar, atualizar, deletar e listar vendedores, bem como recuperar informações detalhadas sobre vendedores junto com suas respectivas filiais.

## Motivação

Esta API foi criada para simplificar a gestão de vendedores e suas filiais, facilitando a integração com outros sistemas que precisam consultar e atualizar esses dados.

## Pré-requisitos

- Java 17+
- Docker
- Docker Compose

## Executando o Projeto

Para executar este projeto localmente, siga os passos abaixo:

### Docker

Certifique-se de que o Docker e o Docker Compose estão instalados na sua máquina.

Execute o seguinte comando para iniciar a aplicação usando Docker Compose:


### Testando o Projeto

#### Docker

Para executar os testes, certifique-se de que o Docker Compose está configurado corretamente. Em seguida, execute:


#### Manualmente

Certifique-se de que o PostgreSQL está rodando e configurado corretamente.

Configure o perfil de teste no `application-test.properties` para usar o banco de dados H2.

Execute os testes usando o Maven:


### Endpoints | Swagger

Para acessar o Swagger, basta iniciar a aplicação e acessar a URL:


## Integração Contínua (CI)

Este projeto utiliza GitHub Actions para CI. O pipeline de CI inclui etapas para:

- Fazer checkout do código
- Configurar o JDK 17
- Cachear pacotes Maven
- Construir o projeto
- Executar os testes
- Verificar a conformidade com o Checkstyle

O pipeline é executado automaticamente em cada push e pull request, garantindo que o código esteja sempre em conformidade com os padrões de qualidade.

## Dependências

- Filial API: Este projeto depende da API de Filiais para funcionar corretamente. Certifique-se de que a API de Filiais está rodando e acessível.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- PostgreSQL
- H2 Database (para testes)
- Swagger
- Maven
- Docker
- GitHub Actions (CI)
