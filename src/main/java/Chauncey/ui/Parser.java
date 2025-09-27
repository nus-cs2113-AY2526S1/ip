package Chauncey.ui;

import Chauncey.exception.ChaunceyException;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    /**
     * Extracts and returns the key instruction.
     *
     * @return The key command word, such as add, delete.
     * @throws ChaunceyException If the String attribute command is empty.
     */
    public String parseCommand() throws ChaunceyException {
        if (command.isEmpty()) {
            throw new ChaunceyException("Command is empty! Please input a command.");
        }
        return command.split(" ")[0];
    }

    /**
     * Extracts and returns the number of the task to be manipulated.
     *
     * @param numOfTasks The number of tasks in the task list.
     * @return The task number to be manipulated.
     * @throws ChaunceyException If the user doesn't indicate the task number or the task number is out of bound.
     */
    public int parseTaskNumber(int numOfTasks) throws ChaunceyException {
        String[] commandDetails = command.split(" ");
        if (commandDetails.length < 2) {
            throw new ChaunceyException("I don't know what is the task number for the command. Please also input the task number (formate: <command: delete/mark/unmark> <task number>).");
        }
        String numberInString = commandDetails[commandDetails.length - 1];
        int taskNumber = Integer.parseInt(numberInString);
        if (taskNumber < 1 || taskNumber > numOfTasks) {
            throw new ChaunceyException("Invalid task number. Task number should be between 1 and " + numberInString);
        }
        return taskNumber;
    }

    /**
     * Returns the String array containing different sections of task details.
     *
     * @return A String array containing separated details of the task to be added.
     * @throws ChaunceyException If the task detail is empty.
     */
    public String[] parseTaskDetails() throws ChaunceyException {
        if (command.isEmpty()) {
            throw new ChaunceyException("Task details can't be empty! Please input task details.");
        }
        return command.split("/");
    }

    /**
     * Parses the keyword to be used to filter the task list.
     *
     * @return Keyword to be used to filter the task list.
     * @throws ChaunceyException If the user doesn't input the keyword or input more than one keyword.
     */
    public String parseKeyword() throws ChaunceyException {
        String[] keywords = command.split(" ");
        if (keywords.length < 2) {
            throw new ChaunceyException("Keyword can't be empty");
        }
        if (keywords.length > 2) {
            throw new ChaunceyException("Please input only one keyword.");
        }
        return keywords[1];
    }
}
