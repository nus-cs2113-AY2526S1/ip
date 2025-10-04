package exceptions;

public class WrongFormatException extends RuntimeException {
    public String getMessage(){
        return "Please double check the format of the command again";
    }
}
