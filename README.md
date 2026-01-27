# ⚡ EcoDriver API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)

> **Status do Projeto:** 🚀 Concluído (MVP)

O **EcoDriver** é uma API REST desenvolvida para o gerenciamento inteligente de veículos elétricos. O sistema foca na sustentabilidade e eficiência, permitindo o cadastro de usuários, gestão de frota, aluguel de veículos e cálculo de eficiência energética (km/kWh).

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas do mercado para desenvolvimento Java Backend:

* **Java 17**: Linguagem moderna e performática.
* **Spring Boot 3**: Framework para desenvolvimento ágil.
* **Spring Security + JWT**: Autenticação e autorização robusta (Stateless).
* **PostgreSQL**: Banco de dados relacional para persistência segura.
* **Lombok**: Redução de código boilerplate.
* **JPA / Hibernate**: ORM para manipulação de dados.
* **BCrypt**: Criptografia de senhas.

---

## 📋 Regras de Negócio

Para garantir a integridade e a proposta sustentável do sistema, as seguintes regras foram implementadas:

1.  **Autenticação Obrigatória**: Apenas as rotas de login e cadastro de usuário são públicas. Todas as demais operações exigem um **Token Bearer JWT** válido no cabeçalho da requisição.
2.  **Propriedade do Veículo**: Ao cadastrar um carro, ele é automaticamente vinculado ao usuário logado no contexto de segurança, garantindo a rastreabilidade da frota.
3.  **Cálculo de Eficiência (Eco)**: O sistema avalia a performance do veículo dividindo a quilometragem rodada pelo total de energia consumida (`km / kWh`).
4.  **Fluxo de Aluguel**: A disponibilidade do veículo é controlada pelo status (`DISPONIVEL`, `ALUGADO`, `MANUTENCAO`).
5.  **Tratamento de Erros**: Tentativas de acessar recursos inexistentes ou operações inválidas retornam respostas padronizadas (JSON) com os devidos códigos HTTP (404, 400, etc.).

---

## 🔌 Endpoints da API

Aqui estão as principais rotas para interagir com o sistema.

### 👤 Usuários e Autenticação

| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/usuarios` | **Cadastrar Usuário**: Cria uma nova conta de motorista ou admin. | Público |
| `POST` | `/api/auth/login` | **Login**: Autentica o usuário e retorna o **Token JWT**. | Público |
| `GET` | `/api/usuarios` | **Listar Usuários**: Exibe todos os usuários cadastrados. | 🔐 Admin |

### 🚗 Gestão de Veículos e Aluguel

| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/carros` | **Cadastrar Carro**: Adiciona um novo veículo elétrico à frota (vinculado ao usuário logado). | 🔐 Autenticado |
| `GET` | `/api/carros` | **Listar Carros**: Exibe todos os veículos disponíveis. | 🔐 Autenticado |
| `PUT` | `/api/carros/{id}` | **Alugar / Atualizar**: Atualiza dados do carro ou altera seu status (ex: de `DISPONIVEL` para `ALUGADO`). | 🔐 Autenticado |
| `DEL` | `/api/carros/{id}` | **Remover Carro**: Exclui um veículo do sistema. | 🔐 Autenticado |

### ⚡ Sustentabilidade (Eco)

| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/carros/{id}/eficiencia` | **Verificar Eficiência**: Calcula a performance do carro baseada em km rodados por kWh consumido. | 🔐 Autenticado |

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
* Java 17+ instalado
* Maven instalado
* PostgreSQL rodando

### Passo a Passo
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/esmeraldo-dev/ecodriver.git](https://github.com/esmeraldo-dev/ecodriver.git)
    ```
2.  **Configure o Banco de Dados:**
    Ajuste o arquivo `src/main/resources/application.properties` com suas credenciais do PostgreSQL.
3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
4.  **Teste a API:**
    Utilize o Postman ou Insomnia para fazer as requisições nas rotas indicadas acima.

---

## 👨‍💻 Autor

**Vinícius Esmeraldo**
*Desenvolvedor Backend Java*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/viniciusesmeraldo)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/esmeraldo-dev)

---
*Projeto desenvolvido com foco em Clean Code e Arquitetura Segura.*