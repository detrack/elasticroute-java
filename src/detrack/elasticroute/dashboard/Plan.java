package detrack.elasticroute.dashboard;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DateTimeException;

import static com.ea.async.Async.await;

public class Plan {
    private static String ApiKey = detrack.elasticroute.dashboard.ApiKey.ApiKey;
    public static HttpClient clients = HttpClient.newHttpClient();
    
    /**
     * Start planning on the given date
     *
     * @param date The date of the plan
     */
    public static void startPlan(String date){
        isApiEmpty();
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date + "/plan"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.noBody())
                                      .build();
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Stop the planning on the given date
     *
     * @param date The date of then plan
     */
    public static void stopPlan(String date){
        isApiEmpty();
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date + "/plan/stop"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .POST(HttpRequest.BodyPublishers.noBody())
                                      .build();
    
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response.body());
        if (response.statusCode() != 200){
            exceptionChecker(response.body());
        }
    }
    
    /**
     * Gets the plan on the given date
     *
     * @param date The date of then plan
     */
    public static void getPlan(String date){
        isApiEmpty();
        dateChecker(date);
        HttpRequest request = HttpRequest.newBuilder()
                                      .uri(URI.create("https://app.elasticroute.com/api/v1/account/stops/" + date + "/plan/status"))
                                      .headers("Authorization", "Bearer "  + ApiKey,  "Content-Type", "application/json")
                                      .GET()
                                      .build();
        
        HttpResponse<String> response = await(clients.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
        System.out.println(response.body());
        if (response.statusCode() != 200){
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
    
    private static void exceptionChecker(String response){
        System.out.println(response);
        
        JsonNode node = stringToJsonNode(response);
        System.out.println(node);
        System.out.println("Checking Exceptions....");
        
        String message = node.get("message").asText();
        System.out.println("Exception Found!!!");
        System.out.println(message);
        if (message.equals("You must add at least one stop before you can plan.")){
            throw new StopNotFoundException("You must add at least one stop before you can plan!");
        }
        if (message.equals("You must add at least one vehicle before you can plan.")){
            throw new VehicleNotFoundException("You must add at least one vehicle before you can plan!");
        }
        if (message.equals("Unauthenticated.")){
            throw new InvalidApiKeyException("The given API Key is invalid!");
        }
        if (message.equals("Plan Not Found.")){
            throw new PlanNotFoundException("There is no ongoing plan.");
        }
        if (message.equals("Geocoding is still in progress.")){
            throw new RuntimeException("Geocoding is still in progress. Please wait a moment before starting the plan");
        }
        else {
            throw new InvalidRequestException(message);
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
    
    public static void isApiEmpty(){
        if (ApiKey == null){
            throw new EmptyApiKeyException("API Key cannot be empty!");
        }
        if (ApiKey.equals("")){
            throw new EmptyApiKeyException("API Key cannot be empty!");
        }
    }
}
