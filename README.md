# RBAC Full-Stack Application

This is a comprehensive full-stack application demonstrating Role-Based Access Control (RBAC), built with a **Spring Boot backend** and a **Next.js frontend**. It showcases how to securely handle user authentication, authorization, and dynamic role-based UI rendering using modern frameworks and technologies.

## Overview

This application covers:

- **Role-Based Access Control**: Users are assigned roles (e.g., ADMIN, USER) that govern their permissions and access levels.
- **JWT-based Authentication**: Secure communication and authorization using JSON Web Tokens (JWT).
- **Dynamic UI Rendering**: Frontend UI dynamically adjusts based on user roles using Redux Toolkit and Tailwind CSS.

## Backend (Spring Boot)

### Features

- **Secure JWT Authentication & Authorization**
- **User Management**: Registration, Login, and Logout.
- **Role-Based Protected API endpoints**
- **CORS Configuration** for frontend integration

### Prerequisites

- Java 21
- Maven
- Docker (optional)

### Setup and Running

Clone and navigate to backend:

```bash
cd backend
```

Build and run the app:

```bash
mvn clean package -DskipTests
java -jar target/your-app.jar
```

Docker:

```bash
docker build -t my-spring-app -f Dockerfile.backend .
docker run -p 8080:8080 my-spring-app
```

### API Endpoints

| Method | Endpoint | Description | Roles |
|--------|----------|-------------|-------|
| POST | `/api/v1/auth/register` | Register new user | Public |
| POST | `/api/v1/auth/login` | User login and token generation | Public |
| POST | `/api/v1/auth/logout` | Logout | Authenticated Users |
| GET | `/api/v1/user` | User-specific content | USER |
| GET | `/api/v1/admin` | Admin-specific content | ADMIN |
| GET | `/api/v1/common` | Common content | Authenticated Users |

## Frontend (Next.js)

### Features

- **JWT Authentication** stored in `localStorage`
- **Global State Management** with Redux Toolkit
- **Dynamic Sidebar** based on user roles
- **Protected Routes** for secure content delivery
- **Responsive UI** with Tailwind CSS

### Prerequisites

- Node.js v18+
- npm
- Docker (optional)

### Setup and Running

Navigate to frontend:

```bash
cd frontend
```

Install dependencies and run locally:

```bash
npm install
npm run dev
```

Production build:

```bash
npm run build
npm start
```

Docker:

```bash
docker build -t my-next-app -f Dockerfile.frontend .
docker run -p 3000:3000 my-next-app
```

### Application Structure

- `app/layout.tsx`: Global layout with Redux provider.
- `app/Providers.tsx`: Redux store setup and token management.
- `app/Menu.tsx`: Sidebar dynamically rendered based on roles.
- `app/login/page.tsx`: User login page with JWT authentication.

#### Protected Pages

- **Admin** (`/admin`): Accessible by ADMIN users.
- **User** (`/user`): Accessible by USER and ADMIN roles.
- **Common** (`/common`): Accessible by all authenticated users.

## Technologies Used

### Backend
- Spring Boot
- Spring Security
- JWT
- H2 Database
- Maven
- Docker

### Frontend
- Next.js 13+ (App Router)
- React
- Redux Toolkit
- Tailwind CSS
- Axios
- jwt-decode
- Docker
