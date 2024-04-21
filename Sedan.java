/**
 * The Sedan class represents a type of car, providing detailed information about Sedans.
 * It implements the Car interface.
 */

public class Sedan implements Car {
    // Provides detailed information about the Sedan.
    // This method overrides the abstract method in the Car class.
    // It compiles a string that includes all relevant details of the Harchback.
    private int id;
    private String type; // e.g., Sedan, SUV, Hatchback, Pickup
    private String model;
    private String condition; // New or Used
    private String color;
    private int capacity;
    private String year;
    private String fuelType;
    private String transmission;
    private String vin; // Vehicle Identification Number
    private double price;
    private int carsAvailable;
    private boolean hasTurbo;
    private int unitsSold; // New field to track units sold
    private double revenueGenerated; // New field to track revenue

    /**
     * Constructor to create a Sedan object with specified attributes.
     * 
     * @param id The unique identifier of the Sedan.
     * @param type The type of the car (e.g., Sedan, SUV, Hatchback, Pickup).
     * @param model The model of the Sedan.
     * @param condition The condition of the Sedan (New or Used).
     * @param color The color of the Sedan.
     * @param capacity The seating capacity of the Sedan.
     * @param year The manufacturing year of the Sedan.
     * @param fuelType The fuel type of the Sedan.
     * @param transmission The transmission type of the Sedan.
     * @param vin The Vehicle Identification Number (VIN) of the Sedan.
     * @param price The price of the Sedan.
     * @param carsAvailable The number of Sedans available in inventory.
     * @param hasTurbo A boolean indicating whether the Sedan has a turbocharger.
     */

    public Sedan(int id, String type, String model, String condition, String color, int capacity, 
                    String year, String fuelType, String transmission, String vin, 
                    double price, int carsAvailable, boolean hasTurbo) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.condition = condition;
        this.color = color;
        this.year = year;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.vin = vin;
        this.price = price;
        this.carsAvailable = carsAvailable;
        this.hasTurbo = hasTurbo;
        this.unitsSold = 0;
        this.revenueGenerated = 0.0;
    }

    /**
     * Records a sale of the Sedan.
     */
    public void recordSale() {
        if (this.carsAvailable > 0) {
            this.unitsSold++;
            this.revenueGenerated += this.price;
            this.carsAvailable--;
        }
    }

    public int getUnitsSold() {
        return this.unitsSold;
    }

    public double getRevenueGenerated() {
        return this.revenueGenerated;
    }

    public boolean isAvailable() {
        return this.carsAvailable > 0;
    }

    // Getters and setters for all attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCarsAvailable() {
        return carsAvailable;
    }

    public void setCarsAvailable(int carsAvailable) {
        this.carsAvailable = carsAvailable;
    }

    public boolean getTurbo(){  
        return hasTurbo;   
    }

    public void setTurbo(boolean hasTurbo){
        this.hasTurbo = hasTurbo;
    }

    /**
     * Returns a string containing details of the Sedan.
     * 
     * @return A string with Sedan details.
     */
    
    public String getDetails() {
        return "Car Details:\n" +
               "  Type: Sedan\n" +
               "  ID: " + getId() + "\n" +
               "  Model: " + getModel() + "\n" +
               "  Condition: " + getCondition() + "\n" +
               "  Color: " + getColor() + "\n" +
               "  Capacity: " + getCapacity() + " passengers\n" +
               "  Year: " + getYear() + "\n" +
               "  Fuel Type: " + getFuelType() + "\n" +
               "  Transmission: " + getTransmission() + "\n" +
               "  VIN: " + getVin() + "\n" +
               "  Price: $" + getPrice() + "\n" +
               "  Cars Available: " + getCarsAvailable() + "\n" +
               "  Has Turbo: " + (getTurbo() ? "Yes" : "No");
    }

    /**
     * Generates a CSV representation of the Sedan.
     * 
     * @return A string containing the CSV representation of the Sedan.
     */

    public String printCSV(){  // update
        return "CSV STRING";
    }

    /**
     * Accepts a CarVisitor and calls the visitSedan method on it.
     * 
     * @param visitor The CarVisitor instance.
     */
    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitSedan(this);
    }
}
