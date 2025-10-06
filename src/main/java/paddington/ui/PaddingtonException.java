package paddington.ui;

/**
 * Custom exception class for handling errors in the Paddington program.
 * Provides specific exception types for various command and task-related errors.
 */
public class PaddingtonException extends Exception {

    /**
     * Constructs a new PaddingtonException with the specified detail message.
     *
     * @param s the detail message
     */
    public PaddingtonException(String s) {
        super(s);
    }

    /**
     * Returns an exception for an invalid command.
     *
     * @return a PaddingtonException with an "Invalid command" message
     */
    public static PaddingtonException invalidCommand() {
        return new PaddingtonException("Invalid command. Try again.");
    }

    /**
     * Returns an exception for a missing description in a Todo task.
     *
     * @return a PaddingtonException with a "Missing description of Todo task!" message
     */
    public static PaddingtonException invalidTodo() {
        return new PaddingtonException("Missing description of Todo task!");
    }

    /**
     * Returns an exception for a missing description in an Event task.
     *
     * @return a PaddingtonException with a "Missing description of Event task!" message
     */
    public static PaddingtonException invalidEvent() {
        return new PaddingtonException("Missing description of Event task!");
    }

    /**
     * Returns an exception for a missing description in a Deadline task.
     *
     * @return a PaddingtonException with a "Missing description of Deadline task!" message
     */
    public static PaddingtonException invalidDeadline() {
        return new PaddingtonException("Missing description of Deadline task!");
    }
}
