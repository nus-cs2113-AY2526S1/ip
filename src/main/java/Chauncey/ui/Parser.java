package Chauncey.ui;

import Chauncey.exception.ChaunceyException;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public String parseCommand() throws ChaunceyException {
        if (command.isEmpty()) {
            throw new ChaunceyException("Command is empty! Please input a command.");
        }
        return command.split(" ")[0];
    }

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

    public String[] parseTaskDetails() throws ChaunceyException {
        if (command.isEmpty()) {
            throw new ChaunceyException("Task details can't be empty! Please input task details.");
        }
        return command.split("/");
    }
}
