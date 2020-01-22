package at.htlkaindorf.exa_105_pocket_calculator.exceptions;

public class NotEnoughOperandsException extends RuntimeException {
    public NotEnoughOperandsException(String message) {
        super(message);
    }
}
