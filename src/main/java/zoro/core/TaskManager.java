package zoro.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import zoro.ui.UserInterface;
import zoro.model.Task;
import zoro.model.Deadline;
import zoro.model.Event;
import zoro.validation.Validator;

public class TaskManager {
    private final List<Task> tasks;
    private final DataManager dataManager;

    public TaskManager() {
        this.dataManager = new DataManager();
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        dataManager.saveTasks(tasks);
    }

    //TTODO
    public void processTodoCommand(String userInput, UserInterface ui) {
        Validator.ValidationResult validation = Validator.validateTodoCommand(userInput);

        if (!validation.isValid()) {
            ui.printValidationError(validation.getErrorMessage());
            ui.printValidInputTodo();
            return;
        }

        String description = getTaskSubstring(userInput);
        Task task = new Task(description);
        addTask(task);
        ui.printTaskAdded(task);
    }

    //MARK
   public void processMarkCommand(String userInput, UserInterface ui) {
       Validator.ValidationResult validation = Validator.validateMarkCommand(userInput, tasks);

       if (!validation.isValid()) {
           ui.printValidationError(validation.getErrorMessage());
           ui.printValidInputMark();
           return;
       }

        String[] args = userInput.split(" ");

        int index = Integer.parseInt(args[1])-1; //get the index of the task the user wants to mark
        tasks.get(index).toggleDone();
        if  (tasks.get(index).isDone()) {
            ui.printTaskMarked(tasks.get(index).getDescription());
        } else {
            ui.printTaskUnmarked(tasks.get(index).getDescription());
            }
   }

   //DEADLINE
   public void processDeadlineCommand(String userInput, UserInterface ui) {
        Validator.ValidationResult validation = Validator.validateDeadlineCommand(userInput);

       if(!validation.isValid()) {
           ui.printValidationError(validation.getErrorMessage());
           ui.printValidInputDeadline();
           return;
       }

       String[] args = userInput.split(" ");
       List<String> argsList = Arrays.asList(args);

       //i=1 so that it skips 'deadline'
       int byIndex = -10;
       for (int i=1; i<argsList.size(); i++) {
           if (argsList.get(i).equals("/by")) {
               byIndex = i;
               break;
           }
       }

       Deadline deadline = new Deadline(
               String.join(" ", argsList.subList(1, byIndex)), //string before /by
               String.join(" ", argsList.subList(byIndex + 1, argsList.size())) //string after /by
       );

       addTask(deadline);
       ui.printTaskAdded(deadline);
   }

   //EVENT
   public void processEventCommand(String userInput, UserInterface ui) {
        Validator.ValidationResult validation = Validator.validateEventCommand(userInput);

        if (!validation.isValid()) {
           ui.printValidationError(validation.getErrorMessage());
           ui.printValidInputEvent();
           return;
        }

       String[] args = userInput.split(" ");
       List<String> argsList = Arrays.asList(args);

       int fromIndex = -10;
       int toIndex = -10;

       //find the index's of the /ffrom and /to
       for (int i=1; i<argsList.size(); i++) {
           if (argsList.get(i).equals("/from")) {
               fromIndex = i;
           } else if (argsList.get(i).equals("/to")) {
               toIndex = i;
           }
       }
       Event event = new Event(
               String.join(" ", argsList.subList(1, fromIndex)),
               String.join(" ", argsList.subList(fromIndex+1, toIndex)),
               String.join(" ", argsList.subList(toIndex+1, argsList.size()))
       );

       addTask(event);
       ui.printTaskAdded(event);
   }

    public void processDeleteCommand(String userInput, UserInterface ui) {
            Validator.ValidationResult validation = Validator.validateDeleteCommand(userInput, tasks);
            if (!validation.isValid()) {
                ui.printValidationError(validation.getErrorMessage());
                ui.printValidInputDelete();
                return;
            }

            int deleteIndex = Integer.parseInt(userInput.split(" ")[1])-1;
            Task deletedTask = tasks.get(deleteIndex);
            tasks.remove(deletedTask);

            ui.printTaskDeleted(deletedTask);
        }

    public void processFindCommand(String userInput, UserInterface ui) {
        Validator.ValidationResult validation = Validator.validateFindCommand(userInput, tasks);
        if (!validation.isValid()) {
                ui.printValidationError(validation.getErrorMessage());
                ui.printValidInputFind();
                return;
        }

        List<Task> foundTasks = new ArrayList<>();
        String[] args = userInput.split(" ");
        String keyword = args[1];
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        ui.printTasksFound(foundTasks);


    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public String getTaskSubstring(String userInput) {
        return userInput.substring(userInput.split(" ")[0].length()+1); //skips the keyword
    }

}
