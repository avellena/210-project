package exception;

// Will be thrown if user tries to input invalid texts to book entry fields
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
