# 🔍 Java & PostgreSQL Integration Study  

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)](https://openjdk.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16%2B-blue?logo=postgresql)](https://www.postgresql.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

Repositório de estudos práticos sobre integração entre Java e PostgreSQL, com exemplos de CRUD, transações e boas práticas de desenvolvimento.

---

## 🛠️ Tecnologias Utilizadas  
- **Java 17+** (var, try-with-resources, modularização)  
- **PostgreSQL** (configurável via Docker ou instalação direta)  
- **JDBC** (Java Database Connectivity)  
- **Maven** (gerenciamento de dependências)  

## ✨ Funcionalidades Destacadas  
- ✅ **Operações CRUD completas**  
- ✅ **Conexão reutilizável** com pool implícito (`DB.java`)  
- ✅ **Histórico de queries** executadas  
- ✅ **Modelo de domínio** para entidade `Product`  
- ✅ **Prevenção contra SQL injection** com `PreparedStatement`  

---

## 🚀 Como Executar

### Pré-requisitos:
- JDK 17+
- PostgreSQL 15+ (ou Docker)
- Maven 3.9+

### Passos:
1. Clone o repositório:
   ```bash
   git clone https://github.com/manoelSCosta/sales.git
   cd sales
