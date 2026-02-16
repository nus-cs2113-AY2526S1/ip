package commands;

import tasks.TaskList;
import tasks.Todo;
import ui.Ui;
import exceptions.PepException;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        if (description == null || description.trim().isEmpty()) {
            throw new PepException("A todo needs a description yo, how bout you Try: todo <description>");
        }
        Todo todo = new Todo(description.trim());
        tasks.addTask(todo);
        ui.showAdded(todo.toString(), tasks.getCount());
    }

}