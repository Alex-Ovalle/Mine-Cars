import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the car dealership system.
 * This class extends the Person class and includes additional fields and methods
 * specific to a user.
 */
public class User extends Person {
    private int ID; // The user's ID
    private double moneyAvailable; // The amount of money available for purchasing cars
    private int carsPurchased; // The number of cars purchased by the user
    private boolean minerCarsMembership; // Indicates whether the user has a Mine Cars membership
    private String username; // The username of the user
    private String password; // The password of the user
    private List<Ticket> tickets; // Field to store tickets
    private static final double MEMBER_DISCOUNT = 0.90; // 10% discount
    private static final double TEXAS_TAX_RATE = 1.0625; // 6.25% tax rate


    /**
     * Constructs a new User object with the specified details.
     * Initializes the list of tickets.
     * 
     * @param fullName The full name of the user.
     * @param ID The ID of the user.
     * @param moneyAvailable The amount of money available for purchasing cars.
     * @param carsPurchased The number of cars purchased by the user.
     * @param minerCarsMembership Indicates whether the user has a Mine Cars membership.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String fullName, int ID, double moneyAvailable, int carsPurchased,
                boolean minerCarsMembership, String username, String password) {
        super(fullName);
        this.ID = ID;
        this.moneyAvailable = moneyAvailable;
        this.carsPurchased = carsPurchased;
        this.minerCarsMembership = minerCarsMembership;
        this.username = username;
        this.password = password;
        this.tickets = new ArrayList<>(); // Initialize the tickets list
    }

    /**
     * Displays details of all cars available in the inventory.
     * 
     * @param inventory The inventory containing all cars.
     */
    public void viewCars(Inventory inventory) {
        List<Car> cars = inventory.getAllCars();
        for (Car car : cars) {
            System.out.println(car.getDetails());
        }
    }
    
    /**
     * Adds a ticket to the user's list of tickets.
     * 
     * @param ticket The ticket to be added.
     */
    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    /**
     * Purchases a car for the user if it is available and the user has sufficient funds.
     * 
     * @param car The car to be purchased.
     * @return true if the purchase is successful, false otherwise.
     */
    public boolean purchaseCar(Car car, Admin admin) {
        // Update user and car values
        if (car.isAvailable() && (car.getPrice() <= this.moneyAvailable)) {
            double finalCarPrice = car.getPrice();
            if(this.isMinerCarsMembership())
                this.moneyAvailable -= (finalCarPrice*MEMBER_DISCOUNT)*TEXAS_TAX_RATE;
            else
                this.moneyAvailable -= finalCarPrice*TEXAS_TAX_RATE;
            this.carsPurchased += 1;
            car.setCarsAvailable(car.getCarsAvailable() - 1);
    
            // Create and add a ticket
            Ticket newTicket = new Ticket(car);
            this.addTicket(newTicket);
            admin.addTicket(newTicket);
            return true;
        }
        return false;
    }

    /**
     * Finds a car in the inventory based on its ID.
     * 
     * @param id The ID of the car to be found.
     * @param inventory The inventory containing all cars.
     * @return The car if found, null otherwise.
     */
    public Car findCarByID(int id, Inventory inventory){
        for (Car car: inventory.getAllCars()){
            if (car.getId() == id)
                return car;
        }
        return null;
    }

    /**
     * Retrieves the list of tickets associated with the user.
     * 
     * @return The list of tickets.
     */
    public List<Ticket> viewTickets() {
        return tickets;
    }

    // Getters and Setters for user attributes

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMoneyAvailable() {
        return moneyAvailable;
    }

    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public int getCarsPurchased() {
        return carsPurchased;
    }

    public void setCarsPurchased(int carsPurchased) {
        this.carsPurchased = carsPurchased;
    }

    public boolean isMinerCarsMembership() {
        return minerCarsMembership;
    }

    public void setMinerCarsMembership(boolean minerCarsMembership) {
        this.minerCarsMembership = minerCarsMembership;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
