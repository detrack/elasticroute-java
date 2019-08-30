package detrack.elasticroute.routingengine;

public class RushHour {
    private String _from;
    private String _till;
    private String _buffer;
    
    public String getFrom(){
        return _from;
    }
    
    public void setFrom(String from){
        _from = from;
    }
    
    public String getTill(){
        return _till;
    }
    
    public void setTill(String till){
        _till = till;
    }
    
    public String getBuffer(){
        return _buffer;
    }
    
    public void setBuffer(String buffer){
        _buffer = buffer;
    }
    
    /**
     * Instantiates the RushHour Class
     *
     * @param from Starting time of the rush hour
     * @param till Ending time of the rush hour
     * @param buffer Buffer time for the rush hour
     */
    public RushHour(String from, String till, String buffer){
        _from = from;
        _till = till;
        _buffer = buffer;
    }
}
