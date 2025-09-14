# DreamRent

Une application moderne et responsive permettant aux utilisateurs de **rechercher, publier et réserver** des biens immobiliers en location.

---

## Technologies utilisées
- **Frontend** : Angular
- **Backend** : Spring Boot
- **Base de données** : MySQL
- **Authentification** : JWT (JSON Web Token)
- **Autres** : Gestion d’images, API REST, architecture modulaire full-stack

---

---

## Installation et exécution
##  Installation

```bash
# Clone the repository
git clone https://github.com/majdeddine-oumanni/Dream-Rent.git
cd Delivery-Match

# Backend
cd backend
./mvnw spring-boot:run

# Frontend
cd frontend
npm install
ng serve
```

##  Run with Docker Compose

### 1. Build your JAR file

```bash
cd backend
mvn clean package
```
### 2. Start services

````bash
docker-compose up --build
````

### 3. Stop service
````bash
docker-compose down
````

### 4. View running containers
````bash
docker-compose ps
````
### 5. View logs
````bash
docker-compose logs -f
````

## API Documentation (Swagger)
### Once the app is running, visit:
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/swagger-ui.html

##  Running Tests
````bash
cd backend
mvn test
````




## UML Diagrams

### 1. Class Diagram

![Diagram](UMLs\diagramDeClass.png)

### 2. Use Case Diagram

![Diagram](UMLs\useCaseDiagram.png)

### 3. Sequence Diagram

![Diagram](UMLs\sequenceDiagram.png)


# 🧑‍💻 Author
Majd Eddine Oumanni # https://github.com/majdeddine-oumanni #