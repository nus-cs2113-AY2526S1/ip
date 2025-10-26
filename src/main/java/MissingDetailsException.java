/**
 * Represents an exception thrown when required task details are missing.
 * A <code>MissingDetailsException</code> is used to indicate that the given
 * input is incomplete.
 */
public class MissingDetailsException extends DogeException{
    /**
     * Constructs a new MissingDetailsException with a fixed error message.
     */
    public MissingDetailsException(){
        super("BONK!! Some details are missing!");
    }
}
