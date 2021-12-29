package br.com.pardalZ7.API.resources.exceptions;

import br.com.pardalZ7.API.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StadardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {

        StadardError error = StadardError.builder()
                .timeStamp(LocalDateTime.now()).error(ex.getMessage()).status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

}
