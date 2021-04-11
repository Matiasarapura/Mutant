# Meli Mutant 

### API-REST
##### /mutant POST
request body
```json
{ 
  "dna":["TAATAA","TAGTGC","TTATGT","TGAAGG","TCCCTA","TCACTG"]
} 
```
##### /stats get
response body
```json
{
    "Spot": 2.3333333333333335
}
```

    
### Tech
* SpringBoot 2.4
* Java 14
* Redis

### Config
-application.properties
```yaml
redis.host= localhost
redis.port= 6379
server.port=8080
```
### Run
```sh 
$docker run -p 6379:6379 -it --rm redis
```
```sh
$ mvn spring-boot:run
```

For production environments...

```sh
$ docker build . -t exchange
$ docker run exchange
```




