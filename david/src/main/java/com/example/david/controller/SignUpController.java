package com.example.david.controller;

import java.util.ResourceBundle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
import com.example.david.dto.MessageResponse;
import com.example.david.model.LogError;
import com.example.david.model.UserDetail;
import com.example.david.service.LogErrorService;
import com.example.david.service.UserDetailService;
import com.example.david.validator.UserDetailValidator;

@Controller
public class SignUpController {
	
	MessageResponse message;
	
	@Autowired
	LogErrorService logErrorService;
	
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	@Autowired
	private UserDetailService userDetailService;
	
	public static final String PATTH_SIGNUP = "/signup";
	public static final String PATTH_REGISTRATION = "/registration";
	
	@InitBinder("UserDetail")
	protected void setupBinder(WebDataBinder binder) {
	    binder.addValidators(new UserDetailValidator());
	}
	
	@RequestMapping(path = PATTH_SIGNUP, method = RequestMethod.GET)
    public String page(Model model) {
		try {
			model.addAttribute("UserDetail", new UserDetail());
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, "N/A", PATTH_SIGNUP));
	    }
		
		return  PATTH_SIGNUP;
	}
	
	@RequestMapping(path = PATTH_REGISTRATION, method = RequestMethod.POST)
    public String registration(
    		Model model, 
    		@ModelAttribute("UserDetail") @Valid UserDetail userDetail,
    		BindingResult bindingResult) {
		
		try {
			message = new MessageResponse();
			
			if (bindingResult.hasErrors()) {
				for(FieldError error : bindingResult.getFieldErrors()){
					model.addAttribute(error.getField(), error.getDefaultMessage());
				}
				
				message.setMessage(rb.getString("error_message"));
				message.setStatus(Constants.ERROR.val());
			 }else {
				 message.setMessage("success");
				 message.setStatus(Constants.SUCCESS.val());
						 
				 userDetailService.saveUserDetail(userDetail);
			 }
						
			model.addAttribute("UserDetail", userDetail);
		} catch (Exception exception) {
			message = logErrorService.save(new LogError(exception, "N/A", PATTH_REGISTRATION));
		}
		
		model.addAttribute(Constants.MESSAGESRESPONSE.val(), message);
		
		return  PATTH_SIGNUP;
	}
}