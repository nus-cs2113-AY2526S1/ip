package tasklist;

import command.Deadlines;
import command.Events;
import command.Task;
import command.ToDo;
import exceptions.*;
import ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class contains all methods for data processing
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui){
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Prints all the items in the list
     */
    public void getList(){
        ui.printTopMessage("list");

        int count = 1;

        for(Task t : tasks){
            ui.printList(count, t);
            count++;
        }

        ui.printBorder();
    }

    /**
     * Prints all the items in the list
     * that has a match with the given task name
     *
     * @param listFoundTask array list of matched task
     */
    public void getMatchList(ArrayList<Task> listFoundTask){
        ui.printTopMessage("find");

        int count = 1;

        for(Task t : listFoundTask){
            ui.printList(count, t);
            count++;
        }

        ui.printBorder();
    }

    /**
     * Executes checks and adds a todo task
     *
     * @param reply user input
     * @param operation name of operation
     * @throws InvalidCommandException invokes when command input is foreign
     */
    public void executeToDoOperation(String reply, String operation) throws InvalidCommandException {
        String taskName = extractTaskName(reply,operation);
        addToDo(taskName);
    }

    /**
     * Executes checks and adds a deadline task
     *
     * @param reply user input
     * @param operation name of operation
     * @throws WrongFormatException invokes when the user inputs the wrong format of the command
     * @throws InvalidCommandException invokes when command input is foreign
     */
    public void executeDeadlineOperation(String reply, String operation) throws WrongFormatException, InvalidCommandException {
        checkContainByFromTo(reply,operation);
        String taskName = extractTaskName(reply,operation);
        String by = extractBy(reply);
        addDeadline(taskName,by);
    }

    /**
     * Executes checks and adds a event task
     *
     * @param reply user input
     * @param operation name of operation
     * @throws WrongFormatException invokes when the user inputs the wrong format of the command
     * @throws InvalidCommandException invokes when command input is foreign
     */
    public void executeEventOperation(String reply, String operation) throws WrongFormatException, InvalidCommandException {
        checkContainByFromTo(reply,operation);
        String taskName = extractTaskName(reply,operation);
        String from = extractFromTo(reply,"from");
        String to = extractFromTo(reply,"to");
        addEvent(taskName,from,to);
    }

    /**
     * Executes checks and marks a task
     *
     * @param tempArray array of user input
     * @param operation name of operation
     * @throws EmptyTaskException invokes when it detects an empty task input
     * @throws OutOfBoundsException invokes when the user inputs a number that accesses outside the array
     */
    public void executeMarkOperation(String[] tempArray, String operation) throws EmptyTaskException, OutOfBoundsException{
        checkArrayLength(tempArray);
        int listIndex = Integer.parseInt(tempArray[1]);
        checkOutOfBounds(listIndex);
        markStatus(operation,listIndex);
    }

    /**
     * Executes checks and unmarks a task
     *
     * @param tempArray array of user input
     * @param operation name of operation
     * @throws OutOfBoundsException invokes when the user inputs a number that accesses outside the array
     */
    public void executeUnmarkOperation(String[] tempArray, String operation) throws OutOfBoundsException{
        int listIndex = Integer.parseInt(tempArray[1]);
        checkOutOfBounds(listIndex);
        markStatus(operation,listIndex);
    }

    /**
     * Executes checks and deletes a task
     *
     * @param tempArray array of user input
     * @throws OutOfBoundsException invokes when the user inputs a number that accesses outside the array
     */
    public void executeDeleteOperation(String[] tempArray) throws OutOfBoundsException{
        int listIndex = Integer.parseInt(tempArray[1]);
        checkOutOfBounds(listIndex);
        deleteTask(listIndex);
    }

    /**
     * Finds task according to keyword
     *
     * @param reply user input
     * @param operation name of operation
     * @throws InvalidCommandException invokes when command input is foreign
     */
    public void executeFindOperation(String reply, String operation) throws InvalidCommandException {
        String keyword = extractTaskName(reply,operation);
        findTask(keyword);
    }

    /**
     * Adds an event task in the list and
     * prints a message
     *
     * @param taskName task name of the task
     * @param from start of the event
     * @param to end of the event
     */
    public void addEvent(String taskName, String from, String to){
        try{
            if(from.contains("-")){
                from = getDate(from);
            }

            if(to.contains("-")){
                to = getDate(to);
            }
        }catch(DateTimeParseException e){
            System.out.println("Please enter a valid date format");
        }

        Events e = new Events(taskName, from, to);
        tasks.add(e);
        ui.printTopMessage("event");
        ui.printEvent(e);
        ui.printBorder();
    }

    /**
     * Adds an event task in the list from the file
     *
     * @param taskName task name of the task
     * @param from start of the event
     * @param to end of the event
     */
    public void readFileEvent(String taskName, String from, String to){
        Events e = new Events(taskName, from, to);
        tasks.add(e);
    }

    /**
     * Adds a deadline task in the list and
     * prints a message
     *
     * @param taskName task name of the task
     * @param by deadline of task
     */
    public void addDeadline(String taskName, String by){
        try{
            if(by.contains("-")){
                by = getDate(by);
            }
        }catch(DateTimeParseException e){
            System.out.println("Please enter a valid date format");
        }

        Deadlines d = new Deadlines(taskName, by);
        tasks.add(d);
        ui.printTopMessage("deadline");
        ui.printDeadline(d);
        ui.printBorder();
    }

    /**
     * Adds a deadline task in the list from the file
     *
     * @param taskName task name of the task
     * @param by due date of task
     */
    public void readFileDeadline(String taskName, String by){
        Deadlines d = new Deadlines(taskName, by);
        tasks.add(d);
    }

    /**
     * Adds a todo task in the list and
     * prints a message
     *
     * @param taskName task name of the task
     */
    public void addToDo(String taskName){
        ToDo td = new ToDo(taskName);
        tasks.add(td);
        ui.printTopMessage("todo");
        ui.printToDo(td);
        ui.printBorder();
    }

    /**
     * Adds a todo task in the list from the file
     *
     * @param taskName task name of the task
     */
    public void readFileToDo(String taskName){
        ToDo td = new ToDo(taskName);
        tasks.add(td);
    }

    /**
     * Deletes a task from the list according to the index
     * and prints a message
     *
     * @param index index of task in the list
     */
    public void deleteTask(int index){
        int decrementedIndex = index - 1;

        ui.printTopMessage("delete");
        ui.printDelete(decrementedIndex);
        ui.printBorder();

        tasks.remove(decrementedIndex);
    }

    /**
     * Marks and Unmarks the task according to the index
     *
     * @param mark the command of whether to mark or unmark
     * @param listIndex index of task in the list
     */
    public void markStatus(String mark, int listIndex){
        int decrementedIndex = listIndex - 1;

        if(mark.equals("mark")){
            tasks.get(decrementedIndex).setMarkStatus(true);
            ui.printTopMessage("mark");
        }else{
            tasks.get(decrementedIndex).setMarkStatus(false);
            ui.printTopMessage("unmark");
        }

        ui.printMark(listIndex);
        ui.printBorder();
    }

    /**
     * Marks the task according to the index from file
     *
     * @param listIndex index of task in the list
     */
    public void readFileMark(int listIndex){
        int decrementedIndex = listIndex - 1;

        tasks.get(decrementedIndex).setMarkStatus(true);
    }

    /**
     * Checks whether there is an empty task input
     *
     * @param array array of string from user input
     * @throws EmptyTaskException invokes when it detects an empty task input
     */
    public void checkArrayLength(String[] array) throws EmptyTaskException {
        if(array.length == 1){
            throw new EmptyTaskException();
        }
    }

    /**
     * Checks whether task is valid or not
     *
     * @param index the index given by the user
     * @throws OutOfBoundsException invokes when the user inputs a number that accesses outside the array
     */
    public void checkOutOfBounds(int index) throws OutOfBoundsException {
        if(index > tasks.size() || index < 1){
            throw new OutOfBoundsException();
        }
    }

    /**
     * Checks whether the reply is valid for by, from and to
     *
     * @param reply the user input
     * @param operation the task operation from user input
     * @throws WrongFormatException invokes when the user inputs the wrong format of the command
     */
    public void checkContainByFromTo(String reply, String operation) throws WrongFormatException {
        if(operation.equals("deadline") && !reply.contains("/by")){
            throw new WrongFormatException();
        }

        if(operation.equals("event") && (!reply.contains("/from") || !reply.contains("/to"))){
            throw new WrongFormatException();
        }
    }

    /**
     * Checks whether there is empty input from user
     * for by, from and to
     *
     * @param reply the user input
     * @throws IncompleteFormatException invokes when it detects that the format of the command is incomplete
     */
    public void checkByFromTo(String reply) throws IncompleteFormatException {
        if(reply.isEmpty()){
            throw new IncompleteFormatException();
        }
    }

    /**
     * Returns the task name through string manipulation
     *
     * @param reply the user input
     * @param operation the task operation from user input
     * @throws InvalidCommandException invokes when command input is foreign
     * @return taskName
     */
    public String extractTaskName(String reply, String operation) throws InvalidCommandException {
        final int TODO_FINDLENGTH = 4;
        final int DEADLINELENGTH = 8;
        final int EVENTLENGTH = 5;
        String taskName;
        String slicedString;

        if(operation.equals("todo") || operation.equals("find")){
            slicedString = reply.substring(TODO_FINDLENGTH);
            taskName = slicedString.trim();
        }else if(operation.equals("deadline")){
            int byIndex = reply.indexOf("/by");
            slicedString = reply.substring(DEADLINELENGTH,byIndex);
            taskName = slicedString.trim();
        }else if(operation.equals("event")){
            int byIndex = reply.indexOf("/from");
            slicedString = reply.substring(EVENTLENGTH,byIndex);
            taskName = slicedString.trim();
        }else{
            throw new InvalidCommandException();
        }

        if(taskName.isEmpty()){
            throw new EmptyTaskException();
        }

        return taskName;
    }

    /**
     * Returns the by field for deadline
     *
     * @param reply the user input
     * @return by
     */
    public String extractBy(String reply){
        final int BYLENGTH = 3;
        int byIndex = reply.indexOf("/by");
        String by = reply.substring(byIndex + BYLENGTH).trim();

        checkByFromTo(by);

        return by;
    }

    /**
     * Returns the from and to field for event
     *
     * @param reply the user input
     * @param operation the task operation from user input
     * @return fromTo
     */
    public String extractFromTo(String reply, String operation){
        final int FROMLENGTH = 5;
        final int TOLENGTH = 3;

        int fromIndex = reply.indexOf("/from");
        int toIndex = reply.indexOf("/to");
        String fromTo;

        if(operation.equals("from")){
            fromTo = reply.substring(fromIndex + FROMLENGTH,toIndex).trim();
        }else{
            fromTo = reply.substring(toIndex + TOLENGTH).trim();
        }

        checkByFromTo(fromTo);

        return fromTo;
    }

    /**
     * Retrieves date formatted in dd MM yyyy
     *
     * @param date user input date
     */
    public String getDate(String date){
        LocalDate dateString = LocalDate.parse(date);

        return dateString.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Finds all the match task according to the keyword
     *
     * @param keyword user input of keyword
     */
    public void findTask(String keyword){
        ArrayList<Task> listFoundTask =  new ArrayList<>();

        for(Task t : tasks){
            if(t.getTaskName().contains(keyword)){
                listFoundTask.add(t);
            }
        }

        if(listFoundTask.isEmpty()){
            ui.printKeywordNotFound(keyword);
        }else{
            getMatchList(listFoundTask);
        }
    }
}
