package detrack.elasticroute.routingengine;

public class PlanNotFoundException extends RuntimeException{
    public PlanNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
