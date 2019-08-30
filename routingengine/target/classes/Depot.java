package detrack.elasticroute.routingengine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Depot {
    private String _name;
    private Float _lat;
    private Float _lng;
    private String _address;
    private String _postal_code;
    
    public String getName(){
        return this._name;
    }
    public void setName(String value){
        this._name = value;
    }
    public Float getLat(){
        return this._lat;
    }
    public void setlat(Float value){
        this._lat = value;
    }
    public Float getLng(){
        return this._lng;
    }
    public void setLng(Float value){
        this._lng = value;
    }
    public String getAddress() {
        return _address;
    }
    public void setAddress(String value){
        this._address = value;
    }
    @JsonProperty("postal_code")
    public String getPostalCode(){
        return this._postal_code;
    }
    public void setPostalCode(String value){
        this._postal_code = value;
    }
    
    /**
     * Instantiates the Depot Class
     *
     * @param name The name of the Depot being created
     * @param address The address of the depot being created
     */
    public Depot(String name, String address){
        this._name = name;
        this._address = address;
    }
    
    /**
     * Instantiates the Depot Class
     *
     * @param name The name of the Depot being created
     * @param lat The latitude of the Depot being created
     * @param lng The longitude of the Depot being created
     */
    public Depot(String name, float lat, float lng){
        this._name = name;
        this._lat = lat;
        this._lng = lng;
    }
}
