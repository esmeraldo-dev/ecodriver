# ⚡ EcoDriver API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)

> **Status do Projeto:** 🚀 Concluído (MVP)

O **EcoDriver** é uma API REST desenvolvida para o gerenciamento inteligente de veículos elétricos. O sistema foca na sustentabilidade e eficiência, permitindo o cadastro de usuários, gestão de frota, aluguel de veículos e cálculo de eficiência energética (km/kWh).

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas do mercado para desenvolvimento Java Backend:

- **Java 17**: Linguagem moderna e performática.
- **Spring Boot 3**: Framework para desenvolvimento ágil.
- **Spring Security + JWT**: Autenticação e autorização robusta (Stateless).
- **PostgreSQL**: Banco de dados relacional para persistência segura.
- **H2 Database**: Banco em memória utilizado nos testes de integração.
- **Lombok**: Redução de código boilerplate.
- **JPA / Hibernate**: ORM para manipulação de dados.
- **BCrypt**: Criptografia de senhas.
- **JUnit 5 & Mockito**: Testes unitários e de integração no padrão AAA.

---

## 📋 Regras de Negócio

Para garantir a integridade e a proposta sustentável do sistema, as seguintes regras foram implementadas:

1. **Autenticação Obrigatória**: Apenas as rotas de login e cadastro de usuário são públicas. Todas as demais operações exigem um **Token Bearer JWT** válido no cabeçalho da requisição.
2. **Propriedade do Veículo**: Ao cadastrar um carro, ele é automaticamente vinculado ao usuário logado no contexto de segurança, garantindo a rastreabilidade da frota.
3. **Cálculo de Eficiência (Eco)**: O sistema avalia a performance do veículo dividindo a quilometragem rodada pelo total de energia consumida (`km / kWh`).
4. **Fluxo de Aluguel**: A disponibilidade do veículo é controlada pelo status (`DISPONIVEL`, `ALUGADO`, `MANUTENCAO`).
5. **Tratamento de Erros**: Tentativas de acessar recursos inexistentes ou operações inválidas retornam respostas padronizadas (JSON) com os devidos códigos HTTP (404, 400, etc.).

---

## 🔌 Endpoints da API

### 👤 Usuários e Autenticação

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/api/usuarios` | **Cadastrar Usuário**: Cria uma nova conta de motorista ou admin. | Público |
| `POST` | `/api/auth/login` | **Login**: Autentica o usuário e retorna o **Token JWT**. | Público |
| `GET` | `/api/usuarios` | **Listar Usuários**: Exibe todos os usuários cadastrados. | 🔐 Admin |

### 🚗 Gestão de Veículos e Aluguel

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `POST` | `/api/carros` | **Cadastrar Carro**: Adiciona um novo veículo elétrico à frota (vinculado ao usuário logado). | 🔐 Autenticado |
| `GET` | `/api/carros` | **Listar Carros**: Exibe todos os veículos disponíveis. | 🔐 Autenticado |
| `PUT` | `/api/carros/{id}` | **Alugar / Atualizar**: Atualiza dados do carro ou altera seu status (ex: de `DISPONIVEL` para `ALUGADO`). | 🔐 Autenticado |
| `DELETE` | `/api/carros/{id}` | **Remover Carro**: Exclui um veículo do sistema. | 🔐 Autenticado |

### ⚡ Sustentabilidade (Eco)

| Método | Endpoint | Descrição | Acesso |
|--------|----------|-----------|--------|
| `GET` | `/api/carros/{id}/eficiencia` | **Verificar Eficiência**: Calcula a performance do carro baseada em km rodados por kWh consumido. | 🔐 Autenticado |

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos

- **Java 17+** instalado
- **Maven** instalado
- **PostgreSQL** rodando

### Passo a Passo

**1. Clone o repositório:**

```bash
git clone https://github.com/esmeraldo-dev/ecodriver.git
cd ecodriver
```

**2. Configure o Banco de Dados:**

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE ecodriver_db;
```

Ajuste o arquivo `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecodriver_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

# JWT Secret (use uma chave longa e segura em produção)
api.security.token.secret=sua_chave_secreta_aqui
```

**3. Execute a aplicação:**

```bash
.\mvnw.cmd spring-boot:run
```

**4. Teste a API:**

Utilize o **Postman** ou **Insomnia** para fazer as requisições nas rotas indicadas acima. Lembre-se de autenticar primeiro via `/api/auth/login` e incluir o Token JWT no header `Authorization: Bearer <token>` nas rotas protegidas.

---

## 🧪 Rodando os Testes

A aplicação utiliza **H2 Database em memória** para os testes de integração, garantindo isolamento total do ambiente de produção e execução rápida.

Execute a suite completa via terminal:

```bash
.\mvnw.cmd test
```

Os testes cobrem os seguintes fluxos, seguindo o padrão **AAA (Arrange, Act, Assert)**:

- Autenticação e geração de Token JWT
- Validação de acesso a rotas protegidas sem token (deve retornar 403)
- Cadastro e vínculo automático de veículo ao usuário logado
- Cálculo de eficiência energética (km/kWh)
- Controle de status do veículo (`DISPONIVEL` → `ALUGADO`)

---

## 🔮 Melhorias Futuras (Roadmap)

- [ ] **Swagger/OpenAPI**: Documentação interativa dos endpoints.
- [ ] **CI/CD**: Pipeline de build e testes via GitHub Actions.
- [ ] **Relatórios de Sustentabilidade**: Dashboard de métricas de consumo e impacto ambiental da frota.
- [ ] **Notificações**: Alertas assíncronos para manutenção preventiva de veículos.

---

## 👨‍💻 Autor

**Vinícius Esmeraldo**  
*Desenvolvedor Backend Java*

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/viniciusesmeraldo)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/esmeraldo-dev)

---

*Projeto desenvolvido com foco em Clean Code e Arquitetura Segura.*
