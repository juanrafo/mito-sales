# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project status

This is a freshly scaffolded Spring Boot project (Spring Boot 4.1.0, Java 25) with no business logic yet — only the application entry point, a port setting, and a placeholder test. There is no established package structure (controllers, services, repositories, etc.) to follow yet; when adding the first real code, set up conventional Spring Boot layering under `com.mitocode`.

## Commands

Use the Maven wrapper (`mvnw`/`mvnw.cmd`) so the build doesn't depend on a local Maven install.

```
./mvnw compile              # compile
./mvnw test                 # run all tests
./mvnw test -Dtest=ClassName          # run a single test class
./mvnw test -Dtest=ClassName#method   # run a single test method
./mvnw spring-boot:run       # run the app locally (listens on port 8080)
./mvnw package               # build the executable jar
```

On Windows PowerShell, use `mvnw.cmd` in place of `./mvnw`.

## Architecture

- Build: Maven, inheriting from `spring-boot-starter-parent` 4.1.0.
- Dependencies: `spring-boot-starter-webmvc` (Spring MVC, servlet-stack web) and Lombok (annotation processor wired into both the `compile` and `test-compile` executions of `maven-compiler-plugin` — remember to keep both configured if the compiler plugin config changes).
- Root package: `com.mitocode`, application class `MitoSalesApplication`.
- Server port is set in `src/main/resources/application.yaml` (currently `8080`).

Agents are located in: .agents/subagents
Skills are located in: .agents/skills
Workflows are located in .agents/workflows