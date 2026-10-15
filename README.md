# mongock-demo2

[![CI](https://github.com/hendisantika/mongock-demo2/actions/workflows/ci.yml/badge.svg)](https://github.com/hendisantika/mongock-demo2/actions/workflows/ci.yml)

Spring Boot demo of [Mongock](https://mongock.io) database migrations on MongoDB. On startup Mongock runs the
change units in `id.my.hendisantika.mongockdemo2.migration` inside a transaction:

1. `ClientInitializerChangeUnit` creates `clientCollection` and inserts 10 clients.
2. `ClientUpdaterChangeUnit` appends `_updated` to every client name.

Applied changes are tracked in `mongockChangeLog`, so each one runs only once.

## Tech stack

- Java 25
- Spring Boot 4.1.1 (Web MVC, Data MongoDB, Docker Compose support)
- Mongock 5.5.1 (`mongock-springboot-v3`, `mongodb-springdata-v4-driver`)
- MongoDB 8 as a single-node replica set (required for Mongock transactions)
- Testcontainers for integration tests

## Prerequisites

- JDK 25
- Docker

## Getting started

1. Create a `.env` file with the MongoDB credentials (it is git-ignored):

   ```shell
   cp .env.example .env
   ```

   ```properties
   MONGO_USERNAME=changeme
   MONGO_PASSWORD=changeme
   ```

   Both Docker Compose and Spring Boot read this file.

2. Run the application:

   ```shell
   ./mvnw spring-boot:run
   ```

   Spring Boot's Docker Compose support starts MongoDB from `compose.yaml` and connects to the `client` database.
   On first start the container's healthcheck initiates replica set `rs0`, then Mongock applies the migrations.

3. Check the result:

   ```shell
   docker exec mongock-demo2 mongosh -u <user> -p <password> --authenticationDatabase admin \
     --eval 'db.getSiblingDB("client").clientCollection.find({}, { name: 1 })'
   ```

To run MongoDB on its own, use `docker compose up -d`.

## Configuration

| Property / variable     | Where              | Purpose                                                     |
|-------------------------|--------------------|-------------------------------------------------------------|
| `MONGO_USERNAME`        | `.env`             | MongoDB root user                                           |
| `MONGO_PASSWORD`        | `.env`             | MongoDB root password                                       |
| `MONGO_INITDB_DATABASE` | `compose.yaml`     | Database the app uses when started via Docker Compose       |
| `spring.mongodb.uri`    | `application.yml`  | Connection string when not using Docker Compose support     |
| `mongock.transactional` | `application.yml`  | Run migrations in a transaction (needs a replica set)       |

## Tests

```shell
./mvnw verify
```

`MongockDemo2ApplicationTests` starts MongoDB in a Testcontainers replica set and checks that both migrations ran.
Docker must be running.

## CI and dependency updates

- **GitHub Actions** (`.github/workflows/ci.yml`) builds and tests on JDK 25 (`ubuntu-24.04`) on every push and pull
  request to `main`, and daily at 17:00 Asia/Jakarta.
- **Dependabot** (`.github/dependabot.yml`) checks Maven, GitHub Actions and Docker Compose dependencies daily at
  17:00 Asia/Jakarta.
