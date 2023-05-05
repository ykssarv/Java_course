package ee.taltech.iti0202.coffee.exception;

public class UnableToMakeDrinkException extends RuntimeException {

    /**
     * There was an unknown recipe (name).
     * @param recipe (name of the recipe).
     */
    public UnableToMakeDrinkException(String recipe) {
        super("Unknown recipe: " + recipe);
    }

    /**
     * There were not enough resources.
     * @param resource name that there was not enough of.
     * @param asked saying how much of the resource was needed.
     * @param have saying how much of the resource there was.
     */
    public UnableToMakeDrinkException(String resource, int asked, int have) {
        super("Not enough " + resource + ": was asked " + asked + ", but only have " + have);
    }

    /**
     * There was not enough water in the container.
     * @param asked saying how much water was needed.
     * @param have saying how much water there was.
     */
    public UnableToMakeDrinkException(int asked, int have) {
        super("Was asked " + asked + " water, but container only has " + have);
    }
}
