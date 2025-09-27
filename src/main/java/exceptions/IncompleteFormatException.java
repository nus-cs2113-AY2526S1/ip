package exceptions;

public class IncompleteFormatException extends RuntimeException {
    public String getMessage(){
        return "Your command is uncomplete, check again";
    }
}
