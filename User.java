import java.util.ArrayList;
import java.util.List;
import java.util.Date; 


public class User extends Person {
    private int ID;
    private double moneyAvailable;
    private int carsPurchased;
    private boolean minerCarsMembership;
    private String username;
    private String password;
    public static List<Ticket> tickets; // Field to store tickets

    // Constructor
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

    // Method to view cars
    public void viewCars(Inventory inventory) {
        List<Car> cars = inventory.getAllCars();
        for (Car car : cars) {
            System.out.println(car.getDetails());
        }
    }
    
    /*public boolean purchaseCar(Inventory inventory, Car car) {
        if (car.getPrice() <= this.moneyAvailable && car.isAvailable()) {
            this.moneyAvailable -= car.getPrice();
            this.carsPurchased += 1;
            inventory.removeCar(car.getId());
    
            // Create and add a ticket
            Ticket newTicket = new Ticket(car, new Date(), car.getPrice());
            tickets.add(newTicket);
    
            return true;
        }
        return false;
    }
    */
    // Method to view tickets
    public static List<Ticket> viewTickets() {
        return tickets;
    }
    

    // Method to login
    //UserDatabase class, responsible for user management, including credential validation.
    public boolean login(String username, String password, UserDatabase userDB) {
        return userDB.validateCredentials(username, password);
    }
    

    // Getters and Setters
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

