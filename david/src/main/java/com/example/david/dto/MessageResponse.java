package com.example.david.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;

public class MessageResponse implements Serializable{
	
    private static final long serialVersionUID = 3407092064428807228L;
    
	private String message;
	
    private String status;
    
    private List<ObjectError> errors;
    
    public void setMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }
    
    public ResponseEntity<MessageResponse> responseEntity() {
		
    	if(status != null && status.equals("E"))
    		return ResponseEntity.badRequest().body(this);
    	else
    		return ResponseEntity.accepted().body(this);
	}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}
}
