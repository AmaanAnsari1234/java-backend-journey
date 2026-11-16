# PROJECT_CONTEXT: Employee API

_Last updated: 2026-11-16. Update at every milestone. Never put passwords, tokens or other secrets in this file._

## 1. Project overview

A learning project: an Employee Management REST API built step by step with Spring Boot, as preparation for Java backend developer roles (0 to 2 years). It started with an in-memory list, moved to PostgreSQL through Spring Data JPA, and now has request/response DTOs, validation and centralized exception handling.

- Repository: `java-backend-journey` (GitHub account `AmaanAnsari1234`)
- Project folder: `Projects/employee-api`
- Base package: `com.example.employee_api`

## 2. Tech stack and versions

| Item | Version / detail |
|---|---|
| Java | 21 (`java.version` = 21 in `pom.xml`) |
| Spring Boot | 4.1.2 (parent POM) |
| Spring Framework | 7.0.10 |
| Hibernate ORM | 7.4.13.Final |
| PostgreSQL JDBC driver | 42.7.14 |
| Connection pool | HikariCP 7.0.2 |
| Build tool | Maven (wrapper: `mvnw`, `mvnw.cmd`) |
| Database | PostgreSQL 17.11 (local) |
| IDE | IntelliJ IDEA |
| API testing | Postman, curl (`curl.exe` on Windows) |

Dependencies in `pom.xml`:
- `spring-boot-starter-webmvc`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-validation`
- `postgresql` (runtime)
- `spring-boot-starter-webmvc-test` (test)

## 3. Project structure

```
Projects/employee-api/
├── PROJECT_CONTEXT.md
├── pom.xml
├── mvnw, mvnw.cmd, .mvn/
└── src/
    ├── main/
    │   ├── java/com/example/employee_api/
    │   │   ├── EmployeeApiApplication.java
    │   │   ├── controller/
    │   │   │   ├── EmployeeController.java
    │   │   │   └── HelloController.java      (leftover practice endpoints, can be deleted)
    │   │   ├── dto/
    │   │   │   ├── EmployeeRequest.java
    │   │   │   └── EmployeeResponse.java
    │   │   ├── exception/
    │   │   │   ├── EmployeeNotFoundException.java
    │   │   │   └── GlobalExceptionHandler.java
    │   │   ├── model/
    │   │   │   └── Employee.java
    │   │   ├── repository/
    │   │   │   └── EmployeeRepository.java
    │   │   └── service/
    │   │       └── EmployeeService.java
    │   └── resources/application.properties
    └── test/java/com/example/employee_api/EmployeeApiApplicationTests.java
```

Architecture: `EmployeeController` -> `EmployeeService` -> `EmployeeRepository` -> Spring Data JPA -> Hibernate -> PostgreSQL. Errors thrown in the service are turned into HTTP responses by `GlobalExceptionHandler`.

Note: `EmployeeApiApplication.java` was once committed by mistake at `java-revision/EmployeeApiApplication.java`. It must live in `Projects/employee-api/src/main/java/com/example/employee_api/`, otherwise IntelliJ reports "Package name does not correspond to the file path" and "Cannot resolve symbol 'springframework'". See section 9.

## 4. Completed features

- Spring Boot project created and running (Tomcat on port 8080).
- Hello and goodbye practice endpoints (in `HelloController`).
- Full CRUD for employees, first with an in-memory list, then in `EmployeeService`, then on PostgreSQL via `EmployeeRepository`.
- `Employee` is a JPA entity; Hibernate creates the `employee` table at startup.
- Constructor-based dependency injection (controller gets the service, service gets the repository).
- Phase 2: POST and PUT take `@Valid @RequestBody EmployeeRequest`, converted with a private `toEntity` helper in the controller (the id is never copied, so any `id` in the body is ignored). All endpoints return `EmployeeResponse` through a private `toResponse` helper.
- Phase 3 (partly): `EmployeeNotFoundException` (a `RuntimeException` with the message "Employee not found with id <id>") and `GlobalExceptionHandler` (`@RestControllerAdvice`) returning 404 with a JSON body `{"status":404,"message":"..."}`. `getById`, `update` and `delete` in `EmployeeService` throw the exception (`getById` uses `orElseThrow`, `update` starts from `getById`, `delete` checks `existsById` first). The controller methods no longer use `Optional` chains or `if` checks for 404.
- The user tested the API in Postman and confirmed it works.

## 5. Database configuration

- Engine: PostgreSQL 17, local on each development PC (the database does **not** travel through Git).
- Host and port: `localhost:5432`
- Database: `employee_db`
- Application role: `employee_app` (login role that owns `employee_db`; created per PC)
- Password: read from the environment variable `DB_PASSWORD`, set in the IntelliJ run configuration (Edit Configurations -> Environment variables). Never stored in the repo. It must match the password given when the role was created.
- Schema handling: `spring.jpa.hibernate.ddl-auto=update` (Hibernate creates or adjusts tables)

`application.properties` (no secrets):

```properties
spring.application.name=employee-api
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=employee_app
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Table `employee` (created by Hibernate): `id bigint generated by default as identity` (primary key), `department varchar(255)`, `name varchar(255)`.

Setup needed on a new PC: install PostgreSQL 17, open pgAdmin as the `postgres` user, then run, one statement at a time (`CREATE DATABASE` cannot be combined with other statements):
1. `CREATE ROLE employee_app WITH LOGIN PASSWORD '<choose your own>';`
2. `CREATE DATABASE employee_db OWNER employee_app;`

Then set `DB_PASSWORD` in the run configuration. The `employee` table is created automatically and starts empty.

Useful check queries (pgAdmin Query Tool, connected to `employee_db`): `SELECT * FROM employee;`, `SELECT * FROM employee WHERE id = 4;`.

Earlier SQL Server plan: abandoned in favour of PostgreSQL. A SQL Server database `employee_db` and login `employee_app` may still exist on the laptop's SQL Server; cleanup is optional and was not confirmed.

## 6. Important classes and responsibilities

| Class | Responsibility |
|---|---|
| `EmployeeApiApplication` | Entry point, `@SpringBootApplication`, starts Spring and Tomcat |
| `EmployeeController` | `@RestController` at `/employees`; handles HTTP, picks status codes via `ResponseEntity`; delegates to the service; has private helpers `toEntity(EmployeeRequest)` and `toResponse(Employee)` |
| `HelloController` | Leftover practice endpoints `/hello` and `/goodbye` (can be deleted) |
| `EmployeeService` | `@Service`; business logic: `getAll`, `getById` (returns `Employee`, throws `EmployeeNotFoundException`), `add` (sets id to null so `save` always inserts), `update` (loads via `getById`, copies name and department, saves), `delete` (`void`, throws if `existsById` is false) |
| `EmployeeRepository` | Interface extending `JpaRepository<Employee, Long>`; Spring generates the implementation |
| `Employee` | JPA entity (`@Entity`, `@Id`, `@GeneratedValue(strategy = IDENTITY)`) with `id`, `name`, `department`, empty and all-args constructors, getters, setters |
| `EmployeeRequest` | Input DTO with `name` and `department`, plus `@NotBlank` and `@Size(max = 100)` on `name` |
| `EmployeeResponse` | Output DTO with `id`, `name`, `department`; all-args constructor and getters only |
| `EmployeeNotFoundException` | Custom `RuntimeException`, message "Employee not found with id <id>" |
| `GlobalExceptionHandler` | `@RestControllerAdvice`; currently handles `EmployeeNotFoundException` -> 404 JSON |

## 7. API endpoints implemented

| Method | URL | Body | Success | Failure |
|---|---|---|---|---|
| GET | `/employees` | none | 200 and a JSON list of `EmployeeResponse` | n/a |
| GET | `/employees/{id}` | none | 200 | 404 with JSON message |
| POST | `/employees` | `{"name":"...","department":"..."}` | 201 and the saved employee with its id | 400 if validation fails |
| PUT | `/employees/{id}` | `{"name":"...","department":"..."}` | 200 | 400 if validation fails; 404 with JSON message |
| DELETE | `/employees/{id}` | none | 204 | 404 with JSON message |
| GET | `/hello`, `/goodbye` | none | 200 (practice) | n/a |

Example 404 body: `{"status":404,"message":"Employee not found with id 999"}`.

Current limitation: the 400 validation response is Spring's default body (status, error, path), with no message saying which field failed. That is Step 20.

Other statuses seen: 400 for a non-numeric id (`/employees/abc`), 415 when the body is not sent as JSON, 405 when PUT or DELETE is sent to `/employees` without an id. A wrong URL such as `/employee/2` (singular) returns Spring's default 404 (with `timestamp` and `error` fields), not the custom JSON message.

## 8. Git

- Branches: `main`, `feature/postgres-jpa` (pushed), `feature/dto-validation` (pushed to GitHub; holds Phase 2 and the Phase 3 work so far; **not yet merged into `main`**).
- Laptop: `C:\AmaanCode\java-backend-journey`. Office PC: `D:\amaan\java-backend-journey`. Another PC was also used at `C:\Users\iccop\Desktop\amaann\java-backend-journey`.
- Latest commit hashes: unknown. Known commit messages: "Add Employee API Spring Boot project" (`16ecae6`), "Add PostgreSQL datasource and JPA configuration", "Add EmployeeRequest DTO and validation dependency", "Use EmployeeRequest DTO with @Valid in POST and PUT", "Add EmployeeResponse DTO and use it in all endpoints", "Add EmployeeNotFoundException and GlobalExceptionHandler for GET by id", "Use EmployeeNotFoundException in PUT and DELETE".
- Push problem on the office PC (seen earlier): `403 denied to al-creado` (see section 9).
- Working habit: run `git status` and read it before `git add .`; make sure new files (such as a new DTO) are included; commit after each small step.

## 9. Errors encountered and solutions

| Error | Cause | Solution |
|---|---|---|
| `git push` 403 "denied to al-creado" | Windows had another GitHub account saved | Credential Manager -> Windows Credentials -> remove `github.com` entries, push again and sign in as `AmaanAnsari1234`; optionally `git remote set-url origin https://AmaanAnsari1234@github.com/AmaanAnsari1234/java-backend-journey.git` |
| IntelliJ "Cannot resolve symbol" (many errors) | Maven libraries not loaded, or a package/file missing on this PC | Fix the first error only; reload Maven (Ctrl + Shift + O); Invalidate Caches; make sure the file or package exists |
| "Cannot resolve symbol 'EmployeeResponse'" after a pull | The file was tracked in Git (`git ls-files` showed it); IntelliJ had not refreshed | Reload Maven / let IntelliJ finish indexing; confirm the file exists in the `dto` folder |
| "Package name ... does not correspond to the file path" plus "Cannot resolve symbol 'springframework'" on `EmployeeApiApplication` | The file sat in `java-revision/` instead of the module's package folder, and `employee-api` was not linked as a Maven project | `git mv` the file into `Projects/employee-api/src/main/java/com/example/employee_api/`; right-click `pom.xml` -> Add as Maven Project; reload Maven; set SDK to 21 |
| No run button on `main` | Maven project not linked | Right-click `pom.xml` -> Add as Maven Project; set Project SDK to 21 |
| "Failed to configure a DataSource: 'url' attribute is not specified" | JPA starter added but no datasource properties | Added `spring.datasource.*` in `application.properties` |
| "ClassNotFoundException: org.postgresql.Driver" | PostgreSQL driver not on classpath | Added the `postgresql` dependency and reloaded Maven |
| "Unable to determine Dialect" / "password authentication failed for user employee_app" | The role and database did not exist on that PC's PostgreSQL | Created role and database in pgAdmin; set `DB_PASSWORD` in the run configuration |
| PUT returned 405 (first time) | `@PutMapping("/id")` typo (needs `"/{id}"`) and an old controller still using an in-memory list | Replaced the controller with the service-based version |
| PUT returned 405 (later) | Request sent to `/employees` without the id | Use `/employees/<id>` |
| 415 Unsupported Media Type | Postman body not sent as raw JSON | Body -> raw -> JSON |
| "Cannot compile module ... JVM target 5" and "Multiple root tags" | `pom.xml` was overwritten with only the `<properties>` and `<dependencies>` blocks | Replaced the whole `pom.xml` with a complete valid file |
| DELETE always returned 204, even for missing ids | `delete` had no `existsById` check (`deleteById` does nothing for a missing row), or the app was not restarted after the change | Added the check that throws `EmployeeNotFoundException`; restart the app after editing |
| 404 with `timestamp` and `error` fields on DELETE `/employee/2` | Wrong URL (singular) so no handler matched | Use `/employees/<id>` |

## 10. Current development status

- Phase 1 (database integration): complete.
- Phase 2 (DTOs and validation): complete (`EmployeeRequest` with `@Valid`, `EmployeeResponse` on all endpoints).
- Phase 3 (error handling): in progress. `EmployeeNotFoundException` and `GlobalExceptionHandler` work for GET by id, PUT and DELETE. Still to do: a handler for validation errors (Step 20) and optionally a catch-all handler (Step 21).
- Phases 4 and 5: not started.

## 11. Next task

1. Step 20: add a handler for `MethodArgumentNotValidException` in `GlobalExceptionHandler` so a 400 lists which field failed and why, for example `{"status":400,"errors":{"name":"must not be blank"}}`. Test with `{}` and a blank name on POST and PUT.
2. Step 21 (optional): catch-all `@ExceptionHandler(Exception.class)` returning a clean 500 JSON.
3. Housekeeping: merge `feature/dto-validation` into `main` through a pull request; delete `HelloController`; consider also adding `@NotBlank` to `department` in `EmployeeRequest`.
4. Phase 4: derived queries (for example, find by department), `@Transactional`, pagination and sorting.
5. Phase 5: JUnit 5, Mockito, basic integration tests.

## 12. Accuracy notes

- The user reported that POST, PUT, DELETE and the 404 handling work in Postman. The exact status codes for every test case were not all pasted into the chat; the user confirmed "everything working" after each step.
- The restart-persistence test (data survives an app restart) was not explicitly confirmed.
- Whether the final commit "Use EmployeeNotFoundException in PUT and DELETE" was pushed was not confirmed in chat; check `git status` and `git log` before starting.
- The exact repository field name in `EmployeeService` (`employeeRepository` or `repository`) was assumed in the code given; the user's own file is the source of truth.