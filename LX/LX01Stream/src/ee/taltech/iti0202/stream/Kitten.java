package ee.taltech.iti0202.stream;
public class Kitten {

    private Gender gender;
    private String name;
    private int age;
    public enum Gender { MALE, FEMALE }

    /**
     * kitten
     * @param name of kitten
     * @param gender of kitten
     * @param age of kitten
     */
    public Kitten(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
