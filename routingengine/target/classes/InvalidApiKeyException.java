package detrack.elasticroute.routingengine;

public class InvalidApiKeyException extends RuntimeException{
    public InvalidApiKeyException(String errorMessage){
        super(errorMessage);
    }
}
