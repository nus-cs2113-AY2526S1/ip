package parser;

import exceptions.*;
import tasklist.TaskList;
import ui.Ui;

/**
 * This class contains all the methods for user interaction
 *
 * @author  Lai Kai Jie Jeremy
 * @since   2025-08-17
 */

public class Parser {
    private TaskList tl;
    private Ui ui;

    public Parser(TaskList tl, Ui ui) {
        this.tl = tl;
        this.ui = ui;
    }

    /**
     * Checks the operation from user input and
     * commits the operation accordingly
     *
     * @param reply the user input
     * @throws EmptyTaskException invokes when it detects an empty task input
     * @throws IncompleteFormatException invokes when it detects that the format of the command is incomplete
     * @throws InvalidCommandException invokes when command input is foreign
     * @throws OutOfBoundsException invokes when the user inputs a number that accesses outside the array
     * @throws WrongFormatException invokes when the user inputs the wrong format of the command
     */
    public void checkOperation(String reply) throws EmptyTaskException, IncompleteFormatException, InvalidCommandException, OutOfBoundsException, WrongFormatException {
        reply = reply.trim();
        String[] splitArray = reply.split(" ");
        String operation = splitArray[0];

        if(operation.equals("mark")){
            tl.executeMarkOperation(splitArray,operation);
        }else if(operation.equals("unmark")){
            tl.executeUnmarkOperation(splitArray,operation);
        }else if(operation.equals("delete")){
            tl.executeDeleteOperation(splitArray);
        }
        else if(operation.equals("list")){
            tl.getList();
        }else if(operation.equals("todo")){
            tl.executeToDoOperation(reply,operation);
        }else if(operation.equals("deadline")){
            tl.executeDeadlineOperation(reply,operation);
        }else if(operation.equals("event")){
            tl.executeEventOperation(reply,operation);
        }else if(operation.equals("find")){
            tl.executeFindOperation(reply,operation);
        }
        else{
            throw new InvalidCommandException();
        }
    }

    /**
     * Runs the entire program
     *
     * @param reply the user input
     */
    public void bootSystem(String reply){
        try{
            checkOperation(reply);
        }catch(NumberFormatException | EmptyTaskException | IncompleteFormatException | InvalidCommandException |
               OutOfBoundsException | WrongFormatException e){
            ui.printError(e.getMessage());
        }
    }

}
