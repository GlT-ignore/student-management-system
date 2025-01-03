# Student Management System 🎓

A modern, responsive web application for managing student records with real-time updates and a clean user interface. Built by a 404 error finder from the land of lost semicolons 😉

## 🎯 Project Description

This Student Management System is a full-stack web application that demonstrates the implementation of core Java concepts (inheritance, interfaces, generics, etc.) along with modern web technologies. It features a sleek, responsive UI with real-time connection monitoring and dynamic statistics.

### Key Highlights:
- 🔄 Real-time connection status monitoring
- 📊 Live statistics dashboard
- 🎯 Automatic grade calculation system
- 🔒 Secure data management
- 💻 Clean, maintainable codebase
- 🎨 Modern, responsive UI design

## 🚀 Features

- ✨ Real-time connection status monitoring
- 📊 Dynamic statistics (Total Students & Class Average)
- 📝 CRUD operations for student records
- 🎯 Automatic grade calculation
- 🔄 ID resequencing after deletion
- 💾 Persistent data storage with H2 Database

## 🛠️ Technologies Used

### Backend
- **Java 17**
  - Classes & Inheritance (`Student extends Person`)
  - Interfaces (`DataOperations`, `StudentRepository`)
  - Generics (`DataManager<T>`)
  - Exception Handling (Custom `ValidationException`)
  - Multithreading (`@Async` operations)
  - Stream API & Lambda expressions
- **Spring Boot 3.2.1**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
- **H2 Database**
- **Maven**

### Frontend
- **HTML5**
- **CSS3**
  - Flexbox
  - CSS Grid
  - Animations
  - Custom Properties
- **JavaScript (ES6+)**
  - Classes
  - Async/Await
  - Fetch API
  - DOM Manipulation

## 📦 Installation

1. Clone the repository:
```bash
git clone https://github.com/GlT-ignore/student-management-system.git
cd student-management-system
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

4. Access the application:
- Frontend: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console

```properties
JDBC URL: jdbc:h2:mem:studentdb
Username: sa
Password: password
```

## 🔍 API Endpoints

```http
GET    /api/students     # Get all students
POST   /api/students     # Add a new student
DELETE /api/students/{id} # Delete a student
```

## 💻 Code Examples

### Adding a Student (Frontend)
```javascript
const student = {
    name: "John Doe",
    email: "john@example.com",
    course: "Computer Science",
    grade: 85,
    status: "ACTIVE"
};

fetch('/api/students', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(student)
});
```

### Adding a Student (cURL)
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com","course":"Computer Science","grade":85}'
```

## 💻 Tech Stack Used

### Backend
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

### Database
![H2 Database](https://img.shields.io/badge/H2_Database-2496ED?style=for-the-badge&logo=database&logoColor=white)

## 👤 Author

**Chanikya Gajjarapu** - [@GlT-ignore](https://github.com/GlT-ignore)

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check [issues page](https://github.com/GlT-ignore/student-management-system/issues).

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📧 Contact

Chanikya Gajjarapu - [@GlT-ignore](https://github.com/GlT-ignore)

Project Link: [https://github.com/GlT-ignore/student-management-system](https://github.com/GlT-ignore/student-management-system)