package detrack.elasticroute.dashboard;

import java.beans.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static com.ea.async.Async.await;

public class Vehicle implements PropertyChangeListener{
    private static String ApiKey = detrack.elasticroute.dashboard.ApiKey.ApiKey;
    public static HttpClient clients = HttpClient.newHttpClient();
    
    private String _name;
    private String _depot;
    private Integer _priority = 0;
    private Integer _buffer;
    private boolean _avail = true;
    private boolean _avail_mon = true;
    private boolean _avail_tue = true;
    private boolean _avail_wed = true;
    private boolean _avail_thu = true;
    private boolean _avail_fri = true;
    private boolean _avail_sat = true;
    private boolean _avail_sun = true;
    private Float _weight_capacity;
    private Float _volume_capacity;
    private Float _seating_capacity;
    private Integer _avail_from = 900;
    private Integer _avail_till = 1700;
    private ArrayList<String> _vehicle_types;
    private ArrayList<String> _groups;
    private boolean _all_groups = true;
    private ArrayList<String> _zones;
    private boolean _return_to_depot = true;
    private String _created_at;
    private String _update_at;
    private Map<String, Object> _modified_properties;
    
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    @JsonProperty("vehicle_types")
    public ArrayList<String> getVehicleTypes(){
        return this._vehicle_types;
    }
    
    public void setVehicleTypes(ArrayList<String> value){
        ArrayList<String> oldValue = this._vehicle_types;
        this._vehicle_types = value;
        pcs.firePropertyChange("vehicle_types", oldValue, _vehicle_types);
    }
    
    @JsonProperty("groups")
    public ArrayList<String> getGroups(){
        return this._groups;
    }
    
    public void setGroups(ArrayList<String> value){
        ArrayList<String> oldValue = this._groups;
        this._groups = value;
        pcs.firePropertyChange("groups", oldValue, _groups);
    }
    
    @JsonProperty("zones")
    public ArrayList<String> getZones(){
        return this._zones;
    }
    
    public void setZones(ArrayList<String> value){
        ArrayList<String> oldValue = this._zones;
        this._zones = value;
        pcs.firePropertyChange("zones", oldValue, _zones);
    }
    
    @JsonProperty("name")
    public String getName(){
        return this._name;
    }
    public void setName(String value){
        String oldValue = this._name;
        this._name = value;
        pcs.firePropertyChange("name", oldValue, _name);
    }
    
    @JsonProperty("depot")
    public String getDepot(){
        return this._depot;
    }
    public void setDepot(String value){
        String oldValue = this._depot;
        this._depot = value;
        pcs.firePropertyChange("depot", oldValue, _depot);
    }
    
    @JsonProperty("priority")
    public Integer getPriority(){
        return this._priority;
    }
    public void setPriority(Integer value){
        Integer oldValue = this._priority;
        this._priority = value;
        pcs.firePropertyChange("priority", oldValue, _priority);
    }
    
    @JsonProperty("buffer")
    public Integer getBuffer(){
        return this._buffer;
    }
    public void setBuffer(Integer value){
        Integer oldValue = this._buffer;
        this._buffer = value;
        pcs.firePropertyChange("buffer", oldValue, _buffer);
    }
    
    @JsonProperty("avail")
    public boolean getAvail(){
        return this._avail;
    }
    public void setAvail(boolean value){
        boolean oldValue = this._avail;
        this._avail = value;
        pcs.firePropertyChange("avail", oldValue, _avail);
    }
    @JsonProperty("avail_mon")
    public boolean getAvailMon(){
        return this._avail_mon;
    }
    public void setAvailMon(boolean value){
        boolean oldValue = this._avail_mon;
        this._avail_mon = value;
        pcs.firePropertyChange("avail_mon", oldValue, _avail_mon);
    }
    @JsonProperty("avail_tue")
    public boolean getAvailTue(){
        return this._avail_tue;
    }
    public void setAvailTue(boolean value){
        boolean oldValue = this._avail_tue;
        this._avail_tue = value;
        pcs.firePropertyChange("avail_tue", oldValue, _avail_tue);
    }
    @JsonProperty("avail_wed")
    public boolean getAvailWed(){
        return this._avail_wed;
    }
    public void setAvailWed(boolean value){
        boolean oldValue = this._avail_wed;
        this._avail_wed = value;
        pcs.firePropertyChange("avail_wed", oldValue, _avail_wed);
    }
    @JsonProperty("avail_thu")
    public boolean getAvailThu(){
        return this._avail_thu;
    }
    public void setAvailThu(boolean value){
        boolean oldValue = this._avail_thu;
        this._avail_thu = value;
        pcs.firePropertyChange("avail_thu", oldValue, _avail_thu);
    }
    @JsonProperty("avail_fri")
    public boolean getAvailFri(){
        return this._avail_fri;
    }
    public void setAvailFri(boolean value){
        boolean oldValue = this._avail_fri;
        this._avail_fri = value;
        pcs.firePropertyChange("avail_fri", oldValue, _avail_fri);
    }
    @JsonProperty("avail_sat")
    public boolean getAvailSat(){
        return this._avail_sat;
    }
    public void setAvailSat(boolean value){
        boolean oldValue = this._avail_sat;
        this._avail_sat = value;
        pcs.firePropertyChange("avail_sat", oldValue, _avail_sat);
    }
    @JsonProperty("avail_sun")
    public boolean getAvailSun(){
        return this._avail_sun;
    }
    public void setAvailSun(boolean value){
        boolean oldValue = this._avail_sun;
        this._avail_sun = value;
        pcs.firePropertyChange("avail_sun", oldValue, _avail_sun);
    }
    @JsonProperty("weight_capacity")
    public Float getWeightCapacity(){
        return this._weight_capacity;
    }
    public void setWeightCapacity(Float value){
        Float oldValue = this._weight_capacity;
        this._weight_capacity = value;
        pcs.firePropertyChange("weight_capacity", oldValue, _weight_capacity);
    }
    @JsonProperty("volume_capacity")
    public Float getVolumeCapacity(){
        return this._volume_capacity;
    }
    public void setVolumeCapacity(Float value){
        Float oldValue = this._volume_capacity;
        this._volume_capacity = value;
        pcs.firePropertyChange("volume_capacity", oldValue, _volume_capacity);
    }
    @JsonProperty("seating_capacity")
    public Float getSeatingCapacity(){
        return this._seating_capacity;
    }
    public void setSeatingCapacity(Float value){
        Float oldValue = this._seating_capacity;
        this._seating_capacity = value;
        pcs.firePropertyChange("seating_capacity", oldValue, _seating_capacity);
    }
    @JsonProperty("avail_from")
    public Integer getAvailFrom(){
        return this._avail_from;
    }
    public void setAvailFrom(Integer value){
        Integer oldValue = this._avail_from;
        this._avail_from = value;
        pcs.firePropertyChange("avail_from", oldValue, _avail_from);
    }
    @JsonProperty("avail_till")
    public Integer getAvailTill(){
        return this._avail_till;
    }
    public void setAvailTill(Integer value){
        Integer oldValue = this._avail_till;
        this._avail_till = value;
        pcs.firePropertyChange("avail_to", oldValue, _avail_till);
    }
    @JsonProperty("all_groups")
    public boolean getAllGroups(){
        return this._all_groups;
    }
    public void setAllGroups(boolean value){
        boolean oldValue = this._all_groups;
        this._all_groups = value;
        pcs.firePropertyChange("all_groups", oldValue, _all_groups);
    }
    @JsonProperty("return_to_depot")
    public boolean getReturnToDepot(){
        return this._return_to_depot;
    }
    public void setReturnToDepot(boolean value){
        boolean oldValue = this._return_to_depot;
        this._return_to_depot = value;
        pcs.firePropertyChange("return_to_depot", oldValue, _return_to_depot);
    }
    @JsonIgnore
    public String getCreatedAt(){
        return this._created_at;
    }
    @JsonIgnore
    public String getUpdateAt(){
        return this._update_at;
    }
    
    public void propertyChange(PropertyChangeEvent event) {
        String propertyName = event.getPropertyName();
        Object propertyValue = event.getNewValue();
        _modified_properties.put(propertyName, propertyValue);
    }
    
    /**
     * Instantiates the Vehicle Class
     * The vehicle instance can then be used to create and update vehicle.
     *
     * @param name The name of the vehicle. It must be unique
     */
    public Vehicle(@JsonProperty("name") String name){
        this._name = name;
        this._modified_properties = new HashMap<>();
        this._zones = new ArrayList<String>();
        this._vehicle_types = new ArrayList<String>();
        this._groups = new ArrayList<String>();
        pcs.addPropertyChangeListener(this);
    }
    
    /**
     * Gets the vehicle based on the given name
     *
     * @param name The name of the vehicle to get
     * @return A Vehicle class instance
     */
    public static Vehicle getVehicle(String name){
        isApiEmpty(ApiKey);
        isNullorEmpty(name);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    
        JsonNode node = stringToJsonNode(response.body());
        return stringToVehicle(getJsonNodeData(node));
    }
    
    /**
     * Creates a vehicle from the given vehicle instance
     *
     * @param vehicle The vehicle instance to create
     */
    public static void createVehicle(Vehicle vehicle) {
        isApiEmpty(ApiKey);
        isNullorEmpty(vehicle.getName());
        
        String json = "{\n\"data\": " + vehicleToString(vehicle) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Updates a vehicle with the same name as the instance vehicle
     */
    public void updateVehicle() {
        isApiEmpty(ApiKey);
        isNullorEmpty(this.getName());
        
        String json = "{\n\"data\": " + mapToString(this._modified_properties) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(this._name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .PUT(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Updates the vehicle based on the given name, using information from the instance vehicle.
     *
     * @param name
     */
    public void updateVehicle(String name) {
        isApiEmpty(ApiKey);
        String json = "{\n\"data\": " + mapToString(this._modified_properties) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .PUT(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Deletes a vehicle with the given name
     *
     * @param name The name of the vehicle to delete
     */
    public static void deleteVehicle(String name){
        isApiEmpty(ApiKey);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(name))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .DELETE()
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Gets all the vehicles
     *
     * @return <code> List<Vehicle> </code> A list of existing vehicles
     */
    public static List<Vehicle> getVehicles(){
        isApiEmpty(ApiKey);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles"))
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
     * Gets all the vehicles with limit and page number
     *
     * @param limit The number of maximum vehicles to get. Maximum value is 100
     * @param pageNumber The page number of the stops to get
     * @return <code> List<Vehicle> </code> A list of vehicles based on the given arguments
     */
    public static List<Vehicle> getVehicles(int limit, int pageNumber){
        isApiEmpty(ApiKey);
        if (limit > 100){
            throw new InvalidParameterException("Limit cannot be more than 100!");
        }
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles?limit="
                                                              + limit + "&page=" + pageNumber))
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
     * Creates vehicles basd on the given vehicleList and date
     *
     * @param vehicleList List containing the vehicles to create
     */
    public static void createVehicles(List<Vehicle> vehicleList) {
        isApiEmpty(ApiKey);
        String json = "{\n\"data\": " + vehicleListToString(vehicleList) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles/bulk"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Delete all existing vehicles on the day and replace it with the new vehicleList
     *
     * @param vehicleList List containing the vehicles to create
     */
    public static void replaceAllVehicles(List<Vehicle> vehicleList) {
        isApiEmpty(ApiKey);
        String json = "{\n\"data\": " + vehicleListToString(vehicleList) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles/bulk?delete=true"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Update vehicles based on the given vehicleList
     *
     * @param vehicleList List of Vehicles that contains information of vehicles to update
     */
    public static void updateVehicles(List<Vehicle> vehicleList) {
        isApiEmpty(ApiKey);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList){
            Map<String, Object> data = new HashMap<>();
            data.put("name", vehicle.getName());
            data.put("data", vehicle._modified_properties);
            dataList.add(data);
        }
        
        String json = "{\n\"data\": " + mapListToString(dataList) + "}";
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .PUT(HttpRequest.BodyPublishers.ofString(json))
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Deletes all vehicles
     */
    public static void deleteVehicles(){
        isApiEmpty(ApiKey);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/vehicles"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .DELETE()
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    private static String vehicleToString(Vehicle vehicle){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try{
            String string = objectMapper.writeValueAsString(vehicle);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("Vehicle to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static void exceptionChecker(String response){
        System.out.println(response);
    
        JsonNode node = stringToJsonNode(response);
        System.out.println(node);
        System.out.println("Checking Exceptions....");
    
        String message = node.get("message").asText();
        System.out.println("Exception Found!!!");
        System.out.println(message);
        if (message.equals("Vehicle Not Found.")){
            throw new VehicleNotFoundException("Vehicle Not Found!");
        }
        else if (message.equals("Unauthenticated.")){
            throw new InvalidApiKeyException("The given API Key is invalid!");
        }
        else {
            // name - this name has already been taken
            //
            for (JsonNode errorNode : node.get("errors")){
                System.out.println("error node is : " + errorNode);
                throw new InvalidRequestException(errorNode.get(0).asText());
            }
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
    
    private static URI encode(String path){
        try {
            URI uri = new URI("https", "app.elasticroute.com", "/api/v1/account/vehicles/" + path, null, null);
            System.out.println(uri);
            return uri;
        }
        catch(URISyntaxException e){
            //code should never reach here
            throw new RuntimeException("Code shd never reach here!" + e);
        }
    }
    
    private static void isNullorEmpty(String string){
        if (string == null || string.equals("")){
            throw new InvalidParameterException("Name or Address cannot be empty!");
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
            throw new StringToJsonParsingException("String to JSON Node conversion failed! Code should never reach here! Reason: " + e);
        }
    }
    
    private static String getJsonNodeData(JsonNode node){
        return node.get("data").toString();
    }
    
    private static Vehicle stringToVehicle(String json){
        System.out.println(json);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            return objectMapper.readValue(json, Vehicle.class);
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to Vehicle conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static String vehicleListToString(List<Vehicle> vehicleList){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try{
            String string = objectMapper.writeValueAsString(vehicleList);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("VehicleList to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static String mapListToString(List<Map<String,Object>> mapList){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            String string = objectMapper.writeValueAsString(mapList);
            System.out.println(string);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("String to Stop conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static ArrayList<Vehicle> addNodeToList(JsonNode node){
        ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        
        var responseString = node.get("data");
        try {
            for (JsonNode data : responseString) {
                Vehicle vehicle = objectMapper.readValue(data.toString(), Vehicle.class);
                vehicleArrayList.add(vehicle);
            }
            return vehicleArrayList;
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to Stop conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static void isApiEmpty(String APIKey){
        if (APIKey == null || APIKey.equals("")) {
            throw new EmptyApiKeyException("API Key cannot be empty!");
        }
    }
}

