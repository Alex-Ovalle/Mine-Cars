import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarDealershipTests {
    private Inventory inventory;
    private UserDatabase userDB;
    private User testUser;
    private Admin testAdmin;

    @Before
    public void setUp() {
        inventory = new Inventory();
        userDB = new UserDatabase();
        testUser = new User("Test User", 1, 100000, 0, false, "testuser", "password");
        userDB.addUser(testUser);
        testAdmin = new Admin("Admin Full Name", "adminID123"); // Use realistic full name and admin ID

    }

    @Test
    public void testAddCar() {
        Car newCar = new Sedan(101, "Sedan", "Test Model", "New", "Black", 4, "2023", "Electric", "Automatic", "VIN123", 30000.00, 5, false);
        inventory.addCar(newCar);
        assertTrue("Car should be added to the inventory", inventory.getAllCars().contains(newCar));
    }

    @Test
    public void testRemoveCar() {
        Car carToRemove = new SUV(102, "SUV", "Test SUV", "Used", "Blue", 5, "2020", "Gasoline", "Manual", "VIN124", 25000.00, 3, true);
        inventory.addCar(carToRemove);
        boolean removed = inventory.removeCar(carToRemove.getId());
        assertTrue("Car should be removed from the inventory", removed && !inventory.getAllCars().contains(carToRemove));
    }

    // @Test
    // public void testPurchaseCar() {
    //     Car carToPurchase = new Hatchback(103, "Hatchback", "Test Hatchback", "New", "Red", 4, "2021", "Hybrid", "Automatic", "VIN125", 20000.00, 2, true);
    //     inventory.addCar(carToPurchase);
    //     boolean success = testUser.purchaseCar(carToPurchase, null);
    //     assertTrue("User should be able to purchase the car", success);
    //     assertEquals("Car availability should be decremented", 1, carToPurchase.getCarsAvailable());
    // }

    @Test
    public void testPurchaseCar() {
        Car carToPurchase = new Hatchback(103, "Hatchback", "Test Hatchback", "New", "Red", 4, "2021", "Hybrid", "Automatic", "VIN125", 20000.00, 2, true);
        inventory.addCar(carToPurchase);
        boolean success = testUser.purchaseCar(carToPurchase, testAdmin);
        assertTrue("User should be able to purchase the car if funds are sufficient and car is available", success);
        assertEquals("Car availability should be decremented", 1, carToPurchase.getCarsAvailable());
    }
}
