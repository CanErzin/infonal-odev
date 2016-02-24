package com.erzin.core.exception;

/**
 * This class represent a custon exception.
 * This exception must be thrown if someone try to perform
 * not allowed operations (delete , update) on a not persisted instance.
 */
public class NotPersistedException extends Exception {


    public NotPersistedException(String message) {
        super(message);
    }

}
