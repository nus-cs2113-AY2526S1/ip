package exceptions;

public class IncompleteFormat extends RuntimeException {
    public String getMessage(){
        return "Your command is uncomplete, check again";
    }
}
