# Introdução
O objetivo desta API é lançar contas a pagar

#### Pré-requisitos
Para rodar este projeto é necessário ter algumas ferramentas instaladas:
* Docker
* Java 17

#### Tecnologias e frameworks
* Java 17
* Spring Boot
* Spring Data JPA
* Postgres
* Docker
* Gradle
* Lombok
* Swagger
* JUnit
* Mockito

#### Rodar o projeto local
Para rodar a aplicação é necessário antes alguns passos:

```
docker-compose up -d
```
Este comando acima irá subir o banco de dados para realizar o teste local. 

```
./gradlew bootRun
```
O comando acima irá baixar as dependências e rodar a aplicação na porta 8080.

#### Rodar os testes

Neste projeto foram implementados dois tipos de testes. Teste unitário e testes de contrato.

```
./gradlew test
```
O comando acima irá rodar os testes unitários e os testes de contrato.

#### Acessando a documentação da aplicação

Toda aplicação está documentada via Swagger. Para acessar basta clicar neste [link](http://localhost:8080/swagger-ui.html).