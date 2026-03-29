package michael.Parser;

import michael.Exception.InvalidCommand;
import michael.Exception.NumberRangeException;
import michael.Exception.UnknownInstructionException;
import michael.TaskList.*;
import michael.Command.*;

import static michael.Michael.dataFile;
import static michael.Michael.numberTasks;

/**
 * This class parses user input instructions into actionable commands
 * and extracts relevant information for task creation and management.
 * It handles input formatting, splitting, indexing, and generates
 * Command objects based on instructions.
 */
public class ParseInput {
    private final String givenInstruction;
    private String[] instructionListCreated;
    private int currentTaskIndex;

    /**
     * Constructor for ParseInput.
     * Initializes with a user instruction and parses it into its components.
     *
     * @param instruction The raw string received from user input
     */
    public ParseInput(String instruction) {
        this.givenInstruction = instruction;
        parseInstruction();
        parseIndex();
    }

    /**
     * Splits the given instruction into two parts:
     * the command keyword and the instruction details.
     */
    public void parseInstruction() {
        this.instructionListCreated = givenInstruction.split(" ", 2);
    }

    /**
     * Determines and sets the task index based on the instruction type.
     * For commands like mark, unmark, and delete,
     * it converts the supplied number to a zero-based index.
     */
    public void parseIndex() {
        int index;
        if (instructionListCreated[0].equals("mark") || instructionListCreated[0].equals("unmark") || instructionListCreated[0].equals("delete")) {
            try {
                index = Integer.parseInt(instructionListCreated[1]) - 1;
                this.currentTaskIndex = index;
            } catch (NumberFormatException e) {
                this.currentTaskIndex = -1;
            }
        }
    }

    /**
     * Parses details from a deadline instruction.
     * Splits the task description and deadline time by delimiter "/by".
     *
     * @return An array containing [description, by time]
     */
    public String[] parseDeadline() {
        return instructionListCreated[1].split(" /by ", 2);
    }

    /**
     * Parses details from an event instruction.
     * Splits the description, start, and end times using "/from" and "/to" delimiters.
     *
     * @return An array containing [description, start time, end time]
     */
    public String[] parseEvent() {
        String[] mainEvent = instructionListCreated[1].split(" /from ", 2);
        String[] eventDuration = mainEvent[1].split(" /to ", 2);
        return new String[]{mainEvent[0], eventDuration[0], eventDuration[1]};
    }

    /**
     * Interprets the parsed instruction and returns the corresponding Command.
     * Handles various user commands, throws appropriate exceptions for
     * unknown instructions or out-of-range indexes, and passes data to task constructors.
     *
     * @return Command object as identified from the instruction
     */
    public Command parse() {
        Task task;
        try {
            switch (instructionListCreated[0]) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ShowListCommand();
            case "mark":
                if (currentTaskIndex >= 0 && currentTaskIndex < numberTasks) {
                    return new MarkTaskCommand(currentTaskIndex);
                } else {
                    throw new NumberRangeException();
                }
            case "unmark":
                if (currentTaskIndex >= 0 && currentTaskIndex < numberTasks) {
                    return new UnmarkTaskCommand(currentTaskIndex);
                } else {
                    throw new NumberRangeException();
                }
            case "todo":
                task = new Todo(instructionListCreated[1], dataFile, numberTasks + 1, false, true);
                return new AddCommand(task, true);
            case "deadline":
                String[] deadlineInstruction = parseDeadline();
                task = new Deadline(deadlineInstruction[0], deadlineInstruction[1], dataFile, numberTasks + 1, false, true);
                return new AddCommand(task, true);
            case "event":
                String[] eventInstruction = parseEvent();
                task = new Event(eventInstruction[0], eventInstruction[1], eventInstruction[2], dataFile, numberTasks + 1, false, true);
                return new AddCommand(task, true);
            case "delete":
                if (currentTaskIndex >= 0 && currentTaskIndex < numberTasks) {
                    return new DeleteCommand(currentTaskIndex);
                } else {
                    throw new NumberRangeException();
                }
            case "find":
                return new FindCommand(instructionListCreated[1]);
            default:
                throw new UnknownInstructionException();
            }
        } catch (UnknownInstructionException e) {
            return new InvalidCommand("Unknown instruction: " + e.unknownInstructionMessage());
        } catch (NumberRangeException e) {
            return new InvalidCommand("Number not within range: " + e.notInRangeMessage());
        }
    }
}
