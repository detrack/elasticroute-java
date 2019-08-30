package detrack.elasticroute.routingengine;

public class EmptyApiKeyException extends RuntimeException{
    public EmptyApiKeyException(String errorMessage){
        super(errorMessage);
    }
}
