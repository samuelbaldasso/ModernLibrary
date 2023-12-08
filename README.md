# Backend para Bibliotecas com Autenticação JWT e Autorização por Roles - Java / Spring Boot

Este projeto é um backend para um sistema de bibliotecas. Ele oferece funcionalidades como autenticação e autorização de usuários, utilizando tokens JWT (JSON Web Tokens) e por um sistema de roles. O backend é construído em Java / Spring Boot com o banco de dados PostgreSQL.

## Exemplos de Recursos

- **Autenticação de Usuário**: Sistema de login e registro de usuários.
- **Autorização via JWT**: Após o login, os usuários recebem um token JWT para acessos subsequentes.
- **Gerenciamento de Restaurantes**: Funcionalidades para adicionar, visualizar, editar e deletar itens de livros, assim como gerenciar pedidos e controlar reservas de livros.

## Tecnologias Utilizadas

- **Java / Spring Boot**: Ambiente de execução do servidor.
- **PostgreSQL**: Banco de dados eficiente para armazenar os dados dos usuários e informações da biblioteca.
- **JWT (JSON Web Tokens)**: Utilizado para a autenticação e autorização de usuários com base em roles.

## Documentação da API

A documentação completa das APIs está disponível no endpoint `/swagger-ui/`. A documentação é interativa e permite testar os endpoints diretamente pela interface do Swagger.

## Instruções de Instalação e Uso

1. Clone o repositório: git clone [URL_DO_REPOSITORIO]

2. Navegue até a pasta do projeto e instale as dependências: cd [NOME_DA_PASTA_DO_PROJETO] e depois rodar o comando mvm install para gerar a atualização das dependências e o JAR da aplicação (caso desejar).

3. Inicie o servidor: execute a aplicação pela classe main da mesma na sua IDE preferida (recomendo Intellij IDEA Community ou Ultimate).

4. Acesse `https://books-api-production.up.railway.app/swagger-ui/` em seu navegador para visualizar a documentação da API.

---

Desenvolvido com ❤️ por Samuel Baldasso

PS: O projeto estará em ambiente de produção no link abaixo:

https://books-api-production.up.railway.app/
