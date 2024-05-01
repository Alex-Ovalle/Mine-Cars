/**
* The inventory class lists all the cars available in the dealership,
* adds and removes cars available.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to manage inventory of cars.
 */
 public class Inventory {
    private List<Car> cars; // List to hold cars

    // Constructor
    public Inventory() {
        this.cars = new ArrayList<>();
        try{
            // Attempt to load car data from CSV file
            loadCarsFromCSV("car_data.csv");
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
            List<String> headers = Arrays.asList(headerLine.split(",")); // List to hold order of header
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
                // if (carFields.length < headers.size()) {
                //     System.out.println("Skipping incomplete or improperly formatted line: " + line);
                //     continue;  // Skip processing this line because it is incomplete
                // }

                // Determine if the "hasTurbo" field is available and properly listed in the current car entry

                // boolean hasTurbo = false; // Default to false
                // if (hasTurboIndex > -1 && hasTurboIndex < carFields.length) {
                //     String turboValue = carFields[hasTurboIndex].trim().toLowerCase();
                //     hasTurbo = turboValue.equals("yes"); // Set hasTurbo to true if the value is "yes"
                // }
                
                boolean hasTurbo = hasTurboIndex > -1 && hasTurboIndex < carFields.length && "yes".equals(carFields[hasTurboIndex].trim().toLowerCase());
                String carType = formatCarType(carFields[carTypeIndex].trim());  // Apply the formatCarType method

                
                // Create car objects based on the type indicated in the CSV
                try {
                    Car car;
                    switch(carType) {  // Now switch on the formatted carType
                        case "Hatchback":
                            car = new Hatchback(
                                Integer.parseInt(carFields[idIndex]), carType,
                                carFields[modelIndex], carFields[conditionIndex],
                                carFields[colorIndex], Integer.parseInt(carFields[capacityIndex]),
                                carFields[yearIndex], carFields[fuelTypeIndex],
                                carFields[transmissionIndex], carFields[vinIndex],
                                Double.parseDouble(carFields[priceIndex]),
                                Integer.parseInt(carFields[carsAvailableIndex]), hasTurbo
                            );
                            break;
                        case "SUV":
                            car = new SUV(
                                Integer.parseInt(carFields[idIndex]), carType,
                                carFields[modelIndex], carFields[conditionIndex],
                                carFields[colorIndex], Integer.parseInt(carFields[capacityIndex]),
                                carFields[yearIndex], carFields[fuelTypeIndex],
                                carFields[transmissionIndex], carFields[vinIndex],
                                Double.parseDouble(carFields[priceIndex]),
                                Integer.parseInt(carFields[carsAvailableIndex]), hasTurbo
                            );
                            break;
                        case "Sedan":
                            car = new Sedan(
                                Integer.parseInt(carFields[idIndex]), carType,
                                carFields[modelIndex], carFields[conditionIndex],
                                carFields[colorIndex], Integer.parseInt(carFields[capacityIndex]),
                                carFields[yearIndex], carFields[fuelTypeIndex],
                                carFields[transmissionIndex], carFields[vinIndex],
                                Double.parseDouble(carFields[priceIndex]),
                                Integer.parseInt(carFields[carsAvailableIndex]), hasTurbo
                            );
                            break;
                        case "Pickup":
                            car = new Pickup(
                                Integer.parseInt(carFields[idIndex]), carType,
                                carFields[modelIndex], carFields[conditionIndex],
                                carFields[colorIndex], Integer.parseInt(carFields[capacityIndex]),
                                carFields[yearIndex], carFields[fuelTypeIndex],
                                carFields[transmissionIndex], carFields[vinIndex],
                                Double.parseDouble(carFields[priceIndex]),
                                Integer.parseInt(carFields[carsAvailableIndex]), hasTurbo
                            );
                            break;
                        default:
                            continue; // Skip if the type is unknown
                    }
                    this.addCar(car);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping entry with invalid data: " + e.getMessage());
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

    // Ensures the first letter of every word is capatalized, before writting to the file
    
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Arrays.stream(input.split("\\s"))
                     .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                     .collect(Collectors.joining(" "));
    }


    private String formatCarType(String carType) {
        if (carType == null) return null;
    
        switch (carType.toLowerCase()) {
            case "suv":
                return "SUV";
            case "sedan":
                return "Sedan";
            case "pickup":
                return "Pickup";
            case "hatchback":
                return "Hatchback";
            default:
                throw new IllegalArgumentException("Unknown car type: " + carType);
        }
    }
    
    

    /**
     * Updates the file with inventory changes, formatting the output to align columns under their respective headers.
     */
    public void addCarToCSV() {
        try (FileWriter writer = new FileWriter("car_data.csv")) {
            // Write headers
            writer.write("ID,Car Type,Model,Condition,Color,Capacity,Year,Fuel Type,Transmission,VIN,Price,Cars Available,hasTurbo\n");
            
            for (Car car : cars) {
                String turboString = car.getTurbo() ? "yes" : "no";
                // Apply capitalization to the appropriate fields
                String carType = formatCarType(car.getType()); // ensures correct format from car type
                String model = capitalize(car.getModel());
                String condition = capitalize(car.getCondition());
                String color = capitalize(car.getColor());
                String fuelType = capitalize(car.getFuelType());
                String transmission = capitalize(car.getTransmission());
                // Format each line to ensure data aligns under the headers
                String carData = String.format("%d,%s,%s,%s,%s,%d,%s,%s,%s,%s,%.2f,%d,%s\n",
                    car.getId(), carType, model, condition, color,
                    car.getCapacity(), car.getYear(), fuelType, transmission,
                    car.getVin(), car.getPrice(), car.getCarsAvailable(), turboString);
                writer.write(carData);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    /**
     * Removes a car from the CSV file by ID. Rewrites all the cars except for the one we removed.
     * 
     * @param carId The ID of the car to be removed.
     */
    public void removeCarFromCSV(int carId) {
        try (FileWriter writer = new FileWriter("car_data.csv", false)) {
            writer.write("ID,Car Type,Model,Condition,Color,Capacity,Year,Fuel Type,Transmission,VIN,Price,Cars Available,hasTurbo\n");
            for (Car car : cars) {
                if (car.getId() != carId) {
                    String turboString = car.getTurbo() ? "yes" : "no";
                    String carType = formatCarType(car.getType());
                    String model = capitalize(car.getModel());
                    String condition = capitalize(car.getCondition());
                    String color = capitalize(car.getColor());
                    String fuelType = capitalize(car.getFuelType());
                    String transmission = capitalize(car.getTransmission());

                    String carData = String.format("%d,%s,%s,%s,%s,%d,%s,%s,%s,%s,%.2f,%d,%s\n",
                        car.getId(), carType, model, condition, color,
                        car.getCapacity(), car.getYear(), fuelType, transmission,
                        car.getVin(), car.getPrice(), car.getCarsAvailable(), turboString);
                    writer.write(carData);
                }
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
