import java.util.Scanner;

/**
 * Represents an interface for admin operations.
 */
public class AdminInterface {
    private Inventory inventory;
    private UserDatabase userDatabase;
    private Scanner scanner;
    private static final double MEMBER_DISCOUNT = 0.10; // 10% discount
    private static final double TEXAS_TAX_RATE = 0.0625; // 6.25% tax rate
    public Admin admin;
    /**
     * Constructs an AdminInterface object with the specified inventory and user database.
     * 
     * @param inventory     The inventory of cars.
     * @param userDatabase  The database of users.
     */
    public AdminInterface(Inventory inventory, UserDatabase userDatabase, Admin admin) {
        this.inventory = inventory;
        this.userDatabase = userDatabase;
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Provides access to admin functionalities through a menu.
     */
    public void adminAccess() {
        boolean running = true;
        while (running) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a Car to Dealership");
            System.out.println("2. Remove a Car from Dealership");
            System.out.println("3. Get Revenue by Car Type");
            System.out.println("4. Add User");
            System.out.println("5. Process a Purchase");
            System.out.println("6. Exit Admin Panel");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCarToDealership();
                    break;
                case 2:
                    System.out.print("Enter Car ID to remove: ");
                    int carId = scanner.nextInt();
                    removeCarFromDealership(carId);
                    break;
                case 3:
                    System.out.print("Enter Car Type for Revenue Info: ");
                    String carType = scanner.nextLine();
                    getRevenue(carType);
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    purchaseCar();
                    break;
                case 6:
                    System.out.println("Exiting Admin Panel.\n");
                    running = false;
                    RunShop.main(new String[0]);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Adds a car to the dealership inventory.
     */
    public void addCarToDealership() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter car type (Hatchback, Sedan, SUV, Pickup):");
        String type = scanner.nextLine();
        System.out.println("Enter model:");
        String model = scanner.nextLine();
        System.out.println("Enter condition (New or Used):");
        String condition = scanner.nextLine();
        System.out.println("Enter color:");
        String color = scanner.nextLine();
        System.out.println("Enter capacity:");
        int capacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter year:");
        String year = scanner.nextLine();
        System.out.println("Enter fuel type:");
        String fuelType = scanner.nextLine();
        System.out.println("Enter transmission:");
        String transmission = scanner.nextLine();
        System.out.println("Enter VIN:");
        String vin = scanner.nextLine();
        System.out.println("Enter price:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter number of cars available:");
        int carsAvailable = Integer.parseInt(scanner.nextLine());
        System.out.println("Has turbo (true or false):");
        boolean hasTurbo = Boolean.parseBoolean(scanner.nextLine());

        Car newCar;
        switch (type.toLowerCase()) {
            case "hatchback":
                newCar = new Hatchback(id, type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
                break;
            case "sedan":
                newCar = new Sedan(id, type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
                break;
            case "suv":
                newCar = new SUV(id, type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
                break;
            case "pickup":
                newCar = new Pickup(id, type, model, condition, color, capacity, year, fuelType, transmission, vin, price, carsAvailable, hasTurbo);
                break;
            default:
                System.out.println("Invalid car type provided.");
                return; // Exit the method if invalid type
        }
        inventory.addCar(newCar);
        inventory.addCarToCSV();  // Immediately update the CSV file
        System.out.println("Car added successfully!");
    }

    /**
     * Retrieves and displays the revenue generated from a specific car ID.
     * 
     * @param carId The ID of the car.
     */
    public void getRevenueById(int carId) {
        double totalRevenue = 0;
        boolean found = false;
        for (Car car : inventory.getAllCars()) {
            if (car.getId() == carId) {
                totalRevenue += car.getRevenueGenerated(); // Assuming getRevenueGenerated() is a method in Car class.
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Total Revenue for Car ID " + carId + ": $" + totalRevenue);
        } else {
            System.out.println("No car found with ID: " + carId);
        }
    }

    /**
     * Retrieves and displays the total revenue and units sold for a specific car type.
     * 
     * @param carType The type of car.
     */
    public void getRevenueByCarType(String carType) {
        double totalRevenue = 0;
        int totalSold = 0;
        for (Car car : inventory.getAllCars()) {
            if (car.getType().equalsIgnoreCase(carType)) {
                totalRevenue += car.getRevenueGenerated();
                totalSold += car.getUnitsSold(); 
            }
        }
        if (totalSold > 0) {
            System.out.println("Total revenue for " + carType + "s: $" + totalRevenue);
            System.out.println("Total number of " + carType + "s sold: " + totalSold);
        } else {
            System.out.println("No cars sold of type: " + carType);
        }
    }

    /**
     * Retrieves and displays the total revenue and units sold for a specific car type.
     * 
     * @param carType The type of car.
     */
    public void getRevenue(String carType) {
        double totalRevenue = 0;
        int totalSold = 0;
        for (Ticket ticket : admin.viewTickets()) {
            Car car = ticket.getCar();
            System.out.println(car.getId());
            if (car.getType().equalsIgnoreCase(carType)) {
                totalRevenue += car.getRevenueGenerated();
                totalSold += 1; 
            }
        }
        if (totalSold > 0) {
            System.out.println("Total revenue for " + carType + "s: $" + totalRevenue);
            System.out.println("Total number of " + carType + "s sold: " + totalSold);
        } else {
            System.out.println("No cars sold of type: " + carType);
        }
    }

    /**
     * Removes a car from the dealership inventory.
     * 
     * @param carId The ID of the car to be removed.
     */
    public void removeCarFromDealership(int carId) {
        boolean removed = inventory.removeCar(carId);
        if (removed) {
            inventory.removeCarFromCSV(carId);  // Update the CSV file immediately after removal
            System.out.println("Car removed successfully and file updated.");
        } else {
            System.out.println("Car not found.");
        }
    }

    /**
     * Adds a new user to the user database.
     */
    public void addUser() {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter ID: ");
        int ID = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter money available: ");
        double moneyAvailable = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter number of cars purchased: ");
        int carsPurchased = Integer.parseInt(scanner.nextLine());
        System.out.print("Is a Miner Cars Membership holder (true/false): ");
        boolean minerCarsMembership = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(fullName, ID, moneyAvailable, carsPurchased, minerCarsMembership, username, password);
        userDatabase.addUser(newUser);
        System.out.println("User added successfully!");
    }

    /**
     * Processes a car purchase.
     */
    public void purchaseCar() {
        System.out.println("Enter the ID of the car you wish to purchase:");
        int carId = Integer.parseInt(scanner.nextLine());
        Car car = inventory.findCarById(carId);
        if (car != null && car.isAvailable()) {
            System.out.println("Is the purchase for a membership user? (yes/no):");
            boolean isMember = scanner.nextLine().trim().equalsIgnoreCase("yes");

            double basePrice = car.getPrice();
            double priceAfterDiscount = isMember ? basePrice * (1 - MEMBER_DISCOUNT) : basePrice;
            double finalPrice = priceAfterDiscount * (1 + TEXAS_TAX_RATE);

            System.out.println("Base price: $" + basePrice);
            if (isMember) {
                System.out.println("Price after discount: $" + priceAfterDiscount);
            }
            System.out.println("Final price after tax: $" + finalPrice);

            car.setCarsAvailable(car.getCarsAvailable() - 1); // Decrease available cars by one
            System.out.println("Sale completed. Car " + car.getModel() + " has been sold.");
        } else {
            System.out.println("Car not available or not found.");
        }
    }
}
