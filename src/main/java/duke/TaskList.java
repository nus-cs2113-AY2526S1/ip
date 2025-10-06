package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }
    public TaskList(TaskList list) {
        this.list = new ArrayList<>();
        this.list.addAll(list.list);
    }

    public void addTask(Task task) {
        list.add(task);
    }
    public void removeTask(int index) {
        list.remove(index);
    }
    public Task getTask(int index) {
        return list.get(index); // is this return by value or by reference?
    }
    public void printAllTasks() {
        int index = 0;
        for (Task task : list) {
            int num = index+1;
            System.out.print(num + ". " );
            System.out.println(task.printinfo());
            index++;
        }
        if (index == 0){
            System.out.println("oops, is there anything in the list?");
        }
    }
    public String printLastTask() {
        if (list.isEmpty()) {
            return ("oops, is there anything in the list?");
        } else {
            return (list.get(list.size() - 1).printinfo());
        }
    }
    public int getSize() {
        return list.size();
    }
    public String findingTaskWithName(String entry) {
        String result = "";
        int index = 1;
        for (Task task : list) {
            if (task.getName().contains(entry)) {
                if (index != 1) {
                    result += "\n";
                }
                result += index + ". ";
                result += task.printinfo();
                index++;
            }
        }
        return result;
    }

}
