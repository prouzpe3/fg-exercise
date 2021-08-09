package fg.exercise.errors;

import fg.exercise.exceptions.BadRequestException;
import fg.exercise.exceptions.InvalidTimestampException;
import fg.exercise.exceptions.NoTemperatureRangeFoundException;
import fg.exercise.exceptions.TemperatureForGivenTimestampAlreadyExistsException;
import fg.exercise.models.dtos.ExceptionDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


@ControllerAdvice
public class BadRequestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ InvalidTimestampException.class,
            NoTemperatureRangeFoundException.class,
            TemperatureForGivenTimestampAlreadyExistsException.class
    })
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.toDto(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ExceptionDto().code(500L).description(ex.getBindingResult().getFieldErrors().stream().map(fieldError -> "[" + fieldError.getField() + "]: " + fieldError.getDefaultMessage()).collect(Collectors.joining(";"))), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
