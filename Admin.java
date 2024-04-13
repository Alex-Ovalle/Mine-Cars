public class Admin extends Person {
    private String adminID;

    // Constructor
    public Admin(String fullName, String adminID) {
        super(fullName);
        this.adminID = adminID;
    }

     // Add a car to the inventory
     public void addCar(Inventory inventory, Car car) {
        inventory.addCar(car);
    }

     // Remove a car from the inventory by ID
     public boolean removeCar(Inventory inventory, int carID) {
        return inventory.removeCar(carID);
    }

    // Update a car in the inventory
    // This method assumes that the car object passed as a parameter
    // contains the updated information and the same ID as the existing car.
    public void updateCar(Inventory inventory, Car updatedCar) {
        boolean isRemoved = inventory.removeCar(updatedCar.getId());
        if (isRemoved) {
            inventory.addCar(updatedCar);
        }
    }

    // Getter for adminID
    public String getAdminID() {
        return adminID;
    }

    // Setter for adminID
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}

