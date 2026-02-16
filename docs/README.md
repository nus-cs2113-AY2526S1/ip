# Pep Chatbot – User Guide

Pep is your friendly task‑tracking chatbot that helps you manage todos, deadlines, and events right from the command line. This guide explains how to use Pep’s features.

---

## Quick Start

1. Ensure you have Java 11 or above installed.
2. Download the Pep JAR file.
3. Run Pep from the terminal:
   ```bash
   java -jar pep.jar
   ```
4. Type a command and press **Enter**.
5. Type `bye` to exit.

---

## Features

### Viewing the task list: `list`
Shows all tasks currently in your list.

**Format:**
```
list
```

---

### Adding a todo: `todo`
Adds a simple task without date/time.

**Format:**
```
todo DESCRIPTION
```

**Example:**
```
todo read book
```

---

### Adding a deadline: `deadline`
Adds a task with a due date.

**Format:**
```
deadline DESCRIPTION /by yyyy-MM-dd
```

**Example:**
```
deadline return book /by 2019-12-02
```
**Displayed as:**
```
[D][ ] return book (by: Dec 02 2019)
```
---

### Adding an event: `event`
Adds a task with a start and end time.

**Format:**
```
event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
```

**Example:**
```
event project meeting /from 2019-12-02 1400 /to 2019-12-02 1600
```

**Displayed as:**
```
[E][ ] project meeting (from: Dec 02 2019 14:00 to: Dec 02 2019 16:00)
```

---

### Marking a task as done: `mark`
Marks a task as completed.

**Format:**
```
mark INDEX
```

**Example:**
```
mark 2
```

---

### Unmarking a task: `unmark`
Marks a task as not done.

**Format:**
```
unmark INDEX
```

**Example:**
```
unmark 2
```

---

### Deleting a task: `delete`
Deletes a task from the list.

**Format:**
```
delete INDEX
```

**Example:**
```
delete 3
```

---

### Finding tasks: `find`
Finds tasks containing a keyword.

**Format:**
```
find KEYWORD
```

**Example:**
```
find book
```

---

### Exiting the program: `bye`
Closes the chatbot.

**Format:**
```
bye
```

---

## Saving the data
- Pep automatically saves your tasks to `./data/pep.txt` after every change.
- When you restart Pep, your tasks are loaded from this file.
- If the file is missing, Pep creates a new one.

---

## Error Handling
- Pep will show an error message if you type an unknown command or invalid format.
- Example:
  ```
  OOPS!!! I'm sorry, but I don't know what that means :-(
  ```

## Summary of Commands

| Action       | Format                                             | Example                                        |
|--------------|----------------------------------------------------|------------------------------------------------|
| **List**     | `list`                                             | `list`                                         |
| **Todo**     | `todo DESCRIPTION`                                 | `todo read book`                               |
| **Deadline** | `deadline DESCRIPTION /by yyyy-MM-dd`              | `deadline return book /by 2019-12-02`          |
| **Event**    | `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm` | `event project meeting /from 2019-12-02 1400 /to 2019-12-02 1600` |
| **Mark**     | `mark INDEX`                                       | `mark 2`                                       |
| **Unmark**   | `unmark INDEX`                                     | `unmark 2`                                     |
| **Delete**   | `delete INDEX`                                     | `delete 3`                                     |
| **Find**     | `find KEYWORD`                                     | `find book`                                    |
| **Exit**     | `bye`                                              | `bye`                                          |

---

## FAQ

**Q: Where are my tasks saved?**  
A: In `./data/pep.txt` in the same folder where you run Pep.

**Q: What happens if I edit the save file manually?**  
A: Pep will try to load it. If a line is corrupted, Pep skips it and continues loading the rest.