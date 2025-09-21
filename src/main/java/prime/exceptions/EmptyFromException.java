package prime.exceptions;

import prime.parser.CommandType;

public class EmptyFromException extends PrimeException {
    public EmptyFromException(CommandType commandType) {
        super("OOPS!!! The /from of " + commandType.toString() + " cannot be empty");
    }
}
