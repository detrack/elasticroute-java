package detrack.elasticroute.dashboard;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
