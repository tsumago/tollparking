# Toll Parking API
This is the Toll Parking Java API coding exercice documentation.
# Installation
## 1. Install Postgres
- Download latest version of Postgresql database from [here]([https://www.postgresql.org/download/).
- Note the admin user (postgres by default) password 
- Add postgresql **/bin** folder to the system path
## 2. Create Database
- In command line terminal:
```
psql -U postgres (you will be then prompted to enter the password)
postgres=# CREATE DATABASE tollparking
```
## 3. Clone the repository
Clone the code source repository:
```
cd <YOUR_WORK_FOLDER>
git clone git@github.com:tsumago/tollparking.git
```
## 4. Update database username and password
In the project folder, open **<PROJECT_FOLDER>\src\main\resources\application.properties**
and update the postgres database parameters:
```
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/tollparking
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
``` 
# Billing formula logic

# Run application
To run the application, open a command line terminal in the project root:
```bash
mvn spring-boot:run
```
# Postman Collection
The postman collection, available in the root

# OpenAPI V3 documentation

# Left TODO

# Toughts and impovements
