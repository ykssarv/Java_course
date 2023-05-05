package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * Exception.
     * @throws CannotFixException
     */
    void fix() throws CannotFixException;

    /**
     * Get times fixed.
     * @return int
     */
    int getTimesFixed();
}
