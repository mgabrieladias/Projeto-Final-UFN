# Choppermed Manager

Choppermed Manager é um sistema de gerenciamento médico que permite o controle de médicos, pacientes e agendamentos. Este projeto é construído em Spring Boot e Thymeleaf.

## Pré-requisitos

Certifique-se de ter instalado em sua máquina:
- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)

## Configuração do Banco de Dados

1. Crie um banco de dados chamado `choppermed`.
2. Abra o arquivo `application.properties` em `src/main/resources` e configure as propriedades do banco de dados com suas credenciais.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/choppermed?useSSL=false
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

