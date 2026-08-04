# 🚀 QA Automation Framework

Framework de automação desenvolvido em **Java** utilizando **Playwright**, seguindo boas práticas de arquitetura para automação Web, integração com serviços SOAP e preparação para validações em banco de dados.

---

# 📌 Objetivo

Este projeto foi desenvolvido com o objetivo de demonstrar a construção de um framework de automação profissional, aplicando princípios de organização, reutilização de código e separação de responsabilidades.

---

# 🛠 Tecnologias

- Java 21
- Maven
- Playwright
- JUnit 5
- AssertJ
- SOAP Web Services
- SQL Server (estrutura preparada)

---

# 🏗 Arquitetura

O projeto foi organizado utilizando uma arquitetura em camadas.

```
Tests
   │
   ▼
Flow
   │
   ▼
Pages
   │
   ▼
Services
   │
   ▼
SOAP Client
   │
   ▼
Parser
   │
   ▼
Repository
   │
   ▼
Database
```

---

# 📂 Estrutura

```
src
├── main
│   ├── builder
│   ├── client
│   ├── config
│   ├── core
│   ├── database
│   ├── factory
│   ├── flow
│   ├── locators
│   ├── model
│   ├── pages
│   ├── parser
│   ├── repository
│   ├── request
│   ├── response
│   ├── service
│   ├── utils
│   └── validator
│
└── test
    ├── parser
    ├── service
    ├── validator
    └── web
```

---

# ✅ Funcionalidades

- Automação Web utilizando Playwright
- Page Object Model
- Flow Layer
- Integração com SOAP
- Parser XML
- Builder Pattern
- Repository Pattern
- Validações automatizadas
- Captura de evidências
- Framework preparado para integração com banco de dados

---

# ▶ Como executar

```bash
mvn clean test
```

Ou executar um teste específico:

```bash
mvn "-Dtest=QuitacaoWebTest" test
```

---

# 📸 Evidências

O framework realiza captura automática de screenshots após a execução dos testes.

---

# 🚀 Próximas evoluções

- Relatórios Allure
- Integração completa com SQL Server
- Pipeline CI/CD
- Execução paralela
- Testes de API REST
- Docker

---

# 👨‍💻 Autor

Projeto desenvolvido para estudo e evolução em QA Automation utilizando boas práticas de arquitetura de testes.