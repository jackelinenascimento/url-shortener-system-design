# URL Shortener — System Design

## 1. Visão Geral
Um serviço que recebe URLs longas e devolve um identificador curto (`shortId`).  
Funcionalidades principais:
- Criar/encurtar URL
- Redirecionar do `shortId` para a URL original
- Contabilizar cliques
- Suportar expiração de links
- Expor API de criação, consulta e redirecionamento

Objetivos não funcionais:
- Baixa latência no redirecionamento (<50ms)
- Alta disponibilidade (99,9%+)
- Escalabilidade horizontal
- Persistência confiável dos pares `shortId → originalUrl`
- Observabilidade com Datadog (logs, métricas, traces)
- Cache para redirecionamentos frequentes
- Segurança contra links maliciosos

---

## 2. Arquitetura Mínima

- **Ingress / LB / API Gateway**: ponto de entrada HTTP
- **App (URL API)**:
  - **ShorteningService**: gera `shortId` (ou usa `customAlias`) e persiste
  - **RedirectService**: resolve `shortId` e retorna HTTP 301/302
  - **ValidationService**: valida URL (formato/blacklist)
- **Banco**: DynamoDB (tabela `urls`, PK = `shortId`)
- **Cache**: Redis (hot keys; opcional, mas recomendado)
- **Observabilidade**: Datadog APM, métricas custom, logs estruturados
- **Deploy**: Kubernetes (EKS), 3 réplicas, Route53 + ACM + ALB

---

## 3. Modelo de Dados

**Tabela `urls`**

| Campo       | Tipo                 | Descrição                                 |
|-------------|----------------------|-------------------------------------------|
| shortId     | STRING (PK)          | Identificador curto e único da URL        |
| originalUrl | STRING               | URL original completa                     |
| ownerId     | STRING (nullable)    | Identificador do dono/criador do link     |
| createdAt   | NUMBER (epoch ms)    | Data/hora de criação                      |
| expiresAt   | NUMBER (nullable)    | Data/hora de expiração (se existir)       |
| clicks      | NUMBER (default: 0)  | Contador de cliques      

- **clicks**: incremento atômico com `ADD clicks :one` no DynamoDB  
- **expiresAt**: checado no redirect (expirado → 404)

---

## 4. API Mínima

| Método | Endpoint             | Descrição                          | Status principais |
|--------|----------------------|------------------------------------|------------------|
| POST   | /api/shorten         | Cria um novo shortId               | 200, 400, 409    |
| GET    | /{shortId}           | Redireciona para a URL original    | 301/302, 404     |
| GET    | /api/urls/{shortId}  | Consulta metadados do shortId      | 200, 404         |


### POST `/api/shorten`

**Request Body**
```json
{
  "url": "https://...",
  "customAlias": "meu-alias",
  "ttlDays": 30,
  "ownerId": "..."
}
```

**Responses**

 - 200 OK

 ```json
 {
  "shortId":"abc123",
  "shortUrl": "https://short.ly/abc123"
 }
 ```

 - 409 Conflict se customAlias já existir
 - 400 Bad Request se a URL for inválida

---

 ### GET `/{shortId}`

**Responses**

- 301/302 Redirect → Location: originalUrl
- 404 Not Found se não encontrado ou expirado

---

### GET `/api/urls/{shortId}`

Responses

- 200 OK

 ```json
 {
  "shortId":"abc123",
  "originalUrl": "https://...",
  "createdAt": 1726245000000,
  "expiresAt": 1728847000000,
  "clicks": 42
 }
 ```

- 404 Not Found se não encontrado 

---
## 5. Alinhamento com Requisitos
Este design cobre os requisitos descritos em `REQUISITOS.md`, garantindo:
- Funcionais: encurtar, redirecionar, clicks, expiração, custom alias, API e validação.
- Não Funcionais: baixa latência, disponibilidade, escalabilidade, persistência, cache e observabilidade (Datadog).
