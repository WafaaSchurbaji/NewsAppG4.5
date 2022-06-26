package at.ac.fhcampuswien.exception;

public class NewsApiException extends Exception {

    public NewsApiException() {
        super();
    }
    public NewsApiException(String message) {
        super(message);
    }

    public NewsApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
