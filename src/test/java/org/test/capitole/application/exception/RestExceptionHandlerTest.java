package org.test.capitole.application.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestExceptionHandlerTest {

    @InjectMocks
    RestExceptionHandler restExceptionHandler;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    ServletWebRequest servletWebRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(servletWebRequest.getRequest()).thenReturn(httpServletRequest);
    }

    @Test
    void testHandleConstraintViolationException() {
        // Arrange
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        ConstraintViolation violation = mock(ConstraintViolation.class);
        when(exception.getConstraintViolations()).thenReturn(Set.of(violation));
        when(violation.getMessage()).thenReturn("Mensaje de Constraint Violation");
        // Act
        var response = restExceptionHandler.processErrorsValidation(exception, servletWebRequest);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testHandleMethodArgumentTypeMismatchException() {
        // Arramge
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getPropertyName()).thenReturn("Property name invalido");
        // Act
        var response = restExceptionHandler.processTypeMismatchError(exception, servletWebRequest);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testHandleHttpClientErrorException() {
        // Arrange
        HttpClientErrorException exception = mock(HttpClientErrorException.class);
        when(exception.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        when(exception.getStatusText()).thenReturn("Status text");
        // Act
        var response = restExceptionHandler.processHttpClientError(exception, servletWebRequest);
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testHandleGeneralException() {
        // Arrange
        Exception exception = mock(Exception.class);
        when(exception.getMessage()).thenReturn("Mensaje de Exception");
        // Act
        var response = restExceptionHandler.processUnHandleError(exception, servletWebRequest);
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testHandleRecordNotFoundException() {
        // Arrange
        RecordNotFoundException exception = mock(RecordNotFoundException.class);
        when(exception.getMessage()).thenReturn("Mensaje de Exception");
        // Act
        var response = restExceptionHandler.handleNotFound(exception, servletWebRequest);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}