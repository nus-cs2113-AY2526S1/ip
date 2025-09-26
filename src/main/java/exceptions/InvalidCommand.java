package exceptions;

public class InvalidCommand extends RuntimeException {
    public String getMessage(){
        return "Please enter a valid entry or command";
    }
}
