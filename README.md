
# Toll Parking API
This is the Toll Parking Java API coding exercice documentation.
# Installation
## 1. Install Postgres
Required:
- JDK 8 or more [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) (Add it to the system path)
- Maven 10 or more [here](https://maven.apache.org/download.cgi) (Add it to the system path)
- Postgresql database 12 or more [here]([https://www.postgresql.org/download/) (Add it to the system path).
**NB:** Note the admin user (postgres by default) password 
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
To be able to save the billing formulas and to be more dynamic and future proof, i used exp4j to parse the formula that will be saved in the DB, this formula will need to be configured with the variables and values that it will process: 
## Example
For formula ``minimalfare + timeparked * priceperhour`` you will need to set the variables minimalfare and priceperhour as the following in the properties file (property formula):
```
formula=priceperhour=5,minimalfare=5
``` 
**NB:** The timeparked variable should always be in the formula as it will be used to input the time spent in the parking (for now it's hardcoded) 
 ## Application
 Fromula: ``flatprice + timeparked * 0``
 withe formula property: 
```
formula=flatprice=20
``` 
will give us a parking with a flat price, the time spent will not be taken in account for the price calculation.
# Run application
To run the application, open a command line terminal in the project root:
```bash
mvn spring-boot:run
```
# Postman Collection
a postman collection, is available in the root poject folder to test the API manually. To use it download Postman [here](https://www.postman.com/downloads/) and import the json collection file. 

# Toughts and impovements
- Simplify the database dependencies would give some performance improvement if there is a lot of traffic (Multiple parkings managed by the same Backend in an airport for example)
- More validations and checks (for example check plates format depending on countries)