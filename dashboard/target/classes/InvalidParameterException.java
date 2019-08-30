package detrack.elasticroute.dashboard;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String errorMessage){
        super(errorMessage);
    }
}
