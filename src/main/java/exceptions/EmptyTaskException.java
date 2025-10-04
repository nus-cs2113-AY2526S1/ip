package exceptions;

public class EmptyTaskException extends RuntimeException {
    public String getMessage(){
        return "Please fill in a task";
    }
}
