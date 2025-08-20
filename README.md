# Contact Management (Frontend)

This is the **frontend** of the Contact Management System application, built with **JavaFX**.  
The application allows users to add, view, and delete contacts by communicating with the backend REST API.

**Backend repository:** [https://github.com/RStephanH/contact-backend](https://github.com/RStephanH/contact-backend)

> ⚠️ Make sure the backend is running before using this frontend.

---

## Features

- **Add Contacts:** Enter first name, last name, phone number, and email to save a new contact.
    
- **View Contacts:** See a list of all saved contacts retrieved from the backend.
    
- **Delete Contacts:** Remove a contact from the database via the backend API.
    

---

## Prerequisites

To build and run this frontend project, you need:

- **OpenJDK 21** (project is built using Java 21)
    
- **Maven** (to manage dependencies and build the project)
    
- **Running Backend** ([contact-backend](https://github.com/RStephanH/contact-backend))
    

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/RStephanH/Contact-Management.git
cd Contact-Management
```

### 2. Build the Project

Navigate to the project root directory and build using Maven:

```bash
mvn clean install
```

This will download all necessary dependencies and compile the code.

### 3. Run the Application

After building, run the application using Maven:

```bash
mvn javafx:run
```

The JavaFX application window will appear. You can now start managing your contacts.

> Make sure the backend is running at the correct URL (default: `http://localhost:8080`) before performing any operations.

---

## Notes

- The frontend **relies entirely on the backend** for storing and retrieving contacts.
    
- If the backend is not running, the frontend will not be able to fetch or save data.
    
- Adjust the backend URL in the frontend configuration if the backend is running on a different host or port.
    

