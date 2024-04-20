public class Hatchback implements Car {
    // Provides detailed information about the Hatchback.
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

    // Constructor
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
    
    public String getDetails() {
        return "Car Details:\n" +
               "  Type: Hatchback\n" +
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
               "  Cars Available: " + getCarsAvailable();
    }

    public String printCSV(){  // update
        return "CSV STRING";
    }
}
