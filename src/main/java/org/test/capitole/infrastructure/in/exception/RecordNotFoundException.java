package org.test.capitole.infrastructure.in.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String statusText) {
        super(statusText);
    }
}
