package detrack.elasticroute.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StopTest {
    @BeforeEach
    void setAPIKey(){
        detrack.elasticroute.dashboard.ApiKey.ApiKey = "qPKIftCmZpVmz6xJaLmtJJDpWzky29lPprfi0e97jhTHHfBb7flbuUwCqbOW";
    }
    
    @Test
    void stopInstantiation_ApiKeyEmpty_ThrowException(){
        Stop stop = new Stop("NullApiKey", "Kallang");
        ApiKey.ApiKey = null;
        assertThrows(EmptyApiKeyException.class, () -> Stop.createStop(stop, "2019-08-04"));
    }
    
    @Test
    void stopInstantiation_WrongAPIKey_ThrowException(){
        Stop stop = new Stop("WrongAPIKey", "Somewhere");
        ApiKey.ApiKey = "I am definitely wrong";
        assertThrows(InvalidApiKeyException.class, () -> Stop.createStop(stop, "2019-08-04"));
    }
    
    @Test
    void stopInstantiation_WrongDate_ThrowException(){
        Stop stop = new Stop("Wrong date", "Kaki Bukit");
        assertThrows(DateTimeException.class, () -> Stop.createStop(stop, "2019-08-32"));
    }
    
    @Test
    void stopInstantiation_WrongMonth_ThrowException(){
        Stop stop = new Stop("Wrong month", "Kaki Bukit");
        assertThrows(DateTimeException.class, () -> Stop.createStop(stop, "2019-15-04"));
    }
    
    @Test
    void stopInstantiation_WrongDateFormat_ThrowException(){
        Stop stop = new Stop("Wrong date format", "Kaki Bukit");
        assertThrows(DateTimeException.class, () -> Stop.createStop(stop, "2019/08/04"));
    }
    
    @Test
    void stopInstantiation_IfStopNameAndAddressIsEmpty_ThrowException(){
        assertThrows(InvalidParameterException.class, () -> {
            Stop stop = new Stop("", "");
        });
    }
    
    @Test
    void stopInstantiation_IfStopNameAndAddressIsNull_ThrowException(){
        assertThrows(InvalidParameterException.class, () -> {
            Stop stop = new Stop("", null);
        });
    }
    
    @Test
    void getReadOnlyFields(){
        Stop stop = new Stop("GetReadOnlyFields", "Kaki Bukit");
        Stop.createStop(stop, "2019-08-04");
        Stop getstop = Stop.getStop("GetReadOnlyFields", "2019-08-04");
        getstop.getAssignTo();
        getstop.getRun();
        getstop.getSequence();
        getstop.getEta();
        getstop.getException();
        getstop.getExceptionReason();
        getstop.getPlanVehicleType();
        getstop.getPlanDepot();
        getstop.getPlanServiceTime();
        getstop.getMappedAt();
        getstop.getPlannedAt();
        getstop.getCreatedAt();
        getstop.getUpdatedAt();
        getstop.getSorted();
        getstop.getViolations();
    }
    
    @Test
    void getStop_StopExists_Pass() {
        Stop stop = new Stop("WhenStopExistExpectPass(getStop)", "Kaki Bukit");
        Stop.createStop(stop, "2019-08-04");
        Stop.getStop("WhenStopExistExpectPass(getStop)", "2019-08-04");
        Stop.deleteStop("WhenStopExistExpectPass(getStop)", "2019-08-04");
    }

    @Test
    void getStop_StopDoesntExist_ThrowException() {
        assertThrows(StopNotFoundException.class, () -> Stop.getStop("poop", "2019-08-04"));
    }
    
    @Test
    void createStop_StopExists_ThrowException() {
        Stop stop = new Stop("WhenStopExistExpectFail", "Kaki Bukit");
        Stop.createStop(stop, "2019-08-04");
        assertThrows(InvalidRequestException.class, () -> Stop.createStop(stop, "2019-08-04"));
        Stop.deleteStop("WhenStopExistExpectFail", "2019-08-04");
    }

    @Test
    void createStop_StopDoesntExist_Pass() {
        Stop stop = new Stop("XWhenStopDoesntExistExpectPass", "Tampines");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("XWhenStopDoesntExistExpectPass", "2019-08-04");
    }

    @Test
    void createStop_StopNameContainSymbols_Pass() {
        Stop stop = new Stop("XName?contain@symbols", "Jurong East");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("XName?contain@symbols", "2019-08-04");
    }

    @Test
    void createStop_StopNameContainWhitespaces_Pass() {
        Stop stop = new Stop("XName contain whitespaces", "Jurong West");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("XName contain whitespaces", "2019-08-04");
    }
    
    @Test
    void updateStop_StopNameDoesntExist_ThrowException() {
        Stop stop = new Stop("WhenStopDoesntExist(UpdateStop)", "Bishan");
        assertThrows(StopNotFoundException.class, () -> stop.updateStop("WhenStopDoesntExist(UpdateStop)", "2019-08-04"));
    }
    
    @Test
    void updateStop_StopNameContainWhitespaces_Pass() {
        Stop stop = new Stop("Stop contain whitespace", "Clementi");
        Stop.createStop(stop, "2019-08-04");
        stop.updateStop("Stop contain whitespace", "2019-08-04");
        Stop.deleteStop("Stop contain whitespace", "2019-08-04");
    }
    
    @Test
    void updateStop_StopNameContainSymbols_Pass(){
        Stop stop = new Stop("Stopcontainsymbols!@#$%^&*()(updateStop)", "Chinese Gardem");
        Stop.createStop(stop, "2019-08-04");
        stop.updateStop("Stopcontainsymbols!@#$%^&*()(updateStop)", "2019-08-04");
        Stop.deleteStop("Stopcontainsymbols!@#$%^&*()(updateStop)", "2019-08-04");
    }
    // tryingto change name to already existing stop will cause error
    @Test
    void updateStop_StopNameIsChanged_Pass() {
        Stop stop = new Stop("WhenStopNameIsChanged(updateStop)", "Woodlands");
        Stop.createStop(stop, "2019-08-04");
        stop.setName("XStopNameChanges!!");
        stop.updateStop("WhenStopNameIsChanged(updateStop)", "2019-08-04");
        Stop.deleteStop("XStopNameChanges!!", "2019-08-04");
    }
    
    @Test
    void updateStop_WithoutParameters_StopWillUpdate(){
        Stop stop = new Stop("UpdateStopWithoutParameters", "Kallang");
        Stop.createStop(stop, "2019-08-04");
        
        stop.setAddress("Changi South Avenue");
        stop.updateStop("2019-08-04");
        Stop.deleteStop("UpdateStopWithoutParameters", "2019-08-04");
    }
    //test for modified properties
    
    @Test
    void deleteStop_StopDoesntExist_ThrowException(){
        assertThrows(StopNotFoundException.class, () -> Stop.deleteStop("poop", "2019-08-04"));
    }
    
    @Test
    void deleteStop_StopExists_Pass() {
        Stop stop = new Stop("WhenStopExistExpectPass", "Changi South Avenue");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("WhenStopExistExpectPass", "2019-08-04");
    }
    
    @Test
    void deleteStop_StopNameContainWhitespaces_Pass(){
        Stop stop = new Stop("Stop contain whitespace 2", "Clementi");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("Stop contain whitespace 2", "2019-08-04");
    }
    
    @Test
    void deleteStop_StopNameContainSymbols_Pass(){
        Stop stop = new Stop("Stopcontainsymbols!@#$%^&*()", "Kallang");
        Stop.createStop(stop, "2019-08-04");
        Stop.deleteStop("Stopcontainsymbols!@#$%^&*()", "2019-08-04");
    }
    
    @Test
    void getStops_StopsExist_Pass(){
        List<Stop> stops = Stop.getStops("2019-08-04");
    }
    
    @Test
    void getStops_StopsDoesntExist_Pass(){
        List<Stop> stops = Stop.getStops("2019-08-06");
    }
    
    @Test
    void getStops_WithLimitAndPageNumber_StopsGet(){
        List<Stop> stops = Stop.getStops("2019-08-02", 4, 1);
    }
    
    @Test
    void getStops_InvalidParameter_ThrowExeption(){
        assertThrows(InvalidParameterException.class, () -> Stop.getStops("2019-08-02", 101, 1));
    }
    
    @Test
    void createStops_StopNameDoesntExist_StopsCreated(){
        Stop stop1 = new Stop("XStop1", "Kaki Bukit");
        Stop stop2 = new Stop("XStop2", "Woodlands");
        Stop stop3 = new Stop("XStop3", "Kallang");
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
        
        Stop.createStops(stopList, "2019-08-04");
        
        Stop.deleteStop("XStop1", "2019-08-04");
        Stop.deleteStop("XStop2", "2019-08-04");
        Stop.deleteStop("XStop3", "2019-08-04");
    }
    
    @Test
    void createStops_StopNameExists_StopWillNotBeCreatedButPass(){
        Stop stop1 = new Stop("StopNameExist1", "Ang Mo Kio");
        Stop stop2 = new Stop("StopNameExist2", "Bishan Street");
        Stop stop3 = new Stop("StopNameExist3", "Tampines West");
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
    
        Stop.createStops(stopList, "2019-08-04");
        Stop.createStops(stopList, "2019-08-04");
        
        Stop.deleteStops("2019-08-04");
    }
    
    @Test
    void createStops_ListIsEmpty_ThrowError(){
        List<Stop> stopList = new ArrayList<>();
    
        assertThrows(InvalidRequestException.class, () -> Stop.createStops(stopList, "2019-08-04"));
    }
    
    @Test
    void replaceAllStops_StopExists_AllWillBeReplaced(){
        Stop stop4 = new Stop("stop4", "Jurong West");
        Stop stop5 = new Stop("stop5", "Jurong East");
        
        Stop.createStop(stop4, "2019-08-04");
        Stop.createStop(stop5, "2019-08-04");
        
        Stop stop1 = new Stop("ReplaceAll1", "Ang Mo Kio");
        Stop stop2 = new Stop("ReplaceAll2", "Bishan Street");
        Stop stop3 = new Stop("ReplaceAll3", "Tampines West");
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
        
        Stop.replaceAllStops(stopList, "2019-08-04");
        
        List<Stop> stops = Stop.getStops("2019-08-04");
        System.out.println(stops); // there is 3 stops
        Stop.deleteStops("2019-08-04");
    }
    
    @Test
    void replaceAllStops_NoStopExists_PutNewStopsIn(){
        Stop stop1 = new Stop("ReplaceAll1", "Ang Mo Kio");
        Stop stop2 = new Stop("ReplaceAll2", "Bishan Street");
        Stop stop3 = new Stop("ReplaceAll3", "Tampines West");
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
    
        Stop.replaceAllStops(stopList, "2019-08-04");
    
        List<Stop> stops = Stop.getStops("2019-08-04");
        System.out.println(stops); // there is 3 stops
        Stop.deleteStops("2019-08-04");
    }
    
    @Test
    void replaceAllStops_ListIsEmpty_(){
        List<Stop> stopList = new ArrayList<>();
    
        assertThrows(InvalidRequestException.class, () -> Stop.replaceAllStops(stopList, "2019-08-04"));
    }
    
    @Test
    void deleteStops_StopDoesntExist_Nothing(){
        Stop.deleteStops("2019-08-04");
    }
    
    @Test
    void deleteStops_StopExist_StopsDeleted(){
        Stop stop1 = new Stop("1", "Ang Mo Kio");
        Stop stop2 = new Stop("2", "Bishan Street");
        Stop stop3 = new Stop("3", "Tampines West");
        List<Stop> stopList = new ArrayList<>();
        stopList.add(stop1);
        stopList.add(stop2);
        stopList.add(stop3);
        
        Stop.createStops(stopList, "2019-08-04");
        
        Stop.deleteStops("2019-08-04");
    }
}