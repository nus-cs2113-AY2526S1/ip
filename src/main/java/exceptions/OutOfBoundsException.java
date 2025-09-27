package exceptions;

public class OutOfBoundsException extends RuntimeException {
    public String getMessage(){
        return "The task does not exist";
    }
}
