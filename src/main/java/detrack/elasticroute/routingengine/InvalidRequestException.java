package detrack.elasticroute.routingengine;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String errorMessage){
        super(errorMessage);
    }
}
