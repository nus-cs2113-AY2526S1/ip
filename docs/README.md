# Luke User Guide

Meet Luke — your smart, reliable companion for staying on top of life 📅✨. Whether it's saving your to-dos, tracking
deadlines, or remembering important events, Luke keeps everything organized and right at your fingertips.

## Listing all tasks

You can view all added tasks with the `list` command.

```
list

eg.
list
1.[T][ ] borrow book
2.[D][X] return book (by:Dec 25 2025)
3.[E][ ] project meeting (from:mon 2pm to 4pm)
```

The first checkbox indicates the type of task:

`T`: Todo

`D`: Deadline

`E`: Event

And the second checkbox indicates if the task has been marked

## Adding Todos

You can add todos to Luke with the `todo` command.

```
todo <task description>

eg.
todo borrow book
added: borrow book
```

## Adding Deadlines

You can add deadlines to Luke with the `deadline` command.

`Note: <deadline> needs to be in a yyyy-mm-dd format`

```
deadline <task description> /by <deadline>

eg.
deadline return book /by 2025-12-25
added: return book
```

## Adding Events

You can add events to Luke with the `event` command.

```
event <task description> /from <start date> /to <end date>

eg.
event project meeting /from mon 2pm /to 4pm
added: project meeting
```

## Marking/Unmarking tasks

You can mark or unmark tasks using the `mark` and `unmark` commands.

`Note: <index> refers to the index of the task`

```
mark <index>

eg.
mark 2
marked task return book as done
```

## Finding tasks

You can search for tasks using keywords using the `find` command.

```
find <keyword>

eg.
find meeting
1.[E][ ] project meeting (from:mon 2pm to 4pm)
```

## Deleting tasks

You can delete tasks using the `delete` command.

`Note: <index> refers to the index of the task`

```
delete <index>

eg.
delete 1
removed: borrow book
```

## Exiting Luke
You can exit the program using the `bye` command.

```
bye

eg.
bye
____________________________________________________________
Bye Bye
____________________________________________________________
```