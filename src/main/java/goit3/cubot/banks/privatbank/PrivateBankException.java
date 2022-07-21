package goit3.cubot.banks.privatbank;

public class PrivateBankException extends RuntimeException {
    public PrivateBankException(String message) {
        super(message);
    }

    public PrivateBankException(String message, Throwable cause) {
        super(message, cause);
    }
}
