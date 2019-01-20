# minipath

## Description
This application is URL shortner.
This application will save a url path to a h2 database and generate a ID, this ID will be transformed to a base 64 string key that can be used to identify the url.

## Usage
There are 3 endpoints configured

* POST http://localhost:8080/url
This endpoint will verify if the given path already exists, if not it will create a new DB entry.

** payload:
{
  "path" : "http://google.com"
}

** response payload:
"http://localhost:8080/go/d"

A string containing the redirect url

* GET http://localhost:8080/url/{id}
This endpoint will get the database entry. The URIparam is the database id.

** response payload
{
  "id" : 1,
  "path" : "http://google.com"
}

* GET http://localhost:8080/go/d
This endpoint uses the encoded id to redirect the call to the url

## Running the application
The app is built using springboot, you can run using maven

mvn spring-boot:run

## TODO'S
1) Need to verify if the path's contain the protocol - Not every protocal should be permited to avoid atacks to other sites
2) If the path is registered without the http protocol the redirect will fail, need to add a rule to verify that issue
3) Add docker configuration
