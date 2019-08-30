package detrack.elasticroute.routingengine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stop{
    
    private String _vehicle_type = "Default";
    private String _depot;
    private String _name;
    private Float _weight_load;
    private Float _volume_load;
    private Float _seating_load;
    private String _address;
    private String _postal_code;
    private Integer _service_time = 0;
    private Float _lat;
    private Float _lng;
    private Integer _from = 900;
    private Integer _till = 1700;
    // read-only
    private String _assign_to;
    private Integer _run;
    private Integer _sequence;
    private String _eta;
    private String _exception;
    
    @JsonProperty("vehicle_type")
    public String getVehicleType(){
        return this._vehicle_type;
    }
    
    public void setVehicleType(String value){
        this._vehicle_type = value;
    }

    public String getDepot(){
        return this._depot;
    }
    
    public void setDepot(String value){
        this._depot = value;
    }
    
    public String getName(){
        return this._name;
    }
    
    public void setName(String value){
        this._name = value;
    }
    
    @JsonProperty("weight_load")
    public Float getWeightLoad(){
        return this._weight_load;
    }
    
    public void setWeightLoad(float value){
        this._weight_load = value;
    }
    
    @JsonProperty("volume_load")
    public Float getVolumeLoad(){
        return this._volume_load;
    }
    
    public void setVolumeLoad(float value){
        this._volume_load = value;
    }
    
    @JsonProperty("seating_load")
    public Float getSeatingLoad(){
        return this._seating_load;
    }
    
    public void setSeatingLoad(float value){
        this._seating_load = value;
    }
    
    public String getAddress(){
        return this._address;
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
    
    @JsonProperty("service_time")
    public Integer getServiceTime(){
        return this._service_time;
    }
    
    public void setServiceTime(int value){
        this._service_time = value;
    }
    
    public Float getLat(){
        return this._lat;
    }
    
    public void setLat(float value){
        this._lat = value;
    }
    
    public Float getLng(){
        return this._lng;
    }
    
    public void setLng(float value){
        this._lng = value;
    }
    
    public Integer getFrom(){
        return this._from;
    }
    
    public void setFrom(int value){
        this._from = value;
    }
    
    public Integer getTill(){
        return this._till;
    }
    
    public void setTill(int value){
        this._till = value;
    }
    
    @JsonProperty("assign_to")
    public String getAssignTo(){
        return this._assign_to;
    }
    public Integer getRun(){
        return this._run;
    }
    public Integer getSequence(){
        return this._sequence;
    }
    public String getEta(){
        return this._eta;
    }
    public String getException(){
        return this._exception;
    }
    
    /**
     * Instantiates the Stop Class
     *
     * @param name The name of the stop being created
     * @param address The address of the stop being created
     */
    @JsonCreator
    public Stop(String name, String address){
        this._name = name;
        this._address = address;
    }
    
    /**
     * Instantiates the Stop Class
     *
     * @param name The name of the stop being created
     * @param lat The latitude of the stop being created
     * @param lng The longitude of the stop being created
     */
    @JsonCreator
    public Stop(String name, float lat, float lng){
        this._name = name;
        this._lat = lat;
        this._lng = lng;
    }
}


