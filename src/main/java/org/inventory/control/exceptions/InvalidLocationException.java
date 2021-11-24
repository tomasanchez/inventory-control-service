package org.inventory.control.exceptions;

public class InvalidLocationException extends RuntimeException {

    public InvalidLocationException() {}

    public InvalidLocationException(String message) {
        super(message);
    }
}
