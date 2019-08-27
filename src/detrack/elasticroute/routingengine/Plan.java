package detrack.elasticroute.routingengine;

import detrack.elasticroute.dashboard.JsonToStringParsingException;
import detrack.elasticroute.dashboard.StringToJsonParsingException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.ea.async.Async.await;

public class Plan {
    public List<Stop> stops;
    public List<Depot> depots;
    public List<Vehicle> vehicles;
    public GeneralSettings general_settings;
    public static HttpClient clients = HttpClient.newHttpClient();
    public static String ApiKey;

    @JsonProperty("generalSettings")
    public GeneralSettings getGeneralSettings(){
        return this.general_settings;
    }
    
    /**
     * Instantiates the Plan Class.
     *
     * @param stopList <code>List<Stop></code> A list of stops to plan
     * @param depotList <code>List<Depot></code> A list of depots
     * @param vehicleList <code>List<Vehicle></code> A list of vehicles
     * @param generalSettings <code>generalSettings</code> The general settings for the plan
     */
    public Plan(List<Stop> stopList, List<Depot> depotList, List<Vehicle> vehicleList, GeneralSettings generalSettings){
        this.stops = stopList;
        this.depots = depotList;
        this.vehicles = vehicleList;
        this.general_settings = generalSettings;
    }
    
    /**
     * Creates a plan based on the instance plan and assigns it to the id
     *
     * @param plan <code>Plan</code> An instance of the Plan Class
     * @param id A unique identifier of the routing plan. It can be referenced later for retrieval of planned results
     */
    public static void createPlan(Plan plan, String id) {
        String json = planToString(plan);
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(id))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.ofString(json))
                                      .build();

        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response);
    }
    
    /**
     * Gets a plan based on the unique identifier
     *
     * @param id The unique identifier of the plan to get
     * @return <code>Plan</code>
     */
    public static Plan getPlan(String id){
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(id))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response.body());
        return stringToPlan(response.body());
    }
    
    /**
     * Gets a plan status based on the unique identifier
     * @param id The unique identifier of the plan to get
     * @return <code>String</code> The status of the plan
     */
    public static String getPlanStatus(String id){
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(id + "/status"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response.body());
        return response.body();
    }
    
    /**
     * Stops the plan with the specified id
     *
     * @param id The unique identifier of the plan to stop
     */
    public static void stopPlan(String id){
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(id + "/stop"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.noBody())
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response.body());
    }
    
    /**
     * Deletes the plan with the specified id
     *
     * @param id The unique identifier of the plan to delete
     */
    public static void deletePlan(String id){
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(encode(id))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .DELETE()
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response);
    }
    
    private static String planToString(Plan plan){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            String string = objectMapper.writeValueAsString(plan);
            return string;
        }
        catch(IOException e){
            // code should never reach here
            throw new JsonToStringParsingException("Stop to String conversion failed! Code should never reach here!" + e);
        }
    }
    
    private static URI encode(String path){
        try {
            URI uri = new URI("https", "app.elasticroute.com", "/api/v1/plan/" + path, null, null);
            System.out.println(uri);
            return uri;
        }
        catch(URISyntaxException e){
            //code should never reach here
            throw new RuntimeException("code shd never reach here!" + e);
        }
    }
    
    private static Plan stringToPlan(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            return objectMapper.readValue(json, Plan.class);
        }
        catch(IOException e){
            // code should never reach here
            throw new StringToJsonParsingException("String to Stop conversion failed! Code should never reach here!" + e);
        }
    }
}
