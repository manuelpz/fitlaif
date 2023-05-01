package com.gym.fitlaif.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FitLaifNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int statusCode;
	
	public FitLaifNotFoundException(int statusCode, String mensaje) {
	      super(mensaje);        
	      this.statusCode = statusCode;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    @Override
    public String toString() {
        return statusCode + ": " + getMessage();
    }
	
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

}
