package amadeus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manage the list of tasks.
 * It allow to add, remove, mark, unmark and find tasks.
 * It also handle commands from user in list mode.
 */
public class TaskList {
    /** List of tasks in memory */
    private final List<Task> tasks;

    /**
     * Creates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates TaskList with already existing tasks.
     *
     * @param tasks list of tasks to initialize with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Handle command from user and execute it.
     * Command can be list, mark, unmark, todo, deadline, event, delete or find.
     *
     * @param input user command
     * @param storage storage to save tasks, can be null for find
     * @param ui user interface to show messages, can be null for find
     * @throws IOException if saving tasks fail
     * @throws AmadeusException if command unknown or invalid
     */
    public void handleCommand(String input, Storage storage, Ui ui) throws IOException, AmadeusException {
        if ("list".equalsIgnoreCase(input)) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        } else if (input.toLowerCase().startsWith("mark")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(idx).markAsDone();
            storage.save(tasks);
            Ui.taskUpdated("Nice! I've marked this task as done:", tasks.get(idx));
        } else if (input.toLowerCase().startsWith("unmark")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(idx).markAsUndone();
            storage.save(tasks);
            Ui.taskUpdated("OK, I've marked this task as not done yet:", tasks.get(idx));
        } else if (input.toLowerCase().startsWith("todo")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) throw new AmadeusException("The description of a todo cannot be empty.");
            tasks.add(new ToDo(desc));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by");
            String desc = parts[0].trim();
            String by = parts.length > 1 ? parts[1].trim() : "";
            if (desc.isEmpty() || by.isEmpty())
                throw new AmadeusException("Usage: deadline <desc> /by yyyy-MM-dd HHmm");
            tasks.add(new Deadline(desc, by));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.substring(6).split("/from");
            String desc = parts[0].trim();
            if (parts.length < 2) throw new AmadeusException("Usage: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            String[] timeParts = parts[1].split("/to");
            String from = timeParts[0].trim();
            String to = timeParts.length > 1 ? timeParts[1].trim() : "";
            tasks.add(new Event(desc, from, to));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("delete")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removed = tasks.remove(idx);
            Ui.taskDeleted(removed, tasks.size());
        } else if (input.toLowerCase().startsWith("find")) {
            String keyword = input.substring(5).trim();
            if (keyword.isEmpty()) {
                throw new AmadeusException("Please provide a keyword to search for.");
            }

            System.out.println("────────────────────────────────────────────────────────────────");
            System.out.println("Here are the matching tasks in your list:");
            int count = 0;
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.description.toLowerCase().contains(keyword.toLowerCase())) {
                    System.out.println((count + 1) + "." + t);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No matching tasks found.");
            }
            System.out.println("────────────────────────────────────────────────────────────────");
        } else {
            throw new AmadeusException("Sorry, I don't know that command.");
        }
    }

    /**
     * Add new task to the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Get task by index.
     *
     * @param index index of task
     * @return task at index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Remove task by index.
     *
     * @param index index of task to remove
     * @return removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Return number of tasks in list.
     *
     * @return size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return all tasks in list.
     *
     * @return list of tasks
     */
    public List<Task> getAll() {
        return tasks;
    }
}
