import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Arrays;


public class UserDatabase {
    private static final String USER_DATA_FILE = "user_data_new.csv";
    private Map<String, String> userCredentials; // Maps username to password

    public UserDatabase() {
        userCredentials = new HashMap<>();  // <------------ delete?
    }

    public boolean initializeUser(User user, String username, String password) throws IOException{
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
            return false;   // no login match returns false
        } finally {
            br.close();
        }
    }
}
