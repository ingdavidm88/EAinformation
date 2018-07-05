package com.example.david.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.david.dto.Constants;
import com.example.david.model.UserDetail;

public class UserDetailValidator implements Validator{
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	private enum Name {
		FULLNAME("fullName"),
		EMAIL("email"),
		PASSWORD("password"),
		REPEATPASSWORD("repeatPassword");
		
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
		return UserDetail.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDetail userDetail = (UserDetail) target;
		
		String requiredField = rb.getString("required_field");
		String onlyContainLetters = rb.getString("only_contain_letters");
		String yourEmailIncorrect = rb.getString("your_email_incorrect");
		String passwordsMatch = rb.getString("passwords_not_match");
		String privacyPolicy = rb.getString("privacy_policy");
			
		//fullName
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.FULLNAME.val(), requiredField, requiredField);
		if (!errors.hasFieldErrors(Name.FULLNAME.val())) {
			Pattern pattern = Pattern.compile(Constants.NAME_PATTERN.val());  
			Matcher matcher = pattern.matcher(userDetail.getFullName());
			
			if (!matcher.matches()) {  
				errors.rejectValue(Name.FULLNAME.val(), onlyContainLetters, onlyContainLetters);  
			} 
		}
		
		//userName
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", requiredField, requiredField);
			
		//birth
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth", requiredField, requiredField);
		
		//email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.EMAIL.val(), requiredField, requiredField);
		if (!errors.hasFieldErrors(Name.EMAIL.val())) {  
			Pattern pattern = Pattern.compile(Constants.EMAIL_PATTERN.val());  
			Matcher matcher = pattern.matcher(userDetail.getEmail());  
			   
			if (!matcher.matches()) {  
				errors.rejectValue(Name.EMAIL.val(), yourEmailIncorrect, yourEmailIncorrect);  
			}  
		}  
		
		//password
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.PASSWORD.val(), requiredField, requiredField);
		
		//repeatPassword
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, Name.REPEATPASSWORD.val(), requiredField, requiredField);
		
		//password and repeatPassword
		if (!errors.hasFieldErrors(Name.PASSWORD.val()) 
				&& !errors.hasFieldErrors(Name.REPEATPASSWORD.val())
				&& !userDetail.getPassword().equals(userDetail.getRepeatPassword())) {
			
			errors.rejectValue(Name.PASSWORD.val(), passwordsMatch, passwordsMatch);
		}
		
		//privacyPolicy
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "privacyPolicy", privacyPolicy, privacyPolicy);
	}
}
