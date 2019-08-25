package pl.sda.register.exception;

public class DuplicatedUserNameException extends RuntimeException {

    public DuplicatedUserNameException(String message) {
        super(message);
    }
}
