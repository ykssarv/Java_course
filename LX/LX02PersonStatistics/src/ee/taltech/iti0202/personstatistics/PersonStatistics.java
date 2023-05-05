package ee.taltech.iti0202.personstatistics;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * For calculating and finding statistical info based on persons.
 */
public class PersonStatistics {

    public static final int HUNDRED = 100;
    private List<Person> persons;
    /**
     * Constructor which stores the given list.
     */
    public PersonStatistics(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Return the number of people in the list.
     */
    public long countPersons() {
        return persons.size();
    }

    /**
     * Find the average height of the persons.
     */
    public OptionalDouble findAverageHeight() {
        return persons.stream()
            .mapToDouble(Person::getHeightInMeters)
            .average();
    }

    /**
     * Return the person with the lowest age.
     */
    public Optional<Person> findYoungestPerson() {
        return persons.stream()
            .min(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Return the person with the highest age.
     */
    public Optional<Person> findOldestPerson() {
        return persons.stream()
            .max(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Return the longest last name.
     */
    public Optional<String> findLongestLastName() {
        return persons.stream()
            .map(Person::getLastName)
            .max(Comparator.comparingInt(String::length));
    }

    /**
     * Return a list of nationalities.
     */
    public List<String> getNationalityData() {
        return persons.stream()
            .map(Person::getNationality)
            .collect(Collectors.toList());
    }

    /**
     * Converts persons heights from m to cm.
     *
     * @return list of heights in cm
     */
    public List<Double> getHeightInCm() {
        return persons.stream()
            .map(person -> person.getHeightInMeters() * HUNDRED)
            .collect(Collectors.toList());
    }

    /**
     * Return a sublist with the given size.
     */
    public List<Person> findSamples(int sampleSize) {
        return persons.stream()
            .limit(Math.max(0, sampleSize))
            .collect(Collectors.toList());
    }

    /**
     * Find first person matching provided parameters criterias.
     *
     * @return first matching person
     */
    public Optional<Person> findSamplePerson(String nationality, Gender gender, int age) {
        return persons.stream()
            .filter(person -> person.getNationality().equals(nationality)
                && person.getGender().equals(gender)
                && person.getAge() == age
            ).findFirst();
    }

    /**
     * Find unique first names.
     */
    public Set<String> getDistinctFirstNames() {
        return persons.stream()
            .map(Person::getFirstName)
            .collect(Collectors.toSet());
    }

    /**
     * Order persons from tallest to shortest.
     *
     * @return ordered list of persons
     */
    public List<Person> getReverseOrderedByHeight() {
        return persons.stream()
            .sorted(Comparator.comparingDouble(Person::getHeightInMeters).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Return list of people whose age is between ageFrom to ageTo (inclusive).
     */
    public List<Person> findBetweenAge(int ageFrom, int ageTo) {
        return persons.stream()
            .filter(person -> person.getAge() >= ageFrom && person.getAge() <= ageTo)
            .collect(Collectors.toList());
    }

    /**
     * Find persons whose first name first letter is same as his/her nationality first letter.
     *
     * @return list of matching persons
     */
    public List<Person> findSameLetterNameAndNationality() {
        return persons.stream()
            .filter(person -> person.getFirstName().toCharArray()[0] == person.getNationality().toCharArray()[0])
            .collect(Collectors.toList());
    }

    /**
     * Create map where each occupation has list of persons who have that occupation.
     *
     * @return map of occupations with persons
     */
    public Map<String, List<Person>> mapOccupationToPersons() {
        return persons.stream()
            .collect(
                Collectors.groupingBy(
                    Person::getOccupation,
                    Collectors.mapping(
                        x -> x,
                        Collectors.toList()
                    )
                )
            );
    }

    public static void main(String[] args) {
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        System.out.println(statistics.countPersons());
    }

}
