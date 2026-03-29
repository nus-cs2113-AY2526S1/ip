package Siao.Command;

import Siao.management.PrintManager;
import Siao.management.Storage;
import Siao.task.Task;
import Siao.task.Todo;

import java.util.ArrayList;
/**
 * Creates a new Todo task, adds it to the task list, prints a confirmation,
 * and saves it to storage.
 * <p>
 * The method uses the user input string to create a {@link Todo} object.
 * The new task is appended to the given list, and the storage file is updated.
 * </p>
 *
 * @param list    the current list of tasks to which the new Todo will be added
 * @param line    the user input string containing the Todo description
 * @param storage the Storage object used to save the new task
 * @throws IllegalArgumentException if the Todo description is empty
 */
public class AddTodo {
    public static void newTodo(ArrayList<Task> list, String line, Storage storage){
        Todo newTodo = new Todo(line);
        list.add(newTodo);
        newTodo.increaseCounter();
        PrintManager.printAddedTask(newTodo, list);
        storage.saveTask(newTodo);
    }
}
