import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< Updated upstream
import java.util.Arrays;
=======
 
>>>>>>> Stashed changes

public class Inventory {
    private List<Car> cars; // List to hold cars
    private List<String> headers; // List to hold order of header

    // Constructor
    public Inventory() {
        this.cars = new ArrayList<>();
        try{
            loadCarsFromCSV("car_data_new.csv");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("An error occured reading car file.");
        }
    }
    
    // Add a car to the inventory
    public void addCar(Car car) {
        this.cars.add(car);
    }

    // Remove a car from the inventory by ID        
    public boolean removeCar(int carID) {                        // <-- needs updating
        return cars.removeIf(car -> car.getId() == carID);
    }

    // Get a list of all cars in the inventory
    public List<Car> getAllCars() {
        return new ArrayList<>(this.cars); // Returning a copy of the list to protect internal data
    }

    // Read car data from CSV file
    public void loadCarsFromCSV(String csvFile) throws IOException{
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        try {
            String headerLine = br.readLine();  // Reading header line and then getting index of possible columns
            headers = Arrays.asList(headerLine.split(","));
            int idIndex = headers.indexOf("ID");
            int yearIndex = headers.indexOf("Year");
            int capacityIndex = headers.indexOf("Capacity");
            int carTypeIndex = headers.indexOf("Car Type");
            int carsAvailableIndex = headers.indexOf("Cars Available");
            int conditionIndex = headers.indexOf("Condition");
            int colorIndex = headers.indexOf("Color");
            int priceIndex = headers.indexOf("Price");
            int vinIndex = headers.indexOf("VIN");
            int transmissionIndex = headers.indexOf("Transmission");
            int fuelTypeIndex = headers.indexOf("Fuel Type");
            int modelIndex = headers.indexOf("Model");
            int hasTurboIndex = headers.indexOf("hasTurbo");
                
            while ((line = br.readLine()) != null) {
                String[] carFields = line.split(",");

                // Determine if the "hasTurbo" field is available and properly listed in the current car entry
                boolean hasTurbo = false; // Default to false
                if (hasTurboIndex > -1 && hasTurboIndex < carFields.length) {
                    String turboValue = carFields[hasTurboIndex].trim().toLowerCase();
                    hasTurbo = turboValue.equals("yes"); // Sets hasTurbo to true if the value is "yes"
                }
                
                
                switch(carFields[carTypeIndex]){
                    case "Hatchback":
                        Car hatchback = new Hatchback(
                            Integer.parseInt(carFields[idIndex]), 
                            carFields[carTypeIndex],
                            carFields[modelIndex], 
                            carFields[conditionIndex], 
                            carFields[colorIndex], 
                            Integer.parseInt(carFields[capacityIndex]), 
                            carFields[yearIndex], 
                            carFields[fuelTypeIndex], 
                            carFields[transmissionIndex], 
                            carFields[vinIndex], 
                            Double.parseDouble(carFields[priceIndex]), 
                            Integer.parseInt(carFields[carsAvailableIndex]), 
                            hasTurbo
                        );
                        this.addCar(hatchback);
                        break;
                    case "SUV":
                        Car suv = new SUV(
                            Integer.parseInt(carFields[idIndex]), 
                            carFields[carTypeIndex],
                            carFields[modelIndex], 
                            carFields[conditionIndex], 
                            carFields[colorIndex], 
                            Integer.parseInt(carFields[capacityIndex]), 
                            carFields[yearIndex], 
                            carFields[fuelTypeIndex], 
                            carFields[transmissionIndex], 
                            carFields[vinIndex], 
                            Double.parseDouble(carFields[priceIndex]), 
                            Integer.parseInt(carFields[carsAvailableIndex]), 
                            hasTurbo
                        );
                        this.addCar(suv);
                        break;
                    case "Sedan":
                        Car sedan = new Sedan(
                            Integer.parseInt(carFields[idIndex]), 
                            carFields[carTypeIndex],
                            carFields[modelIndex], 
                            carFields[conditionIndex], 
                            carFields[colorIndex], 
                            Integer.parseInt(carFields[capacityIndex]), 
                            carFields[yearIndex], 
                            carFields[fuelTypeIndex], 
                            carFields[transmissionIndex], 
                            carFields[vinIndex], 
                            Double.parseDouble(carFields[priceIndex]), 
                            Integer.parseInt(carFields[carsAvailableIndex]), 
                            hasTurbo
                        );
                        this.addCar(sedan);
                        break;
                    case "Pickup":
                        Car pickup = new Pickup(
                            Integer.parseInt(carFields[idIndex]), 
                            carFields[carTypeIndex],
                            carFields[modelIndex], 
                            carFields[conditionIndex], 
                            carFields[colorIndex], 
                            Integer.parseInt(carFields[capacityIndex]), 
                            carFields[yearIndex], 
                            carFields[fuelTypeIndex], 
                            carFields[transmissionIndex], 
                            carFields[vinIndex], 
                            Double.parseDouble(carFields[priceIndex]), 
                            Integer.parseInt(carFields[carsAvailableIndex]), 
                            hasTurbo
                        );
                        this.addCar(pickup);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown car type: " + carFields[carTypeIndex]);
                }
            } 
        } finally {
            br.close();
        }
    }

    // Display all cars in the inventory
    public void displayInventory() {
        for (Car car : this.cars) {
            System.out.println(car.getDetails());
        }
    }

    // updateFile(){ 
    //     write to new_car_data.csv
    //     for loop of list

}
