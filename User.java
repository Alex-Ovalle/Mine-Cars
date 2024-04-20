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
    private static List<Ticket> tickets; // Field to store tickets

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
    
    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

    public boolean purchaseCar(Car car) {
        if (car.isAvailable() && (car.getPrice() <= this.moneyAvailable)) {
            // update user and car values
            this.moneyAvailable -= car.getPrice();
            this.carsPurchased += 1;
            car.setCarsAvailable(car.getCarsAvailable() - 1);
    
            // Create and add a ticket
            Ticket newTicket = new Ticket(car); // ticket like this?
            this.addTicket(newTicket);
            return true;
        }
        return false;
    }

    public Car findCarByID(int id, Inventory inventory){
        for (Car car: inventory.getAllCars()){
            if (car.getId() == id)
                return car;
        }return null;
    }

    // Method to view tickets
    public static List<Ticket> viewTickets() {
        return tickets;
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

