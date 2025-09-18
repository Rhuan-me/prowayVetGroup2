
# API do Zoológico

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.11-red)
![MySQL](https://img.shields.io/badge/MySQL-black)
![License](https://img.shields.io/badge/License-Apache_2.0-blue)

## Sobre o Projeto
Esta é uma API RESTful para gerenciamento de um zoológico. O projeto foi desenvolvido usando **Spring Boot** e **Spring Data JPA** para persistência de dados. Ele permite gerenciar animais, cuidadores, habitats, alimentação e veterinários por meio de endpoints HTTP.

## Funcionalidades
A API oferece os seguintes recursos e endpoints:

### Animais (`/animal`)
- `GET /animal`: Lista todos os animais.
- `GET /animal/{id}`: Busca um animal por ID.
- `POST /animal`: Cria um novo animal.
- `POST /animal/lote`: Cria vários animais em lote.
- `PUT /animal/{id}`: Atualiza os dados de um animal.
- `DELETE /animal/{id}`: Deleta um animal.
- `GET /animal/especie/{especie}`: Busca animais por espécie (ignora maiúsculas/minúsculas).
- `GET /animal/idade?min={idadeMin}&max={idadeMax}`: Busca animais por faixa de idade.
- `GET /animal/nome?nome={nome}`: **(Funcionalidade a ser implementada)** Busca animais por nome parcial.

### Cuidadores (`/cuidadores`)
- `GET /cuidadores`: Lista todos os cuidadores.
- `GET /cuidadores/{id}`: Busca um cuidador por ID.
- `POST /cuidadores`: Cria um novo cuidador.
- `PUT /cuidadores/{id}`: Atualiza os dados de um cuidador.
- `DELETE /cuidadores/{id}`: Deleta um cuidador.
- `GET /cuidadores?especialidade={especialidade}`: Busca por especialidade.
- `GET /cuidadores?turno={turno}`: Busca por turno.

### Habitats (`/habitats`)
- `GET /habitats`: Lista todos os habitats.
- `GET /habitats/{id}`: Busca um habitat por ID.
- `POST /habitats`: Cria um novo habitat.
- `PUT /habitats/{id}`: Atualiza os dados de um habitat.
- `DELETE /habitats/{id}`: Deleta um habitat.
- `GET /habitats?tipo={tipo}`: Busca por tipo de habitat.

### Alimentação (`/alimentacao`)
- `GET /alimentacao`: Lista todos os registros de alimentação.
- `GET /alimentacao/{id}`: Busca um registro por ID.
- `POST /alimentacao`: Cria um novo registro de alimentação.
- `PUT /alimentacao/{id}`: Atualiza um registro de alimentação.
- `DELETE /alimentacao/{id}`: Deleta um registro de alimentação.
- `GET /alimentacao?comida={comida}`: **(Funcionalidade a ser implementada)** Busca por tipo de comida.
- `GET /alimentacao?animalId={id}`: **(Funcionalidade a ser implementada)** Busca pela dieta de um animal específico.

### Veterinários (`/veterinario`)
- `GET /veterinario`: Lista todos os veterinários.
- `GET /veterinario/{id}`: Busca um veterinário por ID.
- `POST /veterinario`: Cria um novo veterinário.
- `PUT /veterinario/{id}`: Atualiza os dados de um veterinário.
- `DELETE /veterinario/{id}`: Deleta um veterinário.
- `GET /veterinario?especialidade={especialidade}`: **(Funcionalidade a ser implementada)** Busca por especialidade.

## ️ Regras de Negócio
- **Capacidade do Habitat**: Um habitat não pode ultrapassar sua capacidade máxima de animais.
- **Cuidador do Animal**: Cada animal deve ter pelo menos um cuidador associado **(Regra a ser implementada)**.

## Tecnologias Utilizadas
- **Linguagem**: Java 17
- **Framework**: Spring Boot 3.5.5
- **ORM**: Spring Data JPA
- **Banco de dados**: MySQL
- **Build Tool**: Maven
- **Outros**: Lombok (para reduzir o código boilerplate)

## Como Executar o Projeto
Para rodar a aplicação, siga os seguintes passos:

### Pré-requisitos
- **Java Development Kit (JDK) 17** ou superior.
- **Maven** 3.9.11 ou superior.
- **MySQL** instalado e em execução (versão 8.0.41, como configurado).

### Configuração do Banco de Dados
1. Crie um banco de dados MySQL chamado `zoologico`.
2. Configure as credenciais de acesso no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/zoologico?useSSL=false&ServerTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=pass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true