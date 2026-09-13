# To-Do App with Login 📋

An Android task management application that allows users to create an account, securely log in, and manage their personal to-do list. User accounts and tasks are stored locally using SQLite, with tasks linked to the currently logged-in user.

## Features

- User registration with name, email, and password.
- User login using registered credentials.
- Passwords are hashed before being stored in the database.
- User-specific task lists.
- Add new tasks. 
- Add optional notes to tasks.
- Mark tasks as completed.
- Delete tasks permanently.
- Logout functionality.
- Session management using SharedPreferences.
- Empty-state message when there are no tasks.
- Local data persistence using SQLite.

## Authentication

The application provides separate **Sign Up** and **Login** screens.

During registration, users provide:

- Name
- Email
- Password
- Password confirmation

Passwords are not stored as plain text. The application hashes passwords before storing them in the SQLite database.

During login, the entered credentials are checked against the stored account information.

## Task Management

Once logged in, users can access their personal task list.

Users can:

1. Add a new task.
2. Add optional notes.
3. Mark a task as completed.
4. Update task information.
5. Delete a task permanently.
6. Log out of the application.

Tasks are associated with the user who created them, meaning users only see their own tasks.

## Database

The application uses **SQLite** for local data storage.

The database stores information related to:

- User accounts
- Tasks

The task records are linked to the corresponding user account so that task data remains user-specific.

The main database operations include:

- `registerUser()` – Creates a new user account.
- `checkUser()` – Verifies login credentials.
- `addTask()` – Adds a new task.
- `getTasks()` – Retrieves tasks belonging to the logged-in user.
- `updateTask()` – Updates an existing task.
- `deleteTask()` – Permanently removes a task.
- `hashPassword()` – Hashes passwords before database storage.

## Session Management

The application uses **SharedPreferences** to keep track of the currently logged-in user.

When the user logs out, the stored session information is cleared and the application returns to the Login screen.

## Technologies Used

- **Java** – Application logic and database operations
- **XML** – User interface design
- **Android Studio** – Development environment
- **SQLite** – Local database
- **SharedPreferences** – Login session management

## Project Structure

The main components of the application include:

- `Login.java` – Handles user login.
- `SignUp.java` – Handles user registration.
- `MainActivity.java` – Displays and manages the user's tasks.
- `DatabaseHelper.java` – Handles SQLite database creation and database operations.
- XML layout files – Define the user interface for the application's screens.

## Security

The application does not store user passwords as plain text. Passwords are processed using a hashing function before being stored in the SQLite database.

## Installation

1. Clone this repository.
2. Open the project in **Android Studio**.
3. Allow Gradle to sync and download the required dependencies.
4. Connect an Android device or start an Android Emulator.
5. Run the application using Android Studio.

## Author

**Azola Boloda**

## Internship

This project was developed as part of the **Oasis Infobyte Android Development Internship**.

#android #androiddevelopment #java #androidstudio #xml #sqlite #todoapp #oasisinfobyte #internship