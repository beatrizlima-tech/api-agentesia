# 🤖 API Agentes IA

![Java](https://img.shields.io/badge/Java-25-red?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-green?style=for-the-badge\&logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-green?style=for-the-badge\&logo=mongodb)
![OpenAI](https://img.shields.io/badge/OpenAI-Responses%20API-black?style=for-the-badge\&logo=openai)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge\&logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge\&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge\&logo=apachemaven)

API REST desenvolvida com Java e Spring Boot para geração de análises financeiras por meio da integração com a OpenAI.

A aplicação recebe uma massa de dados financeiros, solicita a geração de um relatório estruturado à inteligência artificial e armazena o histórico da análise no MongoDB.

---

## 📌 Sobre o projeto

A API Agentes IA implementa um agente especializado em finanças pessoais.

O agente analisa dados relacionados a:

* receitas e despesas;
* contas pagas e pendentes;
* valores recebidos e a receber;
* fluxo financeiro;
* categorias de gastos;
* saldo do período;
* pontos positivos;
* pontos de atenção;
* recomendações financeiras;
* plano de melhoria.

A análise é produzida exclusivamente com base nos dados enviados na requisição, seguindo instruções que impedem a criação de informações ausentes ou o uso de comandos encontrados dentro da própria massa de dados.

> A análise gerada possui caráter informativo e não substitui a orientação de um profissional financeiro.

---

## ✨ Funcionalidades

* Geração de relatórios financeiros com a OpenAI;
* Integração com a Responses API;
* Instruções personalizadas para o agente financeiro;
* Proteção contra instruções maliciosas presentes nos dados analisados;
* Validação de dados vazios;
* Limitação do tamanho da entrada;
* Limitação da quantidade máxima de tokens da resposta;
* Tratamento de respostas vazias, incompletas ou recusadas;
* Tratamento de erros retornados pela OpenAI;
* Persistência do histórico de relatórios no MongoDB;
* Documentação dos endpoints com Swagger/OpenAPI;
* Configuração global de CORS para integração com o frontend;
* Ambiente Docker com MongoDB, Mongo Express e MailHog;
* Configuração externa da chave da OpenAI por variável de ambiente.

---

## 🔄 Fluxo da aplicação

```text
Cliente
   │
   ▼
RelatorioController
   │
   ▼
RelatorioService
   │
   ├──────────────► OpenAiComponent
   │                     │
   │                     ▼
   │               OpenAI Responses API
   │                     │
   │                     ▼
   │               Análise financeira
   │
   ├──────────────► HistoricoRelatorioRepository
   │                     │
   │                     ▼
   │                  MongoDB
   │
   ▼
RelatorioResponse
```

### Etapas do processamento

1. O cliente envia os dados financeiros para a API;
2. O controller encaminha a requisição para a camada de serviço;
3. O serviço envia os dados para o componente de integração com a OpenAI;
4. A OpenAI gera uma análise financeira estruturada;
5. Os dados enviados e o resultado são armazenados no MongoDB;
6. A análise é devolvida ao cliente.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia           | Utilização                               |
| -------------------- | ---------------------------------------- |
| Java                 | Linguagem principal                      |
| Spring Boot          | Desenvolvimento da API                   |
| Spring Web           | Criação dos endpoints REST               |
| Spring MVC           | Configuração global de CORS              |
| RestClient           | Comunicação com a OpenAI                 |
| Spring Data MongoDB  | Persistência dos relatórios              |
| MongoDB              | Banco de dados NoSQL                     |
| Jackson              | Leitura e processamento de JSON          |
| Lombok               | Redução de código repetitivo             |
| Swagger/OpenAPI      | Documentação da API                      |
| Maven                | Gerenciamento de dependências            |
| Docker Compose       | Orquestração do ambiente                 |
| Mongo Express        | Administração visual do MongoDB          |
| MailHog              | Ambiente preparado para testes de e-mail |
| OpenAI Responses API | Geração das análises financeiras         |

---

## 📂 Estrutura do projeto

```text
src
└── main
    ├── java
    │   └── br.com.cotiinformatica.api_agentesia
    │       ├── components
    │       │   └── OpenAiComponent.java
    │       ├── configurations
    │       │   ├── CorsConfiguration.java
    │       │   ├── ObjectMapperConfiguration.java
    │       │   ├── RestClientConfiguration.java
    │       │   └── SwaggerConfiguration.java
    │       ├── controllers
    │       │   └── RelatorioController.java
    │       ├── dtos
    │       │   ├── RelatorioRequest.java
    │       │   └── RelatorioResponse.java
    │       ├── entities
    │       │   └── HistoricoRelatorio.java
    │       ├── repositories
    │       │   └── HistoricoRelatorioRepository.java
    │       └── services
    │           └── RelatorioService.java
    └── resources
        └── application.yaml
```

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, tenha instalado:

* Java;
* Maven ou Maven Wrapper;
* Docker Desktop;
* Git;
* uma chave válida da API da OpenAI.

---

## 🔐 Configuração da OpenAI

A chave da OpenAI não deve ser escrita diretamente no código-fonte.

O projeto utiliza a variável de ambiente:

```text
OPENAI_API_KEY
```

A configuração correspondente no `application.yaml` deve permanecer desta forma:

```yaml
openai:
  apikey: ${OPENAI_API_KEY}
  model: ${OPENAI_MODEL:gpt-4o-mini}
```

### Configuração no IntelliJ IDEA

Acesse:

```text
Run → Edit Configurations → Environment variables
```

Cadastre:

```text
OPENAI_API_KEY=sua_chave_da_openai
```

Opcionalmente, o modelo também pode ser alterado pela variável:

```text
OPENAI_MODEL
```

---

## 🐳 Executando a infraestrutura com Docker

Na raiz do projeto, execute:

```bash
docker compose up -d
```

Para verificar os containers:

```bash
docker compose ps
```

O ambiente Docker inicia os seguintes serviços:

| Serviço                  | Endereço                |
| ------------------------ | ----------------------- |
| MongoDB                  | `localhost:27018`       |
| Mongo Express            | `http://localhost:5058` |
| MailHog                  | `http://localhost:8025` |
| Servidor SMTP do MailHog | `localhost:1025`        |

### Acesso ao Mongo Express

```text
Usuário: coti
Senha: coti
```

Para encerrar os containers:

```bash
docker compose down
```

Para encerrar e remover os volumes:

```bash
docker compose down -v
```

> O comando com `-v` também remove os dados armazenados nos volumes do MongoDB.

---

## 🌐 Configuração de CORS

A aplicação possui uma configuração global de CORS preparada para permitir a comunicação com o projeto frontend.

Origem autorizada:

```text
http://localhost:8083
```

A configuração é aplicada a todos os endpoints da API:

```text
/**
```

Métodos HTTP permitidos:

* `GET`;
* `POST`;
* `PUT`;
* `DELETE`.

Também são permitidos todos os cabeçalhos enviados nas requisições.

A configuração está localizada na classe:

```text
CorsConfiguration.java
```

---

## ▶️ Executando a API

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Também é possível executar a classe principal diretamente pelo IntelliJ IDEA.

A aplicação ficará disponível em:

```text
http://localhost:8084
```

---

## 📚 Documentação Swagger

Com a aplicação em execução, acesse:

```text
http://localhost:8084/swagger-ui/index.html
```

A especificação OpenAPI pode ser consultada em:

```text
http://localhost:8084/v3/api-docs
```

---

## 📡 Endpoint

### Gerar relatório financeiro

```http
POST /api/relatorios
```

### Corpo da requisição

```json
{
  "usuario": "beatriz",
  "dataInicio": "2026-07-01",
  "dataFim": "2026-07-31",
  "dadosAnalise": "Receita de R$ 5.000,00. Aluguel de R$ 1.200,00. Mercado de R$ 750,00. Energia de R$ 180,00."
}
```

### Campos da requisição

| Campo          | Tipo      | Descrição                                       |
| -------------- | --------- | ----------------------------------------------- |
| `usuario`      | String    | Identificação do usuário                        |
| `dataInicio`   | LocalDate | Data inicial do período                         |
| `dataFim`      | LocalDate | Data final do período                           |
| `dadosAnalise` | String    | Massa de dados financeiros enviada para análise |

As datas devem utilizar o formato ISO:

```text
yyyy-MM-dd
```

### Resposta de sucesso

**Status HTTP:**

```text
201 Created
```

**Exemplo:**

```json
{
  "resultadoAnalise": "## 1. Resumo financeiro\n\nA análise financeira foi gerada com base nos dados fornecidos..."
}
```

---

## 🧠 Comportamento do agente financeiro

O agente é instruído a:

* responder sempre em português do Brasil;
* utilizar apenas os dados fornecidos;
* não inventar movimentações, datas, categorias ou valores;
* diferenciar receitas, despesas e contas pendentes;
* identificar informações ausentes ou inconsistentes;
* apresentar valores em reais no formato brasileiro;
* não recomendar produtos financeiros específicos;
* não garantir retornos financeiros;
* apresentar recomendações relacionadas aos dados analisados;
* informar que o relatório possui caráter informativo.

O relatório é organizado nas seguintes seções:

1. Resumo financeiro;
2. Distribuição das receitas e despesas;
3. Fluxo financeiro;
4. Contas pendentes;
5. Pontos positivos;
6. Pontos de atenção;
7. Recomendações;
8. Plano de melhoria;
9. Informações ausentes;
10. Conclusão.

---

## 🛡️ Medidas de segurança da integração

O componente de integração aplica algumas medidas para tornar o processamento mais seguro:

* os dados são delimitados pelas marcações `<dados-financeiros>`;
* instruções presentes dentro dos dados devem ser ignoradas;
* o conteúdo recebido é tratado exclusivamente como informação financeira;
* a resposta não é armazenada pela OpenAI por meio da propriedade `store: false`;
* entradas vazias são rejeitadas;
* o tamanho máximo da entrada é de `200.000` caracteres;
* a resposta possui limite máximo de `3.000` tokens;
* recusas e respostas incompletas são identificadas e tratadas.

---

## 🗄️ Persistência no MongoDB

Cada relatório gerado cria um documento na coleção:

```text
historico_relatorio
```

Estrutura armazenada:

```json
{
  "_id": "identificador-gerado-pelo-mongodb",
  "data_hora": "2026-07-17T12:00:00",
  "usuario": "beatriz",
  "dados_analise": "Dados financeiros enviados pelo usuário",
  "resultado_analise": "Análise financeira gerada pela OpenAI"
}
```

---

## ⚠️ Estado atual do projeto

Nesta versão:

* os campos `dataInicio` e `dataFim` fazem parte do DTO de entrada, mas ainda não são enviados separadamente para a OpenAI;
* o período ainda não é armazenado na entidade de histórico;
* o MailHog está configurado no Docker, mas o envio do relatório por e-mail ainda não foi implementado;
* a configuração do Swagger possui suporte preparado para Bearer Token, mas a autenticação JWT ainda não foi implementada;
* ainda não existe endpoint para consultar o histórico de relatórios;
* ainda não existe tratamento global de exceções.

---

## 🚀 Próximas evoluções

* Implementar validações com Jakarta Validation;
* Validar se a data inicial é anterior à data final;
* Incluir o período no conteúdo enviado para a OpenAI;
* Armazenar as datas inicial e final no MongoDB;
* Implementar consulta ao histórico de relatórios;
* Implementar envio do relatório por e-mail;
* Utilizar o MailHog nos testes de envio;
* Implementar autenticação e autorização com JWT;
* Criar tratamento global de exceções;
* Adicionar testes unitários e testes de integração;
* Documentar exemplos de erros no Swagger;
* Criar container Docker para a própria API.

---

## 👩‍💻 Autora

Desenvolvido por **Beatriz Lima**.

GitHub: [beatrizlima-tech](https://github.com/beatrizlima-tech)
