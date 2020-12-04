![ElasticRoute Logo](https://camo.githubusercontent.com/ab4c7e3aa0867de8f1e5fefe44e68b1c452e3001/687474703a2f2f656c6173746963726f7574652e73746167696e672e7770656e67696e652e636f6d2f77702d636f6e74656e742f75706c6f6164732f323031392f30322f456c61737469632d526f7574652d4c6f676f2d546578742d6f6e2d72696768742d65313535313334343034363830362e706e67)

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

With effect from 02 January 2021, we will deprecate support for this client library and there will be no future updates. If you are currently using the client library, the integrations done based on it should still be able to work. Moving forward, we recommend the use of our API documentation ([Dashboard](https://www.elasticroute.com/dashboard-api-documentation/) and [Routing Engine](https://www.elasticroute.com/routing-engine-api-documentation/)) to build your integration.

## API for solving large scale travelling salesman/fleet routing problems
You have a fleet of just 10 vehicles to serve 500 spots in the city. Some vehicles are only available in the day. Some stops can only be served at night. How would you solve this problem?

You don't need to. Just throw us an array of stops, vehicles and depots and we will do the heavy lifting for you. _Routing as a Service!_

**IMPORTANT NOTE: This will only run on Java 11 and above! If you have lower versions of Java, you need to install JDK 11!**

## Preface
---
ElasticRoute offers two APIs depending on your needs, and different sections of this documentation are relevant to you depending on which API you wish to interact with:

- **Routing Engine API** – if you already have your own fleet management system and you only wish to use ElasticRoute to solve the routing problem and inspect the solution. This is effectively using ElasticRoute in a headless environment, a.k.a. "routing as a service".
- **Dashboard API** – if your team uses the ElasticRoute web application to review stops and vehicles on a map, and you wish to push data from your existing applications to the ElasticRoute dashboard.
Regardless of how you use ElasticRoute, this client library is capable of interacting with both services.

## Routing Engine API
---
### Getting Started
#### Maven:
```html
<dependencies>
    <dependency>
        <groupId>com.detrack.elasticroute</groupId>
        <artifactId>routingengine</artifactId>
        <version>1.3</version>
    </dependency>
</dependencies>
```
**Note:** For IntelliJ IDE, because the default target bytecode is 1.5 which is incompatible with this library, you should add the following to your pom.xml. This is to ensure it is using javac 11.
```html
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.0</version>
            <configuration>
                <release>11</release>
            </configuration>
        </plugin>
    </plugins>
</build>
```
### Plan Instantiation
To create a plan, you need to create `List<Stop>`, `List<Depot>`, `List<Vehicle`, and `generalSettings`
For the full list of `generalSettings` please refer to this [link](https://www.elasticroute.com/api-documentation/ "Routing Engine Documentation")
```
// creating a list of stops
List<Stop> stops = new ArrayList<>();
Stop stop1 = new Stop("stop1", "address1");
Stop stop2 = new Stop("stop2", "address2");
Stop stop3 = new Stop("stop3", "address3");

stops.add(stop1);
stops.add(stop2);
stops.add(stop3);

// creating a list of vehicles
Vehicle vehicle1 = new Vehicle("name1");
Vehicle vehicle2 = new Vehicle("name2");
Vehicle vehicle3 = new Vehicle("name3");

List<Vehicle> vehicles = new ArrayList<>();

vehicles.add(vehicle1);
vehicles.add(vehicle2);
vehicles.add(vehicle3);

// creating a list of depots
Depot depot1 = new Depot("name1", "address1");
Depot depot2 = new Depot("name3", "address3");
Depot depot3 = new Depot("name2", "address2");

List<Depot> depots = new ArrayList<>();

depots.add(depot1);
depots.add(depot2);
depots.add(depot3);

// creating the general settings
GeneralSettings generalSettings = new GeneralSettings("SG", "Asia/Singapore");

// Instantiate the Plan
Plan plan = new Plan(stops, depots, vehicles, generalSettings);
```

### Create Plan
**Note:** `id` is the unique identifier for your plan. You can retrieve your plan using this id.
```Java
// Instantiate a plan before this code. In this example the name of the instantiated plan is plan
Plan.createPlan(plan, "id")
```

### Get Plan
```Java
Plan.getPlan("id");
```

### Get Plan Status
Returns a `String`
```Java
Plan.getPlanStatus("id");
```

### Stop Plan
Stops an ongoing plan
```Java
Plan.stopPlan("id");
```

### Delete Plan
Deletes the plan with the specified id
```Java
Plan.deletePlan("id");
```

## Dashboard API
---
### Getting Started
#### Maven:
```html
<dependencies>
    <dependency>
        <groupId>com.detrack.elasticroute</groupId>
        <artifactId>dashboard</artifactId>
        <version>1.3</version>
    </dependency>
</dependencies>
```
**Note:** For IntelliJ IDE, because the default target bytecode is 1.5 which is incompatible with this library, you should add the following to your pom.xml. This is to ensure it is using javac 11.
```html
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.0</version>
            <configuration>
                <release>11</release>
            </configuration>
        </plugin>
    </plugins>
</build>
```
## Stop

### Single Stop
**Note** : List of fields is specified in the online [API documentation](https://www.elasticroute.com/api-documentation/ "Dashboard Documentation")
#### Instantiate a Stop
**Note:** Date format is YYYY-MM-DD
```Java
Stop stop = new Stop("stopName", "address");
```

#### Get Stop
```Java
Stop.getStop("stopName", "date");
```
##### Create Stop
```Java
Stop stop = new Stop("stopName", "address");
Stop.createStop(stop, "date");
```

#### Update stop (Without changing Stop `name`)
```Java
Stop stop = new Stop("stopName", "address");
// (optional) Change the fields here
Stop.setDepot("Warehouse1");

stop.updateStop();
```

#### Update stop (Change Stop `name`)
```Java
Stop stop = new Stop("stopName", "address");
stop.updateStop("stopToUpdate", "date");
```

#### Delete stop
```Java
Stop.deleteStop("stopName", "date");
```

### Bulk Stops
**Note** : List of fields is specified in the online [API documentation](https://www.elasticroute.com/api-documentation/ "Dashboard Documentation")
#### Get Stops
```Java
List<Stop> stops = Stop.getStops("date");
```
or
```Java
List<Stop> stops = Stop.getStops("date", int limit, int pageNumber);
```
#### Create Stops
```Java
List<Stop> stops = new ArrayList<>();
Stop stop1 = new Stop("stop1", "address1");
Stop stop2 = new Stop("stop2", "address2");
Stop stop3 = new Stop("stop3", "address3");

stops.add(stop1);
stops.add(stop2);
stops.add(stop3);

Stop.createStops(stops, "date");
```

#### Replace All Stops
**Note:** This will delete **ALL** the stops on the specified date and replace it with the new one.
```Java
List<Stop> stops = new ArrayList<>();
Stop stop1 = new Stop("stop1", "address1");
Stop stop2 = new Stop("stop2", "address2");
Stop stop3 = new Stop("stop3", "address3");

stops.add(stop1);
stops.add(stop2);
stops.add(stop3);

Stop.replaceAllStops(stops, "date");
```

#### Delete Stops
```Java
Stop.deleteStops("date");
```

## Vehicle

### Single Vehicle
**Note** : List of fields is specified in the online [API documentation](https://www.elasticroute.com/api-documentation/ "Dashboard Documentation")
#### Instantiate a Vehicle
```Java
Vehicle vehicle = new Vehicle("name");
```

### Get Vehicle
```Java
Vehicle vehicle = Vehicle.getVehicle("name");
```

### Create Vehicle
```Java
Vehicle vehicle = new Vehicle("name");
Vehicle.createVehicle(vehicle);
```

### Update Vehicle (Without updating the `name`)
```Java
Vehicle vehicle = new Vehicle("newName");
// (optional) Change the fields here
vehicle.setAvailMon(false);
vehicle.updateVehicle();
```

### Update Vehicle (Update the `name`)
```Java
Vehicle vehicle = new Vehicle("newName");
// (optional) Change the fields here
vehicle.setAvailMon(false);
vehicle.updateVehicle("nameOfVehicleToUpdate");
```

### Delete Vehicle
```Java
Vehicle.deleteVehicle("name");
```

### Get Vehicles
```Java
List<Vehicle> vehicles = Vehicle.getVehicles();
```
or
```Java
List<Vehicle> vehicles = Vehicle.getVehicles(int limit, int pageNumber);
```

### Create Vehicles
```Java
Vehicle vehicle1 = new Vehicle("name1");
Vehicle vehicle2 = new Vehicle("name2");
Vehicle vehicle3 = new Vehicle("name3");

List<Vehicle> vehicles = new ArrayList<>();

vehicles.add(vehicle1);
vehicles.add(vehicle2);
vehicles.add(vehicle3);

Vehicle.createVehicles(vehicles);
```

### Replace All Vehicles
```Java
Vehicle vehicle1 = new Vehicle("name1");
Vehicle vehicle2 = new Vehicle("name2");
Vehicle vehicle3 = new Vehicle("name3");

List<Vehicle> vehicles = new ArrayList<>();

vehicles.add(vehicle1);
vehicles.add(vehicle2);
vehicles.add(vehicle3);

Vehicle.replaceAllVehicles(vehicles);
```

### Update Vehicles
```Java
Vehicle vehicle1 = new Vehicle("name1");
Vehicle vehicle2 = new Vehicle("name2");
Vehicle vehicle3 = new Vehicle("name3");

// (optional) Change the fields here
vehicle1.setAvail(false);
vehicle2.setPriority(10);

List<Vehicle> vehicles = new ArrayList<>();

vehicles.add(vehicle1);
vehicles.add(vehicle2);
vehicles.add(vehicle3);

Vehicle.updateVehicles(vehicles);
```

### Delete Vehicles
**Note:** This will delete **ALL** your vehicles
```Java
Vehicle.deleteVehicles();
```

## Plan
**Note:** Date format is YYYY-MM-DD

### Start Plan
```Java
Plan.startPlan("date");
```

### Stop Plan
This will stop an ongoing plan
```Java
Plan.stopPlan("date");
```

### Get Plan
```Java
String status = Plan.getPlanStatus("date");
```

Acknowledgement : This library is made using EA-Async library
