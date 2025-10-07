# Prime Chatbot User Guide

![img.png](img.png)

Welcome to **Prime**, your intelligent task management assistant!  
Prime helps you add, manage, and find your tasks efficiently.

---

## Command 1: Adding Todos

Add a todo task

Example: `todo do math homework`

A todo task will be added to the task list

```
    ____________________________________________________________
    Got it. I've added this task:
    [T][ ] do math homework
    Now you have 1 tasks in your task list.
    ____________________________________________________________
```

## Command 2: Adding Deadlines

Add a deadline task, a task that has to be completed by a certain deadline

Example: `deadline submit math assignment /by September 23`

A deadline task will be added to the task list

```
    ____________________________________________________________
    Got it. I've added this task:
    [D][ ] submit math assignment (by: September 23)
    Now you have 2 tasks in your task list.
    ____________________________________________________________
```

## Command 3: Adding Events

Add an event task, a task that has both start and end date/time

Example: `event attend math consultation /from 3pm /to 2pm`

An event task will be added to the task list

```
    ____________________________________________________________
    Got it. I've added this task:
    [E][ ] attend math consultation (from: 3pm to: 2pm)
    Now you have 3 tasks in your task list.
    ____________________________________________________________
```

## Command 4: Listing Tasks

List all the current tasks in the task list with their respective status.

Example: `list`

All tasks in the task list is shown

```
    ____________________________________________________________
    Here are your tasks in your list:
    1.[T][ ] do math homework
    2.[D][ ] submit math assignment (by: September 23)
    3.[E][ ] attend math consultation (from: 3pm to: 2pm)
    ____________________________________________________________
```

## Command 5: Marking Tasks (Tick)

Ticking a task as completed by specifying its number in the list command.

Example: `mark 1`

The marked task is marked as done `[X]`

```
    ____________________________________________________________
    Nice! I've marked this task as done:
      [T][X] do math homework
    ____________________________________________________________
```

## Command 6: Unmarking Tasks (Untick)

Unticking a task as not completed by specifying its number in the list command.

Example: `unmark 1`

The unmarked task is marked as not done `[ ]`

```
    ____________________________________________________________
    OK, I've marked this task as not done yet:
      [T][ ] do math homework
    ____________________________________________________________
```

## Command 7: Finding Tasks using a Keyword

Finding tasks in the task list that contains a specific keyword entered by the user

Example: `find math`

All tasks' description containing the keyword will be listed

```
    ____________________________________________________________
    Found 3 tasks!
    Here are the matching tasks from the list: 
    [T][ ] do math homework
    [D][ ] submit math assignment (by: September 23)
    [E][ ] attend math consultation (from: 3pm to: 2pm)
    ____________________________________________________________
```

## Command 8: Delete Task

Delete a task by specifying its number in the list command.

Example: `delete 1`

The specified task will be deleted

```
    ____________________________________________________________
    Got it. I've deleted this task:
    [T][ ] do math homework
    Now you have 2 tasks in your task list.
    ____________________________________________________________
```

## Command 9: Bye

Stop talking with Prime. 

Example: `bye`

The chat application will close

```
    ____________________________________________________________
    Bye Human, I wish you a nice day. Hope to see you again soon!
    ____________________________________________________________
    ____________________________________________________________
```
