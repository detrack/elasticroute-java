package detrack.elasticroute.routingengine;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(String errorMessage){
        super(errorMessage);
    }
}
