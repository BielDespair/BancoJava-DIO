# Banco Java DIO 🏦

Este é um projeto de um banco feito em Java, com persistência de dados utilizando SQLite. Parte do Bootcamp Java Cloud Native do Bradesco.

## Funcionalidades

- Abrir conta com CPF, nome e senha
- Acesso com número da conta e senha
- Consultar saldo
- Depositar
- Sacar
- Transferir entre contas
- Alterar senha
- Encerrar conta

O projeto tenta seguir diretrizes da programação orientada a objetos, com camadas de acesso Controller, Service DAO, além de um pacote com exceções personalizadas.
Também foi implementado Hashing de senha com salt, embora não seja 100% seguro, implementado apenas como conceito. O banco de dados depende do driver JBDC para comunicar-se com o SQLite (banco local criado no diretório do projeto).

### ✅ Pré-requisitos

- [Java JDK 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- Git instalado

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/BancoJava-DIO.git
cd BancoJava-DIO
```

## 2. Gerar o arquivo .jar
No CMD (windows)
```bash
mvn clean package
```
## 3. Executar o .jar
```bash
cd target
java -jar BancoJava-DIO-1.0-SNAPSHOT-jar-with-dependencies.jar
```
