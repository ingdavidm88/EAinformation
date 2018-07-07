package com.example.david.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.david.dto.Constants;
import com.example.david.model.SystemParameters;

public class SystemParametersValidator implements Validator{
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	private enum Name {
		VALUE("value"),
		DESCRIPTION("description");
		
		private String val;

		Name(String val) {
	        this.val = val;
	    }

	    public String val() {
	        return val;
	    }
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SystemParameters.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SystemParameters systemParameters = (SystemParameters) target;
		
		String requiredField = rb.getString("required_field");
		String onlyContainNumber = rb.getString("only_contain_number");
			
		//value
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.VALUE.val(), requiredField, requiredField);
		if (!errors.hasFieldErrors(Name.VALUE.val())) {
			Pattern pattern = Pattern.compile(Constants.NUMBER_PATTERN.val());  
			Matcher matcher = pattern.matcher(systemParameters.getValue());
			
			if (!matcher.matches()) {  
				errors.rejectValue(Name.VALUE.val(), onlyContainNumber, onlyContainNumber);  
			} 
		}
		
		//description
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.DESCRIPTION.val(), requiredField, requiredField);
	}
}
