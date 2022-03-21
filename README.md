# Desafio Compasso

#### Projeto disponivel:
https://github.com/klebsonlou/Desafio_Compasso.git

#### Especificação do projeto:

* Java 1.8.
* Spring boot.
* Banco H2 em memória .
* Build via Maven.
* Documentado com swagger.
* Teste unitário com JUnit.
* Disponível no Host Heroku (Obs.: versão free desta forma após um tempo de inatividade a aplicação sai do ar e a base de dados é apagada)

#### Acessos localhost swagger (http://localhost:9999/swagger-ui.html)

#### Acessos localhost via Postman:

###### Para criar um produto utilizar a url como POST:
* http://localhost:9999/products

###### Para atualizar um produto utilizar a url como PUT:
* http://localhost:9999/products/1

###### Para consultar lista de produtos utilizar a url como GET:
* http://localhost:9999/products

###### Para consultar um produto por id utilizar a url como GET:
* http://localhost:9999/products/1

###### Para consultar um produto por filtro utilizar a url como GET:
* http://localhost:9999/products/search?min_price=10.5&max_price=50&q=superget

###### Para deletar um produto utilizar a url como DELETE:
* http://localhost:9999/products/1

#### Acessos via Heroku:

###### Para criar um produto utilizar a url como POST:
* https://app-compasso.herokuapp.com/products

###### Para atualizar um produto utilizar a url como PUT:
* https://app-compasso.herokuapp.com/products/1

###### Para consultar lista de produtos utilizar a url como GET:
* https://app-compasso.herokuapp.com/products

###### Para consultar um produto por id utilizar a url como GET:
* https://app-compasso.herokuapp.com/products/1

###### Para consultar um produto por filtro utilizar a url como GET:
* https://app-compasso.herokuapp.com/products/search?min_price=10.5&max_price=50&q=superget

###### Para deletar um produto utilizar a url como DELETE:
* https://app-compasso.herokuapp.com/products/1

#### Tratamento de Erros:

###### Tratamento de erros implementados:
  1. Inserir ou atualizar um Product com formato inválido.
  2. Atualizar um Product com id não existente.

##### exemplo payload :
{
    "name": "nom1",
    "description": "nom2",
    "price": 1.6
  }
