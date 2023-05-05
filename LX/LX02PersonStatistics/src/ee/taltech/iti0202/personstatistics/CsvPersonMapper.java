package ee.taltech.iti0202.personstatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Csv person mapper.
 */
public class CsvPersonMapper {

    /**
     * The constant ZERO.
     */
    public static final int ZERO = 0;
    /**
     * The constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The constant TWO.
     */
    public static final int TWO = 2;
    /**
     * The constant THREE.
     */
    public static final int THREE = 3;
    /**
     * The constant FOUR.
     */
    public static final int FOUR = 4;
    /**
     * The constant FIVE.
     */
    public static final int FIVE = 5;
    /**
     * The constant SIX.
     */
    public static final int SIX = 6;

    /**
     * Map to persons list.
     *
     * @param path the path
     * @return the list
     */
    public List<Person> mapToPersons(String path) {

        try {
            return Files.lines(Path.of(path))
                .map(line -> {
                    String[] splitLine = line.split(",");
                    return new PersonBuilder()
                        .withFirstName(splitLine[ZERO])
                        .withLastName(splitLine[ONE])
                        .withAge(Integer.parseInt(splitLine[TWO]))
                        .withGender(splitLine[THREE].equals("MALE") ? Gender.MALE : Gender.FEMALE)
                        .withHeightInMeters(Double.parseDouble(splitLine[FOUR]))
                        .withOccupation(splitLine[FIVE])
                        .withNationality(splitLine[SIX])
                        .build();
                }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new CsvToPersonMappingException();
        }
    }
}
