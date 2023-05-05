package ee.taltech.iti0202.personstatistics;

/**
 * The type Person builder.
 */
public class PersonBuilder {

    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private double heightInMeters;
    private String occupation;
    private String nationality;

    /**
     * With first name person builder.
     *
     * @param firstName the first name
     * @return the person builder
     */
    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * With last name person builder.
     *
     * @param lastName the last name
     * @return the person builder
     */
    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * With age person builder.
     *
     * @param age the age
     * @return the person builder
     */
    public PersonBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * With gender person builder.
     *
     * @param gender the gender
     * @return the person builder
     */
    public PersonBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    /**
     * With height in meters person builder.
     *
     * @param heightInMeters the height in meters
     * @return the person builder
     */
    public PersonBuilder withHeightInMeters(double heightInMeters) {
        this.heightInMeters = heightInMeters;
        return this;
    }

    /**
     * With occupation person builder.
     *
     * @param occupation the occupation
     * @return the person builder
     */
    public PersonBuilder withOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    /**
     * With nationality person builder.
     *
     * @param nationality the nationality
     * @return the person builder
     */
    public PersonBuilder withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    /**
     * Build person.
     *
     * @return the person
     */
    public Person build() {
        return new Person(firstName, lastName, age, gender, heightInMeters, occupation, nationality);
    }
}
