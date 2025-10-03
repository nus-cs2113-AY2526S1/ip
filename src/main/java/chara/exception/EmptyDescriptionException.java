package chara.exception;

/**
 * Thrown when an add task command is missing a required description.
 */
public class EmptyDescriptionException extends CharaException {
    public EmptyDescriptionException(String what) {
        super("Add a description! I'm not a mind reader...");
    }
}