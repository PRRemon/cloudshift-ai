package com.parthraval.cloudshift.common.exception;

import com.parthraval.cloudshift.common.dto.ErrorResponse;
import com.parthraval.cloudshift.common.dto.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception,
            HttpServletRequest request
    ) {

        ErrorResponse response = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                List.of()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        buildErrorResponse(
                                HttpStatus.CONFLICT,
                                ex.getMessage(),
                                request.getRequestURI(),
                                List.of()
                        )
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        buildErrorResponse(
                                HttpStatus.NOT_FOUND,
                                ex.getMessage(),
                                request.getRequestURI(),
                                List.of()
                        )
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationError(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        return ResponseEntity.badRequest()
                .body(
                        buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                "Request validation failed.",
                                request.getRequestURI(),
                                validationErrors
                        )
                );
    }

    private ErrorResponse buildErrorResponse(
            HttpStatus status,
            String message,
            String path,
            List<ValidationError> details) {

        return new ErrorResponse(
                false,
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                details,
                Instant.now()
        );
    }
}