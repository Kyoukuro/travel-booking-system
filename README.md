# Travel Booking System

This is a simple console-based travel booking example.

## How to run quickly (Windows PowerShell)

1. Ensure you have a JDK installed and both `javac` and `java` are on your PATH.
2. From repository root, run:

```powershell
.\run.ps1
```

## How to run on Linux/macOS

```bash
./run.sh
```

## Build with Maven

If you have Maven installed you can package an executable JAR (requires Java on PATH):

```powershell
mvn clean package
java -jar target\travel-booking-system-0.1.0-SNAPSHOT-shaded.jar
```

Or run with Maven directly:

```powershell
mvn -Dexec.mainClass=Main exec:java
```
