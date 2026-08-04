# 🚀 QA Automation Framework

> Framework profissional de automação de testes desenvolvido em **Java 21**, utilizando **Playwright**, **JUnit 5**, **AssertJ**, **SOAP**, **SQL Server** e arquitetura em camadas.

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Playwright](https://img.shields.io/badge/Playwright-Automation-green?style=for-the-badge&logo=playwright)
![JUnit](https://img.shields.io/badge/JUnit-5-red?style=for-the-badge)
![AssertJ](https://img.shields.io/badge/AssertJ-Assertions-yellow?style=for-the-badge)
![SOAP](https://img.shields.io/badge/API-SOAP-purple?style=for-the-badge)
![SQL Server](https://img.shields.io/badge/SQL_Server-Database-red?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven)
![Git](https://img.shields.io/badge/Git-VersionControl-black?style=for-the-badge&logo=git)
![GitHub](https://img.shields.io/badge/GitHub-Repository-black?style=for-the-badge&logo=github)

</p>

---

# 📌 Sobre o Projeto

Este projeto demonstra a construção de um framework profissional de automação de testes utilizando Java e Playwright, aplicando princípios de organização, reutilização de código e arquitetura escalável.

O framework contempla automação Web, integração com serviços SOAP, manipulação de XML, consultas em banco SQL Server, validações de regras de negócio, captura automática de evidências e separação clara das responsabilidades entre as camadas.

Seu objetivo é servir como um framework reutilizável para projetos corporativos de automação de testes.

---

# ✨ Principais Funcionalidades

- ✅ Automação Web com Playwright
- ✅ Arquitetura Page Object Model
- ✅ Flow Layer
- ✅ Integração SOAP
- ✅ XML Builder
- ✅ XML Parser
- ✅ Repository Pattern
- ✅ Banco de Dados SQL Server
- ✅ Captura Automática de Screenshots
- ✅ Configuração por Properties
- ✅ Framework Escalável
- ✅ Estrutura preparada para CI/CD

---

# 🏗 Arquitetura

```
                 TESTS
                    │
      ┌─────────────┴─────────────┐
      ▼                           ▼
 Web Tests                  Unit Tests
      │
      ▼
    Flow
      │
      ▼
    Pages
      │
      ▼
 Playwright
      │
      ▼
 SOAP Services
      │
      ▼
   XML Parser
      │
      ▼
 Repository
      │
      ▼
 SQL Server
```

---

# 📂 Estrutura do Projeto

```
automacao-quitacao
│
├── docs/
├── evidencias/
├── scripts/
├── src/
│   ├── main/
│   │   ├── builder/
│   │   ├── client/
│   │   ├── config/
│   │   ├── database/
│   │   ├── factory/
│   │   ├── flow/
│   │   ├── locators/
│   │   ├── model/
│   │   ├── pages/
│   │   ├── parser/
│   │   ├── repository/
│   │   ├── request/
│   │   ├── response/
│   │   ├── service/
│   │   ├── utils/
│   │   └── validator/
│   │
│   └── resources/
│
└── pom.xml
```

---

# 📸 Evidências

As execuções geram automaticamente screenshots para auxiliar na análise dos testes.

```
evidencias/

✔ Login

✔ Fluxo de Quitação

✔ Screenshots Automáticos
```

---

# ▶ Como Executar

## Executar todos os testes

```bash
mvn clean test
```

## Executar teste Web

```bash
mvn -Dtest=QuitacaoWebTest test
```

## Executar Builder

```bash
mvn -Dtest=SimularQuitacaoBuilderTest test
```

## Executar Service

```bash
mvn -Dtest=SimularQuitacaoServiceTest test
```

---

# 🛠 Tecnologias

- Java 21
- Playwright
- JUnit 5
- AssertJ
- SOAP
- SQL Server
- Maven
- Git
- GitHub
- PowerShell

---

# 🚀 Roadmap

## ✔ Concluído

- Playwright
- SOAP
- XML Builder
- XML Parser
- Repository Pattern
- Validator Layer
- SQL Server
- Captura de Evidências
- GitHub

## ⏳ Próximas Evoluções

- REST Assured
- Jackson
- Faker
- Apache POI
- Allure Reports
- GitHub Actions
- Docker
- Execução Paralela
- Integração Contínua

---

# 👩‍💻 Autora

## Vanessa Lima

QA Automation Engineer em formação, apaixonada por Qualidade de Software, Automação de Testes e Arquitetura de Frameworks.

### Tecnologias

- Java
- Playwright
- Selenium
- SOAP
- SQL Server
- JUnit
- Maven
- Git
- GitHub

### Contato

🔗 LinkedIn

https://www.linkedin.com/in/vanessa-ads/

🔗 GitHub

https://github.com/vanessalimaQA

---

# ⭐ Contribuições

Este projeto foi desenvolvido para fins de estudo, evolução profissional e construção de portfólio em QA Automation.

Contribuições, sugestões e melhorias são sempre bem-vindas.

---

# 📄 Licença

Este projeto está licenciado sob a licença MIT.