# Calendar

A desktop calendar application written in Java with JavaFX, developed as a university project at SUPSI.

## Features

- Monthly view with navigation to the previous and next month
- Create and edit events, each with a date, time and type
- Five event types: lesson, workshop, exam, meeting and holiday
- Save and load the calendar to and from a file
- Interface available in four languages: English, Italian, German and French

## Download and run

Runnable JARs are included in this repository. They require Java 11 or later.

| Platform | File |
|---|---|
| Linux | [`Executable-Linux.jar`](Executable-Linux.jar) |
| macOS | [`Executable-MacOS.jar`](Executable-MacOS.jar) |
| Windows | [`Executable-Windows.jar`](Executable-Windows.jar) |

```bash
java -jar Executable-Linux.jar
```

## Build from source

Requires JDK 11+ and Maven.

```bash
cd calendar/backend && mvn install
cd ../frontend && mvn javafx:run
```

## Project structure

The project is split into two Maven modules:

- **`backend`**: domain model (events, days, event types), persistence and localized strings. Organized in controllers, services and repositories.
- **`frontend`**: JavaFX user interface (calendar grid, menus, event dialogs) that uses the backend.

## Tech stack

Java 11 · JavaFX 14 · Maven · json-simple

## License

[MIT](LICENSE)
