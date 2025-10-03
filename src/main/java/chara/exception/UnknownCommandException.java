package chara.exception;

/**
 * Thrown when the user enters a command that is not recognized.
 */
public class UnknownCommandException extends CharaException {
    public UnknownCommandException() {
        super("I don't know what you want from me... =(");
    }
}