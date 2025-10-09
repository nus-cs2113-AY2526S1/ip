package command;

import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

// Represents a command to add a Todo task
public class AddTodoCommand extends Command {
    // Description of the todo task
    private String taskDescription;

    // Constructor initializes the todo task description
    public AddTodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    // Adding a todo does not cause the program to exit
    @Override
    public boolean isExit() {
        return false;
    }

    // Executes the add-todo command:
    // 1. Create a new Todo object
    // 2. Add it to the user's task list
    // 3. Display confirmation to the user via UI
    @Override
    public void execute(Ui ui, Storage storage, TaskList listOfUserTasks) {
        Todo todo = new Todo(taskDescription); // create todo
        listOfUserTasks.add(todo);             // add it to the list
        ui.showAddedTodo(todo);                // show confirmation
    }
}
