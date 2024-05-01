/**
 * Provides detailed information about the SUV.
 * This class represents an SUV and implements the Car interface.
 */
public class SUV implements Car {
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
     * Constructor to create an SUV object with specified attributes.
     * 
     * @param id The unique identifier of the SUV.
     * @param type The type of the car (e.g., Sedan, SUV, Hatchback, Pickup).
     * @param model The model of the SUV.
     * @param condition The condition of the SUV (New or Used).
     * @param color The color of the SUV.
     * @param capacity The seating capacity of the SUV.
     * @param year The manufacturing year of the SUV.
     * @param fuelType The fuel type of the SUV.
     * @param transmission The transmission type of the SUV.
     * @param vin The Vehicle Identification Number (VIN) of the SUV.
     * @param price The price of the SUV.
     * @param carsAvailable The number of SUVs available in inventory.
     * @param hasTurbo A boolean indicating whether the SUV has a turbocharger.
     */
    public SUV(int id, String type, String model, String condition, String color, int capacity, 
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
     * Records a sale of the SUV.
     * If SUVs are available, increments the units sold and updates the revenue generated.
     */

    public void recordSale() {
        if (this.carsAvailable > 0) {
            this.unitsSold++;
            this.revenueGenerated += this.price;
            this.carsAvailable--;
        }
    }
    /**
     * Gets the total number of SUV units sold.
     * 
     * @return The total number of SUV units sold.
     */

    public int getUnitsSold() {
        return this.unitsSold;
    }

    /**
     * Gets the total revenue generated from SUV sales.
     * 
     * @return The total revenue generated from SUV sales.
     */

    public double getRevenueGenerated() {
        return this.revenueGenerated;
    }

    /**
     * Checks if the SUV is available in inventory.
     * 
     * @return True if the SUV is available, false otherwise.
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
     * Gets a string representation of the SUV's details.
     * 
     * @return A string containing the details of the SUV.
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
     

    /**
     * Accepts a CarVisitor and invokes the visitSUV method on it.
     * 
     * @param visitor The CarVisitor to accept.
     */

    @Override
    public void accept(CarVisitor visitor) {
        visitor.visitSUV(this);
    }
}
