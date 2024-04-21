import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class to manage inventory of cars.
 */

 public class Inventory {
    private List<Car> cars; // List to hold cars
    private List<String> headers; // List to hold order of header

    // Constructor
    public Inventory() {
        this.cars = new ArrayList<>();
        try{
            // Attempt to load car data from CSV file
            loadCarsFromCSV("car_data_new.csv");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("An error occurred reading the car file.");
        }
    }
    
    /**
     * Adds a car to the inventory.
     * 
     * @param car The car to be added.
     */

    public void addCar(Car car) {
        this.cars.add(car);
    }

    /**
     * Removes a car from the inventory by ID.
     * 
     * @param carId The ID of the car to be removed.
     * @return true if the car is removed successfully, false otherwise.
     */     

    public boolean removeCar(int carId) {
        return cars.removeIf(car -> car.getId() == carId);
    }

    /**
     * Retrieves a list of all cars in the inventory.
     * 
     * @return A list of all cars in the inventory.
     */

    public List<Car> getAllCars() {
        return new ArrayList<>(this.cars); // Return a copy of the list to protect internal data
    }


    /**
     * Reads car data from CSV file.
     * 
     * @param csvFile The path to the CSV file.
     * @throws IOException if an I/O error occurs.
     */

    public void loadCarsFromCSV(String csvFile) throws IOException{
        String line;
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        try {
            String headerLine = br.readLine();  // Read header line and then get index of possible columns
            headers = Arrays.asList(headerLine.split(","));
            // Get index of each column in the CSV
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
                    hasTurbo = turboValue.equals("yes"); // Set hasTurbo to true if the value is "yes"
                }
                
                // Create car objects based on the type indicated in the CSV
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

    /**
     * Displays all cars in the inventory.
     */

    public void displayInventory() {
        for (Car car : this.cars) {
            System.out.println(car.getDetails());
        }
    }

    /**
     * Sells a car.
     * 
     * @param carId The ID of the car to be sold.
     * @return true if the car is sold successfully, false otherwise.
     */
    
    public boolean sellCar(int carId) {
        SalesVisitor salesVisitor = new SalesVisitor();
        for (Car car : this.cars) {
            if (car.getId() == carId && car.isAvailable()) {
                salesVisitor.handleSale(car);
                return true;
            }
        }
        return false;
    }

     /**
     * Reports revenue generated from car sales.
     */

    public void reportRevenue() {
        SalesVisitor salesVisitor = new SalesVisitor();
        for (Car car : this.cars) {
            salesVisitor.handleSale(car);
        }
        System.out.println("Total Revenue: " + salesVisitor.getRevenue());
    }
    
    /**
     * Updates the file with inventory changes.
     */
/**
 * Updates the file with inventory changes, formatting the output to align columns under their respective headers.
 */
public void updateFile() {
    try (FileWriter writer = new FileWriter("car_data_new.csv")) {
        // Write headers
        writer.write("ID,Car Type,Model,Condition,Color,Capacity,Year,Fuel Type,Transmission,VIN,Price,Cars Available,hasTurbo\n");
        
        for (Car car : cars) {
            String turboString = car.getTurbo() ? "yes" : "no";
            // Format each line to ensure data aligns under the headers
            String carData = String.format("%d,%s,%s,%s,%s,%d,%s,%s,%s,%s,%.2f,%d,%s\n",
                car.getId(), car.getType(), car.getModel(), car.getCondition(), car.getColor(),
                car.getCapacity(), car.getYear(), car.getFuelType(), car.getTransmission(),
                car.getVin(), car.getPrice(), car.getCarsAvailable(), turboString);
            writer.write(carData);
        }
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}

    /**
     * Finds a car in the inventory by its ID.
     * 
     * @param carId The ID of the car to be found.
     * @return The car object if found, null otherwise.
     */

    public Car findCarById(int carId) {
        for (Car car : cars) {
            if (car.getId() == carId) {
                return car;
            }
        }
        return null; // Return null if no car matches the ID
    }
}
