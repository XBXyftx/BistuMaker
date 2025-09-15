# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

BistuMaker is a club website system for a university maker space, featuring a Vue.js frontend and Spring Boot backend. The system manages club activities, member information, photo albums, announcements, and includes an admin management interface.

## Project Structure

```
BistuMaker/
├── backend/                    # Spring Boot backend (Java 17)
│   ├── src/main/java/com/bistu/ckkj/
│   │   ├── controller/         # REST API controllers
│   │   ├── service/           # Business logic services
│   │   ├── mapper/            # MyBatis data access layer
│   │   ├── pojo/              # Entity classes
│   │   ├── utils/             # Utility classes (JWT, IP, etc.)
│   │   ├── config/            # Configuration classes
│   │   ├── aop/               # Aspect-oriented programming
│   │   └── exception/         # Global exception handling
│   └── src/main/resources/
│       ├── application.yml    # Main config (profile selection)
│       └── application-dev.yml # Development environment config
└── vue-club重构版/              # Vue.js frontend
    ├── src/
    │   ├── views/             # Page components
    │   │   ├── home/          # Homepage with events, introductions
    │   │   ├── article/       # Article display and comments
    │   │   ├── background/    # Admin management interface
    │   │   ├── phoneAlbum/    # Photo gallery
    │   │   ├── activity/      # Activity timeline
    │   │   └── login/         # Authentication
    │   ├── components/        # Reusable components
    │   └── utils/             # Frontend utilities
    └── vite.config.js         # Vite configuration with proxy
```

## Development Commands

### Backend (Spring Boot)
```bash
# Navigate to backend directory
cd backend

# Run development server (port 8080)
mvn spring-boot:run

# Build JAR package
mvn clean package

# Run tests
mvn test
```

### Frontend (Vue.js)
```bash
# Navigate to frontend directory
cd vue-club重构版

# Install dependencies
npm install

# Run development server (port 5173)
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

## Database Setup

1. Install MySQL 8.0
2. Create database named `club`
3. Import schema from `backend/sql.sql`
4. Update database credentials in `backend/src/main/resources/application-dev.yml`

Default admin credentials:
- Username: `admin`
- Password: `123456`

## Configuration

### Backend Configuration
- Database settings: `application-dev.yml`
- Upload file paths: Configure `upload.location.os` and `upload.location.os1` in `application-dev.yml`
- Server port: 8080 (configurable in application-dev.yml)

### Frontend Configuration
- API proxy: `vite.config.js` - proxies `/api` requests to backend
- Base URL: `src/utils/baseURL.js` - configures image and API base URLs
- Development proxy target: `http://localhost:8080`

## Key Features

### Frontend Pages
- **Home (`/home`)**: Activity timeline, club introduction, event showcase
- **Articles (`/article`)**: Blog posts with markdown support and commenting
- **Photo Album (`/phoneAlbum`)**: Image gallery with waterfall layout
- **Activities (`/activity`)**: Activity timeline and details
- **Admin Panel (`/background`)**: Complete management interface for content, users, and media

### Backend APIs
- **User Management**: Registration, authentication, profile management
- **Article System**: CRUD operations, pagination, categories
- **Image Management**: Upload, compression, album organization
- **Activity Management**: Event creation, timeline display
- **Comment System**: Article comments with nested replies
- **Notification System**: Admin announcements
- **Logging System**: AOP-based operation logging with IP tracking

## Technology Stack

### Backend
- Spring Boot 3.1.3 with Java 17
- MyBatis for database access
- JWT authentication
- AOP for logging
- MySQL 8.0
- File upload with compression (Thumbnailator)
- IP geolocation (ip2region)

### Frontend
- Vue 3 with Composition API
- Element Plus UI framework
- Vite build tool
- Pinia for state management
- Vue Router for navigation
- Axios for HTTP requests
- Markdown editor support
- Animation libraries (animate.css, TypeIt)

## Development Notes

- Backend uses profile-based configuration (`dev`, `prod`)
- Frontend proxy configuration handles CORS during development
- File uploads are stored in `target/classes/static/images` for development
- Image compression is automatically applied on upload
- The system includes comprehensive logging with IP tracking and operation details
- Admin interface provides full CRUD operations for all content types