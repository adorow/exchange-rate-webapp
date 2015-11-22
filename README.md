# exchange-rate-webapp

## intro

This is a small webapp, based on [Spring Boot](http://projects.spring.io/spring-boot/) and Spring MVC.
It maps it's entities with JPA into an in memory [H2 database](http://www.h2database.com/).
[Unirest](http://unirest.io/) as Rest client, used to integrate with [open exchange rates](https://openexchangerates.org/).
Test run using the [JUnit](http://junit.org/) framework.
[Gradle](http://gradle.org/) to automate the build process.

## to see the code running online
Just go to [this](https://obscure-fortress-4251.herokuapp.com/) link. Courtesy of [Heroku](https://www.heroku.com/).

## to run it locally
On Windows:
```
git clone https://github.com/adorow/exchange-rate-webapp.git
cd exchange-rate-webapp
gradlew.bat build && java -jar build\libs\currency-exchange-webapp-0.1.0.jar
```

On Linux:
```
git clone https://github.com/adorow/exchange-rate-webapp.git
cd exchange-rate-webapp
./gradlew build && java -jar build/libs/currency-exchange-webapp-0.1.0.jar
```