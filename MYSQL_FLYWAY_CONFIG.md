# Configuração MySQL e Flyway - PDV

Este documento descreve como configurar e validar a integração do PDV com MySQL e Flyway.

## Pré-requisitos

- MySQL 8+ instalado e rodando
- Maven 3.8+
- Java 21

## Configuração do Banco de Dados

### 1. Criar o Database

```bash
# Conectar ao MySQL
mysql -u root -p

# Criar o database (se não existir)
CREATE DATABASE pdvtrack CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Sair
exit
```

### 2. Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto (copie de `.env.example`):

```bash
cp .env.example .env
```

Edite o arquivo `.env` com suas credenciais MySQL:

```properties
DB_URL=jdbc:mysql://localhost:3306/pdvtrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=root
DB_PASSWORD=sua_senha_aqui
```

### 3. Configuração da Aplicação

O arquivo `src/main/resources/application.properties` contém as configurações principais:

- **spring.datasource.url**: URL de conexão do MySQL
- **spring.datasource.username**: Usuário do banco
- **spring.datasource.password**: Senha do banco
- **spring.flyway.enabled**: Habilita migrations automáticas
- **spring.flyway.locations**: Diretório das migrations (`classpath:db/migration`)

## Executando a Aplicação

### Desenvolvimento Local

```bash
# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

A aplicação irá:
1. Conectar ao MySQL
2. Executar as migrations Flyway automaticamente
3. Criar as tabelas necessárias

### Com Variáveis de Ambiente

```bash
export DB_URL='jdbc:mysql://localhost:3306/pdvtrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC'
export DB_USERNAME=root
export DB_PASSWORD=sua_senha
mvn spring-boot:run
```

## Estrutura das Migrations

As migrations Flyway estão localizadas em:

```
src/main/resources/db/migration/
├── V1__create_pdv_schema.sql
├── V2__... (próximas migrations)
└── ...
```

**Convenção de nomenclatura:**
- `V{numero}__{descricao}.sql` - Migrations de versão
- `U{numero}__{descricao}.sql` - Undo migrations (se habilitadas)

## Monitoramento

### Health Check

A aplicação expõe um endpoint de health:

```bash
curl http://localhost:8080/actuator/health
```

Resposta esperada:

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "MySQL",
        "validationQuery": "isValid()"
      }
    }
  }
}
```

### Logs

Para DEBUG de conexão com banco de dados:

1. Edite `application.properties`:
```properties
logging.level.com.zaxxer.hikari=DEBUG
logging.level.org.hibernate.SQL=DEBUG
```

2. Ou defina variável de ambiente:
```bash
export LOGGING_LEVEL_COM_ZAXXER_HIKARI=DEBUG
```

## Troubleshooting

### Erro: "Connection refused"

- Verifique se MySQL está rodando: `mysqladmin ping -h localhost -P 3306`
- Verifique credenciais no arquivo `.env`
- Verifique URL da conexão

### Erro: "Unknown database"

- Criar o database: `mysql -u root -p -e "CREATE DATABASE pdvtrack;"`

### Erro: "Public Key Retrieval is not allowed"

- Garanta `allowPublicKeyRetrieval=true` na URL de conexão (auth `caching_sha2_password`)

### Erro: Flyway Migration Failed

- Verifique o arquivo de migration em `db/migration/`
- Limpe migrations com: `mvn flyway:clean` (apenas em dev!)
- Verifique logs da aplicação

### Timeout de Conexão

Aumente o timeout no arquivo `application.properties`:
```properties
spring.datasource.hikari.connection-timeout=60000
```

## Diferenças com o RETAGUARDA (GasStation)

| Aspecto | RETAGUARDA (MySQL) | PDV (MySQL) |
|--------|-------------------|-----------------|
| Banco de Dados | MySQL 8+ | MySQL 8+ |
| Driver | com.mysql.cj.jdbc.Driver | com.mysql.cj.jdbc.Driver |
| Dialect | MySQLDialect | MySQLDialect |
| UUID | CHAR(36) | CHAR(36) DEFAULT (UUID()) |
| Timestamps | DATETIME/CURRENT_TIMESTAMP | DATETIME/CURRENT_TIMESTAMP |
| Multi-tenant | Sim (coluna tenant_id) | Não (sem contexto de tenant) |
| Flyway | flyway-core + flyway-mysql | flyway-core + flyway-mysql |

## Próximos Passos

1. ✅ Configurar MySQL e credenciais
2. ✅ Executar a aplicação (Flyway rodará automaticamente)
3. ⏳ Adicionar novas migrations conforme necessário
4. ⏳ Configurar replicação de dados do RETAGUARDA se necessário

## Referências

- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Flyway Documentation](https://flywaydb.org/documentation)
- [Spring Boot Database Configuration](https://spring.io/projects/spring-data-jpa)
