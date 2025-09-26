package exceptions;

public class Empty extends RuntimeException {
    public String getMessage(){
        return "Please fill in a task";
    }
}
