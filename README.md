# Fitness-Zombies Backend

The robust REST API backend for the Fitness-Zombies mobile application, built using Spring Boot and Java 26.

---

## 🛠️ Tech Stack & Prerequisites

* **Java:** 26 (Oracle JDK / Eclipse Temurin)
* **Framework:** Spring Boot 3.x / 4.x
* **Build Tool:** Gradle (using wrapper)
* **Database:** PostgreSQL (Supabase)

---

## 🔑 Environment Configuration

To keep sensitive credentials secure, this project uses system environment variables instead of hardcoded values in `application.properties`. 

Before running the application locally, you **must** configure the following environment variables on your machine:

| Variable Name | Description | Example / Value |
| :--- | :--- | :--- |
| `DB_URL` | JDBC connection string to the database | `jdbc:postgresql://<host>:<port>/<dbname>` |
| `DB_USER` | Database access username | `postgres` |
| `DB_PASSWORD`| Database access password | `your_secure_password` |

### How to set them locally:

#### Windows (PowerShell)
```powershell
$env:DB_URL="jdbc:postgresql://your-database-url:5432/postgres"
$env:DB_USER="your_user"
$env:DB_PASSWORD="your_password"