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
                throw new ChaunceyException("Task details not enough! Please input task input in this format: task description/task deadline");
            }
            if (taskDetails.length > 2) {
                throw new ChaunceyException("Task details more than expected! Please only input task description and task deadline.");
            }
            String deadline = taskDetails[1].trim();
            tasks.add(new Deadline(taskDetails[0].trim(), deadline));
            break;
        case "event":
            if (taskDetails.length < 3) {
                throw new ChaunceyException("Task details not enough! Please input task input in this format: task description/(from) start time/(to) end time");
            }
            if (taskDetails.length > 3) {
                throw new ChaunceyException("Task details more than expected! Please only input task description, task start time and task end time.");
            }
            String startTime = taskDetails[1].trim();
            String endTime = taskDetails[2].trim();
            tasks.add(new Event(taskDetails[0].trim(), startTime, endTime));
            break;
        default:
            throw new ChaunceyException("Invalid task type. Please choose among: todo / deadline / event");
        }
    }

    public String deleteTask(int taskNumber) throws ChaunceyException {
        if (tasks.isEmpty()) {
            throw new ChaunceyException("There is no task in the list. Can't do delete command.");
        }
        String taskDetails = tasks.get(taskNumber - 1).getTaskDetails();
        tasks.remove(taskNumber-1);
        return taskDetails;
    }

    public void markTask(int taskNumber) throws ChaunceyException {
        if (tasks.isEmpty()) {
            throw new ChaunceyException("There is no task in the list. Can't do mark command.");
        }
        if (tasks.get(taskNumber-1).getStatus()) {
            throw new ChaunceyException("Task " + taskNumber + " is already marked done.");
        }
        tasks.get(taskNumber - 1).markAsDone();
    }

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
