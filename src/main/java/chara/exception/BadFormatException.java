package chara.exception;

/**
 * Thrown when a user command is provided in the wrong format.
 */
public class BadFormatException extends CharaException {
    public BadFormatException(String hint) {
        super(hint);
    }
}