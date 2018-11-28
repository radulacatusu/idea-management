# My Idea Pool

This is a A Spring Boot Application for Securing a REST API with JSON Web Token (JWT)
* Sign up an user
* Login the user
* Create new idea
* Get all ideas
* Update an idea
* Delete an idea

## About

The application is developed with Spring Boot 2.1.0.RELEASE and maven as a building tool.
The database used is mysql and is connected to an instance of Amazon RDS.

## To run the application
Use one of the several ways of running a Spring Boot application. Below are just three options:

1. Build using maven goal: `mvn clean package` and execute the resulting artifact as follows `java -jar idea-management-1.0-SNAPSHOT.jar` or
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./idea-management-1.0-SNAPSHOT.jar`


## To manually test the application

I used POSTMAN and cURL to test the application. 

### Sign-up
```
POST - http://localhost:8080/users
```
#####Body
```
    {
      "email": "jack-black@test.io",
      "name": "Jack Black",
      "password": "the-Secret-123"
    }
```
Response: 201 CREATED
```
{"jwt":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrLWJsYWNrQGNvZGVtZW50b3IuaW8iLCJpYXQiOjE1NDMzNDA0NTksImV4cCI6MTU0MzM0MTA1OX0.gTAHDLaI23Ga49gfTi2ytshg5ot5e8TKzNxkgptvRdfclliNDDLg0BzquLrSvFyiv-KxT4hoHFVhvvCGkVMpUA"}
```
### Login
```
POST - http://localhost:8080/access-tokens
```
#####Body
```
    {
      "email": "jack-black@test.io",
      "password": "the-Secret-123"
    }
```
Response: 200 OK
```
{
    "jwt": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNrLWJsYWNrQGNvZGVtZW50b3IuaW8iLCJpYXQiOjE1NDMzNjAxMDQsImV4cCI6MTU0MzM2MDcwNH0.eQyUFPM1TkEK38hdobXelDpMXegS730B4NNpEebkQI1h1JP9Ww0DhDG2QBGqRrTTtQ4NShR83MVdPXpoHak4hA"
}
```
### Create new idea
```
POST - http://localhost:8080/ideas
```
#####Body
```
{
    "content": "the-content",
    "impact": 9,
    "ease": 8,
    "confidence": 8
}
```
Response: 201 CREATED
```
{
    "id": 13,
    "content": "the-content",
    "impact": 9,
    "ease": 8,
    "confidence": 8,
    "createdAt": 1543418756714,
    "averageScore": 8.333333333333334
}
```
### Get all the ideas
```
GET - http://localhost:8080/ideas
```
Response: 200 OK
```
[
    {
        "id": 13,
        "content": "the-content",
        "impact": 9,
        "ease": 8,
        "confidence": 8,
        "createdAt": 1543418757000,
        "averageScore": 8.333333333333334
    },
    {
        "id": 12,
        "content": "the-content",
        "impact": 15,
        "ease": 8,
        "confidence": 8,
        "createdAt": 1543360207000,
        "averageScore": 10.333333333333334
    }
]
```
### Update an idea
```
PUT - http://localhost:8080/ideas/12
```
#####Body
```
{
    "content": "the-content new",
    "impact": 8,
    "ease": 8,
    "confidence": 8
}
```
Response: 202 OK
```
{
    "id": 12,
    "content": "the-content33",
    "impact": 8,
    "ease": 8,
    "confidence": 8,
    "createdAt": 1543360207000,
    "averageScore": 8
}
```
### Delete an Idea
```
DELETE - http://localhost:8080/ideas/10
```
Response: 204 NO CONTENT

### Get user
```
GET - http://localhost:8080/me
```
Response: 200 OK
```
{
    "name": "Jack Black",
    "email": "jack-black@test.io"
}
```

## Authentication for the API

TODO - I want to add refresh token in the response of sign-up and login

## Testing the API

TODO - I have to add unit tests. Currently only manual testing is done

## Deployment

The web service is deployed on AWS and can be accessed at 34.209.73.115

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Radu Stefan Lacatusu** - [radulacatusu](https://github.com/radulacatusu/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details