# SuperIdol project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   ____________________________________________________________
   Hello! I'm SuperIdol
   <a SuperIdol logo>
   What can I do for you?
   ____________________________________________________________
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Notes about command format

* Words in **`UPPER_CASE`** are parameters supplied by the user.
  e.g., in `todo <DESCRIPTION>`, `DESCRIPTION` is a parameter.
* Parameters **MUST** be in any order.
* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit`) will be ignored.
  e.g., `list 123` will be interpreted as `list`.

## Usage Guide

SuperIdol accepts commands via the command line. Use the following commands to manage your tasks:

* **`list`**: Shows all tasks in your list.
```angular2html
list
____________________________________________________________
1. [T][ ] Buy groceries
2. [T][X] Call Mom
3. [D][X] Submit final paper (by: Oct 05 2025)
4. [D][ ] Team meeting slides (by: Sep 25 2025)
5. [E][ ] Project presentation (from: Oct 15 2025 to: Oct 15 2025)
6. [E][ ] Company retreat (from: Nov 20 2025 to: Nov 22 2025)
____________________________________________________________
```
* **`todo <DESCRIPTION>`**: Adds a new todo task.
```angular2html
todo run
____________________________________________________________
Got it. I've added this task:
[T][ ] run
Now you have 7 tasks in the list.
____________________________________________________________
```
* **`deadline <DESCRIPTION> /by <TIME: YYYY-MM-DD>`**: Adds a new task with a deadline.
```angular2html
deadline submit assignment /by 2025-10-10
____________________________________________________________
Got it. I've added this task:
[D][ ] submit assignment (by: Oct 10 2025)
Now you have 8 tasks in the list.
____________________________________________________________
```
* **`event <DESCRIPTION> /from <BEGIN: YYYY-MM-DD> /to <END: YYYY-MM-DD>`**: Adds a new event.
```angular2html
event fan meeting /from 2025-10-10 /to 2025-10-13
____________________________________________________________
Got it. I've added this task:
[E][ ] fan meeting (from: Oct 10 2025 to: Oct 13 2025)
Now you have 9 tasks in the list.
____________________________________________________________
```
* **`mark <INDEX>`**: Marks a task as completed.
```angular2html
mark 5
____________________________________________________________
Nice! I've marked this task as done:
[E][X] Project presentation (from: Oct 15 2025 to: Oct 15 2025)
____________________________________________________________
```
* **`unmark <INDEX>`**: Unmarks a task.
```angular2html
unmark 5
____________________________________________________________
OK, I've marked this task as not done yet:
[E][ ] Project presentation (from: Oct 15 2025 to: Oct 15 2025)
____________________________________________________________
```
* **`delete <INDEX>`**: Deletes a task from the list.
```angular2html
delete 5
____________________________________________________________
Noted. I've removed this task:
[E][ ] Project presentation (from: Oct 15 2025 to: Oct 15 2025)
Now you have 8 tasks in the list.
____________________________________________________________
```
* **`find <KEYWORD>`**: Finds tasks matching a keyword.
```angular2html
find presentation
____________________________________________________________
Here are the matching tasks in your list:
Nothing found
____________________________________________________________
find meeting
____________________________________________________________
Here are the matching tasks in your list:
1. [D][ ] Team meeting slides (by: Sep 25 2025)
2. [E][ ] fan meeting (from: Oct 10 2025 to: Oct 13 2025)
____________________________________________________________
```
* **`print <TIME: YYYY-MM-DD>`**: Prints tasks that valid for a specific date.
```angular2html
print 2025-01-01
____________________________________________________________
[D][X] Submit final paper (by: Oct 05 2025)
[D][ ] Team meeting slides (by: Sep 25 2025)
[E][ ] Company retreat (from: Nov 20 2025 to: Nov 22 2025)
[D][ ] submit assignment (by: Oct 10 2025)
[E][ ] fan meeting (from: Oct 10 2025 to: Oct 13 2025)
____________________________________________________________
```
* **`exit`**: Exits the application. **MUST** run this command to saving tasks.
```angular2html
exit
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```