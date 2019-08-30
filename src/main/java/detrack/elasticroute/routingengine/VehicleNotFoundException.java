package detrack.elasticroute.routingengine;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
