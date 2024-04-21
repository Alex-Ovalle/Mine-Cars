/**
 * Represents an administrator extending the Person class.
 */
public class Admin extends Person {
    private String adminID;

    /**
     * Constructs an Admin object with a full name and admin ID.
     * 
     * @param fullName The full name of the admin.
     * @param adminID  The unique ID of the admin.
     */
    public Admin(String fullName, String adminID) {
        super(fullName);
        this.adminID = adminID;
    }

    /**
     * Adds a car to the inventory.
     * 
     * @param inventory The inventory to which the car will be added.
     * @param car       The car to be added to the inventory.
     */
    public void addCar(Inventory inventory, Car car) {
        inventory.addCar(car);
    }

    /**
     * Removes a car from the inventory by ID.
     * 
     * @param inventory The inventory from which the car will be removed.
     * @param carID     The ID of the car to be removed.
     * @return true if the car was successfully removed, false otherwise.
     */
    public boolean removeCar(Inventory inventory, int carID) {
        return inventory.removeCar(carID);
    }

    /**
     * Updates a car in the inventory.
     * 
     * This method assumes that the car object passed as a parameter
     * contains the updated information and the same ID as the existing car.
     * 
     * @param inventory   The inventory in which the car will be updated.
     * @param updatedCar  The updated car object.
     */
    public void updateCar(Inventory inventory, Car updatedCar) {
        boolean isRemoved = inventory.removeCar(updatedCar.getId());
        if (isRemoved) {
            inventory.addCar(updatedCar);
        }
    }

    /**
     * Retrieves the admin ID.
     * 
     * @return The admin ID.
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * Sets the admin ID.
     * 
     * @param adminID The admin ID to be set.
     */
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
