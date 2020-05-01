package com.julio.amamenteAVida.exception.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.julio.amamenteAVida.exception.AuthorizationException;
import com.julio.amamenteAVida.exception.DataIntegrityException;
import com.julio.amamenteAVida.exception.ObjectNotFoundException;
import com.julio.amamenteAVida.external.dto.response.ResponseBaseDTO;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseBaseDTO> objectNotFound(final ObjectNotFoundException e,
            final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ResponseBaseDTO(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ResponseBaseDTO> dataIntegrity(final DataIntegrityException e,
            final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ResponseBaseDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBaseDTO> validation(final MethodArgumentNotValidException e,
            final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ResponseBaseDTO(e.getMessage()));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ResponseBaseDTO> authorization(final AuthorizationException e,
            final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ResponseBaseDTO(e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBaseDTO> exception(final AuthorizationException e,
            final HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ResponseBaseDTO(e.getMessage()));
    }
}
