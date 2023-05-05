package ee.taltech.iti0202.files.input;

public class FileReaderException extends RuntimeException {
    private String message;
    private Throwable reason;

    /**
     * File Reader Exception
     * @param message of exception
     * @param reason for exception
     */
    public FileReaderException(String message, Throwable reason) {
        super(message, reason);
        this.message = message;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getReason() {
        return reason;
    }
}
