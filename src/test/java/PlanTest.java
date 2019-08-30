import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

import detrack.elasticroute.dashboard.*;

public class PlanTest {
    @BeforeEach
    void setAPIKey(){
        ApiKey.ApiKey = "qPKIftCmZpVmz6xJaLmtJJDpWzky29lPprfi0e97jhTHHfBb7flbuUwCqbOW";
    }
    
    @AfterEach
    
    void deleteVehiclesandStops(){
        ApiKey.ApiKey = "qPKIftCmZpVmz6xJaLmtJJDpWzky29lPprfi0e97jhTHHfBb7flbuUwCqbOW";
        Vehicle.deleteVehicles();
        Stop.deleteStops("2019-08-04");
    }
    
    @Test
    void startPlan_NoVehicles_ThrowVehicleNotFoundException(){
        Stop stop = new Stop("stop1", "Kaki Bukit");
        
        Stop.createStop(stop, "2019-08-04");
        
        assertThrows(VehicleNotFoundException.class, () -> Plan.startPlan("2019-08-04"));
        
    }
    
    @Test
    void startPlan_NoStops_ThrowStopNotFoundException(){
        assertThrows(StopNotFoundException.class, () -> Plan.startPlan("2019-08-04"));
    }
    
    @Test
    void startPlan_OneVehicle_PlanCreated() throws InterruptedException{
        Vehicle vehicle = new Vehicle("vehicle1");
        Vehicle.createVehicle(vehicle);
        
        Stop stop1 = new Stop("Stop1", "Kaki Bukit");
        Stop stop2 = new Stop("Stop2", "Bedok");
        Stop stop3 = new Stop("Stop3", "Tampines");
        
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
    
        Stop.createStops(stopList, "2019-08-04");
        Thread.sleep(2000);
        Plan.startPlan("2019-08-04");
    }
    
    @Test
    void startPlan_OneStopOneVehicle_PlanCreated(){
        Vehicle vehicle = new Vehicle("vehicle1");
        Vehicle.createVehicle(vehicle);
    
        Stop stop1 = new Stop("Stop1", "Kaki Bukit");
        Stop.createStop(stop1, "2019-08-04");
        
        Plan.startPlan("2019-08-04");
    }
    
    @Test
    void startPlan_NoAvailableVehicles_PlanCreated() throws Exception{
        Vehicle vehicle = new Vehicle("vehicle1");
        vehicle.setAvail(false);
        Vehicle.createVehicle(vehicle);
    
        Stop stop1 = new Stop("Stop1", "Kaki Bukit");
        Stop stop2 = new Stop("Stop2", "Bedok");
        Stop stop3 = new Stop("Stop3", "Tampines");
    
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
    
        Stop.createStops(stopList, "2019-08-04");
        Thread.sleep(1000);
        Plan.startPlan("2019-08-04");
    }
    
    @Test
    void startPlan_InvalidDay_ThrowDateTimeException(){
        assertThrows(DateTimeException.class, () -> Plan.startPlan("2019-08-35"));
        
    }
    
    @Test
    void startPlan_InvalidMonth_ThrowDateTimeException(){
        assertThrows(DateTimeException.class, () -> Plan.startPlan("2019-14-35"));
        
    }
    
    @Test
    void startPlan_InvalidDateFormat_ThrowDateTimeException(){
        assertThrows(DateTimeException.class, () -> Plan.startPlan("2019/08/04"));
    
    }
    
    @Test
    void startPlan_WrongApiKey_ThrowInvalidApiKeyException(){
        ApiKey.ApiKey = "Invalid API Key";
        assertThrows(InvalidApiKeyException.class, () -> Plan.startPlan("2019-08-04"));
    }
    
    @Test
    void startPlan_EmptyApiKey_ThrowEmptyApiKeyException(){
        ApiKey.ApiKey = "";
        assertThrows(EmptyApiKeyException.class, () -> Plan.startPlan("2019-08-04"));
    }
    
    @Test
    void stopPlan_NoPlanOngoing_ThrowPlanNotFoundException(){
        assertThrows(PlanNotFoundException.class, () -> Plan.stopPlan("2019-08-04"));
    }
    
    @Test
    void stopPlan_PlanOngoing_PlanStopped() throws Exception{
        Vehicle vehicle1 = new Vehicle("vehicle1");
        Vehicle vehicle2 = new Vehicle("vehicle2");
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        Vehicle.createVehicles(vehicles);
    
        Stop stop1 = new Stop("stop1", "Woodlands");
        Stop stop2 = new Stop("stop2", "Boon lay");
        Stop stop3 = new Stop("stop3", "Lakeside");
        Stop stop4 = new Stop("stop4", "Bishan Street");
        Stop stop5 = new Stop("stop5", "Kallang");
        Stop stop6 = new Stop("stop6", "Chinese Garden");
        Stop stop7 = new Stop("stop7", "Clementi");
        Stop stop8 = new Stop("stop8", "Changi South Avenue");
        Stop stop9 = new Stop("stop9", "Ang Mo Kio");
        Stop stop10 = new Stop("stop10", "Bishan Stret");
        Stop stop11 = new Stop("stop11", "Tampines West");
        Stop stop12 = new Stop("stop12", "Kaki Bukit");
    
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
        stopList.add(stop4);
        stopList.add(stop5);
        stopList.add(stop6);
        stopList.add(stop7);
        stopList.add(stop8);
        stopList.add(stop9);
        stopList.add(stop10);
        stopList.add(stop11);
        stopList.add(stop12);
        
        Stop.createStops(stopList, "2019-08-04");
    
        Thread.sleep(5000);
        Plan.startPlan("2019-08-04");
        Plan.stopPlan("2019-08-04");
    }
    
    @Test
    void getPlan_PlanOngoing_PlanGet() throws Exception{
        Vehicle vehicle1 = new Vehicle("vehicle1");
        Vehicle vehicle2 = new Vehicle("vehicle2");
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        Vehicle.createVehicles(vehicles);
    
        Stop stop1 = new Stop("stop1", "Woodlands");
        Stop stop2 = new Stop("stop2", "Boon lay");
        Stop stop3 = new Stop("stop3", "Lakeside");
        Stop stop4 = new Stop("stop4", "Bishan Street");
        Stop stop5 = new Stop("stop5", "Kallang");
        Stop stop6 = new Stop("stop6", "Chinese Garden");
        Stop stop7 = new Stop("stop7", "Clementi");
        Stop stop8 = new Stop("stop8", "Changi South Avenue");
        Stop stop9 = new Stop("stop9", "Ang Mo Kio");
        Stop stop10 = new Stop("stop10", "Bishan Stret");
        Stop stop11 = new Stop("stop11", "Tampines West");
        Stop stop12 = new Stop("stop12", "Kaki Bukit");
    
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
        stopList.add(stop4);
        stopList.add(stop5);
        stopList.add(stop6);
        stopList.add(stop7);
        stopList.add(stop8);
        stopList.add(stop9);
        stopList.add(stop10);
        stopList.add(stop11);
        stopList.add(stop12);
    
        Stop.createStops(stopList, "2019-08-04");
        
        Thread.sleep(5000);
        Plan.startPlan("2019-08-04");
        Plan.getPlan("2019-08-04");
    }
    
    @Test
    void getPlan_NoPlanOngoing_ThrowPlanNotFoundException(){
        assertThrows(PlanNotFoundException.class, () -> Plan.getPlan("2019-08-04"));
    }
    
}
