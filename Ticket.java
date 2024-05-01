/**
 * Represents a ticket for a car purchase.
 * This class stores information about the car ID, model, purchase amount,
 * and the date of purchase.
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private int carID; // The ID of the car
    private String carModel; // The model of the car
    private double purchaseAmount; // The amount paid for the purchase
    private LocalDateTime purchaseDate; // The date and time of the purchase
    private Car car; // The car associated with the ticket

    /**
     * Constructs a new Ticket object with the provided car.
     * Sets the purchase date to the current date and time.
     * 
     * @param car The car associated with the ticket.
     */
    public Ticket(Car car) {
        this.car = car;
        setPurchaseDate();
    }

    /**
     * Gets the ID of the car associated with the ticket.
     * 
     * @return The ID of the car.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Sets the purchase date and time to the current date and time.
     */
    public void setPurchaseDate(){
        this.purchaseDate = LocalDateTime.now();
    }

    /**
     * Gets the date and time of the car purchase.
     * 
     * @return The date and time of the purchase.
     */
    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Generates a formatted ticket with details of the car purchase.
     * 
     * @return A string representation of the ticket.
     */
    public String printTicket() {
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        // Printing the ticket with all details in a clear format
        return "--------- TICKET ---------\n" +
               "Car ID: " + this.car.getId() + 
               "\nCar Model: " + this.car.getModel() +
               "\nPurchase Amount: $" + String.format("%.2f", this.car.getPrice()) +
               "\nPurchase Date: " + purchaseDate.format(myFormat) +
               "\n--------------------------";
    }
}
