/**
 * Application-specific checked exception for Bob.
 * <p>
 * Used to signal invalid commands
 */
public class BobException extends Exception {

    public BobException(String message) {
        super(message);
    }

}
