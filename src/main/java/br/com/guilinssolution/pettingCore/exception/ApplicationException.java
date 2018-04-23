package br.com.guilinssolution.pettingCore.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 906405341510503084L;
	
	private HttpStatus code;
	private List<String> messages = new ArrayList<>();
    
    public ApplicationException(String message, HttpStatus code) {
        this.messages.add(message);
        this.code = code;
    }
    
    public ApplicationException(List<String> messages, HttpStatus code) {
        this.messages = messages;
        this.code = code;
    }

    ApplicationException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
    }

    ApplicationException(Throwable cause) {
        super(cause);
    }

    ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
