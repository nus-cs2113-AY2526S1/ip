package Siao.task;

import Siao.Parser.Parser;

public class Todo extends Task {
    public Todo(String description) {
        super(parseDescription(description));
    }
    /**
     * returns the description of the event
     * @param userInput user's keyed in input
     * @return description of Event
     * @throws IllegalArgumentException if description is empty
     */
    private static String parseDescription(String userInput) {
        String raw = Parser.preProcessInput(userInput, "todo");
        if (raw.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a TODO cannot be empty.");
        }
        return raw;
    }

    public String getType(){
        return "T";
    }
}
