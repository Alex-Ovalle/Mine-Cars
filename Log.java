import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * The Log class manages logging user actions.
 */

public class Log {

    private static final String fileName = "log.txt";
    private String currentUser;
    private int action;
    private static FileWriter logWriter;

    /**
     * Constructs a Log object with the specified username.
     * 
     * @param username The username of the current user.
     */

    public Log(String username){
        create_file();
        this.currentUser = username;
        //this.action = action;
    }

    /**
     * Sets the username.
     * 
     * @param username The username to set.
     */

    public void set_username(String username){ this.currentUser = username; }
    
    /**
     * Sets the action.
     * 
     * @param userChoice The action to set.
     */

    public void set_action(int userChoice){ this.action = userChoice; }

    /**
     * Retrieves the username.
     * 
     * @return The username.
     */

    private String get_username(){ return this.currentUser; }

    /**
     * Retrieves the action.
     * 
     * @return The action.
     */

    private int get_action(){ return this.action; }

    /**
     * Retrieves the current date and time.
     * 
     * @return The current date and time.
     */

    private String get_date_time(){
        LocalDateTime currDateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return currDateTime.format(myFormat);
    }
    /**
     * Creates a log file if it does not exist.
     */

    private void create_file(){
        try{
            File file = new File(fileName);
            if (!file.exists())  // Check if file exists before creating
                file.createNewFile();
            
        }catch(IOException e){
            System.out.println("An error occured with the log file.");
            e.printStackTrace();
        }
    }

    /**
     * Converts the action code to a human-readable string.
     * 
     * @param action The action code.
     * @return A string representing the action.
     */

    private String action_to_str(int action){
        String output = "";
        switch(action){
            case 0:
                output = "logged in";
                break;
            case 1:
                output = "displayed all cars";
                break;
            case 6:
                output = "filtered by new cars";
                break;
            case 7:
                output = "filtered by used cars";
                break;
            case 3:
                output = "purchased a car";
                break;
            case 4:
                output = "viewed ticket";
                break;
            case 5:
                output = "Signed out";
                break;
            case 8:
                output = "added a car";
                break;
            case 9:
                output = "removed a car";
                break;
            case 10:
                output = "updated a car";
                break;
            case 11:
                output = "retrieved revenue data";
                break;
            default:
                output = "performed an unspecified admin action";
                break;
        }
        return output;    
        }

    static {
        try {
            logWriter = new FileWriter(fileName, true); // Open file in append mode
        } catch (IOException e) {
            System.out.println("An error occurred while initializing logWriter.");
            e.printStackTrace();
        }
    }

    /**
     * Writes the log entry to the log file.
     * 
     * @param action The action code.
     */
    
    public void write_log(int action){
        try{
            logWriter.write(get_date_time() + " - " + get_username() + " " + action_to_str(action) + "\n");
            logWriter.flush(); // Flush the writer to ensure data is written immediately
        }catch(IOException e){
            System.out.println("An error occured while trying to write log");
            e.printStackTrace();
        }
    }

/**
 * Writes a detailed log entry to the log file with a custom message.
 * This method allows for flexible logging of detailed actions, such as specific admin operations.
 * 
 * @param message The custom message to be logged, typically describing the action performed.
 */
public void writeDetailedLog(String message) {
    try {
        // Combine the current date and time, the current user, and the provided message into one log entry
        logWriter.write(get_date_time() + " - " + currentUser + " - " + message + "\n");
        // Flush immediately to ensure the entry is written to the file and not held in a buffer
        logWriter.flush(); 
    } catch (IOException e) {
        // Handle potential I/O errors during the writing process
        System.out.println("Error writing to log file: " + e.getMessage());
    }
}
    
    
}

