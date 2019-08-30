package detrack.elasticroute.dashboard;

public class EmptyApiKeyException extends RuntimeException{
    public EmptyApiKeyException(String errorMessage){
        super(errorMessage);
    }
}
