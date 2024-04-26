/**
 * Represents a visitor interface for visiting different types of cars.
 */
public interface CarVisitor {
    // Define a visit method for each type of car.
    void visitHatchback(Hatchback hatchback);
    void visitSedan(Sedan sedan);
    void visitSUV(SUV suv);
    void visitPickup(Pickup pickup);

    // Methods for handling operations common to all types of cars
    void handleSale(Car car);  // Method to handle the sale of any car
    double getRevenue();       // Method to get total revenue from sales
}
