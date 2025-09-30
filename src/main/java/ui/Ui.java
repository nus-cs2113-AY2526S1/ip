package ui;

import command.Deadlines;
import command.Events;
import command.Task;
import command.ToDo;

import java.util.ArrayList;

/**
 * This class contains all the methods for system output messages
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class Ui {

    private ArrayList<Task> tasks;

    public Ui(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Prints logo
     */
    public void printLogo(){
        System.out.print("--------------------------------\n" +
                "Hello I'm Toothless\n" + "What can I do for you?\n" +
                "--------------------------------\n");
    }

    /**
     * Prints list of commands that can be used
     */
    public void printCommandList(){
        System.out.print("Commands List\n" + "--------------------------------\n" +
                "list - to list all tasks added\n" +
                "bye - to exit CLI\n" +
                "mark [num] eg. mark 1\n" +
                "unmark [num] eg.unmark 2\n" +
                "todo [task name] eg. todo go run\n" +
                "deadline [task name] /by [deadline date/time] eg. deadline pay loan /by 01/01/25\n" +
                "event [task name] /from [start date/time] /to [end date/time] eg. attend conference /from Monday 1pm /to Friday 9pm\n" +
                "--------------------------------\n");
    }

    /**
     * Prints exit message
     */
    public void printBye(){
        System.out.print("--------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "--------------------------------");
    }

    /**
     * Prints header message according to command type
     */
    public void printTopMessage(String command){
        printBorder();

        if(command.equals("list")){
            System.out.println("Here are the tasks in your list:");
        }else if(command.equals("mark")){
            System.out.println("Nice! I've marked this task as done:");
        }else if(command.equals("unmark")){
            System.out.println("OK, I've marked this task as not done yet:");
        }else if(command.equals("delete")){
            System.out.println("Noted I've removed this task:");
        }else if(command.equals("find")){
            System.out.println("Here are the matching tasks in your list:");
        }
        else{
            System.out.println("Got it. I've added this task:");
        }
    }

    /**
     * Prints border
     */
    public void printBorder(){
        System.out.println("--------------------------------");
    }

    /**
     * Prints delete message
     */
    public void printDelete(int index){
        System.out.println(tasks.get(index).getTask() + "\n" + "Now you have " + (tasks.size() - 1) + " tasks in the list." );
    }

    /**
     * Prints mark/unmark message
     */
    public void printMark(int listIndex){
        System.out.println("[" + tasks.get(listIndex-1).getMarkStatus() + "] " + tasks.get(listIndex-1).getTaskName());
    }

    /**
     * Prints todo message
     */
    public void printToDo(ToDo td){
        System.out.println("[" + td.getType() + "]" + "[" + td.getMarkStatus() + "] " + td.getTaskName() + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints deadline message
     */
    public void printDeadline(Deadlines d){
        System.out.println("[" + d.getType() + "]" + "[" + d.getMarkStatus() + "] " + d.getTaskName() + " (by: " + d.getBy() + ")\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints event message
     */
    public void printEvent(Events e){
        System.out.println("[" + e.getType() + "]" + "[" + e.getMarkStatus() + "] " + e.getTaskName() + " (from: " + e.getFrom() + " to: " + e.getTo()  + ")\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints list of all the tasks
     */
    public void printList(int count, Task t){
        System.out.println(count + ". " + t.getTask());
    }

    /**
     * Prints error message exceptions
     */
    public void printError(String error){
        System.out.println(error);
    }

    /**
     * Prints error message for keyword that was not found
     */
    public void printKeywordNotFound(String keyword){
        System.out.println("There are no search results for " + keyword + ".");
    }
}
