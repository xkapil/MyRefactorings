package trips_good;

public class NotLoggedInException extends RuntimeException {

    public NotLoggedInException(String message) {
        super(message);
    }

}