package detrack.elasticroute.dashboard;

public class PlanNotFoundException extends RuntimeException{
    public PlanNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
