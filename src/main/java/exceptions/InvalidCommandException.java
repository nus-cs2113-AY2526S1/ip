package exceptions;

public class InvalidCommandException extends RuntimeException {
    public String getMessage(){
        return "Please enter a valid entry or command";
    }
}
