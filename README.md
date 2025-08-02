# Task Manager

A console-based task managing tool built with Java, designed to help you manage personal tasks with deadlines. This project demonstrates core Java concepts including object-oriented programming and file I/O.

## ✨ Features

- 📝 Create and manage tasks  
- ⏰ Set deadlines  
- 🎵 Background music playback (runs in a separate thread)  
- 💾 Save and load tasks to/from disk  
- 📂 Structured using Maven conventions  
- 🔁 Cross-platform (runs on any system with Java 17+)  

## 📦 Tech Stack

- Java 17+  
- File I/O  
- Java Sound API for audio  

## 📁 Project Structure

This project follows the standard Maven directory layout:

    java-task-manager/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   └── resources/
    │   └── test/
    │       ├── java/
    ├── target/
    ├── pom.xml
    ├── README.md


## How to use

1. Install JDK.
1. Clone the repository.
1. Create two files inside `src/main/.data` directory:
    - `.settings.properties`
    - `.tasks.csv `

In `.settings.properties`, add your settings like this:

    These are settings
    Thu Jul 31 18:27:33 PDT 2025
    color=
    username=

In `.tasks.csv`, add the CSV header and a mandatory empty line below it:

    id,title,body,start,end
    [mandatory empty line here]
