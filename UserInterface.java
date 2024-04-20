import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date; 



public class UserInterface{

    private String username;
    private String password;
    public static User user = new User("", -1, -1, -1, false, "", "");
    public static Inventory inventory = new Inventory();

    public UserInterface(){     }
    
    public void user_login(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();
        
        UserDatabase userDB = new UserDatabase();
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
        }
    }

    public void show_menu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("1. Display all cars.");
            System.out.println("2. Filter Cars (used / new)");
            System.out.println("3. Purchase a car");
            System.out.println("4. View Tickets");
            System.out.println("5. Sign out");
            System.out.print("Enter your choice: ");
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
                    RunShop.main(new String[0]);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    private void display_cars() {
        System.out.println("Displaying all cars...");
        // here 
        for (Car car : inventory.getAllCars()) {
            System.out.println(car.getDetails() + "\n");
        }
    }   


    private void filter_cars(int filter) {
        System.out.println("\nFiltering cars...");
        // here
        Log userLog = new Log(username);

        if(filter == 1){
            userLog.write_log(6);
            for (Car car : inventory.getAllCars()){
                if (car.getDetails().contains("New"))
                    System.out.println(car.getDetails() + "\n");
            }
        }else if(filter == 2){
            userLog.write_log(7);
            for (Car car : inventory.getAllCars()){
                if (car.getDetails().contains("Used"))
                    System.out.println(car.getDetails() + "\n");
            }
        }else
            System.out.println("Option invalid.\n");
        // Implementation to filter cars (used / new)
    }

    private void purchase_car(){
        System.out.println("Purchasing a car...");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter car ID:");
        int carID = scanner.nextInt();
        scanner.nextLine();

        Car car = user.findCarByID(carID, inventory);
        if(car == null)
            System.out.println("The ID entered was either invalid or this car is no longer available.\nReturning to main menu.");    // Invalid id entry
        
        if(user.purchaseCar(car)){
            System.out.println("Congratulations, you have purchased car: " + carID + "!\nYour new budget is: " + user.getMoneyAvailable());
            Log userLog = new Log(username);
            userLog.write_log(3); // <- make more specific?
        }else
            System.out.println("You do not have the funds or this car is no longer available!\n Returning to main menu.");    // eventually specify
    }

    
    private void view_tickets(){
        System.out.println("Viewing tickets...");
        for(Ticket ticket: user.viewTickets()){
            System.out.print(ticket.printTicket() + "\n");
        }
    }
}
