package org.test.capitole.application.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String statusText) {
        super(statusText);
    }
}
