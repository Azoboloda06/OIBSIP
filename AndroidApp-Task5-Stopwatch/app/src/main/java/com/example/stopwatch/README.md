# Stopwatch App ⏱️

An Android stopwatch application with start, pause, and reset controls that accurately tracks elapsed time in `HH:MM:SS` format.

## Features

- Large, clearly visible time display showing elapsed time as `HH:MM:SS`.
- Start button: begins timing from zero, or resumes from a paused state.
- Pause button: freezes the timer at the current elapsed time.
- Reset button: stops the timer and resets the display back to `00:00:00`.
- Buttons visually reflect the current state — Start is disabled while running, Pause is disabled while stopped.
- Timer keeps accurate time if the user navigates away and returns to the app, handled through the Activity's `onPause`/`onResume` lifecycle methods.

## How It Works

1. The user taps Start to begin timing; the Start button becomes disabled and Pause becomes enabled.
2. The display updates once per second while the stopwatch is running.
3. Tapping Pause freezes the display at the current elapsed time and re-enables Start.
4. Tapping Start again resumes counting from where it left off, rather than starting over.
5. Tapping Reset stops the timer (if running) and returns the display to `00:00:00`.
6. If the app is backgrounded while running (e.g. the user switches apps) and later reopened, the displayed time reflects the actual elapsed time correctly, rather than losing or freezing time in the background.

## Timing Approach

The stopwatch does not rely on a simple incrementing counter, since that would drift or lose accuracy if the app is paused by the system. Instead:

- `startTime` records the system's elapsed real time (`SystemClock.elapsedRealtime()`) at the moment Start is pressed.
- `elapsedTime` accumulates the total time banked from any previous runs before a pause.
- The displayed time is always calculated as `elapsedTime + (now - startTime)`.
- A `Handler` posts a `Runnable` every second to recalculate and redraw the display while running.
- `onPause()` stops the update loop to avoid wasting resources while the app isn't visible; `onResume()` restarts it. Because the elapsed time is calculated from real clock time rather than counted, the display is always correct immediately after resuming.

## Technologies Used

- **Java** – Application logic and timing handling
- **XML** – User interface design (`ConstraintLayout`)
- **Android Studio** – Development environment
- **Android SDK** – Android application development

## Project Structure

- `MainActivity.java` – Handles Start/Pause/Reset logic, time calculation, and lifecycle management.
- `activity_main.xml` – Defines the time display and control buttons.

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