# 💸 DoaçãoPay - Sistema de Gestão de Doações via Pix

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Asaas](https://img.shields.io/badge/Asaas-Integration-blue?style=for-the-badge)

> **Uma solução SaaS Fullstack para recebimento, monitoramento e saque de doações em tempo real, integrada via API bancária.**

---

## 📸 Screenshots

| Dashboard Principal | Detalhe das Transações |
|:-------------------------:|:-------------------------:|

---

## 🚀 Sobre o Projeto

O **DoaçãoPay** nasceu da necessidade de gerenciar recebimentos via Pix de forma automatizada para pequenos negócios e criadores de conteúdo. Diferente de enviar apenas a chave Pix, este sistema oferece um **painel financeiro completo** que escuta o banco em tempo real.

O sistema resolve problemas como:
* **Conferência manual:** O sistema atualiza o status para "PAGO" automaticamente via Webhook.
* **Feedback visual:** Dashboard intuitivo construído com React.
* **Segurança:** Blindagem contra erros de API e tratamento de dados sensíveis.

---

## 🛠️ Tecnologias Utilizadas

### Backend (API Robusta)
* **Java 21 & Spring Boot 3:** Estrutura principal da API REST.
* **Spring WebClient:** Para consumo assíncrono e performático da API do Asaas.
* **Jackson:** Manipulação avançada de JSON e formatação de datas (ISO 8601).
* **Lombok:** Redução de código boilerplate.
* **Maven:** Gerenciamento de dependências.

### Frontend (Interface Moderna)
* **React + TypeScript:** Tipagem estática para evitar erros em produção.
* **Tailwind CSS:** Estilização responsiva e moderna.
* **Vite:** Build ultra-rápido.
* **Integração via Fetch API:** Comunicação direta com o Backend Java.

### Infraestrutura & Dados
* **Supabase (PostgreSQL):** Banco de dados relacional na nuvem.
* **Railway:** Deploy contínuo (CI/CD) da aplicação Java.
* **Vercel:** Hospedagem do Frontend.
* **Asaas API:** Gateway de pagamento e gestão de subcontas.

---

## 🧠 Arquitetura e Fluxo de Dados

```mermaid
graph LR
A[Doador] -- Faz Pix --> B(Asaas Banco)
B -- Webhook (JSON) --> C{API Java Spring}
C -- Valida & Salva --> D[(Supabase DB)]
E[Frontend React] -- GET /doacoes --> C
E -- Mostra Dados --> F[Admin Dashboard]
