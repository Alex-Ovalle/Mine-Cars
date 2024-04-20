import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

public class Ticket {
    // Attributes to store the car ID, model, purchase amount, and the date of purchase
    private int carID;
    private String carModel;
    private double purchaseAmount;
    private LocalDateTime purchaseDate;
    private Car car;

    // Constructor to initialize a new Ticket object with provided details
    public Ticket(Car car) {   // maybe just car?
        this.car = car;
        setPurchaseDate();
    }

    // Getter for car ID
    public int getCarID() {
        return carID;
    }

    // Getter for car model
    public String getCarModel() {
        return carModel;
    }

    // Getter for purchase amount
    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseDate(){
        this.purchaseDate = purchaseDate.now();
    }

    // Getter for purchase date
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    // Method to print ticket in a formatted manner, displaying all relevant purchase details
    public void printTicket() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(this.purchaseDate); // Formatting the date
        
        // Printing the ticket with all details in a clear format
        System.out.println("--------- TICKET ---------");
        System.out.println("Car ID: " + this.car.getId());
        System.out.println("Car Model: " + this.car.getModel());
        System.out.println("Purchase Amount: $" + String.format("%.2f", this.car.getPrice()));
        System.out.println("Purchase Date: " + formattedDate);
        System.out.println("--------------------------");
    }
}

