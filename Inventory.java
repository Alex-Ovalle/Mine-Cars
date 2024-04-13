import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 //hello im here

public class Inventory {
    private List<Car> cars; // List to hold cars

    // Constructor
    public Inventory() {
        this.cars = new ArrayList<>();
        loadCarsFromCSV("car_data.csv");
    }

    // Add a car to the inventory
    public void addCar(Car car) {
        this.cars.add(car);
    }

    // Remove a car from the inventory by ID
    public boolean removeCar(int carID) {
        return cars.removeIf(car -> car.getId() == carID);
    }

    // Get a list of all cars in the inventory
    public List<Car> getAllCars() {
        return new ArrayList<>(this.cars); // Returning a copy of the list to protect internal data
    }

    // Read car data from CSV file
    public void loadCarsFromCSV(String csvFile) {
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator and trim potential whitespace
                String[] carData = line.split(csvSplitBy);

                // Create an instance of ConcreteCar for each line of data
                ConcreteCar car = new ConcreteCar(
                    Integer.parseInt(carData[0].trim()),
                    carData[1].trim(),
                    carData[2].trim(),
                    carData[3].trim(),
                    carData[4].trim(),
                    Integer.parseInt(carData[5].trim()),
                    Double.parseDouble(carData[6].trim()),
                    carData[7].trim(),
                    carData[8].trim(),
                    carData[9].trim(),
                    Double.parseDouble(carData[10].trim()),
                    Integer.parseInt(carData[11].trim())
                );
                this.addCar(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double carBought(User user, int carID) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("car_data.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tmp" + "car_data.csv"));

        String line;
        Boolean worked = false;
        double carPrice = -1;
        reader.readLine(); 
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]); // checking current line id to compare to given car id
            if(id == carID){
                int carQuantity = Integer.parseInt(parts[11]);
                carPrice = Double.parseDouble(parts[10]);
                if (carQuantity > 0 && user.getMoneyAvailable() >= carPrice) {
                    parts[11] = String.valueOf(carQuantity - 1);
                    worked = true;
                }
            }
        
            // Write the modified line to the temporary file
            writer.write(String.join(",", parts));
            writer.newLine();
        }

        reader.close();
        writer.close();
        if(worked)
            return carPrice;
        return -1;
    }

    // Display all cars in the inventory
    public void displayInventory() {
        for (Car car : this.cars) {
            System.out.println(car.getDetails());
        }
    }

    // Main method for testing
    // public static void main(String[] args) {
    //     Inventory inventory = new Inventory();
    //     inventory.loadCarsFromCSV("car_data.csv");
    //     inventory.displayInventory();
    // }
}
