# Requisitos - URL Shortener

Este documento descreve os requisitos funcionais e não funcionais do sistema de encurtamento de URLs.

---

## ✅ Requisitos Funcionais

1. O sistema deve permitir **encurtar URLs longas** em identificadores curtos (ex: `abc123`).
2. O sistema deve **redirecionar** para a URL original quando um usuário acessa o link encurtado.
3. O sistema deve garantir que **cada identificador curto seja único**.
4. O sistema deve registrar a **data/hora de criação** de cada link.
5. O sistema deve permitir contabilizar o número de **cliques** em cada link.
6. O sistema deve oferecer a possibilidade de **expiração** dos links após um tempo definido.
7. O sistema deve suportar **customização de links curtos** (quando permitido pelo usuário).
8. O sistema deve expor uma **API** para criação, consulta e redirecionamento de URLs.
9. O sistema deve validar se a **URL original é válida** antes de encurtá-la.
10. O sistema deve retornar erro adequado quando o link encurtado **não existir**.

---

## ⚙️ Requisitos Não Funcionais

1. O sistema deve ter **baixa latência** no redirecionamento (ideal <50ms).
2. O sistema deve ser **altamente disponível** (SLA mínimo 99,9%).
3. O sistema deve ser **escalável horizontalmente**, suportando picos de tráfego.
4. O sistema deve armazenar os dados de forma **persistente e confiável**.
5. O sistema deve suportar um volume de pelo menos **1 bilhão de URLs** ao longo do tempo.
6. O sistema deve suportar **milhares de requisições por segundo** em redirecionamentos.
7. O sistema deve incluir **observabilidade**: logs, métricas e monitoramento.
8. O sistema deve ter mecanismos de **cache** para acelerar redirecionamentos frequentes.
9. O sistema deve incluir mecanismos de **segurança** contra links maliciosos (ex.: phishing).
10. O sistema deve ser **multirregional**, garantindo acesso rápido para usuários globais.

---

✨ Esses requisitos podem evoluir conforme os **trade-offs** e decisões de arquitetura forem definidos.
