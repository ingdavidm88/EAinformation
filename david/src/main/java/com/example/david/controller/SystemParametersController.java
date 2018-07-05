package com.example.david.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.david.dto.Constants;
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

@Controller
public class SystemParametersController {
	
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
	
	TransactionUtilities transactionUtilities = new TransactionUtilities();
	TransactionPage transactionPage = new TransactionPage();
		
	public static final String PATTH_SYSTEMPARAMETERS = "/systemparameters";
	public static final String PATTH_SEARCH = "/searchsystemparameters";
	public static final String FINDBYSEARCHSYSTEMPARAMETERS = "/findbyidsystemparameters";
	
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
    		@ModelAttribute("Pagination") Pagination pagination,
    		BindingResult bindingResult) {
		
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
        	systemParametersService.findAll(pagination);
        } catch (Exception exception) {
        	model.addAttribute(Constants.MESSAGESRESPONSE.val(), logErrorService.save(new LogError(exception, transactionPage.getUserName(), PATTH_SEARCH)));
        }
        
        model.addAttribute("pagination", pagination);
        model.addAttribute("tp", transactionPage);
        
        return  PATTH_SYSTEMPARAMETERS;
	}
	
	@RequestMapping(path = FINDBYSEARCHSYSTEMPARAMETERS, method = RequestMethod.POST)
    public ResponseEntity<SystemParameters> findByIdsystemParameters(
    		Model model, 
    		HttpServletRequest request,
    		@RequestBody SystemParameters idSystemParameters) {
		
        SystemParameters systemParameters = null;
       
        try {
        	transactionPage = transactionUtilities.getTransactionPage(request, PATTH_SYSTEMPARAMETERS);
        	systemParameters = systemParametersService.findById(idSystemParameters.getIdSystemParameters());
        } catch (Exception exception) {
        	logErrorService.save(new LogError(exception, transactionPage.getUserName(), FINDBYSEARCHSYSTEMPARAMETERS));
        }
        
        return  ResponseEntity.ok(systemParameters);
	}
}