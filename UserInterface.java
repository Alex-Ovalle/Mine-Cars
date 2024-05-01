import java.io.IOException;
import java.util.Scanner; 

/**
 * Represents the user interface for interacting with the system.
 * This class provides methods for user login, displaying menu options, filtering and purchasing cars,
 * and viewing tickets.
 */
public class UserInterface{

    private String username;
    private String password;
    public static User user = new User("", -1, -1, -1, false, "", "");
    public static Inventory inventory = new Inventory();
    public static UserDatabase userDB = new UserDatabase();
    public Admin admin;

    /**
     * Constructs a new UserInterface object.
     */
    public UserInterface(Admin admin){ this.admin = admin; }
    
    /**
     * Handles the user login process.
     */
    public void user_login(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
        
        try{
            if(userDB.initializeUser(user, username, password)){

                System.out.println("\nWelcome, " + user.getFullName() + "!");

                Log userLog = new Log(username);
                userLog.write_log(0);

                System.out.println("----------------------");
                this.show_menu();

            }else
                System.out.println("Incorrect login, please try again.\n");
                this.user_login();

        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error reading user data file.");
        }finally{
            scanner.close();
        }
    }

    /**
     * Displays the menu options for the user.
     */
    public void show_menu() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Display all cars.");
            System.out.println("2. Filter Cars (used / new)");
            System.out.println("3. Purchase a car");
            System.out.println("4. View Tickets");
            System.out.println("5. Sign out");
            System.out.print("Enter your choice: ");
            while(!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Invalid choice. Please try again: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            Log userLog = new Log(username);
            switch (choice) {
                case 1:
                    display_cars();
                    userLog.write_log(1);
                    break;
                case 2:
                    System.out.println("\nFiltering options:");
                    System.out.println("1. NEW cars");
                    System.out.println("2. USED cars");
                    System.out.println("3. return");
                    while(!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Invalid choice. Please try again: ");
                    }
                    int filterInt = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    filter_cars(filterInt);
                    break;
                case 3:
                    purchase_car();
                    userLog.write_log(3);
                    break;
                case 4:
                    view_tickets();
                    userLog.write_log(4);
                    break;
                case 5:
                    userLog.write_log(5);
                    System.out.println("Signing out.\n");
                    inventory.addCarToCSV();;
                    userDB.updateUserFile(user);
                    RunShop.main(new String[0]);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    /**
     * Displays all cars in the inventory.
     */
    private void display_cars() {
        System.out.println("Displaying all cars...");
        System.out.println("--Type--------ID----------Model-----------------Condition---Color-------Capacity-------Year--FuelType----Transmission-Price---Capacity-Turbo?-");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        for (Car car : inventory.getAllCars()) {
            System.out.println(car.getDetails());
        }
    }   

    /**
     * Filters cars based on their condition (used / new).
     *
     * @param filter The filter option chosen by the user.
     */
    private void filter_cars(int filter) {
        System.out.println("Filtering cars...");
        // here
        Log userLog = new Log(username);

        if(filter == 1){
            userLog.write_log(6);
            System.out.println("--Type--------ID----------Model-----------------Condition---Color-------Capacity-------Year--FuelType----Transmission-Price---Capacity-Turbo?-");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            for (Car car : inventory.getAllCars()){
                if (car.getDetails().contains("New"))
                    System.out.println(car.getDetails());
            }
        }else if(filter == 2){
            userLog.write_log(7);
            System.out.println("--Type--------ID----------Model-----------------Condition---Color-------Capacity-------Year--FuelType----Transmission-Price---Capacity-Turbo?-");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            for (Car car : inventory.getAllCars())
                if (car.getDetails().contains("Used"))
                    System.out.println(car.getDetails());
            
        }else
            System.out.println("Option invalid.\n");
    }

    /**
     * Handles the process of purchasing a car.
     */
    private void purchase_car(){
        System.out.println("Purchasing a car...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter car ID:");
        while(!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Please input a number.");
        }
        int carID = scanner.nextInt();
        scanner.nextLine();

        Car car = user.findCarByID(carID, inventory);
        if(car == null)
            System.out.println("The ID entered was either invalid or this car is no longer available.\nReturning to main menu.");    // Invalid id entry
        
        if(user.purchaseCar(car, admin)){
            System.out.println("Congratulations, you have purchased car: " + carID + "!\nYour new budget is: " + user.getMoneyAvailable());
            Log userLog = new Log(username);
            userLog.write_log(3);
        }else
            System.out.println("You do not have the funds or this car is no longer available!\nReturning to main menu.");    // eventually specify
    }

    /**
     * Displays all tickets belonging to the user.
     */
    
    private void view_tickets(){
        System.out.println("Viewing tickets...");
        for(Ticket ticket: user.viewTickets()){
            System.out.print(ticket.printTicket() + "\n");
        }
    }
}
