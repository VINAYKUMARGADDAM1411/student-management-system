
# Student Management System (SMS)

Welcome to the **Student Management System (SMS)** repository!  
This is a full-stack **web-based Java application** built using **Spring Boot**, **Spring Security**, and **Thymeleaf**, designed to transform traditional student data management into an efficient, secure, and automated online solution.

The system provides features for **admin users** to manage students, attendance, results, exam fees, and courses. Meanwhile, **students** have a secure login to view their academic details including attendance, marks, and fee status.


## Project Highlights

- Admin dashboard to manage students, courses, attendance, results, and exam fees.
- Student portal to view academic progress.
- Role-based access with login functionality.
- Intuitive web interface using Thymeleaf.
- Clean backend structure using MVC, Service, and Repository layers.



## Tech Stack

| Technology      | Description                            |
|-----------------|----------------------------------------|
| Java            | Core programming language              |
| Spring Boot     | Framework for building RESTful backend |
| Thymeleaf       | Template engine for frontend           |
| Spring Security | Role-based authentication & sessions   |
| MySQL           | Database to store application data     |
| Maven           | Project dependency management          |
| HTML/CSS        | Web layout and styling                 |
| Bootstrap       | Responsive design                      |



## Features

### Admin:

- Login with admin credentials.
- Add, view, edit, and delete students.
- Assign results and mark attendance.
- Manage exam fees and course data.

### Student:

- Secure login.
- View profile details.
- View attendance records.
- View subject-wise results.
- View assigned course information.
- Check exam fee status.


## Database Design

- **Entities**: `Student`, `Result`, `Attendance`, `ExamFee`, `User`, `Role`, `Course`
- Relationships handled with **JPA/Hibernate** annotations.
- Designed with **normalization principles** for scalability.


## Security

- Implemented using **Spring Security**.
- Role-based routing:
  - `/admin/**` – for admin
  - `/student/**` – for students
- Session management and authentication.


## Challenges Faced

- Configuring Spring Security with custom roles and URLs.
- Managing Thymeleaf form bindings and templates.
- Handling entity relationships with proper mapping.
- Designing user-friendly UI with Thymeleaf and Bootstrap.
- Debugging form validation and data fetch logic.



## Future Scope

- Add PDF report generation for results and attendance.
- Include parent module for progress tracking.
- Integrate SMS/Email alerts for important updates.
- Enable student enrollment flow dynamically.
- Add pagination, search, and sorting for large data tables.


## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL
- IDE like IntelliJ IDEA or Eclipse


### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/VINAYKUMARGADDAM1411/student-management-system.git
   ```

2. **Import into your IDE** as a Maven project.

3. **Set up MySQL and create a database:**

   ```sql
   CREATE DATABASE smsdb;
   ```

4. **Configure `application.properties`:**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/smsdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

5. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

6. **Open in browser:**

   ```
   http://localhost:8080/home
   ```

---

##  Default Login Credentials

| Role    | username           | Password  |
|---------|--------------------|-----------|
| Admin   | admin1411          | 12345     |
| Student | raki123            | 12345     |

> You can update these in the database or implement a registration system for dynamic user creation.

