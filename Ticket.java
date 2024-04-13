import java.util.Date; 

public class Ticket {
    private int carID;
    private Date purchaseDate;
    private double purchaseAmount;

    // Constructor
    public Ticket(int carID, Date purchaseDate, double purchaseAmount) {
        this.carID = carID;
        this.purchaseDate = purchaseDate;
        this.purchaseAmount = purchaseAmount;
    }

    // Getters
    public int getCarID() {
        return carID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
    
    public double getPurchaseAmount() {
        return purchaseAmount;
    }

    // Setters
    public void setCarId(int CarID) {
        this.carID = carID;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
}

