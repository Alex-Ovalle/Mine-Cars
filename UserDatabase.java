import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.*;


/**
 * Represents a database to manage user data.
 * This class provides methods to add users, retrieve all users, and initialize users from a file.
 */
public class UserDatabase {
    private static final String USER_DATA_FILE = "user_data_new.csv"; // The file path for user data
    private Map<String, String> userCredentials; // Maps username to password
    private List<User> users; // List to store user data

    /**
     * Constructs a new UserDatabase object.
     * Initializes the user list.
     */
    public UserDatabase() {
        this.users = new ArrayList<>(); // Initialize the user list
    }

      /**
     * Adds a new user to the database.
     * 
     * @param newUser The user to be added.
     */
    public void addUser(User newUser) {
        this.users.add(newUser);
        saveUserToFile(newUser);
    }
    
    /**
     * Saves the user's data to the user data file.
     * 
     * @param user The user whose data is to be saved.
     */
    private void saveUserToFile(User user) {
        try (FileWriter writer = new FileWriter(USER_DATA_FILE, true)) {  // Set true for append mode
            String userData = String.format("%s,%d,%.2f,%d,%b,%s,%s\n",
                user.getFullName(),
                user.getID(),
                user.getMoneyAvailable(),
                user.getCarsPurchased(),
                user.isMinerCarsMembership(),
                user.getUsername(),
                user.getPassword());
            writer.write(userData);
        } catch (IOException e) {
            System.out.println("Error writing to user file: " + e.getMessage());
        }
    }

    /**
     * Retrieves all users from the database.
     * 
     * @return The list of all users.
     */
    public List<User> getAllUsers() {
        return this.users;
    }

    /**
     * Initializes a user from the database using the provided username and password.
     * 
     * @param user The user to be initialized.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if initialization is successful, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    public boolean initializeUser(User user, String username, String password) throws IOException {
        String line;

        BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE));
        try {
            String headerLine = br.readLine();
            List<String> headers = Arrays.asList(headerLine.split(","));
            int idIndex = headers.indexOf("ID");
            int firstNameIndex = headers.indexOf("First Name");
            int lastNameIndex = headers.indexOf("Last Name");
            int moneyAvailableIndex = headers.indexOf("Money Available");
            int carsPurchasedIndex = headers.indexOf("Cars Purchased");
            int minerCarsMembershipIndex = headers.indexOf("MinerCars Membership");
            int usernameIndex = headers.indexOf("Username");
            int passwordIndex = headers.indexOf("Password");

            while ((line = br.readLine()) != null) {
                String[] userFields = line.split(",");
                if (userFields[usernameIndex].equals(username) && userFields[passwordIndex].equals(password)) {
                    user.setID(Integer.parseInt(userFields[idIndex]));
                    user.setFullName(userFields[firstNameIndex] + " " + userFields[lastNameIndex]);
                    user.setMoneyAvailable(Double.parseDouble(userFields[moneyAvailableIndex]));
                    user.setCarsPurchased(Integer.parseInt(userFields[carsPurchasedIndex]));
                    user.setMinerCarsMembership(Boolean.parseBoolean(userFields[minerCarsMembershipIndex]));
                    return true;
                }
            }
            return false; // No login match returns false
        } finally {
            br.close();
        }
    }

    public void updateUserFile(User user){
        try (FileWriter writer = new FileWriter(USER_DATA_FILE + ".tmp");            
        BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE))){
        
            String line;
            String headerLine = br.readLine();
            List<String> headers = Arrays.asList(headerLine.split(","));
            int idIndex = headers.indexOf("ID");
            int moneyAvailableIndex = headers.indexOf("Money Available");
            int carsPurchasedIndex = headers.indexOf("Cars Purchased");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                // Check if the line contains the target ID
                if (Integer.parseInt(parts[idIndex]) == user.getID()) {
                    // Replace the line with new values
                    parts[moneyAvailableIndex] = String.valueOf(user.getMoneyAvailable());
                    parts[carsPurchasedIndex] = String.valueOf(user.getCarsPurchased());
                    line = String.join(",", parts);
                }
                
                // Write the line to the temporary file
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error updating user file");
        }

        // Rename the temporary file to the original file
        File originalFile = new File(USER_DATA_FILE);
        File tempFile = new File(USER_DATA_FILE + ".tmp");
        if (tempFile.renameTo(originalFile)) {
            System.out.println("Replacement successful.");
        } else {
            System.out.println("Failed to replace the file.");
        }
    }
}
