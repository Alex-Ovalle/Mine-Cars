import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class UserDatabase {
    private static final String USER_DATA_FILE = "user_data.csv";
    private Map<String, String> userCredentials; // Maps username to password

    public UserDatabase() {
        userCredentials = new HashMap<>();
        loadUserCredentials();
    }

    private void loadUserCredentials() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] userFields = line.split(",");
                userCredentials.put(userFields[6], userFields[7]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading user data file.");
        }
    }

    public void fillUserDetails(User user, String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] userFields = line.split(",");
                if(userFields[6].equals(username) && userFields[7].equals(password)){
                    user.setID(Integer.parseInt(userFields[0]));
                    user.setFullName(userFields[1] + " " + userFields[2]);
                    user.setMoneyAvailable(Double.parseDouble(userFields[3]));
                    user.setCarsPurchased(Integer.parseInt(userFields[4]));
                    user.setMinerCarsMembership(Boolean.parseBoolean(userFields[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading user data file.");
        }
    }

    public void carBought(User user, double carPrice) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("user_data.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("tmp" + "user_data.csv"));

        String line;
        int userID = user.getID();
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]); // checking current line id to compare to given car id
            if(id == userID){
                double userMoney = Double.parseDouble(parts[3]);
                double newUserBudget = userMoney - carPrice; 
                user.setMoneyAvailable(newUserBudget);
            }
        
            // Write the modified line to the temporary file
            writer.write(String.join(",", parts));
            writer.newLine();
        }

        reader.close();
        writer.close();
    }

    public boolean validateCredentials(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}

