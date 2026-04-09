# Course Companion Web App

University coursework project developed as part of CO2302 at the University of Leicester.

## Overview

A web-based application designed to support students in exploring and managing course-related content. The system integrates a Spring Boot backend with a MySQL database to provide structured data handling, search functionality, and scalable backend services.

## Features

- Spring Boot backend for handling application logic and requests
- MySQL database integration for persistent data storage
- Course management system with structured entities
- Search functionality for discovering courses and content
- REST-style backend architecture
- Preloaded dataset for testing badges and search features
- Local deployment via browser (`localhost`)

## Tech Stack

- Java
- Spring Boot
- MySQL
- Gradle
- REST APIs
- JUnit

## Contribution

This was a collaborative group project, and I was involved across the full development lifecycle, including:

- Contributing to system design and backend architecture decisions
- Supporting development of core backend features and data handling logic
- Working with database setup, configuration, and integration
- Assisting with feature implementation, debugging, and testing
- Collaborating with team members to integrate components and deliver the system

## How to Run

### 1. Database Setup (MySQL)

- Start a local MySQL server with:
  - Host: `127.0.0.1`
  - Port: `3306`
- Create and select the database:

```sql
CREATE DATABASE co2123db;
USE co2123db;
```

### 2. Configure Application Properties

Update `Project/src/main/resources/application.properties` with your local MySQL username and password if needed.

### 3. Run the Backend

From the `Project/` directory:

```bash
./gradlew bootRun
```

### 4. Access the App

Open your browser and visit:

```text
http://localhost:8080
```
