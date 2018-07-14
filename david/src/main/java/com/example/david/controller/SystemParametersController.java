package com.example.david.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
import com.example.david.dto.MessageResponse;
import com.example.david.dto.Pagination;
import com.example.david.dto.TransactionPage;
import com.example.david.model.LogError;
import com.example.david.model.SystemParameters;
import com.example.david.service.LogErrorService;
import com.example.david.service.MenuService;
import com.example.david.service.ParentMenuService;
import com.example.david.service.SystemParametersService;
import com.example.david.service.UserService;
import com.example.david.utils.TransactionUtilities;
import com.example.david.validator.SystemParametersValidator;

@Controller
public class SystemParametersController {
	
	MessageResponse message;
	
	ResourceBundle rb = ResourceBundle.getBundle("messages_en_US");
	
	TransactionUtilities transactionUtilities = new TransactionUtilities();
	TransactionPage transactionPage = new TransactionPage();
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParentMenuService parentMenuService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	LogErrorService logErrorService;
	
	@Autowired
	SystemParametersService systemParametersService;
		
	public static final String PATTH_SYSTEMPARAMETERS = "/systemparameters";
	public static final String PATTH_SEARCH = "/searchsystemparameters";
	public static final String FINDBYSEARCHSYSTEMPARAMETERS = "/findbyidsystemparameters";
	public static final String SAVESYSTEMPARAMETERS = "/savesystemparameters";
		
	@RequestMapping(path = PATTH_SYSTEMPARAMETERS, method = RequestMethod.GET)
    public String page(Model model, HttpServletRequest request) {
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
			model.addAttribute("pagination", new Pagination());
			model.addAttribute("tp",transactionPage);
		} catch (Exception exception) {
			logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_SYSTEMPARAMETERS));
	    }
		
    	return  PATTH_SYSTEMPARAMETERS;
	}
	
	@RequestMapping(path = PATTH_SEARCH, method = RequestMethod.POST)
    public String search(
    		Model model,
    		HttpServletRequest request,
    		@ModelAttribute("Pagination") Pagination pagination) {
		
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
        	systemParametersService.findAll(pagination, transactionPage.getPageSize());
        	
            model.addAttribute("tp", transactionPage);
            model.addAttribute("pagination", pagination);
        } catch (Exception exception) {
        	model.addAttribute(Constants.MESSAGESRESPONSE.val(), logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_SEARCH)));
        }
        
        return  PATTH_SYSTEMPARAMETERS;
	}
	
	@RequestMapping(path = FINDBYSEARCHSYSTEMPARAMETERS, method = RequestMethod.POST)
    public ResponseEntity<Object> findByIdsystemParameters(
    		Model model, 
    		HttpServletRequest request,
    		@RequestBody SystemParameters idSystemParameters) {
		
        SystemParameters systemParameters = null;
       
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
        	systemParameters = systemParametersService.findById(idSystemParameters.getIdSystemParameters());
        } catch (Exception exception) {
        	message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), FINDBYSEARCHSYSTEMPARAMETERS));
        	return ResponseEntity.badRequest().body(message);
        }
        
        return ResponseEntity.ok(systemParameters);
	}
	
	@RequestMapping(path = SAVESYSTEMPARAMETERS, method = RequestMethod.POST)
    public ResponseEntity<Object> save(
    		Model model,
    		HttpServletRequest request,
    		@Valid @RequestBody SystemParameters systemParameters,
    		Errors errors) {
		
		ValidationUtils.invokeValidator(new SystemParametersValidator(), systemParameters, errors);
		message = new MessageResponse();
		
		try {
			transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
			
			if(errors.hasErrors()) {
				message.setErrors(errors.getAllErrors());
				message.setMessage(rb.getString("error_message"));
				message.setStatus(Constants.ERROR.val());
			 }else {
				 systemParametersService.save(systemParameters);
				 message.setMessage("success");
				 message.setStatus(Constants.SUCCESS.val());
			 }
		} catch (Exception exception) {
			message = logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_SYSTEMPARAMETERS));
			return ResponseEntity.badRequest().body(message);
		}
		
		return ResponseEntity.ok(message);
	}
}