# fg-exercise

RESTful API for storing temperature measurements with timestamps.

API Definition on [SwaggerHub](https://app.swaggerhub.com/apis/p6331/FG-Exercise/1.0.3)
GitHub [repository](https://github.com/prouzpe3/fg-exercise)

A server stub is generated using the latest version of [OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) from the API documentation and can be regenerated any time.

The application connects to a PostgreSQL database, but for integration tests an H2 database is used.

To run the application alongside relational database in docker, run `docker-compose up` inside the `application` folder.