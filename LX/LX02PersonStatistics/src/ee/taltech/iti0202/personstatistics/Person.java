package ee.taltech.iti0202.personstatistics;

/**
 * The type Person.
 */
public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private double heightInMeters;
    private String occupation;
    private String nationality;

    /**
     * Instantiates a new Person.
     *
     * @param firstName      the first name
     * @param lastName       the last name
     * @param age            the age
     * @param gender         the gender
     * @param heightInMeters the height in meters
     * @param occupation     the occupation
     * @param nationality    the nationality
     */
    public Person(
        String firstName,
        String lastName,
        int age,
        Gender gender,
        double heightInMeters,
        String occupation,
        String nationality
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.heightInMeters = heightInMeters;
        this.occupation = occupation;
        this.nationality = nationality;
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
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets height in meters.
     *
     * @return the height in meters
     */
    public double getHeightInMeters() {
        return heightInMeters;
    }

    /**
     * Gets occupation.
     *
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Gets nationality.
     *
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }
}
