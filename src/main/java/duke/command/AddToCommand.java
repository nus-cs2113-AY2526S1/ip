package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;



public class AddToCommand extends Command {
    private String UserCommand;
    public AddToCommand(String UserCommand) {
        this.UserCommand = UserCommand;
    }
    /**
     * Add new tasks into the task list
     *
     * @param tasks which is a list of tasks under type TaskList
     * @param storage which is the instance used for reading and writing information into the given file path
     * @param ui which outputs information to the user to show message
     * @throws DukeException when the there are errors in the file
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        int firstspace;
        String[] parts;
        try {
            parts = UserCommand.split("/");
            firstspace = parts[0].indexOf(" ");
            if (firstspace == -1 || firstspace + 1 >= UserCommand.length()) {
                throw new IndexOutOfBoundsException();
            }
        } catch(IndexOutOfBoundsException e){
            ui.showError("emmmm, seems like you forgot to enter your task name?");
            return;
        }


        if(UserCommand.contains("todo")){
            try{
                String TaskName = UserCommand.substring(firstspace + 1);
                tasks.addTask(new Todo(TaskName));
                storage.SaveTasks(tasks);
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }

        } else if(UserCommand.contains("deadline")){
            try{
                String TaskName = parts[0].substring(firstspace + 1);
                tasks.addTask(new Deadline(TaskName, parts[1]));
                storage.SaveTasks(tasks);
            } catch (ArrayIndexOutOfBoundsException e){
                ui.showError("emmm, seems like you forgot to enter your deadline date?");
                return;
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }

        } else if (UserCommand.contains("event")) {
            try {
                String TaskName = parts[0].substring(firstspace + 1);
                tasks.addTask(new Event(TaskName, parts[1], parts[2]));
                storage.SaveTasks(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("emmmm, seems like you forgot to enter your event time?");
                return;
            }
        }
        ui.showMessage("well! ive added: ");
        System.out.println(tasks.printLastTask());

    }
}
