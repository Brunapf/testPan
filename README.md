## Avaliacao para time Tecnologia Digital

> Status do Projeto: Em desenvolvimento :warning:

API feita com spring, banco H2 e REST para consultas.
Os dados estão sendo inseridos nas tabelas através do script no impot.sql que está localizado na raiz do projeto.

- [X] CENÁRIO 1 - Consultar Clientes 
```sh
http://localhost:8080/client
```
- [x] CENÁRIO 2 - Consultar Produtos do Cliente
```sh
http://localhost:8080/product/findByCPF/12345678915
```
- [X] CENÁRIO 3 - Consultar CEP
```sh
http://localhost:8080/address/findCep?cep=38400432
```
- [X] CENÁRIO 4 - Consultar Estados
```sh
http://localhost:8080/address/findStates
```
- [X] CENÁRIO 5 - Consultar Municípios
```sh
http://localhost:8080/address/findCityByIdUF/11
```
- [x] CENÁRIO 6 - Alterar endereço
```sh
http://localhost:8080/client/12345678915

Obs.: Passando o cep no payload, a api buscará os dados Do
endereço e irá atualizar. Não sendo necessário incluir nome da rua, bairro e cidade no payload.
Passando os produtos no payload o sistema também atualizará os produtos.
```

Para rodar o sistema execute os comandos abaixo na pasta raiz do projeto.


## Compilar o projeto
```sh
mvn clean install
```