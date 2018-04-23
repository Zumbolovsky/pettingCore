package br.com.guilinssolution.pettingCore.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConstraintException extends ApplicationException {

	private static final long serialVersionUID = 906405341510503084L;
	
	private HttpStatus code;
    
    public ConstraintException(String message, HttpStatus code) {
        super(message, code);
        this.code = code;
    }

    public ConstraintException(String message, Throwable cause, HttpStatus code) {
        super(message, cause, code);
    }

    public ConstraintException(Throwable cause) {
        super(cause);
    }

    public ConstraintException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
