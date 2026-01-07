# AI Rules & Guidelines

## Tech Stack Constraints
- **Java:** Must use Java 17 features.
- **Spring Boot:** Version 3.3.0. Use standard Spring patterns (Dependency Injection, Repository pattern).
- **Angular:** Version 14. Use functional programming where possible, strict typing, and Observables.
- **Styling:** Use standard CSS/SCSS. Avoid inline styles.

## Coding Standards
### Backend (Java)
- **DTOs:** Always use DTOs for API requests/responses. Never expose Entities directly.
- **Lombok:** Allowed for boilerplate (Getters, Setters, Constructors).
- **Testing:** JUnit 5. Write unit tests for critical logic.

### Frontend (Angular)
- **Components:** Keep components small and focused.
- **Services:** Use services for API calls and state management.
- **Observables:** Manage subscriptions properly (use `async` pipe or `unsubscribe`).

## Docker
- Ensure `Dockerfile` configurations match the specified versions (Java 17, Node 16).
- Use `docker-compose` for orchestration.
