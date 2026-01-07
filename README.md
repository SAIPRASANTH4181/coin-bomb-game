# Coin Bomb Game

An online strategy game where users navigate a grid, predicting gold slots while avoiding mines. As levels increase, the grid grows, and the difficulty scales with more mines and fewer gold slots.

## Tech Stack

### Backend
- **Language:** Java 17
- **Framework:** Spring Boot 3.3.0
- **Build Tool:** Maven 3.9
- **Database:** MySQL (Production), H2 (Dev/Test)

### Frontend
- **Framework:** Angular 14
- **Runtime:** Node.js 16 (Build), Nginx (Production)

### Infrastructure
- **Containerization:** Docker
- **Orchestration:** Docker Compose

## Getting Started

### Prerequisites
- Docker & Docker Compose
- Java 17 JDK (for local dev)
- Node.js 16+ (for local dev)

### Running with Docker
```bash
docker-compose up --build
```
The application will be available at:
- Frontend: `http://localhost:80`
- Backend API: `http://localhost:8080`

## Project Structure
- `backend/`: Spring Boot application
- `frontend/`: Angular application
- `docs/`: Project documentation and context
