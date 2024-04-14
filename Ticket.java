import java.util.Date;
import java.text.SimpleDateFormat;

public class Ticket {
    // Attributes to store the car ID, model, purchase amount, and the date of purchase
    private int carID;
    private String carModel;
    private double purchaseAmount;
    private Date purchaseDate;

    // Constructor to initialize a new Ticket object with provided details
    public Ticket(int carID, String carModel, double purchaseAmount, Date purchaseDate) {
        this.carID = carID;
        this.carModel = carModel;
        this.purchaseAmount = purchaseAmount;
        this.purchaseDate = purchaseDate;
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

    // Getter for purchase date
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Method to print ticket in a formatted manner, displaying all relevant purchase details
    public void printTicket() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = formatter.format(purchaseDate); // Formatting the date
        
        // Printing the ticket with all details in a clear format
        System.out.println("--------- TICKET ---------");
        System.out.println("Car ID: " + carID);
        System.out.println("Car Model: " + carModel);
        System.out.println("Purchase Amount: $" + String.format("%.2f", purchaseAmount));
        System.out.println("Purchase Date: " + formattedDate);
        System.out.println("--------------------------");
    }
}

