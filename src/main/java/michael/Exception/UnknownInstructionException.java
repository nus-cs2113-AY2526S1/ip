package michael.Exception;

/**
 * Exception thrown when the user provides an unknown instruction.
 * Used to signal unrecognized commands in user input.
 */
public class UnknownInstructionException extends Exception {
    /**
     * Returns a message indicating the instruction is unknown.
     *
     * @return The error message for unknown instructions
     */
    public String unknownInstructionMessage() {
        return "I don't understand your instruction :{ Please try again";
    }
}
