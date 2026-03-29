package Siao.task;

import Siao.Command.CommandHandler;
import Siao.management.Constants;
import Siao.Parser.Parser;

public class Deadline extends Task {
    public final String by;

    public Deadline(String description) {
        super(parseDescription(description));
        this.by = parseBy(description);
    }

    /**
     * returns the parsed description for deadlines keyed in by user
     * if the description is empty, throws an exception
     * @param userInput user's keyed in input
     * @return description part of the input only
     * @throws IllegalArgumentException if description is empty
     */

    private static String parseDescription(String userInput) {
        String raw = Parser.preProcessInput(userInput, "deadline");
        String[] parts = raw.split("/by");

        if (parts[0].isEmpty()) {
            throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_MESSAGE);
        }

        return parts[0].trim();
    }

    /**
     * returns the parsed deadline for deadlines keyed in by user
     * if deadline is empty, throws an exception
     * @param userInput user's keyed in input
     * @return the deadline of the task
     */
    public static String parseBy(String userInput) {
        String raw = Parser.preProcessInput(userInput, "by");
        String[] parts =  raw.split("/by",2);

        if (parts[1].isEmpty()) {
            throw new IllegalArgumentException(Constants.ILLEGAL_DEADLINE_MESSAGE);
        }
        return parts[1].trim();
    }

    @Override
    public String getType() {
        return "D";
    }

    public String toString() {
        return String.format("[%s][%s] %s (by: %s)\n",
                getType(), getStatusIcon(), this.description, this.by);
    }
}
