(Just an old assignment to join some company I cannot longer remember its name. Making this repo public since it is outdated)
# Global Collateral and Margin Management
I decided to develop this project as a microservice because most financial applications are following the Reactive approach, therefore this application must be consumed as a REST webservice using Postman/SoapUI/curl/etc.

## Requirements
- Java 10+
- Port 8080 must be unused

## Architecture
The project was developed having DDD concepts in mind, such as; Entity, Value Object, Repository, Service, etc.

### Services
- Misc: Used to perform various calculations, such as Revenue Yield, Price Earnings Ratio and Inventory Index
- Trading: Used to record transactions and to get the current Volume Weighted Oil Price based on past transactions

Ideally this microservice should be split, one microservice to register/fetch Oil Types and the Trading service as a separate microservice. This could not be done due to time constraints.

### Misc Endpoints
#### Revenue Yield
POST http://localhost:8080/misc/revenue-yield/

The body must include key/value pairs representing "Oil Id : price":

```
{ "AAC" : 1,"REW" : 2,"BWO" : 3,"TIM" : 4,"QFC" : 5  }
```
RevenueYield Output example:
```
{"TIM"=1.9425000000000001, "AAC"=1.0, "QFC"=4.4, "BWO"=5.666666666666667, "REW"=3.5}
```
#### Price Earnings Ratio
POST http://localhost:8080/misc/price-earnings-ratio

The body must include key/value pairs representing "Oil Id : price":

```
{ "AAC" : 1,"REW" : 2,"BWO" : 3,"TIM" : 4,"QFC" : 5  }
```
PriceEarningsRatio Output example:
```
{"TIM"=0.5148005148005148, "AAC"=1.0, "QFC"=0.22727272727272727, "BWO"=0.17647058823529413, "REW"=0.2857142857142857}
```
#### Inventory Index
POST http://localhost:8080/misc/inventory-index

The body must include key/value pairs representing "Oil Id : price":

```
{ "AAC" : 1,"REW" : 2,"BWO" : 3,"TIM" : 4,"QFC" : 5  }
```
InventoryIndex Output example:
```
1.7187719275874789
```
## Trading Endpoints
#### Record Transaction
POST http://localhost:8080/trading/trade

The body must include the following properties:

```
{"timestamp":1578535189975,"oilType":"AAC","transactionType":"BUY","accountId":1234567890,"qty":12,"price":25}
```
Expected output:
```
Transaction registered successfully
```
#### Get Volume Weighted Oil Price
GET http://localhost:8080/trading/vw-oil-price
Example output:
```
25.0
```
## Compiling
```
mvn clean package
```
The above command is enough to compile and run all unit tests
## Running
Navigate to the target folder and run the JAR file:
```
cd target
java -jar .\oil-trading-0.0.1-SNAPSHOT.jar
```
The following output indicates our Spring Boot app is up and running:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.2.RELEASE)

2020-01-09 17:43:05.457  INFO 42008 --- [           main] c.leo.oiltrading.OilTradingApplication   : Starting OilTradingApplication v0.0.1-SNAPSHOT on LEO-ASUS with PID 42008 (C:\Users\blink\src\oil-trading\target\oil-trading-0.0.1-SNAPSHOT.jar started by blink in C:\Users\blink\src\oil-trading\target)
2020-01-09 17:43:05.460  INFO 42008 --- [           main] c.leo.oiltrading.OilTradingApplication   : No active profile set, falling back to default profiles: default
2020-01-09 17:43:06.592  INFO 42008 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-01-09 17:43:06.601  INFO 42008 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-01-09 17:43:06.601  INFO 42008 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.29]
2020-01-09 17:43:06.656  INFO 42008 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-01-09 17:43:06.656  INFO 42008 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1127 ms
2020-01-09 17:43:06.812  INFO 42008 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-01-09 17:43:07.229  INFO 42008 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-01-09 17:43:07.232  INFO 42008 --- [           main] c.leo.oiltrading.OilTradingApplication   : Started OilTradingApplication in 2.125 seconds (JVM running for 2.475)
```
