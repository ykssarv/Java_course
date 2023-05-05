package ee.taltech.iti0202.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class KittenStatistics {

    private List<Kitten> kittens;

    /**
     * Set kitten
     * @param kittens to set
     */
    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     * Find average age
     * @return average age
     */
    public OptionalDouble findKittensAverageAge() {
        return this.kittens.stream().mapToDouble(Kitten::getAge).average();
    }

    /**
     * Find oldest kitten
     * @return oldest kitten
     */
    public Optional<Kitten> findOldestKitten() {
        return this.kittens.stream().max(Comparator.comparing(Kitten::getAge));
    }

    /**
     * Find youngest kittens
     * @return youngest kittens
     */
    public List<Kitten> findYoungestKittens() {
        return this.kittens.stream()
            .collect(groupingBy(Kitten::getAge, TreeMap::new, toList()))
            .firstEntry()
            .getValue();
    }

    /**
     * Find kittens according to gender
     * @param gender of kitten
     * @return list of kittens
     */
    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        return this.kittens.stream().filter(x -> x.getGender() == gender).collect(toList());
    }

    /**
     * meow
     * @param minAge of kitten
     * @param maxAge of kitten
     * @return list of kittens
     */
    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        return this.kittens.stream().filter(x -> minAge <= x.getAge() && x.getAge() <= maxAge).collect(toList());
    }

    /**
     * find first kitten
     * @param givenName of 1st kitten
     * @return optional kitten
     */
    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        return this.kittens.stream()
            .filter(x -> x.getName().toLowerCase().equals(givenName.toLowerCase()))
            .findFirst();
    }

    /**
     * meow
     * @return list kitten
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        return this.kittens.stream().sorted(Comparator.comparing(Kitten::getAge)).collect(toList());
    }

    /**
     * meow
     * @return list kitten
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        return this.kittens.stream().sorted(Comparator.comparing(Kitten::getAge).reversed()).collect(toList());
    }

}
