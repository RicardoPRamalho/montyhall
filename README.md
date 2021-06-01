# Monty Hall Problem

### Assignment Statement:

The assignment is to write a program that finds the best odds of getting a prize on getting a new box or keeping the one you've received. The show rules are

a) The player picks one box

b) The host picks one empty box.

c) Without opening its box, the player must decide if they want to keep it or get a new one.

## Getting Started

Instructions to run a local copy for development and tests purposes. 
Deployment notes show how to run the application as standalone.

### Prerequisites

All dependencies are mentioned in pom.xml

```
Install java 11
Install maven
```

### Installing

To build the project

```
./mvnw clean install
```

And to Run the application.

```
./mvnw -q spring-boot:run -Dspring-boot.run.arguments="INTERACTIONS,DOORS,PRIZE"
```
INTERACTIONS, DOORS and PRIZE should be positive integers. DOORS must be higher than PRIZE

Sample Output
```
$ ./mvnw -q spring-boot:run -Dspring-boot.run.arguments="10,3,1"

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.0)

2021-06-01 05:01:54.499  INFO 11024 --- [           main] c.t.montyhall.MontyhallApplication       : Starting MontyhallApplication using Java 12.0.1 on TD-CBKKMQ2 with PID 11024 (C:\Users\ricpe\dev\personal\montyhall\target\classes started by ricpe in C:\Users\ricpe\dev\personal\montyhall)
2021-06-01 05:01:54.502  INFO 11024 --- [           main] c.t.montyhall.MontyhallApplication       : No active profile set, falling back to default profiles: default
2021-06-01 05:01:56.220  INFO 11024 --- [           main] c.t.montyhall.MontyhallApplication       : Started MontyhallApplication in 2.551 seconds (JVM running for 3.325)
2021-06-01 05:01:56.223  INFO 11024 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state LivenessState changed to CORRECT
2021-06-01 05:01:56.240  INFO 11024 --- [           main] com.trustly.montyhall.gameshow.GameShow  : Switching wins 8 times.
2021-06-01 05:01:56.241  INFO 11024 --- [           main] com.trustly.montyhall.gameshow.GameShow  : Staying wins 2 times.
2021-06-01 05:01:56.244  INFO 11024 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state ReadinessState changed to ACCEPTING_TRAFFIC
```
## Running the tests

To run the tests

```
./mvnw test
```

## Deployment

Execute below command to get an executable jar file under project *{projectRoot}/target/* folder

```
./mvnw clean install
```

Execute below command to execute as java application from *{projectRoot}/target* location 

```
java -jar montyhall-0.0.1-SNAPSHOT.jar INTERACTIONS,DOORS,PRIZE
```
INTERACTIONS, DOORS and PRIZE should be positive integers. DOORS must be higher than PRIZE

Sample output

```
$ java -jar montyhall-0.0.1-SNAPSHOT.jar 10,3,1

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.0)

2021-06-01 05:08:36.051  INFO 18768 --- [           main] c.t.montyhall.MontyhallApplication       : Starting MontyhallApplication v0.0.1-SNAPSHOT using Java 12.0.1 on TD-CBKKMQ2 with PID 18768 (C:\Users\ricpe\dev\personal\montyhall\target\montyhall-0.0.1-SNAPSHOT.jar started by ricpe in C:\Users\ricpe\dev\personal\montyhall\target)
2021-06-01 05:08:36.062  INFO 18768 --- [           main] c.t.montyhall.MontyhallApplication       : No active profile set, falling back to default profiles: default
2021-06-01 05:08:42.703  INFO 18768 --- [           main] c.t.montyhall.MontyhallApplication       : Started MontyhallApplication in 9.717 seconds (JVM running for 12.062)
2021-06-01 05:08:42.712  INFO 18768 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state LivenessState changed to CORRECT
2021-06-01 05:08:42.735  INFO 18768 --- [           main] com.trustly.montyhall.gameshow.GameShow  : Switching wins 6 times.
2021-06-01 05:08:42.736  INFO 18768 --- [           main] com.trustly.montyhall.gameshow.GameShow  : Staying wins 4 times.
2021-06-01 05:08:42.740  INFO 18768 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state ReadinessState changed to ACCEPTING_TRAFFIC
```
## References

* [Monty Hall problem](https://en.wikipedia.org/wiki/Monty_Hall_problem) - About the challenge

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Ricardo Pereira Ramalho** - *Initial work* - [RicardoPRamalho](https://github.com/RicardoPRamalho)
