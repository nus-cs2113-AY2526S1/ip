/**
 * Represents an exception thrown when an unknown command is provided.
 * An <code>UnknownCommandException</code> is used to indicate that the given
 * input does not match any valid command.
 */
public class UnknownCommandException extends DogeException{
    /**
     * Constructs a new UnknownCommandException with a fixed error message
     * suggesting valid commands.
     */
    public UnknownCommandException(){
        super("Much confused. Try other commands like: 'list', 'todo', 'deadline', " +
                "'event', 'mark', 'unmark', 'delete', 'find', or 'bye'.");
    }
}
