package Siao.task;

import Siao.Parser.Parser;
import Siao.management.Constants;


public class Event extends Task {
    public final String from;
    public final String to;

    public Event(String description) {
        super(parseDescription(description));
        this.from = parseFrom(description);
        this.to = parseTo(description);
    }

    /**
     * Returns the description part of user's keyed in input if an Event is recorded
     * throws an exception if the description is empty
     * @param userInput user's keyed in input
     * @return description of the Event
     * @throws IllegalArgumentException if description is empty
     */
    private static String parseDescription(String userInput) {
        String raw = Parser.preProcessInput(userInput, "event");
        String[] parts = raw.split("/from", 2);
        if (parts[0].isEmpty()) {
            throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_MESSAGE);
        }

        return parts[0].trim();
    }

    /**
     * Returns the "from" timing part of user's keyed in input if an Event is recorded
     * throws an exception if "from" is empty
     * @param userInput user's keyed in input
     * @return "from" timing of the Event
     */

    public static String parseFrom(String userInput) {
        String[] parts = userInput.split("/from", 2);
        String[] subParts = parts[1].split("/to", 2);

        if (subParts[0].isEmpty()) {
            throw new IllegalArgumentException(Constants.ILLEGAL_START_MESSAGE);
        }
        return subParts[0].trim();
    }

    /**
     * Returns the "to" part of user's keyed in input if an Event is recorded
     * throws an exception if the "to" is empty
     * @param userInput user's keyed in input
     * @return "to" timing of the Event
     */
    public static String parseTo(String userInput) {
        String[] parts = userInput.split("/to", 2);

        if (parts[1].isEmpty()) {
            throw new IllegalArgumentException(Constants.ILLEGAL_END_MESSAGE);
        }

        return parts[1].trim();
    }

    @Override
    public String getType() {
        return "E";
    }

    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)\n",
                getType(), getStatusIcon(), this.description, this.from, this.to);
    }
}
