# Projeto Tokio Marine Back-End

O Projeto foi desenvolvido em Java 17, utilizando Spring Boot, Spring JPA, Spring Web e a dependência do banco de dados H2.

## Instruções

- Para executar o projeto em sua máquina, é necessário ter instalado o Java Development Kit 17 e Maven em sua máquina.

- Após isso, basta clonar o repositório usando o comando `git clone URL_DO_REPOSITORIO`

- Para executar a aplicação, basta executar o comando `mvn spring-boot:run`. A Porta padrão é 8080.
- Para executar os testes da aplicação, basta executar o comando `mvn test`

## Endpoints

-  `GET api/v1/transacao` : Retorna a Lista de todas as transações realizadas
-  `POST api/v1/transacao` : Cadastra uma nova transação financeira a ser realizada. Exemplo de body da requisição:
```JSON
{
    "contaOrigem": "123456",
    "contaDestino": "654321",
    "dataAgendamento": "2023-12-30",
    "valorTransferencia": 1232113
}
```



