public class Hatchback extends Car {
    // Constructor
    public Hatchback(int id, String model, String condition, String color, int capacity, 
                     double mileage, String fuelType, String transmission, String vin, 
                     double price, int carsAvailable) {
        super(id, "Hatchback", model, condition, color, capacity, mileage, fuelType, transmission, vin, price, carsAvailable);
    }
    // Provides detailed information about the Hatchback.
    // This method overrides the abstract method in the Car class.
    // It compiles a string that includes all relevant details of the Harchback.
    public String getDetails() {
        return "Car Details:\n" +
               "  Type: Hatchback\n" +
               "  ID: " + getId() + "\n" +
               "  Model: " + getModel() + "\n" +
               "  Condition: " + getCondition() + "\n" +
               "  Color: " + getColor() + "\n" +
               "  Capacity: " + getCapacity() + " passengers\n" +
               "  Mileage: " + getMileage() + " miles\n" +
               "  Fuel Type: " + getFuelType() + "\n" +
               "  Transmission: " + getTransmission() + "\n" +
               "  VIN: " + getVin() + "\n" +
               "  Price: $" + getPrice() + "\n" +
               "  Available: " + getCarsAvailable();
    }
}
