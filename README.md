### Contact Management System

This is a simple contact management application built with JavaFX. The application allows users to add, view, and delete contacts.

#### Features

* **Add Contacts**: Enter a name, phone number, and email to save a new contact.
* **View Contacts**: See a list of all saved contacts.
* **Delete Contacts**: Remove a contact from the list.

#### Prerequisites

To build and run this project, you need the following software installed on your machine:

* **OpenJDK 21**: The project is built using Java 21.
* **Maven**: A build automation tool used to manage the project's dependencies and build process.

#### Getting Started

Follow these steps to get a copy of the project up and running on your local machine.

1.  **Clone the Repository**

    Clone the project from GitHub using the following command:

    ```bash
    git clone https://github.com/RStephanH/Contact-Management.git
    cd Contact-Management
    ```

2.  **Build the Project**

    Navigate to the project's root directory and use Maven to build the application. This command will download all necessary dependencies and compile the code.

    ```bash
    mvn clean install
    ```

3.  **Run the Application**

    After a successful build, you can run the application directly from the command line using Maven's exec plugin:

    ```bash
    mvn javafx:run
    ```

The application window should now appear, and you can start managing your contacts.

-----
