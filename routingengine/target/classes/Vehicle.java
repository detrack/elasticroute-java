package detrack.elasticroute.routingengine;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String _name;
    private String _depot;
    private int _priority = 0;
    private Float _weight_capacity;
    private Float _volume_capacity;
    private Float _seating_capacity;
    private Integer _avail_from = 900;
    private Integer _avail_till = 1700;
    private List<String> _vehicle_types;
    private boolean _return_to_depot = false;
    
    public String getName(){
        return this._name;
    }
    public void setName(String value){
        this._name = value;
    }
    public String getDepot(){
        return this._depot;
    }
    public void setDepot(String value){
        this._depot = value;
    }
    public int getPriority(){
        return this._priority;
    }
    public void setPriority(int value){
        this._priority = value;
    }
    @JsonProperty("weight_capacity")
    public Float getWeightCapacity(){
        return this._weight_capacity;
    }
    public void setWeightCapacity(Float value){
        this._weight_capacity = value;
    }
    @JsonProperty("volume_capacity")
    public Float getVolumeCapacity(){
        return this._volume_capacity;
    }
    public void setVolumeCapacity(Float value){
        this._volume_capacity = value;
    }
    @JsonProperty("seating_capacity")
    public Float getSeatingCapacity(){
        return this._seating_capacity;
    }
    public void setSeatingCapacity(Float value){
        this._seating_capacity = value;
    }
    @JsonProperty("avail_from")
    public Integer getAvailFrom(){
        return this._avail_from;
    }
    public void setAvailFrom(Integer value){
        this._avail_from = value;
    }
    @JsonProperty("avail_till")
    public Integer getAvailTill(){
        return this._avail_till;
    }
    public void setAvailTill(Integer value){
        this._avail_till = value;
    }
    @JsonProperty("vehicle_types")
    public List<String> getVehicleTypes(){
        return this._vehicle_types;
    }
    public void setVehicleTypes(List<String> value){
        this._vehicle_types = value;
    }
    @JsonProperty("return_to_depot")
    public boolean getReturnToDepot(){
        return this._return_to_depot;
    }
    public void setReturnToDepot(boolean value){
        this._return_to_depot = value;
    }
    
    public Vehicle(String name){
        this._name = name;
        this._vehicle_types = new ArrayList<String>();
        this._vehicle_types.add("Default");
    }
    
}
