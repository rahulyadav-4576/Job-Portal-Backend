# Job Portal Backend (Spring Boot)

A robust and scalable **Job Portal Backend Application** built using **Spring Boot**.
This project provides REST APIs for user authentication, job posting, and job application management.

---

##  Features

* 🔐 JWT-based Authentication & Authorization
* 👤 User Registration & Login
* 💼 Job Posting & Management
* 📄 Apply for Jobs
* 📊 Role-based Access Control (Admin/User)
* ⚠️ Global Exception Handling
* 📦 Clean Architecture (Controller → Service → Repository)

---

##  Tech Stack

* ☕ Java
* 🌱 Spring Boot
* 🔐 Spring Security + JWT
* 🗄️ MySQL / H2 Database
* 📦 Maven
* 🔗 REST APIs

---

##  Project Structure

src/
├── controller
├── service
├── repository
├── entity
├── dto
├── security
└── config

---

##  API Endpoints (Sample)

### 🔐 Auth APIs

* POST `/auth/register` → Register user
* POST `/auth/login` → Login user

### 💼 Job APIs

* POST `/jobs` → Create job (Admin)
* GET `/jobs` → Get all jobs

### 📄 Application APIs

* POST `/applications` → Apply for job
* GET `/applications` → Get applications

---

## ⚙️ Setup & Run Locally

### 🔹 Clone Repository

```bash
git clone https://github.com/rahulyadav-4576/Job-Portal-Backend.git
```

### 🔹 Navigate to Project

```bash
cd Job-Portal-Backend
```

### 🔹 Run Application

```bash
./mvnw spring-boot:run
```

---

## 📸 API Testing

You can test APIs using:

* Postman
* Swagger (if enabled)

---

##  Contributing

Contributions are welcome! Feel free to fork this repo and submit a PR.

---

##  Contact
🔗 GitHub: https://github.com/rahulyadav-4576

---

## ⭐ Show your support

If you like this project, please give it a ⭐ on GitHub!
