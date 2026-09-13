# Calculator App 🧮

An Android calculator application that performs basic arithmetic operations through a clean, responsive button grid interface.

## Features

- Large display TextView showing the current input and the running expression.
- Number buttons (0–9) and a decimal point button.
- Operator buttons: addition, subtraction, multiplication, and division.
- Equals button to evaluate the current expression.
- Clear (AC) button to reset the calculator to its initial state.
- Backspace (DEL) button to delete the last entered character.
- Division-by-zero handling: displays "Error" instead of crashing.
- Expression stays visible after evaluation (e.g. `12 + 5 = 17`) instead of only showing the result.
- Supports chaining calculations by continuing from a previous result.
- Button grid built using `GridLayout` nested inside a `ConstraintLayout`.
- Handles rapid, repeated button taps without crashing.

## How It Works

1. The user taps number buttons to build the first value, shown live on the display.
2. Tapping an operator (+, −, ×, ÷) locks in the first number and appends the operator to the running expression.
3. The user taps number buttons again to build the second value.
4. Tapping equals evaluates the expression and appends the result to the display (e.g. `12 + 5 = 17`).
5. Tapping a digit right after a result starts a brand-new expression; tapping an operator right after a result continues a calculation from that result.
6. Dividing by zero displays "Error" and safely resets the calculator's internal state.
7. The backspace button removes the last character of the number currently being typed.
8. The clear button resets the display and all stored values back to zero.

## Calculation Approach

Rather than parsing a full multi-operator expression, the app uses a simple state-based approach:

- `currentInput` (`StringBuilder`) – the number currently being typed.
- `displayExpression` (`StringBuilder`) – the full expression shown on screen so far.
- `firstNumber` and `secondNumber` (`double`) – the two operands of the active calculation.
- `currentOperator` (`String`) – the operator selected between the two operands.

When equals is pressed, the two operands are combined using a `switch` statement based on the selected operator, and the result is converted back into a display-friendly string (whole numbers are shown without a trailing `.0`).

## Technologies Used

- **Java** – Application logic and calculation handling
- **XML** – User interface design (`ConstraintLayout` and `GridLayout`)
- **Android Studio** – Development environment
- **Android SDK** – Android application development

## Project Structure

- `MainActivity.java` – Handles all button interactions, input building, and arithmetic evaluation.
- `activity_main.xml` – Defines the display and the button grid layout.

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