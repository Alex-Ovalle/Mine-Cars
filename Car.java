public abstract class Car {
    private int id;
    private String type; // e.g., Sedan, SUV, Hatchback, Pickup
    private String model;
    private String condition; // New or Used
    private String color;
    private int capacity;
    private double mileage;
    private String fuelType;
    private String transmission;
    private String vin; // Vehicle Identification Number
    private double price;
    private int carsAvailable;

    // Constructor
    public Car(int id, String type, String model, String condition, String color, int capacity, 
               double mileage, String fuelType, String transmission, String vin, double price, int carsAvailable) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.condition = condition;
        this.color = color;
        this.capacity = capacity;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.vin = vin;
        this.price = price;
        this.carsAvailable = carsAvailable;
    }

    // Abstract method to get details about the car
    public abstract String getDetails();

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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
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
}
