import detrack.elasticroute.dashboard.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import detrack.elasticroute.dashboard.*;

public class VehicleTest {
    @BeforeEach
    void setAPIKey(){
        detrack.elasticroute.dashboard.ApiKey.ApiKey = "qPKIftCmZpVmz6xJaLmtJJDpWzky29lPprfi0e97jhTHHfBb7flbuUwCqbOW";
    }
    
    @Test
    void vehicleInstantiation_ApiKeyEmpty_ThrowException(){
        Vehicle vehicle = new Vehicle("truck");
        ApiKey.ApiKey = null;
        assertThrows(EmptyApiKeyException.class, () -> Vehicle.createVehicle(vehicle));
    }
    
    @Test
    void vehicleInstantiation_WrongApiKey_ThrowException(){
        Vehicle vehicle = new Vehicle("truck");
        ApiKey.ApiKey = "I am definitely wrong";
        assertThrows(InvalidApiKeyException.class, () -> Vehicle.createVehicle(vehicle));
    }
    
    @Test
    void getReadOnlyFields(){
        Vehicle vehicle = new Vehicle("car");
        Vehicle.createVehicle(vehicle);
        
        Vehicle getVehicle = Vehicle.getVehicle("car");
        getVehicle.getCreatedAt();
        getVehicle.getUpdateAt();
    }
    
    @Test
    void createVehicle_NameExist_ThrowException(){
        Vehicle vehicle = new Vehicle("van");
        Vehicle.createVehicle(vehicle);
        assertThrows(InvalidRequestException.class, () -> {
            Vehicle.createVehicle(vehicle);
        });
        Vehicle.deleteVehicle("van");
    }
    
    @Test
    void createVehicle_NameDoesntExist_VehicleCreated(){
        Vehicle vehicle = new Vehicle("van");
        Vehicle.createVehicle(vehicle);
        Vehicle.deleteVehicle("van");
    }
    
    @Test
    void createVehicle_NameIsEmpty_ThrowException(){
        Vehicle vehicle = new Vehicle("");
        assertThrows(InvalidParameterException.class, () -> Vehicle.createVehicle(vehicle));
    }
    
    @Test
    void createVehicle_NameIsNull_ThrowException(){
        Vehicle vehicle = new Vehicle(null);
        assertThrows(InvalidParameterException.class, () -> Vehicle.createVehicle(vehicle));
    }
    
    @Test
    void getVehicle_VehicleDoesntExist_ThrowException(){
        assertThrows(VehicleNotFoundException.class, () -> {
            Vehicle.getVehicle("cat");
        });
    }
    
    @Test
    void getVehicle_VehicleExist_ReturnVehicle(){
        Vehicle vehicle = new Vehicle("truck");
        Vehicle.createVehicle(vehicle);
        Vehicle.getVehicle("truck");
        Vehicle.deleteVehicle("truck");
    }
    
    @Test
    void getVehicle_NameContainWhitespace_ReturnVehicle(){
        Vehicle vehicle = new Vehicle("Mini Truck");
        Vehicle.createVehicle(vehicle);
        Vehicle.getVehicle("Mini Truck");
        Vehicle.deleteVehicle("Mini Truck");
    }
    
    @Test
    void getVehicle_NameContainSymbols_ReturnVehicle(){
        Vehicle vehicle = new Vehicle("Mini Truck !@#$%^&*()");
        Vehicle.createVehicle(vehicle);
        Vehicle.getVehicle("Mini Truck !@#$%^&*()");
    }
    
    @Test
    void updateVehicle_VehicleExist_VehicleUpdated(){
        Vehicle vehicle = new Vehicle("updateVehicle");
        Vehicle.createVehicle(vehicle);
        vehicle.setAvailMon(false);
        vehicle.updateVehicle();
        vehicle.deleteVehicle("updateVehicle");
    }
    
    @Test
    void updateVehicle_VehicleDoesntExist_ThrowException(){
        Vehicle vehicle = new Vehicle("updateVehicle");
        assertThrows(VehicleNotFoundException.class, () -> {
            vehicle.updateVehicle();
        });
    }
    
    @Test
    void updateVehicle_NothingIsChanged_(){
        Vehicle vehicle = new Vehicle("updateVehicle");
        Vehicle.createVehicle(vehicle);
        vehicle.updateVehicle();
        vehicle.deleteVehicle("updateVehicle");
    }
    @Test
    void updateVehicle_NameContainWhitespace_VehicleUpdated(){
        Vehicle vehicle = new Vehicle("contain whitespace");
        Vehicle.createVehicle(vehicle);
        vehicle.updateVehicle();
        Vehicle.deleteVehicle("contain whitespace");
    }
    
    @Test
    void updateVehicle_NameContainSymbols_VehicleUpdated(){
        Vehicle vehicle = new Vehicle("contains !@#$%^&*()");
        Vehicle.createVehicle(vehicle);
        vehicle.updateVehicle();
        Vehicle.deleteVehicle("contains !@#$%^&*()");
    }
    
    @Test
    void updateVehicle_ChangeVehicleName_VehicleNameChanged(){
        Vehicle vehicle = new Vehicle("truck");
        Vehicle.createVehicle(vehicle);
        vehicle.setName("van");
        vehicle.updateVehicle("truck");
        Vehicle.deleteVehicle("van");
    }
    
    @Test
    void deleteVehicle_VehicleNameExist_VehicleDeleted(){
        Vehicle vehicle = new Vehicle("van");
        Vehicle.createVehicle(vehicle);
        Vehicle.deleteVehicle("van");
    }
    
    @Test
    void deleteVehicle_VehicleNameDoesntExist_ThrowException(){
        assertThrows(VehicleNotFoundException.class, () -> {
            Vehicle.deleteVehicle("van");
        });
    }
    
    @Test
    void deleteVehicle_NameContainWhitespaces_VehicleDeleted(){
        Vehicle vehicle = new Vehicle("contain whitespace");
        Vehicle.createVehicle(vehicle);
        Vehicle.deleteVehicle("contain whitespace");
    }
    
    @Test
    void deleteVehicle_NameContainSymbols_VehicleDeleted(){
        Vehicle vehicle = new Vehicle("contain !@#$%^&*()");
        Vehicle.createVehicle(vehicle);
        Vehicle.deleteVehicle("contain !@#$%^&*()");
    }
    
    @Test
    void getVehicles_VehicleExists_ReturnVehicleList(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
        
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        
        Vehicle.createVehicles(vehicles);
        Vehicle.getVehicles();
        Vehicle.deleteVehicles();
    }
    
    @Test
    void getVehicles_VehicleDoesntExists_ReturnEmptyVehicleList(){
        Vehicle.getVehicles();
    }
    
    @Test
    void getVehicles_WithLimitAndPageConstraint_ReturnVehicleList(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
        Vehicle.getVehicles(10, 1);
        Vehicle.deleteVehicles();
    }
    
    @Test
    void createVehicles_VehicleDoesntExist_VehiclesCreated(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
        Vehicle.deleteVehicles();
    }
    
    @Test
    void createVehicles_VehiclesNameExist_AddTheNewlyAddedVehicleWithouhtChangingTheOldOne(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
        Vehicle vehicle4 = new Vehicle("Vehicle4");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        
        Vehicle.createVehicles(vehicles);
        vehicles.add(vehicle4);
        Vehicle.createVehicles(vehicles);
        
        Vehicle.deleteVehicles();
    }
    
    @Test
    void replaceAllVehicles_ListOfVehicleIsNotEmpty_VehicleReplaced(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
    
        Vehicle vehicle4 = new Vehicle("Vehicle4");
        Vehicle vehicle5 = new Vehicle("Vehicle5");
        Vehicle vehicle6 = new Vehicle("Vehicle6");
    
        List<Vehicle> vehicles2= new ArrayList<>();
        vehicles2.add(vehicle4);
        vehicles2.add(vehicle5);
        vehicles2.add(vehicle6);
        
        Vehicle.replaceAllVehicles(vehicles2);
        List<Vehicle> getVehicleList = Vehicle.getVehicles();
        assertEquals(3, getVehicleList.size());
    }
    
    @Test
    void replaceAllVehicles_ListOfVehicleIsEmpty_ThrowException(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
    
        List<Vehicle> vehicles2= new ArrayList<>();
    
        assertThrows(InvalidRequestException.class, () -> {
            Vehicle.createVehicles(vehicles2);
        });
        
        Vehicle.deleteVehicles();
    }
    
    @Test
    void updateVehicles_NameExist_ThrowException(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
        Vehicle vehicle4 = new Vehicle("Vehicle4");
        Vehicle vehicle5 = new Vehicle("Vehicle5");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
        vehicles.add(vehicle5);
    
        Vehicle.createVehicles(vehicles);
        
        vehicles.remove(vehicle1);
        vehicles.remove(vehicle2);
        
        vehicles.get(0).setAvailMon(false);
        vehicles.get(1).setAvailSat(false);
        vehicles.get(2).setName("new vehicle");
    
        assertThrows(InvalidRequestException.class, () -> {
            Vehicle.updateVehicles(vehicles);
        });
        
        Vehicle.deleteVehicles();
    }
    
    @Test
    void updateVehicles_NameDoesntExist_ThrowException(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
        Vehicle vehicle4 = new Vehicle("Vehicle4");
        Vehicle vehicle5 = new Vehicle("Vehicle5");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
    
        vehicles.add(vehicle4);
        vehicles.add(vehicle5);
    
        assertThrows(InvalidRequestException.class, () -> {
            Vehicle.updateVehicles(vehicles);
        });
    
        Vehicle.deleteVehicles();
    }
    
    @Test
    void deleteVehicles_VehicleExist_VehicleDeleted(){
        Vehicle vehicle1 = new Vehicle("Vehicle1");
        Vehicle vehicle2 = new Vehicle("Vehicle2");
        Vehicle vehicle3 = new Vehicle("Vehicle3");
    
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    
        Vehicle.createVehicles(vehicles);
        Vehicle.deleteVehicles();
    }
    
    @Test
    void deleteVehicles_VehicleDoesntExist_NothingHappens(){
        Vehicle.deleteVehicles();
    }
}
