## Avaliacao para time Tecnologia Digital

> Status do Projeto: Em desenvolvimento :warning:

API feita com spring-boot + H2 + docker-compose e REST para consultas.
Os dados estão sendo inseridos nas tabelas através do script no impot.sql que está localizado na raiz do projeto.

- [X] CENÁRIO 1 - Consultar Clientes
- [ ] CENÁRIO 2 - Consultar Produtos do Cliente
- [X] CENÁRIO 3 - Consultar CEP
- [X] CENÁRIO 4 - Consultar Estados
- [X] CENÁRIO 5 - Consultar Municípios
- [ ] CENÁRIO 6 - Alterar endereço

Para rodar o sistema execute os comandos abaixo na pasta raiz do projeto.


## Compilar o projeto
```sh
mvn clean install
```

### Sonarqube 

Acesse o Sonar pelo endereço:

> http://localhost:9000

Usuário e senha padrão: 

> admin
