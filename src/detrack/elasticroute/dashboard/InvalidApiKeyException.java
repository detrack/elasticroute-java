package detrack.elasticroute.dashboard;

public class InvalidApiKeyException extends RuntimeException{
    public InvalidApiKeyException(String errorMessage){
        super(errorMessage);
    }
}
