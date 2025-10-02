## User Guide

**Toothless** is a chatbot that users can use to manage tasks. Everything is done through **Command Line Interface (CLI)**, simple and quick.

## Quick Start

1. Ensure you have Java `17` or above installed in your device
2. Download the lastest `.jar` file from [here](https://github.com/MinionWolf/ip/releases/download/A-Release/ip.jar)
3. Copy the file to the folder you want to use as the *home folder* for your Toothless.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.

## Command Features

Listing Task: `list`\
Lists all the tasks that has been added\
Format: `list`

Adding ToDo: `todo`\
Adds a todo task\
Format: `todo TASKNAME`\
Example: `todo go running`

Adding Deadline: `deadline`\
Adds a deadline task\
Format: `deadline TASKNAME /by DEADLINE`\
Remarks: DEADLINE can be text or `yyyy-mm-dd` format\
Example: `deadline submit CS2113 project /by Friday`

Adding Event: `event`\
Adds a event task\
Format: `event TASKNAME /from START /to END`\
Remarks: START and END can be text or `yyyy-mm-dd` format\
Example: `event meeting /from 1pm /to 2pm`

Marking Task: `mark`\
Marks a task\
Format: `mark LISTINDEX`\
Example: `mark 1`

Unmarking Task: `unmark`\
Unmarks a task\
Format: `unmark LISTINDEX`\
Example: `unmark 1`

Deleting Task: `delete`\
Deletes a task\
Format: `delete LISTINDEX`\
Example: `delete 1`

Finding Tasks: `find`\
List all task containing the keyword provided\
Format: `find KEYWORD`\
Example: `find test`

Exiting program: `bye`\
Exits the program\
Format: `bye`

Saving the data\
Toothless will save the data in a text file once you exit the program

## Command Summary

| Action | Format, Examples |
| List | `list` |
| ToDo | `todo TASKNAME`\ eg. `todo go running` |
| Deadline | `deadline TASKNAME /by DEADLINE`\ DEADLINE can be text or `yyyy-mm-dd` format\ eg. `deadline submit CS2113 project /by Friday` |
| Event | `event TASKNAME /from START /to END`\ START and END can be text or `yyyy-mm-dd` format\ eg. `event meeting /from 1pm /to 2pm` |
| Mark | `mark LISTINDEX`\ eg. `mark 1` |
| Unmark | `unmark LISTINDEX`\ eg. `unmark 1` |
| Delete | `delete LISTINDEX`\ eg. `delete 1` |
| Find | `find KEYWORD`\ eg. `find test` |
| Exit | `bye` |

