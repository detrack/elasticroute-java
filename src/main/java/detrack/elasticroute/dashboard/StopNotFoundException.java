package detrack.elasticroute.dashboard;

public class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
