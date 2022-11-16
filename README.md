# Drone communication service

<p>This project provides next endpoints:

- registering a drone;
- check drone battery level for a given drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
</p>
<p>
Application initializes default drones and medications in database with first start.
</p>

## Required software
* **java 11**
* **maven**
* **docker (or instance of PostgreSQL)**

## Building application
You can build a project with tests:
```bash
./mvnw clean install
```
or without tests:
```bash
./mvnw clean install -Dmaven.test.skip=true
```

## Run application

### Prepare database
1) If you already have instance of PostgreSQL just create database with name **drone_communication** set up next environmental variables:
* **datasourceUrl** - database jdbc url
* **datasourceUsername** - db user 
* **datasourcePassword** - db user's password

you can set each environmental variable with:
```bash
export datasourceUrl=jdbc:postgresql://localhost:5432/drone_communication
```
2) **OR** if you don't have installed PostgreSQL instance just run next command from the root folder of application:
```bash
 docker-compose -f ./docker/docker-compose.yaml up -d
```

### Start spring boot application
After preparing database run command from the root folder of our application:
```bash
mvn spring-boot:run
```
swagger available at: http://localhost:8080/swagger-ui.html