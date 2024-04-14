public abstract class Person {  // make into interface that user and admin will use
    private String fullName;

    // Constructor
    public Person(String fullName) {
        this.fullName = fullName;
    }

    // Getter
    public String getFullName() {
        return fullName;
    }

    // Setter
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

