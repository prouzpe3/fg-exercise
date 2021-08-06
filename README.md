# fg-exercise

RESTful API for storing temperature measurements with timestamps.

- API Definition on [SwaggerHub](https://app.swaggerhub.com/apis/p6331/FG-Exercise/1.0.3)
- GitHub [repository](https://github.com/prouzpe3/fg-exercise)

A server stub is generated using the latest version of [OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) from the API documentation and can be regenerated any time.
`java -jar openapi-generator-cli-5.2.1-SNAPSHOT.jar generate -i documentation/p6331-FG-Exercise-1.0.3-resolved.yaml -g spring --model-name-suffix Dto -o application/ -c documentation/stub-conf.yaml`

The application connects to a PostgreSQL database, but for integration tests an H2 database is used.

To run the application alongside relational database in docker, run `docker-compose up` inside the `application` folder.
