package exceptions;

public class WrongFormat extends RuntimeException {
    public String getMessage(){
        return "Please double check the format of the command again";
    }
}
