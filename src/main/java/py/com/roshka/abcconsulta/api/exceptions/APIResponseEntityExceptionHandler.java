package py.com.roshka.abcconsulta.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class APIResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(APIResponseEntityExceptionHandler.class);

    @ExceptionHandler({APIException.class})
    public ResponseEntity<Object> apiExceptionHandler(APIException ex) {
        LOGGER.error(ex);
        return new ResponseEntity<>(new ErrorMessage(ex.getStatusCode(), ex.getMessage()), ex.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> authenticationExceptionHandler(BadCredentialsException ex) {
        LOGGER.error(ex);
        return new ResponseEntity<>(new ErrorMessage("g103", "No autorizado"), HttpStatus.UNAUTHORIZED);
    }
}

@Data
@AllArgsConstructor
class ErrorMessage {

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("error")
    private String error;

}