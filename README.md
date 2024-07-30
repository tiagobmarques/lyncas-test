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

#### Autenticando
```
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{"username": "tiago", "password": "password123"}'
```

#### Salvar contas a pagar
```
curl --location 'localhost:8080/contasapagar' \
--header 'Content-Type: application/json' \
--data '{
  "dataVencimento": "2024-08-01",
  "dataPagamento": "2024-07-25",
  "valor": 150.75,
  "descricao": "Compra de material de escritório",
  "situacao": "pendente"
}
'
```

#### Importar CSV
```
curl --location 'localhost:8080/contasapagar/importar' \
--form 'file=@"/C:/Users/tiago.marques/Documents/pessoal/lyncas-test/contasapagar.csv"'
```

#### Atualizar contas a pagar
```
curl --location --request PUT 'localhost:8080/contasapagar/97256956-db23-4ac8-a30d-386f5b9dcc1d' \
--header 'Content-Type: application/json' \
--data '{
  "dataVencimento": "2024-08-01",
  "dataPagamento": "2024-07-25",
  "valor": 150.75,
  "descricao": "Tiago teste",
  "situacao": "pendente"
}
'
```

#### Atualizar situação
```
curl --location --request PUT 'localhost:8080/contasapagar/97256956-db23-4ac8-a30d-386f5b9dcc1d/situacao' \
--header 'Content-Type: application/json' \
--data '{
  "situacao": "aprovado"
}
'
```

#### Buscar por ID
```
curl --location --request GET 'localhost:8080/contasapagar/97256956-db23-4ac8-a30d-386f5b9dcc1d' \
--header 'Content-Type: application/json' \
--data '{
  "situacao": "aprovado"
}
'
```

#### Buscar total pago
```
curl --location --request GET 'localhost:8080/contasapagar/total-pago?startDate=2024-01-01&endDate=2025-01-31' \
--header 'Content-Type: application/json' \
--data '{
  "situacao": "aprovado"
}
'
```

#### Buscar lista por paginação
```
curl --location 'localhost:8080/contasapagar?startDate=2024-01-01&endDate=2025-01-31&page=1&size=3' \
```
