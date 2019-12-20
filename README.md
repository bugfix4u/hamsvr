# **Home Asset Manager API Server**

## Description
This is a simple example project I created to help teach myself more about Spring Boot and Spring Data JPA. This project represents a very simple asset management API that could be used to manage your personal assets for insurance purposes. 

## Resources
The project uses the following software and components:
- Spring Boot - https://spring.io/projects/spring-boot
- Spring Data JPA - https://spring.io/projects/spring-data-jpa
- OpenJDK 8 - https://openjdk.java.net/install/
- Maven - https://maven.apache.org/
- Postgres DB - https://www.postgresql.org/
- Docker - https://www.docker.com/
- Docker Compose - https://docs.docker.com/compose/
- Swagger - https://swagger.io/

## Building the Code
This project uses Maven for building and packaging. To build the the executable jar file, simple run "mvn clean package -DskipTests"

## Packaging
The Home Asset Manager server is designed to be packaged as a set of microservices, each running as a seperate Docker container. To package and run this example, you will need to have a working Docker installation, as well as the Docker Compose tool installed.

## Running the server
To run the Home Asset Manager server and the Postgres DB, simply run "sudo docker-compose up". You will need internet access to run this command, since it will be pulling down a Postgres docker image and a 8-jdk-alpine image for packaging of the Home Asset Manager jar file. The Postgres DB is accessible from the standard 5432 port, while the Home Asset Manager server is accessible through HTTP port 8080.

## Examples
Below are a few examples to get you going with the Home Asset Manager servers. Please see the swagger documentation in the ./api directory for more info.

Create an asset:

```bash
curl --request POST \
  --url http://localhost:8080/assets \
  --header 'content-type: application/json' \
  --data '{
	"name":"82 inch TV",
	"manufacturer":"Samsung",
	"model":"FN6Q82",
	"serialId":"123abc456",
	"purchasePrice":3199.99,
	"location":"basement",
	"description":"82 inch Samsung QLED Tv",
	"tags":"TV,Electronics"
}'
```
Get all assets:

```bash
curl --request GET \
  --url http://localhost:8080/assets \
  --header 'content-type: application/json'
```

Get an asset by ID:

```bash
curl --request GET \
  --url http://localhost:8080/asset/1993aa6a-6e7e-42d8-b253-2cc5d09ce7ba \
  --header 'content-type: application/json'
```

Modify an asset by ID:

```bash
curl --request PUT \
  --url http://localhost:8080/asset/1993aa6a-6e7e-42d8-b253-2cc5d09ce7ba \
  --header 'content-type: application/json' \
  --data '{
  "id": "a28e297c-0ecf-4036-b01c-db6fc1e6b714",
  "name": "82 inch TV",
  "description": "82 inch Samsung QLED Tv",
  "tags": "TV,Electronics",
  "manufacturer": "Samsung",
  "model": "FN6Q82",
  "serialId": "123abc456",
  "purchasePrice": 3199.99,
  "location": "living room"
}'
```

Delete an asset by ID:

```bash
curl --request DELETE \
  --url http://localhost:8080/asset/1993aa6a-6e7e-42d8-b253-2cc5d09ce7ba \
  --header 'content-type: application/json'
```


## API Documentation
The API's for this example server have been documented using Swagger (https://swagger.io/). You can run the swagger-ui as a docker container using the following instructions:

This will start nginx with Swagger UI on port 80.

```bash
docker pull swaggerapi/swagger-ui
docker run -p 80:8080 swaggerapi/swagger-ui
```

To start swagger with the Home Asset Manager swagger documentation, you can use a command similar to:

```bash
docker run -p 80:8081 -e SWAGGER_JSON=/swagger/swagger.yaml -v /path/to/hamsvr/api:/swagger swaggerapi/swagger-ui
```

## License
GPL v3 ( http://www.gnu.org/licenses/gpl-3.0.html )