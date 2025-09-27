package exceptions;

public class InvalidCommandExecption extends RuntimeException {
    public String getMessage(){
        return "Please enter a valid entry or command";
    }
}
