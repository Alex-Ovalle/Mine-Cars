/**
 * Represents a Hatchback car implementing the Car interface.
 */
public class Hatchback implements Car {
    // Provides detailed information about the Hatchback.
    // This method overrides the abstract method in the Car class.
    // It compiles a string that includes all relevant details of the Hatchback.
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
     * Constructor to create a Hatchback object with specified attributes.
     * 
     * @param id The unique identifier of the Hatchback.
     * @param type The type of the car (e.g., Sedan, SUV, Hatchback, Pickup).
     * @param model The model of the Hatchback.
     * @param condition The condition of the Hatchback (New or Used).
     * @param color The color of the Hatchback.
     * @param capacity The seating capacity of the Hatchback.
     * @param year The manufacturing year of the Hatchback.
     * @param fuelType The fuel type of the Hatchback.
     * @param transmission The transmission type of the Hatchback.
     * @param vin The Vehicle Identification Number (VIN) of the Hatchback.
     * @param price The price of the Hatchback.
     * @param carsAvailable The number of Hatchbacks available in inventory.
     * @param hasTurbo A boolean indicating whether the Hatchback has a turbocharger.
     */
    public Hatchback(int id, String type, String model, String condition, String color, int capacity, 
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

    // New method to record a sale
    public void recordSale() {
        if (this.carsAvailable > 0) {
            this.unitsSold++;
            this.revenueGenerated += this.price;
            this.carsAvailable--;
        }
    }

    /**
     * Retrieves the number of units sold.
     * 
     * @return The number of units sold.
     */
    public int getUnitsSold() {
        return this.unitsSold;
    }

    /**
     * Retrieves the revenue generated by sales.
     * 
     * @return The revenue generated.
     */
    public double getRevenueGenerated() {
        return this.revenueGenerated;
    }

    /**
     * Checks if the Hatchback is available for sale.
     * 
     * @return true if the Hatchback is available, false otherwise.
     */
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
     * Retrieves detailed information about the Hatchback.
     * 
     * @return A string containing all details of the Hatchback.
     */
    @Override
    public String getDetails() {
        return String.format(
            "  %-10s" +
            "  %-10d" +
            "  %-20s" +
            "  %-10s" +
            "  %-10s" +
            "  %-2d passengers" +
            "  %-4s" +
            "  %-10s" +
            "  %-10s" +
            "  $%-10.2f" +
            "  %-3d" +
            "  %-5s",
            getType(), getId(), getModel(), getCondition(), getColor(), 
            getCapacity(), getYear(), getFuelType(), getTransmission(), 
            getPrice(), getCarsAvailable(), getTurbo() ? "Yes" : "No"
        );
    }


    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitHatchback(this);
    }

}
