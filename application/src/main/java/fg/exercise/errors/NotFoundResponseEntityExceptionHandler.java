package fg.exercise.errors;

import fg.exercise.exceptions.TemperatureNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class NotFoundResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ TemperatureNotFoundException.class })
    public ResponseEntity<Object> handleBadRequest(final TemperatureNotFoundException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.toDto(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
