/**
 * The Person interface represents a person with a full name.
 */

public abstract class Person {  // make into interface that user and admin will use
    private String fullName;

    // Constructor
    public Person(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Retrieves the full name of the person.
     * 
     * @return The full name of the person.
     */

    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the person.
     * 
     * @param fullName The full name to set.
     */
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

