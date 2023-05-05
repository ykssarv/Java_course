package ee.taltech.iti0202.hashcode;

/**
 * The type Person.
 */
public class Person {
    /**
     * The constant TEN.
     */
    public static final int TEN = 10;
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;

    /**
     * Instantiates a new Person.
     *
     * @param firstName  the first name
     * @param middleName the middle name
     * @param lastName   the last name
     * @param age        the age
     */
    public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        }
        Person other = (Person) o;

        int thisAge = age / TEN;
        int otherAge = other.getAge() / TEN;
        if (thisAge != otherAge) {
            return false;
        }

        if (middleName.length() > 0
            && other.getMiddleName().length() > 0
            && !middleName.equals(other.getMiddleName())
        ) {
            return false;
        }

        if (firstName.toCharArray()[0] != other.getFirstName().toCharArray()[0]) {
            return false;
        }

        return lastName.equals(other.getLastName());
    }

    @Override
    public int hashCode() {
        return age / TEN;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }
}
