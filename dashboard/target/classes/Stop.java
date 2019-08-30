package detrack.elasticroute.dashboard;

import java.beans.*;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DateTimeException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.ea.async.Async.await;

public class Stop implements PropertyChangeListener {
    
    public static HttpClient clients = HttpClient.newHttpClient();
    private static String ApiKey = detrack.elasticroute.dashboard.ApiKey.ApiKey;
    
    //region all the fields
    private String _vehicle_type;
    private String _depot;
    private String _name;
    private Float _weight_load;
    private Float _volume_load;
    private Float _seating_load;
    private String _address;
    private String _address_1;
    private String _address_2;
    private String _address_3;
    private String _postal_code;
    private Integer _service_time;
    private Float _lat;
    private Float _lng;
    private String _country;
    private String _city;
    private String _state;
    private String _time_window;
    private String _group;
    private Integer _priority;
    // foodpanda only -start-
    private String _load_id;
    // -end-
    private String _assign_to;
    private Integer _run;
    private Integer _sequence;
    private Date _eta;
    private String _exception;
    private String _exception_reason;
    private String _plan_vehicle_type;
    private String _plan_depot;
    private String _plan_service_time;
    private Date _mapped_at;
    private Date _planned_at;
    private Date _created_at;
    private Date _updated_at;
    private boolean sorted;
    private String _violations;
    private Map<String, Object> _modified_properties;
    
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    @JsonProperty("vehicle_type")
    public String getVehicleType() {
        return _vehicle_type;
    }
    
    @JsonProperty("vehicle_type")
    public void setVehicleType(String vehicle_type) {
        String oldValue = this._vehicle_type;
        _vehicle_type = vehicle_type;
        pcs.firePropertyChange("vehicle_type", oldValue, _vehicle_type);
    }
    
    public String getDepot() {
        return _depot;
    }
    
    public void setDepot(String depot) {
        String oldValue = this._depot;
        _depot = depot;
        pcs.firePropertyChange("depot", oldValue, _depot);
    }
    
    public String getName() {
        return _name;
    }
    
    public void setName(String name) {
        String oldValue = this._name;
        _name = name;
        pcs.firePropertyChange("name", oldValue, _name);
    }
    
    @JsonProperty("weight_load")
    public Float getWeightLoad() {
        return _weight_load;
    }
    
    @JsonProperty("weight_load")
    public void setWeightLoad(Float weight_load) {
        Float oldValue = this._weight_load;
        _weight_load = weight_load;
        pcs.firePropertyChange("weight_load", oldValue, _weight_load);
    }
    
    @JsonProperty("volume_load")
    public Float getVolumeLoad() {
        return _volume_load;
    }
    
    @JsonProperty("volume_load")
    public void setVolumeLoad(Float volume_load) {
        Float oldValue = this._volume_load;
        _volume_load = volume_load;
        pcs.firePropertyChange("volume_load", oldValue, _volume_load);
    }
    
    @JsonProperty("seating_load")
    public Float getSeatingLoad() {
        return _seating_load;
    }
    
    @JsonProperty("seating_load")
    public void setSeatingLoad(Float seating_load) {
        Float oldValue = this._seating_load;
        _seating_load = seating_load;
        pcs.firePropertyChange("seating_load", oldValue, _seating_load);
    }
    
    public String getAddress() {
        return _address;
    }
    
    public void setAddress(String address) {
        String oldValue = this._address;
        _address = address;
        pcs.firePropertyChange("address", oldValue, _address);
    }
    
    @JsonProperty("address_1")
    public String getAddress1() {
        return _address_1;
    }
    
    @JsonProperty("address_1")
    public void setAddress1(String address_1) {
        String oldValue = this._address_1;
        _address_1 = address_1;
        pcs.firePropertyChange("address_1", oldValue, _address_1);
    }
    
    @JsonProperty("address_2")
    public String getAddress2() {
        return _address_2;
    }
    
    @JsonProperty("address_2")
    public void setAddress2(String address_2) {
        String oldValue = this._address_2;
        _address_2 = address_2;
        pcs.firePropertyChange("address_2", oldValue, _address_2);
    }
    
    @JsonProperty("address_3")
    public String getAddress3() {
        return _address_3;
    }
    
    @JsonProperty("address_3")
    public void setAddress3(String address_3) {
        String oldValue = this._address_3;
        _address_3 = address_3;
        pcs.firePropertyChange("address_3", oldValue, _address_3);
    }
    
    @JsonProperty("postal_code")
    public String getPostalCode() {
        return _postal_code;
    }
    
    @JsonProperty("postal_code")
    public void setPostalCode(String postal_code) {
        String oldValue = this._postal_code;
        _postal_code = postal_code;
        pcs.firePropertyChange("postal_code", oldValue, _postal_code);
    }
    
    @JsonProperty("service_time")
    public Integer getServiceTime() {
        return _service_time;
    }
    
    @JsonProperty("service_time")
    public void setServiceTime(Integer service_time) {
        Integer oldValue = this._service_time;
        _service_time = service_time;
        pcs.firePropertyChange("service_time", oldValue, _service_time);
    }
    
    public Float getLat() {
        return _lat;
    }
    
    public void setLat(Float lat) {
        Float oldValue = this._lat;
        _lat = lat;
        pcs.firePropertyChange("lat", oldValue, _lat);
    }
    
    public Float getLng() {
        return _lng;
    }
    
    public void setLng(Float lng) {
        Float oldValue = this._lng;
        _lng = lng;
        pcs.firePropertyChange("lng", oldValue, _lng);
    }
    
    public String getCountry() {
        return _country;
    }
    
    public void setCountry(String country) {
        String oldValue = this._country;
        _country = country;
        pcs.firePropertyChange("country", oldValue, _country);
    }
    
    public String getCity() {
        return _city;
    }
    
    public void setCity(String city) {
        String oldValue = this._city;
        _city = city;
        pcs.firePropertyChange("city", oldValue, _city);
    }
    
    public String getState() {
        return _state;
    }
    
    public void setState(String state) {
        String oldValue = this._state;
        _state = state;
        pcs.firePropertyChange("state", oldValue, _state);
    }
    
    @JsonProperty("time_window")
    public String getTimeWindow() {
        return _time_window;
    }
    
    @JsonProperty("time_window")
    public void setTimeWindow(String time_window) {
        String oldValue = this._time_window;
        _time_window = time_window;
        pcs.firePropertyChange("time_window", oldValue, _time_window);
    }
    
    public String getGroup() {
        return _group;
    }
    
    public void setGroup(String group) {
        String oldValue = this._group;
        _group = group;
        pcs.firePropertyChange("group", oldValue, _group);
    }
    
    public Integer getPriority(){
        return _priority;
    }
    
    public void setPriority(Integer priority){
        Integer oldValue = this._priority;
        _priority = priority;
        pcs.firePropertyChange("priority", oldValue, _priority);
    }
    
    // foodpanda only -start-
    public String getLoadID(){
        return _load_id;
    }
    
    public void setLoadID(String loadID){
        String oldValue = this._load_id;
        _load_id = loadID;
        pcs.firePropertyChange("load_id", oldValue, _load_id);
    }
    // foodpanda only -end-
    
    @JsonProperty("assign_to")
    public String getAssignTo() {
        return _assign_to;
    }
    
    @JsonIgnore
    public Integer getRun() {
        return _run;
    }
    
    @JsonIgnore
    public Integer getSequence() {
        return _sequence;
    }
    
    @JsonIgnore
    public Date getEta() {
        return _eta;
    }
    
    @JsonIgnore
    public String getException() {
        return _exception;
    }
    
    @JsonIgnore
    public String getExceptionReason() {
        return _exception_reason;
    }
    
    @JsonIgnore
    public String getPlanVehicleType() {
        return _plan_vehicle_type;
    }
    
    @JsonIgnore
    public String getPlanDepot() {
        return _plan_depot;
    }
    
    @JsonIgnore
    public String getPlanServiceTime() {
        return _plan_service_time;
    }
    
    @JsonIgnore
    public Date getMappedAt() {
        return _mapped_at;
    }
    
    @JsonIgnore
    public Date getPlannedAt() {
        return _planned_at;
    }
    
    @JsonIgnore
    public Date getCreatedAt() {
        return _created_at;
    }
    
    @JsonIgnore
    public Date getUpdatedAt() {
        return _updated_at;
    }
    
    @JsonIgnore
    public boolean getSorted() {
        return sorted;
    }
    
    @JsonIgnore
    public String getViolations() {
        return _violations;
    }
    
    public void propertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
        Object propertyValue = event.getNewValue();
        _modified_properties.put(propertyName, propertyValue);
    }
    //endregion
    
    /**
     * Instantiates the Stop Class.
     * The stop instance can then be used to create and update stop.
     * @param name The name of the stop. It must be unique on the same day
     * @param address The address of the stop
     */
    public Stop(@JsonProperty("name") String name, @JsonProperty("address") String address){
        pcs.addPropertyChangeListener(this);
        if (name == null || address == null || name.isEmpty() || address.isEmpty()){
            throw new InvalidParameterException("Name or address cannot be empty!");
        }
        this._name = name;
        this._address = address;
        this._modified_properties = new HashMap<>();
        this._modified_properties.put("name", name);
        this._modified_properties.put("address", address);
    }
    
    /**
     * Gets the stop based on the given name and date
     *
     * @param name The name of the stop to get
     * @param date The date of the stop to get
     * @return A Stop class instance
     */
    public static Stop getStop(String name, String date){
        isApiEmpty(ApiKey);
        dateChecker(date);
        
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(date + "/" + name))
                                      .headers("Authorization", "Bearer " + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if(response.statusCode() != 200){
            exceptionChecker(response.body());
        }
        JsonNode node = stringToJsonNode(response.body());
        return stringToStop(getJsonNodeData(node));
    }
    
    /**
     * Creates a stop from the given stop instance on the date specified
     *
     * @param stop The stop instance to create
     * @param date The date of the stop to create
     */
    public static void createStop(Stop stop, String date){
        isApiEmpty(ApiKey);
        dateChecker(date);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        String json = "{\n\"data\": " + stopToString(stop) + "}";
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if(response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Updates a stop with the same name as the instance stop on the specified date.
     *
     * @param date The date of the stop to update
     */
    public void updateStop(String date){
        isApiEmpty(ApiKey);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        String json = "{\n\"data\": " + mapToString(this._modified_properties) + "}";
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(date + "/" + this._name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .PUT(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if(response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Updates a stop based on the given name and date, using information from the instance stop
     *
     * @param name The name of the stop to update
     * @param date The date of the stop to update
     */
    public void updateStop(String name, String date){
        isApiEmpty(ApiKey);
        dateChecker(date);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        String json = "{\n\"data\": " + mapToString(this._modified_properties) + "}";
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(date + "/" + name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .PUT(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if(response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Deletes a stop with the given name and date
     *
     * @param name The name of the stop to delete
     * @param date The date of the stop to delete
     */
    public static void deleteStop(String name, String date){
        isApiEmpty(ApiKey);
        dateChecker(date);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(date + "/" + name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .DELETE()
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if(response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Gets a list of stop on the given date
     * @param date The date of the stops to get
     * @return {@code List<Stop>} A list of stops on the given date
     */
    public static List<Stop> getStops(String date) {
        isApiEmpty(ApiKey);
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        
        return await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                 .thenApply(HttpResponse::body)
                 .thenApply(Stop::stringToJsonNode)
                 .thenApply(Stop::addNodeToList));
    }
    
    /**
     * Gets a list of stop on the given date with a limit and page number. It is used to get stops on the day
     * where the stops exceeded 100.
     *
     * @param date The date of the stops to get
     * @param limit The number of maximum stops to get. Maximum value is 100.
     * @param pageNumber The page number of the stops to get
     * @return {@code List<Stop>} A list of stops based on the given arguments
     */
    public static ArrayList<Stop> getStops(String date, int limit, int pageNumber) {
        if (limit > 100){
            throw new InvalidParameterException("Maximum limit is 100!");
        }
        isApiEmpty(ApiKey);
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/"
                                                              + date + "?limit=" + limit + "&page=" + pageNumber))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
        JsonNode node = stringToJsonNode(response.body());
        return addNodeToList(node);
    }
    
    /**
     * Creates stops based on the given stopList and date
     *
     * @param stopList List containing the stops to create
     * @param date The date of the stopList to create
     */
    public static void createStops(List<Stop> stopList, String date) {
        isApiEmpty(ApiKey);
        dateChecker(date);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        String json = "{\n\"data\": " + stopListToString(stopList) + "}";
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date + "/bulk"))
                                      .headers("Authorization", "Bearer " + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();

        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Deletes all existing stops on the day and replace it with the new stopList
     *
     * @param stopList List containing the stops to create
     * @param date The date of the stopList to create
     */
    public static void replaceAllStops(List<Stop> stopList, String date) {
        isApiEmpty(ApiKey);
        dateChecker(date);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    
        String json = "{\n\"data\": " + stopListToString(stopList) + "}";
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date + "/bulk" + "?delete=true"))
                                      .headers("Authorization", "Bearer " + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200) {
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Deletes all the stops on the given date
     * @param date The date of the stops to delete
     */
    public static void deleteStops(String date){
        isApiEmpty(ApiKey);
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .DELETE()
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200) {
            exceptionChecker(response.body());
        }
    }
    
    private static void dateChecker(String date){
        if(date.matches("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
            int day = Integer.parseInt(date.split("-")[2]);
            int month = Integer.parseInt(date.split("-")[1]);
            if (month > 12 || month <= 0){
                throw new DateTimeException("Please enter a valid month");
            }
            if (day > 31 || day <= 0){
                throw new DateTimeException("Please enter a valid day");
            }
            return;
        }
        throw new DateTimeException("Invalid date format. Date must be integer and date format must be yyyy-mm-dd");
    }
    
    private static void isApiEmpty(String APIKey){
        if (APIKey == null || APIKey.equals("")) {
            throw new EmptyApiKeyException("API Key cannot be empty!");
        }
    }
    
    private static JsonNode stringToJsonNode(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            return objectMapper.readTree(json);
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to JSON Node conversion failed. Reason: " + e);
        }
    }
    
    private static void exceptionChecker(String response){
        JsonNode node = stringToJsonNode(response);
        String message = node.get("message").asText();
        if (message.equals("Stop Not Found.")){
            throw new StopNotFoundException("Stop Not Found!");
        }
        else if (message.equals("Unauthenticated.")){
            throw new InvalidApiKeyException("The given API Key is invalid!");
        }
        else {
            // empty data
            // invalid stop name in create stop
            for (JsonNode errorNode : node.get("errors")){
                throw new InvalidRequestException(errorNode.asText());
            }
        }
    }
    
    private static String getJsonNodeData(JsonNode node){
        return node.get("data").toString();
    }
    
    private static Stop stringToStop(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            return objectMapper.readValue(json, Stop.class);
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to Stop conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static String stopToString(Stop stop){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            String string = objectMapper.writeValueAsString(stop);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("Stop to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static String stopListToString(List<Stop> stopList){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try{
            String string = objectMapper.writeValueAsString(stopList);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("StopList to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static String mapToString(Map<String,Object> map){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            String string = objectMapper.writeValueAsString(map);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("Map to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static ArrayList<Stop> addNodeToList(JsonNode node){
        ArrayList<Stop> stopArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        JsonNode responseString = node.get("data");
        try {
            for (JsonNode data : responseString) {
                Stop stop = objectMapper.readValue(data.toString(), Stop.class);
                stopArrayList.add(stop);
            }
            return stopArrayList;
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to Stop conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static URI encode(String path){
        try {
            URI uri = new URI("https", "app.elasticroute.com", "/api/v1/account/stops/" + path, null, null);
            return uri;
        }
        catch(URISyntaxException e){
            //code should never reach here
            throw new RuntimeException("code shd never reach here!" + e);
        }
    }

}
