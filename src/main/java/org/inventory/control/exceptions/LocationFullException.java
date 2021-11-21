package org.inventory.control.exceptions;

/**
 * Runtime exception when items cannot be added to a certain location.
 */
public class LocationFullException extends RuntimeException {

    public LocationFullException() {}

    public LocationFullException(String message) {
        super(message);
    }
}
