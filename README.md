# WELCOME TO HERO, THE BEST CHAT BOT

## FUNCTIONS
1) Add a To Do task
2) Add a Deadline task
3) Add an Event task
4) List your tasks
5) Mark your tasks as done
6) Mark your tasks as not done
7) Delete your tasks
8) Find your tasks

## FUNCTIONS USAGE

### 1) Add a To Do task

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

### 2) Add a Deadline task

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

### 3) Add an Event task

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

### 4) List your tasks

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

### 5) Mark your tasks as done

Usage: ```mark [task number]```

> Example: ```mark 3```

Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[E][X] project meeting (from: Mon 2pm | to: 4pm)
____________________________________________________________
```

### 6) Unmark your tasks as not done

Usage: ```unmark [task number]```

> Example: ```unmark 3```

Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[E][ ] project meeting (from: Mon 2pm | to: 4pm)
____________________________________________________________
```

### 7) Delete your tasks

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


### 8) Find your tasks

Usage: ```find [text]```

> Example: ```find book```

Output:
```
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] return book (by: Sunday)
____________________________________________________________
```

### 9) Exit the program

Usage: ```bye```

Output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```