package detrack.elasticroute.dashboard;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String errorMessage){
        super(errorMessage);
    }
}
