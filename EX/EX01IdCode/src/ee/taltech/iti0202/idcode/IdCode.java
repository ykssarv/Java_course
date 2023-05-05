package ee.taltech.iti0202.idcode;

import java.util.Arrays;

public class IdCode {

    public static final int ID_CODE_LENGTH = 11;
    public static final int DAY_START = 5;
    public static final int DAY_END = 7;
    public static final int MONTH_START = 3;
    public static final int MONTH_END = 5;
    public static final int QUEUE_START = 7;
    public static final int QUEUE_END = 10;
    public static final int KURESSAARE = 10;
    public static final int TARTU = 20;
    public static final int TALLINN = 220;
    public static final int KOHTLA_JARVE = 270;
    public static final int TARTU_2 = 370;
    public static final int NARVA = 420;
    public static final int PARNU = 470;
    public static final int TALLINN_2 = 490;
    public static final int PAIDE = 520;
    public static final int RAKVERE = 570;
    public static final int VALGA = 600;
    public static final int VILJANDI = 650;
    public static final int VORU = 710;
    public static final int CENTURY_19 = 1800;
    public static final int CENTURY_20 = 1900;
    public static final int CENTURY_21 = 2000;
    public static final int MONTHS_IN_YEAR = 12;
    public static final int DAY_NUMBER_2 = 6;
    public static final int DAYS_IN_FEBRUARY = 28;
    public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
    public static final int LAST_MONTH_WHERE_FIRST_FORMULA_APPLIES = 7;
    public static final int DEFAULT_DAYS_IN_FIRST_SIDE_OF_THE_YEAR = 30;
    public static final int DEFAULT_DAYS_IN_SECOND_SIDE_OF_THE_YEAR = 31;
    public static final int FIRST_QUEUE_NUMBER = 7;
    public static final int SECOND_QUEUE_NUMBER = 8;
    public static final int THIRD_QUEUE_NUMBER = 9;
    public static final int CONTROL_NUMBER_INDEX = 10;
    public static final int CONTROL_NUMBER_FORMULA_MOD_NUMBER = 11;
    public static final int CONTROL_NUMBER_FORMULA_CYCLE_LENGTH = 9;
    public static final int FIRST_TWO_DIGIT_NUMBER = 10;
    public static final int FIRST_LEAP_YEAR_INDICATOR = 400;
    public static final int FIRST_NORMAL_YEAR_INDICATOR = 100;
    public static final int SECOND_LEAP_YEAR_INDICATOR = 4;
    public static final int DAY_NUMBER_1 = 5;
    public static final int DAY_INDEX_END = 7;
    private final String idCodeValue;

    enum Gender {
        MALE, FEMALE
    }

    /**
     * Get id code value.
     * @return String describing id code.
     */
    public String getIdCodeValue() {
        return idCodeValue;
    }

    /**
     * Id code
     * @param idCodeValue containing id code.
     */
    public IdCode(String idCodeValue) {
        this.idCodeValue = idCodeValue;
    }

    /**
     * Check if the id code is valid or not.
     *
     * @return boolean describing whether or not the id code was correct.
     */
    public boolean isCorrect() {
        if (idCodeValue.length() == ID_CODE_LENGTH && isYearNumberCorrect() && isControlNumberCorrect()
            && isDayNumberCorrect() && isGenderNumberCorrect()
            && isMonthNumberCorrect() && isQueueNumberCorrect()) {
            return true;
        }
        return false;
    }

    /**
     * Get all information about id code.
     *
     * @return String containing information.
     */
    public String getInformation() {
        if (!isCorrect()) {
            return "Given invalid ID code!";
        }
        String informationString = "This is a ";
        if (getGender() == Gender.FEMALE) {
            informationString = informationString + "FEMALE";
        } else {
            informationString = informationString + "MALE";
        }
        informationString = informationString + " born on ";
        informationString = informationString + idCodeValue.substring(DAY_START, DAY_END) + "."
            + idCodeValue.substring(MONTH_START, MONTH_END) + ".";
        informationString = informationString + String.valueOf(getFullYear());
        informationString = informationString + " in ";
        informationString = informationString + getBirthPlace();
        return informationString;
    }

    /**
     * Get gender enum.
     *
     * @return enum describing person's gender
     */
    public Gender getGender() {
        if (!isGenderNumberCorrect()) {
            return null;
        }
        if (Character.getNumericValue(idCodeValue.charAt(0)) % 2 == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    /**
     * Get person's birth location.
     *
     * @return String with the person's birth place.
     */
    public String getBirthPlace() {
        if (!isQueueNumberCorrect()) {
            return "Wrong input!";
        }
        int b = Integer.parseInt(idCodeValue.substring(QUEUE_START, QUEUE_END));
        if (b == 0) {
            return "unknown";
        }
        if (b <= KURESSAARE) {
            return "Kuressaare";
        }
        if (b <= TARTU) {
            return "Tartu";
        }
        if (b <= TALLINN) {
            return "Tallinn";
        }
        if (b <= KOHTLA_JARVE) {
            return "Kohtla-Järve";
        }
        if (b <= TARTU_2) {
            return "Tartu";
        }
        if (b <= NARVA) {
            return "Narva";
        }
        if (b <= PARNU) {
            return "Pärnu";
        }
        if (b <= TALLINN_2) {
            return "Tallinn";
        }
        if (b <= PAIDE) {
            return "Paide";
        }
        if (b <= RAKVERE) {
            return "Rakvere";
        }
        if (b <= VALGA) {
            return "Valga";
        }
        if (b <= VILJANDI) {
            return "Viljandi";
        }
        if (b <= VORU) {
            return "Võru";
        }
        return "unknown";
    }

    /**
     * Get the year that the person was born in.
     *
     * @return int with person's birth year.
     */
    public int getFullYear() {
        int century;
        if (Arrays.asList('1', '2').contains(idCodeValue.charAt(0))) {
            century = CENTURY_19;
        } else if (Arrays.asList('3', '4').contains(idCodeValue.charAt(0))) {
            century = CENTURY_20;
        } else if (Arrays.asList('5', '6').contains(idCodeValue.charAt(0))) {
            century = CENTURY_21;
        } else {
            return -1;
        }
        if (!isYearNumberCorrect()) {
            return -1;
        }
        int yearNumber = Integer.parseInt(idCodeValue.substring(1, 3));
        return century + yearNumber;
    }

    /**
     * Check if gender number is correct.
     *
     * @return boolean describing whether the gender number is correct.
     */
    private boolean isGenderNumberCorrect() {
        if (Arrays.asList('1', '2', '3', '4', '5', '6').contains(idCodeValue.charAt(0))) {
            return true;
        }
        return false;
    }

    /**
     * Check if the year number is correct.
     *
     * @return boolean describing whether the year number is correct.
     */
    private boolean isYearNumberCorrect() {
        if (Character.isDigit(idCodeValue.charAt(1)) && Character.isDigit(idCodeValue.charAt(2))) {
            return true;
        }
        return false;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isMonthNumberCorrect() {
        if (!Character.isDigit(idCodeValue.charAt(3)) || !Character.isDigit(idCodeValue.charAt(4))) {
            return false;
        }
        if (Integer.parseInt(idCodeValue.substring(3, 5)) <= MONTHS_IN_YEAR
            && Integer.parseInt(idCodeValue.substring(3, 5)) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Check if the day number is correct.
     *
     * @return boolean describing whether the day number is correct.
     */
    private boolean isDayNumberCorrect() {
        if (!Character.isDigit(idCodeValue.charAt(DAY_NUMBER_1))
            || !Character.isDigit(idCodeValue.charAt(DAY_NUMBER_2))) {
            return false;
        }
        int daysAllowed = getDaysAllowed();
        int dayNumber = Integer.parseInt(idCodeValue.substring(IdCode.DAY_NUMBER_1, DAY_INDEX_END));
        if (dayNumber <= daysAllowed && dayNumber > 0) {
            return true;
        }
        return false;
    }

    /**
     * Get amount of days in month.
     *
     * @return int describing amount of days.
     */
    private int getDaysAllowed() {
        int daysAllowed;
        int monthNumber = Integer.parseInt(idCodeValue.substring(3, 5));
        if (monthNumber == 2) {
            daysAllowed = DAYS_IN_FEBRUARY;
            if (isLeapYear(getFullYear())) {
                daysAllowed = DAYS_IN_FEBRUARY_IN_LEAP_YEAR;
            }
        } else if (monthNumber <= LAST_MONTH_WHERE_FIRST_FORMULA_APPLIES) {
            daysAllowed = DEFAULT_DAYS_IN_FIRST_SIDE_OF_THE_YEAR + monthNumber % 2;
        } else {
            daysAllowed = DEFAULT_DAYS_IN_SECOND_SIDE_OF_THE_YEAR - monthNumber % 2;
        }
        return daysAllowed;
    }

    /**
     * Check if queue number is correct
     *
     * @return boolean describing stuff.
     */
    private boolean isQueueNumberCorrect() {
        if (Character.isDigit(idCodeValue.charAt(FIRST_QUEUE_NUMBER))
            && Character.isDigit(idCodeValue.charAt(SECOND_QUEUE_NUMBER))
            && Character.isDigit(idCodeValue.charAt(THIRD_QUEUE_NUMBER))) {
            return true;
        }
        return false;
    }

    /**
     * Check if the control number is correct.
     *
     * @return boolean describing whether the control number is correct.
     */
    private boolean isControlNumberCorrect() {
        int controlNumber;
        int sum = 0;
        for (int i = 0; i < CONTROL_NUMBER_INDEX; i++) {
            sum += (i % CONTROL_NUMBER_FORMULA_CYCLE_LENGTH + 1) * Character.getNumericValue(idCodeValue.charAt(i));
        } if (sum % CONTROL_NUMBER_FORMULA_MOD_NUMBER == FIRST_TWO_DIGIT_NUMBER) {
            sum = 0;
            for (int i = 0; i < CONTROL_NUMBER_INDEX; i++) {
                sum += ((i + 2) % CONTROL_NUMBER_FORMULA_CYCLE_LENGTH + 1)
                    * Character.getNumericValue(idCodeValue.charAt(i));
            } if (sum % CONTROL_NUMBER_FORMULA_MOD_NUMBER == FIRST_TWO_DIGIT_NUMBER) {
                controlNumber = 0;
            } else {
                controlNumber = sum % CONTROL_NUMBER_FORMULA_MOD_NUMBER;
            }
        } else {
            controlNumber = sum % CONTROL_NUMBER_FORMULA_MOD_NUMBER;
        }
        if (Character.getNumericValue(idCodeValue.charAt(CONTROL_NUMBER_INDEX)) == controlNumber) {
            return true;
        }
        return false;
    }

    /**
     * Check if the given year is a leap year.
     *
     * @param fullYear describing year number.
     * @return boolean describing whether the given year is a leap year.
     */
    private boolean isLeapYear(int fullYear) {
        if (fullYear % FIRST_LEAP_YEAR_INDICATOR == 0) {
            return true;
        }
        if (fullYear % FIRST_NORMAL_YEAR_INDICATOR == 0) {
            return false;
        }
        if (fullYear % SECOND_LEAP_YEAR_INDICATOR == 0) {
            return true;
        }
        return false;
    }

    /**
     * Run tests.
     * @param args info.
     */
    public static void main(String[] args) {
        IdCode validMaleIdCode = new IdCode("37605030299");
        System.out.println(validMaleIdCode.isCorrect());
        System.out.println(validMaleIdCode.getInformation());
        System.out.println(validMaleIdCode.getGender());
        System.out.println(validMaleIdCode.getBirthPlace());
        System.out.println(validMaleIdCode.getFullYear());
        System.out.println(validMaleIdCode.isGenderNumberCorrect());
        System.out.println(validMaleIdCode.isYearNumberCorrect());
        System.out.println(validMaleIdCode.isMonthNumberCorrect());
        System.out.println(validMaleIdCode.isDayNumberCorrect());
        System.out.println(validMaleIdCode.isQueueNumberCorrect());
        System.out.println(validMaleIdCode.isControlNumberCorrect());
        System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));

    }

}
