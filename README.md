# URL Shortener — System Design & Implementation

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-8.8-02303A?logo=gradle)
![AWS](https://img.shields.io/badge/AWS-Cloud-orange?logo=amazonaws)
![Kubernetes](https://img.shields.io/badge/Kubernetes-EKS-326CE5?logo=kubernetes)
![Datadog](https://img.shields.io/badge/Observability-Datadog-632CA6?logo=datadog)

---

Este projeto é um exercício de **System Design** e implementação de um serviço de encurtamento de URLs, construído em **Kotlin + Spring Boot** e pensado para rodar em **AWS + Kubernetes**.

---

## 📌 Visão Geral
O sistema permite:
- Encurtar URLs longas em identificadores curtos (`shortId`)
- Redirecionar do `shortId` para a URL original
- Contabilizar cliques
- Definir expiração de links
- Expor uma API mínima para criação, consulta e redirecionamento

---

## 🚀 Tecnologias
- **Kotlin 1.9 + Spring Boot 3.3**
- **Gradle** para build
- **DynamoDB** (armazenamento principal)
- **Redis** (cache para redirecionamentos frequentes)
- **Kubernetes (EKS)** para deploy
- **Datadog** para observabilidade (logs, métricas, traces)

---

## 📂 Estrutura
- `docs/REQUISITOS.md` → requisitos funcionais e não funcionais
- `docs/design.md` → arquitetura mínima, modelo de dados e API
- `src/` → código fonte (MVP em Kotlin + Spring Boot)

---

## 🔑 Endpoints MVP
- `POST /api/shorten` → cria um shortId
- `GET /{shortId}` → redireciona e contabiliza clique
- `GET /api/urls/{shortId}` → consulta metadados do link

---

## ▶️ Como rodar localmente
1. Clone o repositório:
   ```bash
   git clone git@github.com:jackelinenascimento/url-shortener-system-design.git
   cd url-shortener-system-design
   ```

2. Rode a aplicação:
   ```bash
   ./gradlew bootRun
   ```

3. Teste:
   ```bash
   curl -X POST http://localhost:8080/api/shorten -H 'Content-Type: application/json' -d '{"url":"https://github.com/jackelinenascimento"}'
   ```
   
---

## ✨ Próximos passos

- Implementar repositório in-memory
- Migrar para DynamoDB
- Adicionar cache Redis
- Deploy em Kubernetes (EKS)
- Observabilidade com Datadog

---

## 👩‍💻 Autora

Jackeline Nascimento