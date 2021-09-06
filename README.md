# Mercy Hospital

Projeto desenvolvido em Angular no frontend e Java para a API como desafio técnico

## Como rodar a aplicação

### Requisitos
- Java 8
- Angular 9+ CLI
- NodeJs
- PostgreSQL

### PostgreSQL
É necessário que exista um banco de dados vazio com a seguinte configuração
- Nome: mercy-db
- Usuário: master
- Senha: master

Caso queira alterar esta configuração, basta mudar os parâmetros em `application.properties`
### Backend
Para iniciar a API navegue até o diretório `mercy-backend` e então rode o comando abaixo

> mvn spring-boot:run

### Frontend
Para iniciar o frontend navegue até o diretório `mercy-backend` e então rode os comandos abaixo

> npm i
>
> ng serve

Então poderá acessar o projeto em http://localhost:4200