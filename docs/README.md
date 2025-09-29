# WELCOME TO HERO, THE BEST CHAT BOT

## FUNCTIONS
- [Add a To Do task](#add-a-to-do-task)
- [Add a Deadline task](#add-a-deadline-task)
- [Add an Event task](#add-an-event-task)
- [List your tasks](#list-your-tasks)
- [Mark your tasks as done](#mark-your-tasks-as-done)
- [Unmark your tasks as not done](#unmark-your-tasks-as-not-done)
- [Delete your tasks](#delete-your-tasks)
- [Find your tasks](#find-your-tasks)
- [Exit the program](#exit-the-program)


## FUNCTIONS USAGE

### Add a To Do task

Usage: ```todo [description]```

> Example: ```todo borrow book```

Output:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the task list.
____________________________________________________________
```

### Add a Deadline task

Usage: ```deadline [description] /by [deadline]```

> Example: ```deadline return book /by Sunday```

Output:
```
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 2 tasks in the task list.
____________________________________________________________
```

### Add an Event task

Usage: ```event [description] /from [start] /to [end]```

> Example: ```event project meeting /from Mon 2pm /to 4pm```

Output:
```
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm | to: 4pm)
Now you have 3 tasks in the task list.
____________________________________________________________
```

### List your tasks

Usage: ```list```

Output:
```
____________________________________________________________
Here are the tasks in your task list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (from: Mon 2pm | to: 4pm)
____________________________________________________________
```

### Mark your tasks as done

Usage: ```mark [task number]```

> Example: ```mark 3```

Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] project meeting (from: Mon 2pm | to: 4pm)
____________________________________________________________
```

### Unmark your tasks as not done

Usage: ```unmark [task number]```

> Example: ```unmark 3```

Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[E][ ] project meeting (from: Mon 2pm | to: 4pm)
____________________________________________________________
```

### Delete your tasks

Usage: ```delete [task number]```

> Example: ```delete 1```

Output:
```
____________________________________________________________
Got it. I've deleted this task:
[T][ ] borrow book
Now you have 2 tasks in the task list.
____________________________________________________________
```


### Find your tasks

Usage: ```find [text]```

> Example: ```find book```

Output:
```
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] return book (by: Sunday)
____________________________________________________________
```

### Exit the program

Usage: ```bye```

Output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```