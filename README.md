# Espresso Task Manager

Espresso is a lightweight command-line task manager for tracking todos, deadlines, and events. It’s fast, minimal, and designed for users who prefer typing to clicking. All tasks are saved automatically to disk.


- [Quick Start](#-quick-start)
- [Features](#-features)
    - [Notes on Command Format](#notes-on-command-format)
- [Commands](#-commands)
    - [`help`](#help)
    - [`todo`](#todo)
    - [`deadline`](#deadline)
    - [`event`](#event)
    - [`list`](#list)
    - [`mark`](#mark)
    - [`unmark`](#unmark)
    - [`delete`](#delete)
    - [`find`](#find)
    - [`bye`](#bye)
- [Saving and Data File](#-saving-and-data-file)
- [FAQ](#-faq)
- [Known Issues](#-known-issues)
- [Command Summary](#-command-summary)

---

## Quick Start

1. Ensure you have **Java 17 or above** installed.
2. Download the latest `ip.jar` file from [here](https://github.com/xelisce/ip/releases).
3. Place it in your desired working directory.
4. Open a terminal, and navigate to that directory with `cd` and run:

```bash
java -jar ip.jar
```

5. Type commands directly into the console and press Enter to execute.

---

## Features

* Add tasks: `todo`, `deadline`, `event`
* Mark and unmark tasks as done
* Delete tasks by index
* List all tasks
* Find tasks by keyword(s)
* Automatic persistent storage on every change
* Input parsing and validation with user-friendly messages

---

### Notes on Command Format

* Words in `UPPER_CASE` are parameters to be supplied by the user.\
  Example: `todo DESCRIPTION` → `todo read book`

* Parameters are **positional** and must follow the expected order.

* Extraneous parameters for commands that don’t accept them (e.g. `list`, `bye`) will be ignored.

---

## Commands

### `help`

Displays a message explaining how to access help.

**Format:**

```
help
```

---

### `todo`

Adds a simple task with no time constraints.

**Format:**

```
todo DESCRIPTION
```

**Examples:**

```
todo read book
todo submit assignment
```

---

### `deadline`

Adds a task with a due date or time.

**Format:**

```
deadline DESCRIPTION /by DEADLINE
```

**Examples:**

```
deadline submit report /by Friday
deadline pay bills /by 2025-09-30
```

---

### `event`

Adds a task with a start and end time.

**Format:**

```
event DESCRIPTION /from START /to END
```

**Examples:**

```
event team meeting /from 2pm /to 4pm
event conference /from Monday /to Wednesday
```

---

### `list`

Displays all tasks with their status and type.

**Format:**

```
list
```

---

### `mark`

Marks the specified task as completed.

**Format:**

```
mark INDEX
```

**Example:**

```
mark 2
```

---

### `unmark`

Marks the specified task as not completed.

**Format:**

```
unmark INDEX
```

**Example:**

```
unmark 2
```

---

### `delete`

Removes the specified task from the list.

**Format:**

```
delete INDEX
```

**Example:**

```
delete 3
```

---

### `find`

Searches for tasks whose descriptions contain any of the given keywords.

**Format:**

```
find KEYWORD [MORE_KEYWORDS]
```

**Examples:**

```
find report
find meeting deadline
```

---

### `bye`

Saves all tasks and exits the application.

**Format:**

```
bye
```

---

## Saving and Data File

All tasks are saved automatically to disk after any change. No manual saving is required.

Tasks are stored in a plain text file. Each line represents a task in the format:

```
TYPE | DONE | DESCRIPTION [| METADATA...]
```

* `TYPE` is a single-letter code (e.g., `T`, `D`, `E`)
* `DONE` is `1` (done) or `0` (not done)
* `METADATA` includes task-specific data like deadlines or event times

Advanced users may edit this file directly, but malformed edits may cause tasks to be skipped on load. Always back up the file before manual changes.

---

## FAQ

**Q:** How do I transfer my tasks to another computer?\
**A:** Copy the task file from your current working directory to the same location on the new machine before launching the app.

---

## Known Issues

* Invalid or malformed lines in the data file will be skipped during startup.
* Manual edits to the data file can produce unexpected behavior if formatting rules are not followed.

---

## Command Summary

| Action        | Format / Example                     | 
|---------------|--------------------------------------| 
| Add Todo      | `todo read book`                     | 
| Add Deadline  | `deadline submit report /by Friday`  | 
| Add Event     | `event meeting /from 2pm /to 4pm`    | 
| Mark          | `mark 2`                             | 
| Unmark        | `unmark 2`                           | 
| Delete        | `delete 3`                           | 
| Find          | `find report meeting`                | 
| List          | `list`                               | 
| Help          | `help`                               | 
| Exit          | `bye`                                | 

---

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Espresso.java` file, right-click it, and choose `Run Espresso.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
   .·:''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''':·.
   : : ███████╗███████╗██████╗ ██████╗ ███████╗███████╗███████╗ ██████╗  : :
   : : ██╔════╝██╔════╝██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝██╔═══██╗ : :
   : : █████╗  ███████╗██████╔╝██████╔╝█████╗  ███████╗███████╗██║   ██║ : :
   : : ██╔══╝  ╚════██║██╔═══╝ ██╔══██╗██╔══╝  ╚════██║╚════██║██║   ██║ : :
   : : ███████╗███████║██║     ██║  ██║███████╗███████║███████║╚██████╔╝ : :
   : : ╚══════╝╚══════╝╚═╝     ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝ ╚═════╝  : :
   '·:...................................................................:·'
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.