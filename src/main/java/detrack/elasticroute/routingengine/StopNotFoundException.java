package detrack.elasticroute.routingengine;

public class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
