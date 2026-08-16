# 👥 User Management CRUD Application

A simple **User Management Web Application** built using **Java Servlet, JSP, PostgreSQL, and Bootstrap**.

This application allows users to be added, viewed, edited, deleted, searched, and sorted easily.

---

## 🚀 Live Demo

🌐 **Deployed Application:** 
[Open User Management App](jsp-servlet-crud-production.up.railway.app)

🚀 Take a look at Live Demo

---

## 📌 Features

- ➕ Add new users
- 📋 View all users
- ✏️ Edit existing users
- 🗑️ Delete users
- 🔍 Search users by name or email
- ↕️ Sort users by ID or name
- 🌙 Dark Mode
- ✅ Success messages after adding, updating, or deleting users
- 📱 Responsive user interface

---

## 🛠️ Technologies Used

### Frontend
- HTML
- CSS
- JSP
- Bootstrap
- JavaScript

### Backend
- Java
- Jakarta Servlet

### Database
- PostgreSQL

### Server
- Apache Tomcat

### Deployment
- Railway

---

## 🏗️ Project Structure

```text
UserManagement/
│
├── src/
│   └── main/
│       └── java/
│           └── net/
│               └── javaguides/
│                   └── usermanagement/
│                       ├── dao/
│                       │   └── UserDAO.java
│                       │
│                       ├── model/
│                       │   └── User.java
│                       │
│                       └── web/
│                           └── UserServlet.java
│
├── WebContent/
│   ├── user-form.jsp
│   └── user-list.jsp
│
├── Dockerfile
├── pom.xml
└── README.md
```

---

## 🔄 How the Application Works

The application follows this flow:

```text
User
  ↓
JSP Page
  ↓
Servlet
  ↓
UserDAO
  ↓
PostgreSQL Database
```

### Example : 

- When a user clicks Save :

```
User enters details
        ↓
     JSP Form
        ↓
    UserServlet
        ↓
      UserDAO
        ↓
 PostgreSQL Database
        ↓
   User is saved
```

---

## 🗄️ Database

The application uses PostgreSQL.

Users Table :
```
CREATE TABLE userss (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);
```

---

## ⚙️ Configuration

The application uses environment variables for the PostgreSQL database connection.

Required Variables : 

```
DATABASE_URL
PGHOST
PGPORT
PGUSER
PGPASSWORD
PGDATABASE
```
- These values are configured in the deployment environment.

⚠️ Database passwords and private credentials should never be uploaded to GitHub.

---

## 🌐 Deployment

- The application is deployed using:

- Railway + Apache Tomcat + PostgreSQL

- The deployed application connects to the Railway PostgreSQL database using environment variables.

---

## ▶️ Execution Process

The project was developed and deployed step by step using Java Servlet, JSP, PostgreSQL, Apache Tomcat, Docker, and Railway.

### Step 1: Create the Project

The User Management CRUD application was created using:

- Java
- JSP
- Jakarta Servlet
- JDBC
- PostgreSQL
- Bootstrap

The project was developed in **Eclipse IDE**.

---

### Step 2: Create the Database

A PostgreSQL database was created for storing user information.

The `userss` table was created using:

```sql
CREATE TABLE userss (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);
```

---

### Step 3: Connect Java Application with PostgreSQL

JDBC was used to connect the Java application with PostgreSQL.

The UserDAO class handles the database operations.

The application performs:
```
INSERT
SELECT
UPDATE
DELETE
```
operations on the userss table.

---

### Step 4: Create JSP Pages

Two main JSP pages were created:

user-list.jsp

This page displays:
```
All users
Search option
Sort option
Add User button
Edit button
Delete button
Dark Mode
user-form.jsp
```
This page is used for:
```
Adding a new user
Editing an existing user
```

---

### Step 5: Create User Servlet

The UserServlet handles the requests coming from the JSP pages.

The servlet handles the following operations:
```
/new
    ↓
Open Add User Form

/insert
    ↓
Insert User into Database

/edit
    ↓
Open Edit User Form

/update
    ↓
Update User in Database

/delete
    ↓
Delete User from Database

/list
    ↓
Display All Users
```

---

### Step 6: Test the Application Locally

The application was deployed on Apache Tomcat and tested locally.

The application was accessed using : 
```
http://localhost:8080/
```
The CRUD operations were tested : 
```
Add User
   ↓
View User
   ↓
Edit User
   ↓
Update User
   ↓
Delete User
```
Search and sorting functionality were also tested.

---

### Step 7: Create WAR File

After successfully testing the application locally, the project was exported as a WAR file.
```
crudapp.war
```
The WAR file contains the complete web application and is used for deployment.

---

### Step 8: Create Dockerfile

A Dockerfile was created to run the application using Apache Tomcat.
```
FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY crudapp.war /usr/local/tomcat/webapps/ROOT.war
```

---

### Step 9: Create PostgreSQL Database on Railway

A PostgreSQL service was created on Railway.

Railway provided PostgreSQL database environment variables such as : 
```
DATABASE_URL
PGHOST
PGPORT
PGUSER
PGPASSWORD
PGDATABASE
```
These variables were added to the application service environment.

---

### Step 10: Deploy the Application on Railway

The project was deployed on Railway using the Dockerfile.

The deployment process was : 
```
Java Project
    ↓
WAR File
    ↓
Dockerfile
    ↓
Apache Tomcat
    ↓
Railway
    ↓
Live Web Application
```

---

### Step 11: Connect the Deployed Application to Railway PostgreSQL

The deployed Java application reads the PostgreSQL connection information from Railway environment variables.

The application connects using : 
```
PGHOST
PGPORT
PGUSER
PGPASSWORD
PGDATABASE
```
The database connection is handled inside UserDAO.java.

---

### Step 12: Final Testing

After deployment, the live application was tested again.

The following operations were verified : 
```
✅ Add User
✅ View Users
✅ Edit User
✅ Update User
✅ Delete User
✅ Search User
✅ Sort Users
✅ Dark Mode
✅ PostgreSQL Database Connection
```

---

## ⭐ Conclusion

This project demonstrates a complete Java-based CRUD web application with JSP, Servlet, JDBC, PostgreSQL, Apache Tomcat, Docker, and Railway deployment.

Thank you for checking out this project! 🚀
