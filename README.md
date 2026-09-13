# OIBSIP - Android App Development Internship 📱

This repository contains all Android application projects completed as part of the **Oasis Infobyte Android App Development Internship (OIBSIP)**. Each task is a separate, self-contained Android Studio project focused on a specific concept, ranging from unit conversion and CRUD data management to quiz logic and time-based operations.

## Repository Structure

Each project follows the naming convention:

```
AndroidApp-Task[Number]-[ProjectName]/
```

| Task | Project | Description |
|------|---------|--------------|
| Task 1 | [Unit Converter](./AndroidApp-Task1-Unit_Converter) | Converts values between common units of measurement across multiple categories (length, weight, and more). |
| Task 2 | [To-Do List](./AndroidApp-Task2-ToDo_List) | A secure task management app with user registration/login and per-user, database-backed to-do lists. |
| Task 3 | [Quiz App](./AndroidApp-Task3-Quiz) | A multiple-choice quiz app with score tracking, shuffled questions, and immediate answer feedback. |
| Task 4 | [Calculator](./AndroidApp-Task4-Calculator) | A basic arithmetic calculator with a button-grid interface and expression display. |
| Task 5 | [Stopwatch](./AndroidApp-Task5-Stopwatch) | A stopwatch app with start, pause, and reset controls that survives app lifecycle changes. |

Each project folder contains its own `README.md` with a full breakdown of that app's features, technical approach, and project structure.

## Task Summaries

### Task 1 — Unit Converter
Converts numeric values between units across at least three measurement categories (length, weight, and a third category of choice). Includes source/target unit dropdowns, input validation with Toast error messages, and a category selector that resets the unit options accordingly.

### Task 2 — To-Do List
A task management app requiring user registration and login before accessing a personal to-do list. Passwords are hashed (not stored in plain text) using SQLite for persistence. Tasks are linked per user, support marking complete and permanent deletion, and the app displays a friendly empty-state message when no tasks exist.

### Task 3 — Quiz App
A multiple-choice quiz with a Welcome screen, at least 10 questions, a live question counter, and immediate visual feedback (green for correct, red for incorrect) after each answer. Questions are shuffled on each attempt, and a Results screen shows the final score with a Restart option.

### Task 4 — Calculator
A calculator supporting basic arithmetic (addition, subtraction, multiplication, division) through a responsive button grid. Handles division-by-zero gracefully, supports backspace and clear, and displays the full expression (e.g. `12 + 5 = 17`) rather than only the final result.

### Task 5 — Stopwatch
A stopwatch with Start, Pause, and Reset controls, displaying elapsed time in `HH:MM:SS` format. Timing is calculated from real elapsed system time rather than a simple counter, so accuracy is preserved even if the app is backgrounded and reopened mid-run.

## Technologies Used

- **Java** – Application logic across all projects
- **XML** – User interface design (ConstraintLayout, GridLayout)
- **SQLite** – Local data persistence (Task 2)
- **Android Studio** – Development environment
- **Android SDK** – Android application development

## Installation

Each project can be run independently:

1. Clone this repository.
2. Open the desired task folder (e.g. `AndroidApp-Task4-Calculator`) directly in **Android Studio** as its own project.
3. Allow Gradle to sync and download the required dependencies.
4. Connect an Android device or start an Android Emulator.
5. Run the application using Android Studio.

## Author

**Azola Boloda**

## Internship

These projects were developed as part of the **Oasis Infobyte Android App Development Internship (OIBSIP)**.

#android #androiddevelopment #java #androidstudio #xml #oasisinfobyte #internship
