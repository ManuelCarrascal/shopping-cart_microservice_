package emazon.cart.domain.exception;

public class InsufficientStockException extends  RuntimeException{
    public InsufficientStockException(String message) {
        super(message);
    }
}
