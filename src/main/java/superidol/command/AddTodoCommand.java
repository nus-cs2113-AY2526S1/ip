package superidol.command;

import superidol.storage.Storage;
import superidol.task.Todo;
import superidol.tasklist.TaskList;

public class AddTodoCommand extends Command{
    private String task;

    public AddTodoCommand(String task){
        super();
        this.task = task;
    }

    public void execute(TaskList taskList, Storage storage){
        Todo todo = new Todo(task, false);
        taskList.addTask(todo, true);
    }
}
