import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Ticket {
    // Attributes to store the car ID, model, purchase amount, and the date of purchase
    private int carID;
    private String carModel;
    private double purchaseAmount;
    private LocalDateTime purchaseDate;
    private Car car;

    // Constructor to initialize a new Ticket object with provided details
    public Ticket(Car car) {
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
    public String printTicket() {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        // Printing the ticket with all details in a clear format
        return("--------- TICKET ---------\n" +
        "Car ID: " + this.car.getId() + 
        "\nCar Model: " + this.car.getModel() +
        "\nPurchase Amount: $" + String.format("%.2f", this.car.getPrice()) +
        "\nPurchase Date: " + purchaseDate.format(myFormat) +
        "\n--------------------------");
    }
}

