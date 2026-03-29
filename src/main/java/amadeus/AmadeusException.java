package amadeus;

/**
 * Exception for errors inside Amadeus program.
 * It is used when something wrong happened with tasks or commands.
 */
public class AmadeusException extends Exception {

    /**
     * Create new AmadeusException with message.
     *
     * @param message message to explain the error
     */
    public AmadeusException(String message) {
        super(message);
    }
}