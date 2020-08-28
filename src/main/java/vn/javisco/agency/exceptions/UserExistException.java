package vn.javisco.agency.exceptions;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
