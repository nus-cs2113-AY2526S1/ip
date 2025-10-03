package chara.exception;

/**
 * Base exception class for all errors specific to Chara.
 * Other custom exceptions extend from this class.
 */
public class CharaException extends Exception {
    public CharaException(String message) {
        super(message);
    }
}