/**
 * Names: Rey Sanchez, Alejandro Ovalle, Paola Odriozola
 * Date: 03/29/2024
 * Course: Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Programming Assignment 1
 * 
 * Lab description:
 * This project involves the creation of a new car dealership named Mine Cars, which offers both brand 
 * new and used cars. Our system provides a wide variety of different models with varying prices and 
 * mileage to satisfy customers. Customers have a budget and can opt for a membership to receive 
 * discounts or better interest rates. Additionally, our system enhances security by enabling users and 
 * managers to sign in and access their information securely from the database.
 * 
 * Honesty statement:
 * We declare that we completed this work entirely on our own without any outside sources,
 * including peers, experts, online sources, or the like. Only assistance from the
 * instructor, TA, or IA was taken.
 */

 import java.util.Scanner;
 
 /**
  * The main class to run the Mine Cars Online application.
  */
 public class RunShop {
     
     /**
      * The main method to start the application.
      * 
      * @param args The command line arguments.
      */
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         Inventory inventory = new Inventory(); 
         UserDatabase userDatabase = new UserDatabase(); 
         while (true) {
             System.out.println("Welcome to Mine Cars Online");
             System.out.println("Options:");
             System.out.println("1. Login as User");
             System.out.println("2. Login as Admin");
             System.out.println("3. Exit");
             System.out.print("Enter your choice: ");
             while(true){
                while(!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Invalid choice. Please try again: ");
                }
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
 
                 switch (choice){
                     case 1:
                         UserInterface ui = new UserInterface();
                         ui.user_login();
                         break;
                     case 2:
                         AdminInterface ai = new AdminInterface(inventory, userDatabase);
                         ai.adminAccess();
                         break;
                     case 3:
                         System.out.println("Thank you for visiting us, goodbye!\n");
                         System.exit(0);
                     default:
                         System.out.println("Invalid choice. Please try again: ");
                         break;
                 }
             }
         }
     }
 }
 