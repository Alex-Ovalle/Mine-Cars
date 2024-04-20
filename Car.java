public interface Car {
    // Getter methods
    int getId();
    String getModel();
    String getCondition();
    String getColor();
    int getCapacity();
    String getYear();
    String getFuelType();
    String getTransmission();
    String getVin();
    double getPrice();
    int getCarsAvailable();
    boolean getTurbo();
    // Print all details
    String getDetails();
    boolean isAvailable();
    // Setter methods
    void setId(int id);
    void setModel(String model);
    void setCondition(String condition);
    void setColor(String color);
    void setCapacity(int capacity);
    void setYear(String year);
    void setFuelType(String fuelType);
    void setTransmission(String transmission);
    void setVin(String vin);
    void setPrice(double price);
    void setCarsAvailable(int carsAvailable);
    void setTurbo(boolean hasTurbo);
}


