# 🚀 QA Automation Framework

<p align="center">
Framework de automação de testes desenvolvido em <strong>Java 21</strong>, utilizando <strong>Playwright</strong>, <strong>JUnit 5</strong>, <strong>AssertJ</strong>, integração <strong>SOAP</strong> e arquitetura em camadas.
</p>

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange)
![Playwright](https://img.shields.io/badge/Playwright-Automation-green)
![JUnit](https://img.shields.io/badge/JUnit-5-red)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![AssertJ](https://img.shields.io/badge/AssertJ-Assertions-yellow)
![SOAP](https://img.shields.io/badge/API-SOAP-purple)

</p>

---

## 📌 Sobre o projeto

Este projeto demonstra a construção de um framework de automação de testes com foco em qualidade, organização, reutilização de código e separação de responsabilidades.

O framework contempla automação Web, estrutura para comunicação com serviços SOAP, manipulação de XML, validações de regras de negócio, geração de evidências e preparação da camada de acesso a dados.

O objetivo é permitir a evolução de cenários automatizados mantendo o código organizado, escalável e de fácil manutenção.

---

## 🧪 Cenário de negócio

Como domínio de demonstração, o framework utiliza um fluxo de **quitação bancária**, permitindo exercitar diferentes camadas de uma automação.

Exemplos:

- simulação de quitação;
- interação com interface Web;
- envio de requisições SOAP;
- processamento de respostas XML;
- validação de respostas;
- validação de regras de negócio;
- geração de evidências;
- preparação para consultas e validações em banco de dados.

---

## 🛠️ Tecnologias

| Tecnologia | Utilização |
|---|---|
| Java 21 | Linguagem principal |
| Playwright | Automação Web |
| JUnit 5 | Execução e organização dos testes |
| AssertJ | Assertions |
| Maven | Build e gerenciamento de dependências |
| SOAP | Testes de serviços Web |
| XML | Requests e responses SOAP |
| JDBC | Estrutura de acesso ao banco |
| Git | Versionamento |
| PowerShell | Scripts auxiliares |

---

## 🏗️ Arquitetura do Framework

O projeto utiliza separação de responsabilidades através de diferentes camadas.

```text
                   TESTS
                     │
                     ▼
                   FLOW
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
        PAGES                SERVICES
          │                     │
          ▼                     ▼
     PLAYWRIGHT             SOAP CLIENT
                                │
                                ▼
                             BUILDER
                                │
                                ▼
                              PARSER
                                │
                                ▼
                             RESPONSE


                  REPOSITORY
                     │
                     ▼
                  DATABASE
                     │
                     ▼
                    JDBC
```

Essa separação permite alterar uma camada com menor impacto sobre as demais partes do framework.

---

## 🧩 Principais camadas

### Pages

Responsável pela interação com os elementos da interface.

### Flow

Centraliza fluxos de negócio compostos por múltiplas ações.

### Service

Coordena operações relacionadas aos serviços.

### SOAP Client

Responsável pelo envio das requisições SOAP.

### Builder

Realiza a construção dos requests utilizados pelos serviços.

### Parser

Transforma as respostas recebidas em estruturas manipuláveis pelo framework.

### Repository

Centraliza operações relacionadas ao acesso aos dados.

### Validator

Concentra validações e regras utilizadas pelos testes.

---

## 📂 Estrutura do projeto

```text
automacao-quitacao
│
├── evidencias
│
├── scripts
│   ├── create-class.ps1
│   ├── create-test.ps1
│   └── framework.ps1
│
├── src
│   ├── main
│   │   ├── java/com/automation
│   │   │   ├── builder
│   │   │   ├── client
│   │   │   ├── config
│   │   │   ├── database
│   │   │   ├── factory
│   │   │   ├── flow
│   │   │   ├── locators
│   │   │   ├── model
│   │   │   ├── pages
│   │   │   ├── parser
│   │   │   ├── repository
│   │   │   ├── request
│   │   │   ├── response
│   │   │   ├── service
│   │   │   ├── utils
│   │   │   └── validator
│   │   │
│   │   └── resources
│   │
│   └── test
│       ├── java/com/automation
│       └── resources
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🌐 Automação Web

A camada Web utiliza **Playwright** e segue conceitos de Page Object.

Exemplo de responsabilidade:

```java
public void simularQuitacao(
        String idConta,
        String valorDivida
) {
    informarIdConta(idConta);
    informarValorDivida(valorDivida);
    clicarEmSimular();
}
```

O teste não precisa conhecer diretamente os seletores utilizados pela página.

---

## 🔌 Automação SOAP

O framework possui estrutura separada para trabalhar com serviços SOAP.

Fluxo:

```text
Test
 ↓
Service
 ↓
Builder
 ↓
SOAP Client
 ↓
Endpoint
 ↓
Response
 ↓
Parser
 ↓
Validator
```

Isso reduz o acoplamento entre os testes e a implementação da comunicação com o serviço.

---

## 🗄️ Banco de dados

O projeto possui uma camada preparada para acesso a banco de dados através de JDBC.

A arquitetura permite manter consultas e operações de dados fora das classes de teste.

```text
Test
 ↓
Repository
 ↓
DatabaseExecutor
 ↓
DatabaseConnection
 ↓
Database
```

A integração com ambiente real e os cenários completos de validação em banco fazem parte da evolução do projeto.

---

## 📸 Evidências

O framework possui suporte à captura de screenshots durante a execução dos testes Web.

### Fluxo de quitação

![Fluxo de Quitação](evidencias/deveRealizarFluxoDeQuitacaoComVariosContratos.png)

### Login

![Login](evidencias/deveRealizarLoginComSucesso.png)

---

## ▶️ Executando o projeto

### Executar todos os testes

```bash
mvn clean test
```

### Executar teste Web

```bash
mvn "-Dtest=QuitacaoWebTest" test
```

### Executar teste do Builder

```bash
mvn "-Dtest=SimularQuitacaoBuilderTest" test
```

### Executar teste do Service

```bash
mvn "-Dtest=SimularQuitacaoServiceTest" test
```

---

## ⚙️ Configuração

Exemplo de configuração do navegador:

```properties
browser=chrome
headless=false

viewport.width=1920
viewport.height=1080
```

Para execução sem interface:

```properties
headless=true
```

---

## 🛠️ Scripts auxiliares

O projeto possui scripts PowerShell para auxiliar na criação e organização de classes.

Exemplo:

```powershell
.\scripts\framework.ps1 page LoginPage
```

Também existem scripts específicos para criação de classes e testes.

---

## 🗺️ Roadmap

### Implementado

- [x] Java 21
- [x] Maven
- [x] Playwright
- [x] JUnit 5
- [x] AssertJ
- [x] Page Object
- [x] Flow Layer
- [x] Builder
- [x] SOAP Client
- [x] Parser
- [x] Repository
- [x] Validator
- [x] Screenshots
- [x] Scripts PowerShell
- [x] Git
- [x] GitHub

### Próximas evoluções

- [ ] Rest Assured
- [ ] Testes REST
- [ ] Jackson
- [ ] Faker
- [ ] Apache POI
- [ ] Lombok
- [ ] Allure Report completo
- [ ] Integração completa com SQL Server
- [ ] GitHub Actions
- [ ] Pipeline CI/CD
- [ ] Execução paralela
- [ ] Docker

---

## 🎯 Boas práticas aplicadas

- Page Object Model
- Separação de responsabilidades
- Reutilização de código
- Configuração externa
- Builder Pattern
- Repository Pattern
- Assertions com AssertJ
- Testes independentes
- Evidências automatizadas
- Versionamento com Git

---

## 👩‍💻 Autora

**Vanessa Lima**

Estudante de Análise e Desenvolvimento de Sistemas com foco em **Quality Assurance e Automação de Testes**.

Tecnologias em estudo e aplicação:

**Java • Playwright • Selenium • APIs • SOAP • SQL • Maven • Git**

---

⭐ Este projeto está em evolução contínua como parte do desenvolvimento de conhecimentos em QA Automation e Engenharia de Qualidade.