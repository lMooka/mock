package br.com.guiabolso.mock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseStatusCodeException extends ResponseStatusException {
    private String code;

    public ResponseStatusCodeException(HttpStatus status, String reason, String code, Throwable cause) {
        super(status, reason, cause);
        this.code = code;
    }

    public ResponseStatusCodeException(HttpStatus status, String reason, String code) {
        super(status, reason, null);
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
