package br.com.guiabolso.mock.exception;

import br.com.guiabolso.mock.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResponseStatusCodeException.class})
    private ResponseEntity<ErrorResponse> responseStatusCode(ResponseStatusCodeException ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getStatus().getReasonPhrase());
        error.setMessage(ex.getReason());
        error.setStatus(ex.getStatus().value());
        error.setCode(ex.getCode());
        error.setPath(request.getRequestURI());
        error.setTimestamp(new Date());

        return new ResponseEntity<>(error, ex.getStatus());
    }
}
