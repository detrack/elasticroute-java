import detrack.elasticroute.routingengine.*;

//import detrack.elasticroute.dashboard.*;
//import Depot;
//import GeneralSettings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
    
        Plan.ApiKey = "qPKIftCmZpVmz6xJaLmtJJDpWzky29lPprfi0e97jhTHHfBb7flbuUwCqbOW";
        
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
        
        
        Vehicle vehicle1 = new Vehicle("vehicle1");
        Vehicle vehicle2 = new Vehicle("vehicle2");
        Vehicle vehicle3 = new Vehicle("vehicle3");
        Vehicle vehicle4 = new Vehicle("vehicle4");
        Vehicle vehicle5 = new Vehicle("vehicle5");
        
        List<Vehicle> vehicleList = new ArrayList<>();
        
        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        vehicleList.add(vehicle3);
        vehicleList.add(vehicle4);
        vehicleList.add(vehicle5);
        
        Stop stop = new Stop("stop1", "Kallang");
        Vehicle vehicle = new Vehicle("vehicle");
        
        //Stop.createStop(stop, "2019-08-23");
        //Stop getstop = Stop.getStop("stop1", "2019-08-23");
        
        stop.setVehicleType("van");
        //stop.updateStop("2019-08-23");
        
        //Stop.deleteStop("stop1", "2019-08-23");
        
        //Stop.createStops(stopList, "2019-08-23");
        
        //List<Stop> StopList = Stop.getStops("2019-08-04");
        //List<Stop> StopList = Stop.getStops("2019-08-23", 8, 1);
        //System.out.println(StopList);
        
        //Stop.replaceAllStops(stopList, "2019-08-23");
        //Stop.deleteStops("2019-08-23");
        
        //Vehicle.createVehicle(vehicle);
        //Vehicle getvehicle = Vehicle.getVehicle("vehicle");
        //System.out.println(getvehicle);
        
        vehicle.setPriority(10);
        //vehicle.updateVehicle();
        
        //Vehicle.deleteVehicle("vehicle");
        
        //Vehicle.createVehicles(vehicleList);
        //List<Vehicle> vehicles = Vehicle.getVehicles();
        //System.out.println(vehicles);
        
        //Vehicle.updateVehicles(vehicleList);
        
        vehicleList.remove(vehicle1);
        //Vehicle.replaceAllVehicles(vehicleList);
        
        //Vehicle.deleteVehicles();
        
        //Plan.startPlan("2019-08-23");
        //Plan.getPlan("2019-08-23");
        //Plan.stopPlan("2019-08-23");
    
        GeneralSettings generalSettings = new GeneralSettings("SG", "Asia/Singapore");
        Depot depot1 = new Depot("warehouse1", "Kaki Bukit");
        Depot depot2 = new Depot("warehouse2", "Changi South Avenue");
        List<Depot> depots = new ArrayList<>();
        depots.add(depot1);
        depots.add(depot2);
        
        Plan plan = new Plan(stopList, depots, vehicleList, generalSettings);
        //Plan.createPlan(plan, "firstplan");
        Plan.getPlan("firstplan");
        Thread.sleep(2000);
    }
}
// createStops wont throw error if a stop with the same name exists - will just add the new stop
// mapped stops can be deleted

// stop name is taken = The given data was invalid

// encoding failed for / () !
// emoji is encoded

//create stops if same name different address, stops will just update
