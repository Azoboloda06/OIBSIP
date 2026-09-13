# Quiz App 🌌

An Android multiple-choice quiz application based on astronomy and space. Users answer a series of questions, receive immediate visual feedback, and receive their final score at the end of the quiz.

## Features

- Welcome screen with a Start Quiz button.
- 10 astronomy-related multiple-choice questions.
- Four answer options for each question.
- Question counter showing the user's progress.
- Immediate visual feedback after selecting an answer.
- Correct answers are highlighted in green.
- Incorrect answers are highlighted in red.
- Answer options are disabled after an answer is selected.
- Questions are shuffled each time the quiz starts.
- Score tracking throughout the quiz.
- Separate Results screen.
- Results display:
    - Total score
    - Number of correct answers
    - Number of incorrect answers
- Restart Quiz button.

## Quiz Topic

The quiz focuses on basic astronomy and space science, including topics such as:

- The Solar System
- Planets
- Stars
- Galaxies
- Gravity
- Space exploration
- Light-years
- Black holes

The questions are designed to be accessible to a general audience while including a small number of more challenging questions.

## How It Works

1. The user starts the quiz from the Welcome screen.
2. The application randomly shuffles the 10 questions.
3. One question is displayed at a time.
4. The user selects one of four answer options.
5. The selected answer is immediately highlighted:
    - Green for a correct answer.
    - Red for an incorrect answer.
6. The user proceeds using the Next button.
7. On the final question, the Next button is replaced by a Finish button.
8. The final results are displayed on the Results screen.
9. The user can restart the quiz and receive a newly shuffled question order.

## Question Management

The quiz uses a custom `Question` class rather than creating a separate Activity for every question.

Each `Question` object contains:

- The question text.
- Four answer options.
- The index of the correct answer.

The questions are stored in an `ArrayList<Question>` and shuffled using `Collections.shuffle()`.

This allows the same quiz screen to display all 10 questions dynamically.

## Score Tracking

The application keeps track of:

- Correct answers
- Incorrect answers

The score is updated as the user progresses through the quiz and is passed to the Results screen using an Android `Intent`.

## Technologies Used

- **Java** – Application logic and quiz functionality
- **XML** – User interface design
- **Android Studio** – Development environment
- **Android SDK** – Android application development

## Project Structure

The main components of the application include:

- `MainActivity.java` – Handles the Welcome screen and starts the quiz.
- `QUIZ.java` – Displays questions, handles answer selection, provides feedback, and tracks scores.
- `Question.java` – Represents an individual quiz question and its answer options.
- `Results.java` – Displays the user's final results and handles quiz restart.
- XML layout files – Define the user interface for the Welcome, Quiz, and Results screens.
- `colors.xml` – Contains the colours used for correct and incorrect answer feedback.

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

#android #androiddevelopment #java #androidstudio #xml #quizapp #astronomy #oasisinfobyte #internship