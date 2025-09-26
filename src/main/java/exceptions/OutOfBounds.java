package exceptions;

public class OutOfBounds extends RuntimeException {
    public String getMessage(){
        return "The task does not exist";
    }
}
