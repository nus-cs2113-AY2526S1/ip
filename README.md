# Sugon User Guide

Welcome to **Sugon**, your friendly personal task management chatbot! Sugon helps you keep track of your tasks, deadlines, and events with simple text commands.

---

## Introduction
Sugon is a Java-based chatbot designed to help you organize your tasks efficiently. With Sugon, you can:

- Add, view, find and delete tasks.
- Track deadlines and events with start/end times.
- Mark tasks as done or not done.
- Save your task list between sessions automatically.

---

## Getting Started 
 
**Creating and running JAR file**
### Navigate to the Project Folder:
Run `cd ip`

### Compile the Project and save class files in bin:
Run `javac -d bin src/*.java`

### Create JAR file
Run `jar cfm Sugon.jar manifest.txt -C bin .` to create and output new JAR filename 

### Run Sugon.Jar:
Run `java -jar Sugon.jar`  
Sugon will automatically load any saved tasks from `data/sugon.txt`.

---

## Features

### Add Tasks

- **ToDo**: A simple task without a date/time.  
  Run `todo <description>`

- **Deadline**: A task with a due date.  
  Run `deadline <description> /by <YYYY-MM-DD>`

- **Event**: A task with start and end times.  
  Run `event <description> /from <start-time> /to <end-time>`

---

### View Tasks
List all tasks: `list`

---

### Mark / Unmark Tasks

- Mark as done: `mark <task-number>`  
- Mark as not done: `unmark <task-number>`

---

### Delete Tasks
Remove a task: `delete <task-number>`

---

### Exit Sugon
Exit the application: `bye`

---

## Saving and Loading Tasks
- Sugon automatically saves all tasks in `data/sugon.txt`.  
- When Sugon is restarted, it loads previously saved tasks, so you never lose your data.

---

## Command Summary

- `todo Buy groceries` – Add a ToDo task  
- `deadline Submit report /by 2025-10-05` – Add a deadline task  
- `event Meeting /from 14:00 /to 15:00` – Add an event  
- `list` – List all tasks  
- `mark 2` – Mark row 2 task as done  
- `unmark 2` – Unmark row 2 task  
- `delete 3` – Delete row 3 task  
- `bye` – Exit Sugon
- `find book` - Find Task with substring 'book'