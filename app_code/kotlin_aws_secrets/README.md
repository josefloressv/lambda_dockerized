# Kotlin AWS Secrets

This is a Kotlin-based Spring Boot application that demonstrates how to manage AWS Secrets. The application is built using Gradle and includes a simple web interface.

## Stack

- **Kotlin**: Programming language
- **Spring Boot**: Framework for building Java-based applications
- **Thymeleaf**: Template engine for rendering web pages
- **Gradle**: Build tool

## Prerequisites

- JDK 21
- Gradle 8.11.1
- AWS account with necessary permissions to manage secrets

## Project Structure

- `src/main/kotlin`: Contains the main application code
- `src/main/resources`: Contains application properties and templates
- `src/test/kotlin`: Contains test cases

## How to Run

1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd kotlin_aws_secrets
    ```

2. **Build the project**:
    ```sh
    ./gradlew build
    ```

3. **Run the application**:
    ```sh
    ./gradlew bootRun
    ```

4. **Access the application**:
    Open your browser and navigate to `http://localhost:8080`

## Configuration

The application configuration is located in . You can set the application name and other properties here.

```properties
spring.application.name=kotlin_aws_secrets