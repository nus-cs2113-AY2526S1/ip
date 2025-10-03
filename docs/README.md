# Sugon User Guide

**Sugon** is a Java-based personal task management chatbot that helps you manage tasks, deadlines, and events efficiently with simple text commands.

---

## Quick start
1. [Introduction](#introduction)  
2. [Getting Started](#getting-started)  
   - [Project Setup](#project-setup)  
   - [Running the JAR](#running-the-jar)  
3. [Features](#features)  
   - [Add Tasks](#add-tasks)  
   - [View Tasks](#view-tasks)  
   - [Mark / Unmark Tasks](#mark--unmark-tasks)  
   - [Delete Tasks](#delete-tasks)  
   - [Exit Sugon](#exit-sugon)  
4. [Saving and Loading Tasks](#saving-and-loading-tasks)  
5. [Command Summary](#command-summary)

---

## Introduction
Sugon helps you:

- Add, view, find, and delete tasks.  
- Track deadlines and events with start/end times.  
- Mark tasks as done or not done.  
- Save your task list between sessions automatically.

---

## Getting Started

### Project Setup
1. Navigate to the project folder:  
   ```bash
   cd ip
   ```
2. Compile the project and save class files in bin:
   ```bash
   javac -d bin src/main/java/*.java
   ```
3. Create the JAR file:
   ```bash
   jar cfm Sugon.jar manifest.txt -C bin .
   ```

## Running the JAR

Run Sugon with:

```bash
java -jar Sugon.jar
```

## Features

### Add Tasks

**ToDo** – A simple task without date/time:
- Adds a task without any specific date or time.
- Useful for general tasks or reminders.
- description must be non-empty.

```bash
todo <description>
```

**Deadline** – A task with a due date:
- Adds a task with a due date.
- description must be non-empty.
- /by <YYYY-MM-DD> specifies the deadline date.
- Date must follow the format YYYY-MM-DD.
```bash
deadline <description> /by <YYYY-MM-DD>
```

**Event** – A task with start and end times:
- Adds a task with a start time and an end time.
- description must be non-empty.
- /from <start-time> specifies when the event begins.
- /to <end-time> specifies when the event ends.
- Start time must be before end time.
```bash
event <description> /from <start-time> /to <end-time>
```

**View Tasks**
List all tasks in task list:

```bash
list
```

**Mark/Unmark** Tasks.
- Marks the task at specified index
- index refers to the index number shown in the displayed task list.
- index must be positive number and within the range of tasks present within list

Mark as done:

```bash
mark <task-number>
```

Mark as not done:

```bash
unmark <task-number>
```


**Find** Tasks: Find a task whose names contain any of the keywords.
- The search is case-insensitive
- checks if the keyword substring appears within task name 

```bash
find <KEYWORD>
```

***Delete*** Tasks: Removes a task from list
- Deletes the task at specified index
- Index refers to the index number shown in the displayed task list.
- index must be positive number and within the range of tasks present within list

```bash
delete <task-number>
```

**Help** list of commands

Displays all available commands:

```bash
help
```


***Exit*** programme

Exits the application:

```bash
bye
```

## Saving and Loading Tasks

- Sugon automatically saves all tasks in `data/sugon.txt`.
- When restarted, Sugon loads previously saved tasks, ensuring no data is lost.

---

## Command Summary

| Command | Description |
|---------|-------------|
| `todo Buy groceries` | Add a ToDo task |
| `deadline Submit report /by 2025-10-05` | Add a deadline task, marking deadline by 2025-10-05|
| `event Meeting /from 14:00 /to 15:00` | Add an event, marking period 14:00 to 15:00|
| `list` | List all tasks |
| `mark 2` | Mark task 2 as done |
| `unmark 2` | Unmark task 2 |
| `delete 3` | Delete task 3 |
| `bye` | Exit Sugon |
| `find book` | Find tasks containing "book" |

