# Bob User Guide

Bob is a simple task manager chatbot. You can add, list, mark, find, and delete tasks!
There are 3 types of tasks:
- **Todo** - a task to be done with no deadline
- **Deadline** - a task that has a deadline
- **Event** - a task that has a start and end time
Commands:

### 1. Add a Todo
```
todo DESCRIPTION
```
Adds a todo task.  
Example:
```
todo read book
```

---

### 2. Add a Deadline
```
deadline DESCRIPTION /by DATE
```
Adds a task with a deadline.  
Example:
```
deadline return book /by Sunday
```

---

### 3. Add an Event
```
event DESCRIPTION /from START /to END
```
Adds an event task with start and end times.  
Example:
```
event meeting /from 2pm /to 4pm
```

---

### 4. List all tasks
```
list
```
Shows all tasks you have added.  
Example:
```
list
```

---

### 5. Mark a task as done
```
mark TASK_NUMBER
```
Marks the given task as completed.  
Example:
```
mark 2
```

---

### 6. Unmark a task
```
unmark TASK_NUMBER
```
Marks the given task as **not done**.  
Example:
```
unmark 2
```

---

### 7. Delete a task
```
delete TASK_NUMBER
```
Deletes the task from the list.  
Example:
```
delete 3
```

---

### 8. Find tasks by keyword
```
find KEYWORD
```
Searches for tasks that contain the keyword.  
Example:
```
find book
```

---

### 9. Exit the program
```
bye
```
Closes Bob.  
Example:
```
bye
```

---

## Saving

- Your tasks are automatically saved to a file (`data/bob.txt`).
- When you run Bob again, it will load your previous tasks.

---

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
