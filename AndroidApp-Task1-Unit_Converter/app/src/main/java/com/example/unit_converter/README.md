# Unit Converter 📱

An Android application that converts values between different units of measurement. The app allows users to select a measurement category, choose the source and target units, enter a value, and view the converted result.

## Features

- Convert values between different units of measurement.
- Supports three measurement categories:
    - Length
    - Weight
    - Temperature
- Select a source unit using a dropdown (Spinner).
- Select a target unit using a dropdown (Spinner).
- Enter a numeric value to convert.
- Display the converted result with its unit.
- Input validation for empty or invalid values.
- Category selection automatically updates the available units.

## Supported Conversions

### Length
- Centimetres (cm)
- Metres (m)

### Weight
- Kilograms (kg)
- Grams (g)

### Temperature
- Celsius (°C)
- Fahrenheit (°F)

## Technologies Used

- **Java** – Application logic and unit conversion calculations
- **XML** – User interface design
- **Android Studio** – Development environment
- **Android SDK** – Android application development

## How It Works

1. Select a measurement category.
2. Select the unit to convert **from**.
3. Select the unit to convert **to**.
4. Enter the value you want to convert.
5. Press the **Convert** button.
6. The converted value is displayed on the screen.

## Input Validation

The application checks whether the input field contains a valid numeric value. If the field is empty or the input is invalid, the application displays a Toast message asking the user to enter a valid value.

## Project Structure

The main components of the application include:

- `MainActivity.java` – Handles the user interface, Spinner selections, input, and conversion logic.
- `activity_main.xml` – Defines the application's user interface.
- `strings.xml` – Contains text resources used by the application.

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

#android #androiddevelopment #java #androidstudio #xml #oasisinfobyte #internship