# Bob project template

This is a project template for a greenfield Java project. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE)

  


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
