package internship.rest.car.exceptionshandler;

import org.springframework.http.HttpStatus;

public class CustomCarServiceException extends Exception {
    private final HttpStatus responseCode;

    public CustomCarServiceException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}
