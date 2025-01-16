# Cafeteria - Aplicação Backend

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

Essa aplicação foi feita usando **Java e PostgreSQL.**

Essa API foi desenvolvida para meu portfolio, para demonstrar conhecimento em Backend com Java, Springboot e PostgreSQL.

## Funcionalidades

- `Listar` GET para listar conteudo da tabela do banco de dados.
- `Adicionar` POST para adicionar um produto da tabela do banco de dados.
- `Remover` DELETE para remover um produto da tabela do banco de dados.
- `Atualizar` POST para atualizar um produto da tabela do banco de dadosa.

## Dependencias
- Java JDK 23

## Instalação

1. Clone o repositorio:

```bash
git clone https://github.com/JustinoLucas/coffe-api-rest
cd coffe-api-rest
```

## Criação Banco de dados

- Banco de dados com nome "coffee"


- SQL abaixo para PostgreSQL
```bash
CREATE TABLE coffee (
    id_coffee SERIAL PRIMARY KEY,
    nome_coffee VARCHAR(255) NOT NULL, 
    preco_coffee NUMERIC(10, 2) NOT NULL,
    desc_coffee TEXT,
    image_coffee VARCHAR(255)
);
```


## Integração com Frontend

Para realizar a integração com o Frontend, você pode clonar o projeto e rodar localmente, ou desenvolver você mesmo.

[Link do repositório Frontend](https://github.com/JustinoLucas/React-TS-Coffe.git)


