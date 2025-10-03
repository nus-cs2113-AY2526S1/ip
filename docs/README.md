# Chara User Guide

Welcome to **Chara**, your personal chatbot assistant for managing tasks.  
Chara helps you keep track of todos, deadlines, and events, all with a touch of personality.

---

## Quick Start
To run Chara:
1. Open a terminal/command prompt.
2. Navigate to the folder containing `ip.jar`, or provide the full path.
3. Run:
   java -jar ip.jar
4. You’ll be greeted by Chara and can start typing commands.
5. Type `bye` to exit when you’re done.

---

## Notes on Command Format
- Parameters in **UPPER_CASE** must be supplied by the user.
- Parameters are case-insensitive.

---

## Expected Output Style
Chara always responds with playful comments alongside your task list, e.g.:

Chara: You never fail to impress!  
Marked as done: [T][X] read book

The first square bracket preceding a task description indicates the task type (Todo, Deadline or Event).

The second bracket indicates whether the task is marked done '[X]' or not done '[ ]'.

---

## Features

### 1. Add a Todo
Adds a simple task with only a description.

**Usage:** `todo DESCRIPTION`  
**Example:** `todo read book`  
**Expected Output:**  
Chara has added "[T][ ] read book" to your list.  
Now you have 1 task(s) in your list! =)

---

### 2. Add a Deadline
Adds a task that must be completed by a specific date/time.

**Usage:** `deadline DESCRIPTION /by WHEN`  
**Example:** `deadline return book /by June 6th`

---

### 3. Add an Event
Adds a task that occurs within a time range.

**Usage:** `event DESCRIPTION /from START /to END`  
**Example:** `event project meeting /from Monday 2pm /to 4pm`

---

### 4. List Tasks
Shows all current tasks in your list.

**Usage:** `list`  
**Expected Output:**
Chara: Here's your list! (What's it for anyway?)
1. [D][ ] essay final submission (by: friday 2359)
2. [E][ ] concert (from: 19 oct 12pm to: 5pm)
3. [T][X] get supper

---

### 5. Mark/Unmark Tasks
Marks a task as done or not done.

**Usage:** `mark INDEX` or `unmark INDEX`  
**Example:** `mark 2`

---

### 6. Delete Tasks
Removes a task from the list.

**Usage:** `delete INDEX`  
**Example:** `delete 3`

---

### 7. Find Tasks
Searches for tasks containing a keyword.

**Usage:** `find KEYWORD`  
**Example:** `find book`

---

### 8. Exit
Exits the program.

**Usage:** `bye`