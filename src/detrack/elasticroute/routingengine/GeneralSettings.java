package detrack.elasticroute.routingengine;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralSettings {
    private String _country;
    private String _timezone;
    private Integer _loading_time;
    private Integer _buffer;
    private Integer _service_time = 0;
    private Integer _distance_unit;
    private Integer _max_time;
    private Integer _max_distance;
    private Integer _max_stops;
    private Integer _max_runs;
    private Integer _from = 900;
    private Integer _till = 1700;
    private String _webhook_url;
    
    public String getCountry(){
        return this._country;
    }
    public void setCountry(String value){
        this._country = value;
    }
    public String getTimezone(){
        return this._timezone;
    }
    public void setTimezone(String value){
        this._timezone = value;
    }
    @JsonProperty("loading_time")
    public Integer getLoadingTime(){
        return this._loading_time;
    }
    public void setLoadingTime(Integer value){
        this._loading_time = value;
    }
    public Integer getBuffer(){
        return this._buffer;
    }
    public void setBuffer(Integer value){
        this._buffer = value;
    }
    @JsonProperty("service_time")
    public Integer getServiceTime(){
        return this._service_time;
    }
    public void setServiceTime(Integer value){
        this._service_time = value;
    }
    @JsonProperty("distance_unit")
    public Integer getDistanceUnit(){
        return this._distance_unit;
    }
    public void setDistanceUnit(Integer value){
        this._distance_unit = value;
    }
    @JsonProperty("max_time")
    public Integer getMaxTime(){
        return this._max_time;
    }
    public void setMaxTime(Integer value){
        this._max_time = value;
    }
    @JsonProperty("max_distance")
    public Integer getMaxDistance(){
        return this._max_distance;
    }
    public void setMaxDistance(Integer value){
        this._max_distance = value;
    }
    @JsonProperty("max_stops")
    public Integer getMaxStops(){
        return this._max_stops;
    }
    public void setMaxStops(Integer value){
        this._max_stops = value;
    }
    @JsonProperty("max_runs")
    public Integer getMaxRuns(){
        return this._max_runs;
    }
    public void setMaxRuns(Integer value){
        this._max_runs = value;
    }
    public Integer getFrom(){
        return this._from;
    }
    public void setFrom(Integer value){
        this._from = value;
    }
    public Integer getTill(){
        return this._till;
    }
    public void setTill(Integer value){
        this._till = value;
    }
    @JsonProperty("webhook_url")
    public String getWebhookUrl(){
        return this._webhook_url;
    }
    public void setWebhookUrl(String value){
        this._webhook_url = value;
    }
    
    /**
     * Instantiates the GeneralSettings Class
     *
     * @param country The country where the plan will be made.
     * @param timezone Timezone where the plan will be made .
     */
    public GeneralSettings(String country, String timezone){
        this._country = country;
        this._timezone = timezone;
    }
}
