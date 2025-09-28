package Chauncey.ui;

import Chauncey.exception.ChaunceyException;
import Chauncey.task.Deadline;
import Chauncey.task.Event;
import Chauncey.task.Task;
import Chauncey.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Create a new Todo/Deadline/Event object according to the input and add it to the ArrayList.
     * If the input is invalid, it will throw a ChaunceyException with a detailed error description.
     *
     * @param type The type of the task to be added.
     * @param taskDetails The details of the task to be added. For todo, taskDetails only contains task description.
     *                    For deadline, taskDetails contains task description and task deadline. For event, taskDetails
     *                    contains task description, task start time and task end time.
     * @throws ChaunceyException If task type is empty or invalid, if taskDetails is empty or not in desired length for
     *                           the specific task type(either lack information or contain extra information).
     */
    public void addTask(String type, String[] taskDetails) throws ChaunceyException {
        if (type.isEmpty()) {
            throw new ChaunceyException("Task type input can't be empty! Please select: todo/deadline/event");
        }
        if (taskDetails.length == 0) {
            throw new ChaunceyException("Task details can't be empty! Please input task details.");
        }
        switch (type) {
        case "todo":
            tasks.add(new Todo(taskDetails[0]));
            break;
        case "deadline":
            if (taskDetails.length < 2) {
                throw new ChaunceyException("Task details not enough! Please input task input in this format: <description> / by <yyyy-MM-dd HHmm> ");
            }
            if (taskDetails.length > 2) {
                throw new ChaunceyException("Task details more than expected! Please only input task description and task deadline in this format: <description> / by <yyyy-MM-dd HHmm>.");
            }
            String deadline = taskDetails[1].trim();
            tasks.add(new Deadline(taskDetails[0].trim(), deadline));
            break;
        case "event":
            if (taskDetails.length < 3) {
                throw new ChaunceyException("Task details not enough! Please input task input in this format: <description> / from <yyyy-MM-dd HHmm> / to <yyyy-MM-dd HHmm>");
            }
            if (taskDetails.length > 3) {
                throw new ChaunceyException("Task details more than expected! Please only input task description, task start time and task end time in this format: <description> / from <yyyy-MM-dd HHmm> / to <yyyy-MM-dd HHmm>.");
            }
            String startTime = taskDetails[1].trim();
            String endTime = taskDetails[2].trim();
            tasks.add(new Event(taskDetails[0].trim(), startTime, endTime));
            break;
        default:
            throw new ChaunceyException("Invalid task type. Please choose among: todo / deadline / event");
        }
    }

    /**
     * Delete the task specified by the input from the task list and return the details of the deleted task.
     * If the task list is empty, it will throw a ChaunceyException.
     *
     * @param taskNumber Task number indicated in the task list (starting with 1)
     * @return The detailed description of the deleted task.
     * @throws ChaunceyException If the task list is empty.
     */
    public String deleteTask(int taskNumber) throws ChaunceyException {
        if (tasks.isEmpty()) {
            throw new ChaunceyException("There is no task in the list. Can't do delete command.");
        }
        String taskDetails = tasks.get(taskNumber - 1).getTaskDetails();
        tasks.remove(taskNumber-1);
        return taskDetails;
    }

    /**
     * Mark the task specified by the input as done.
     *
     * @param taskNumber Task number indicated in the task list (starting with 1)
     * @throws ChaunceyException If the task list is empty or the task is already done.
     */
    public void markTask(int taskNumber) throws ChaunceyException {
        if (tasks.isEmpty()) {
            throw new ChaunceyException("There is no task in the list. Can't do mark command.");
        }
        if (tasks.get(taskNumber-1).getStatus()) {
            throw new ChaunceyException("Task " + taskNumber + " is already marked done.");
        }
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Unmark the task specified by the input.
     *
     * @param taskNumber Task number indicated in the task list (starting with 1)
     * @throws ChaunceyException If the task list is empty or the task undone.
     */
    public void unmarkTask(int taskNumber) throws ChaunceyException {
        if (tasks.isEmpty()) {
            throw new ChaunceyException("There is no task in the list. Can't do unmark command.");
        }
        if (!tasks.get(taskNumber-1).getStatus()) {
            throw new ChaunceyException("Task " + taskNumber + " is not done! Can't unmark it.");
        }
        tasks.get(taskNumber - 1).markAsUndone();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int indexNumber) {
        return tasks.get(indexNumber);
    }

    public ArrayList<Task> getTasksList() {
        return tasks;
    }

    /**
     * Filter the task list by the keyword.
     *
     * @param keyword The keyword to be searched in the task list.
     * @return An ArrayList with all the tasks containing the keyword.
     */
    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
