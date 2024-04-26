/**
 * The SalesVisitor class implements the CarVisitor interface to calculate and track the total revenue 
 * generated from sales of cars.
 */
public class SalesVisitor implements CarVisitor {
    private double totalRevenue = 0.0; // Variable to store the total revenue generated

    /**
     * Visits a Hatchback car and processes the sale.
     * 
     * @param hatchback The Hatchback car to be visited.
     */
    @Override
    public void visitHatchback(Hatchback hatchback) {
        processSale(hatchback);
    }

    /**
     * Visits a Sedan car and processes the sale.
     * 
     * @param sedan The Sedan car to be visited.
     */
    @Override
    public void visitSedan(Sedan sedan) {
        processSale(sedan);
    }

    /**
     * Visits an SUV car and processes the sale.
     * 
     * @param suv The SUV car to be visited.
     */
    @Override
    public void visitSUV(SUV suv) {
        processSale(suv);
    }

    /**
     * Visits a Pickup car and processes the sale.
     * 
     * @param pickup The Pickup car to be visited.
     */
    @Override
    public void visitPickup(Pickup pickup) {
        processSale(pickup);
    }

    /**
     * Processes the sale of a car.
     * 
     * @param car The car to be processed for sale.
     */
    private void processSale(Car car) {
        if (car.isAvailable()) {
            car.recordSale();  // Assume each Car implementation has recordSale()
            totalRevenue += car.getPrice();
        }
    }

    /**
     * Gets the total revenue generated from sales.
     * 
     * @return The total revenue generated.
     */
    public double getRevenue() {
        return totalRevenue;
    }

    /**
     * Handles the sale of a car by accepting the visitor.
     * 
     * @param car The car for which the sale is handled.
     */
    @Override
    public void handleSale(Car car) {
        car.accept(this);
    }
}
