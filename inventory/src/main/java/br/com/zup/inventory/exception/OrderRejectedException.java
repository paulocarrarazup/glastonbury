package br.com.zup.inventory.exception;

public class OrderRejectedException extends RuntimeException {

    public OrderRejectedException(final String message) {
        super(message);
    }
}
