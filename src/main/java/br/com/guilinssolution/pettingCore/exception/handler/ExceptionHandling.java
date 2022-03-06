package br.com.guilinssolution.pettingCore.exception.handler;

import java.util.List;

import br.com.guilinssolution.pettingCore.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.guilinssolution.pettingCore.exception.ApplicationException;
import br.com.guilinssolution.pettingCore.model.dto.util.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandling {

    private final MessageService message;

    @Autowired
    public ExceptionHandling(MessageService message) {
        this.message = message;
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDTO> showmessage(ApplicationException exception) {
        List<String> message = exception.getMessages();
        int code = exception.getCode().value();
        
        log.warn("Exception: {}", message);

        ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setCode(code);
        exceptionDTO.setDescription(message);
        
        return new ResponseEntity<>(exceptionDTO, exception.getCode());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> showmessage(Exception exception) {
        String error = message.getMessage("unexpected.error");
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        
        int code = httpStatus.value();
        
        log.warn("Exception: {}", exception.getMessage());
        
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.getDescription().add(error);
		exceptionDTO.setCode(code);
		
        return new ResponseEntity<>(exceptionDTO, httpStatus);
    }
}
