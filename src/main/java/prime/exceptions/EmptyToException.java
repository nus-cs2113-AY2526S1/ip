package prime.exceptions;

import prime.parser.CommandType;

public class EmptyToException extends PrimeException {
    public EmptyToException(CommandType commandType) {
        super("OOPS!!! The /to of " + commandType.toString() + " cannot be empty");
    }
}
