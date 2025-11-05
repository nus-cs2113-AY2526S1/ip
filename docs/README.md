# Tarnia User Guide 😊

Tarnia is a simple command-line task manager that helps you keep track of tasks such as **todos**, **deadlines**, and **events**.

---

## Running the Program 😈
1. Ensure you have **Java 17** installed.  
   You can check with:  
   `java -version`  
2. Download our program through here:
   [https://github.com/m-xrtin/ip/releases/tag/v1.0](https://github.com/m-xrtin/ip/releases/tag/A-Release)
3. In the directory containing our jar, run:  
   `java -jar tarnia.jar`  

---

## Features 😎

- **Todo** – add a basic task with just a description.  
- **Deadline** – add a task with a description and a due date.  
- **Event** – add a task with a description and date/time.  
- **List** all tasks with their completion status.  
- **Mark** and **unmark** tasks as done.  
- **Delete** tasks.  
- **Find** tasks by keyword.  
- Tasks are **saved automatically** between sessions.

---

## Commands 🤪

**Add a Todo**  
`todo <description>`  
Example: `todo read book`  

**Add a Deadline**  
`deadline <description> /by <due date>`  
Example: `deadline return book /by friday`  

**Add an Event**  
`event <description> /from <start time> /to <end time>`  
Example: `event project meeting /from 2pm /to 4pm`  

**List Tasks**  
`list`  

**Mark a Task**  
`mark <task index>`  

**Unmark a Task**  
`unmark <task index>`  

**Delete a Task**  
`delete <task index>`  

**Find by Keyword**  
`find <keyword>`  

**Exit**  
`bye`  

## Notes 🥳
- Commands are case-insensitive.
- Tasks are saved to a local file and reloaded when you run the program again.
- Date/time is saved as strings, so a bit more flexibility in terms of format.
